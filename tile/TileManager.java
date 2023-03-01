package tile;


import javax.imageio.ImageIO;

import main.GamePanel;

import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import pathfinding.*;

public class TileManager {
    
    public GamePanel gp;
    public Tile[] tile;
    public String[] tilePaths = new String[]{
        "/tiles/grass.png",
        // "/tiles/water.png",
        "/tiles/stone.png"
    };
    public int[][] mapTileNum;
    public int currentNum = 4;
    ArrayList<Coordinate> path;

    public TileManager(GamePanel gp) {


        this.gp = gp;

        tile = new Tile[tilePaths.length];

        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];

        this.getTileImage();

        this.loadMap(currentNum);

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


    public void getTileImage() {
        String current = System.getProperty("user.dir");
        try {
            for(int i = 0; i < tilePaths.length; i++) {
                tile[i] = new Tile();
                System.out.println(i);
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
            System.out.println("My current is: " + current);
            File file = new File(current + "/tile/levels/level" + number + ".txt");
            // InputStream is = new FileInputStream(new File(current + "tile/levels/level" + number + ".txt"));
            // BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Scanner scan = new Scanner(file);

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow && scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.println(line +"\n");

                while(col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    row++;
                    col = 0;
                }
            }

            scan.close();
            System.out.println(mapTileNum);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}


