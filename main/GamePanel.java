package main;



import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pathfinding.Astar;
import pathfinding.Coordinate;
import tile.*;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Thread;
import java.awt.image.BufferedImage;
import java.io.File;

public class GamePanel extends JPanel implements Runnable {

    //credit for GamePanel code is from RyiShow
    //https://youtu.be/om59cwR7psI

    //Screen Settings below
    final int originalTileSize = 16; //16x16 tiles Original Sprite
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //Product of scaling the tile thing
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    
    final int screenHeight = maxScreenRow * tileSize;
    final int screenWidth = maxScreenCol * tileSize;

    int FPS = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int playerX = 0;
    int playerY = 0;
    int playerSpeed = 10;

    long movementDelay = 1000;
    boolean canMove = true;

    ArrayList<Coordinate> path = new ArrayList<Coordinate>();
    int currentNum = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(false);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        int zeroes = 9;
        double drawInterval = (1*Math.pow(10, zeroes))/FPS; //1 second / 60 to render at 60 frames per second
        long current;
        long last = System.nanoTime();
        double delta = 0;
        float movement = 0;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            current = System.nanoTime();
            // System.out.println(current);
            movement += (current - last) / drawInterval;
            delta += (current - last) / drawInterval; //If 1 second has passed update and repaint. Basically
            //checks if 1*10^9 nanoseconds has passed
            timer += (current - last);
            last = current;

            if(delta >= 1) {
                System.out.println("hi");
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(movement >= 8) {
                System.out.println("Triggered");
                if(path.size() != 0) {
                    currentNum++;
                    currentNum = currentNum % path.size();
                    playerX = path.get(path.size()-1-currentNum).position[1] * tileSize;
                    playerY = path.get(path.size()-1-currentNum).position[0] * tileSize;
                    movement = 0;
                }
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
        System.out.println("what the heck");
    }

    public void update() {
        // if(keyH.upPressed) {
        //     playerY -= playerSpeed;
        //     canMove = false;
        // }
        // if(keyH.downPressed) {
        //     playerY += playerSpeed;
        //     canMove = false;
        // }
        // if(keyH.rightPressed) {
        //     playerX += playerSpeed;
        //     canMove = false;
        // }
        // if(keyH.leftPressed) {
        //     playerX -= playerSpeed;
        //     canMove = false;
        // }
        // canMove = true;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        System.out.println(path.size());
        if(path.size() == 0) {
            System.out.println("Algorithms");
            Astar alg = new Astar(new int[]{1, 2, 3});
            int[] startCoord = {0, 0};
            int[] endCoord = {maxScreenRow-1,maxScreenCol-1};
            path = alg.astar(tileM.mapTileNum, startCoord, endCoord);
            path.add(new Coordinate(null, new int[]{0, 0}));
        }
        for(Coordinate c : path) {
            Color ourColor = new Color(255, 0, 0, 80);
            g2.setColor(ourColor);
            g2.fillRect(c.position[1] * tileSize, c.position[0] * tileSize, tileSize, tileSize); 
            //Columns represent x values while rows are y values
        }
        g2.setColor(Color.white);
        g2.fillOval(playerX, playerY, tileSize, tileSize); 
        g2.dispose();
        // System.out.println("First tile collides: " + tileM.tile[0].collision);
        // System.out.println("Tile Size: " + tileM.gp.tileSize);
    }

}


