import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Thread;
import java.math.*;

public class GamePanel extends JPanel implements Runnable {

    //credit for GamePanel code is from RyiShow
    //https://youtu.be/om59cwR7psI

    //Screen Settings below
    final int originalTileSize = 16; //16x16 tiles Original Sprite
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //Product of scaling the tile thing
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    
    final int screenHeight = maxScreenRow * tileSize;
    final int screenWidth = maxScreenCol * tileSize;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 10;

    long movementDelay = 1000;
    boolean canMove = true;

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
        double movement = 0;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            current = System.nanoTime();

            movement += (current - last) / drawInterval;
            delta += (current - last) / drawInterval; //If 1 second has passed update and repaint. Basically
            //checks if 1*10^9 nanoseconds has passed
            timer += (current - last);
            last = current;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if(keyH.upPressed) {
            playerY -= playerSpeed;
            canMove = false;
        }
        if(keyH.downPressed) {
            playerY += playerSpeed;
            canMove = false;
        }
        if(keyH.rightPressed) {
            playerX += playerSpeed;
            canMove = false;
        }
        if(keyH.leftPressed) {
            playerX -= playerSpeed;
            canMove = false;
        }
        canMove = true;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize); 
        g2.dispose();
    }

}
