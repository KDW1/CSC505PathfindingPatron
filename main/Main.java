import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        //credit for JFrame code is from RyiShow
        //https://youtu.be/om59cwR7psI
        JFrame window = new JFrame();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}