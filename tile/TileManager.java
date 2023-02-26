package tile;


import javax.imageio.ImageIO;

import main.GamePanel;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    
    public GamePanel gp;
    public Tile[] tile;
    String[] tilePaths = {
        "/tile/tiles/Grass.png",
        // "/tiles/water.png",
        // "/tiles/stone.png"
    };
    int mapTileNum[][];
    int currentNum = 0;

    public TileManager(GamePanel gp) {


        this.gp = gp;

        tile = new Tile[tilePaths.length];

        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];

        this.getTileImage();

        this.loadMap(currentNum);

    }

    public void getTileImage() {
        String current = System.getProperty("user.dir");
        try {
            for(int i = 0; i < tilePaths.length; i++) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(new File(current + tilePaths[i]));;
            }
            // for(int i = 0; i < tile.length; i++) {
            //     tile[i] = new Tile();
            //     tile[i].image = ImageIO.read(new File(tilePaths[i]));
            // }
        } catch (Exception e) {
            System.out.println("");
            e.printStackTrace();
            // System.out.println(e.getStackTrace());
            // TODO: handle exception
        }
    }

    public void loadMap(int number) {
        try {
            String current = System.getProperty("user.dir");
            InputStream is = new FileInputStream(new File(current + "/levels/level" + number + ".txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while(col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    row++;
                    col = 0;
                }
            }

            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void draw(Graphics2D g2) {
        int cols = 0;
        int rows = 0;
        int x = 0;
        int y = 0;

        while(cols < gp.maxScreenCol && rows < gp.maxScreenRow) {
            int tileNum = mapTileNum[rows][cols];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            cols++;
            x += gp.tileSize;

            if(cols == gp.maxScreenCol) {
                rows++;
                cols = 0;
                x = 0;
                y += gp.tileSize;
            }
        }
    }
}


