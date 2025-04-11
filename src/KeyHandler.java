package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KeyHandler implements KeyListener {
    main.GamePanel gp;
    @Override
    public void keyTyped(KeyEvent e) {
    }
    public boolean upPressed,downPressed,leftPressed, rightPressed;
    public KeyHandler(main.GamePanel gp)
    {
        this.gp=gp;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(gp.gameState==gp.titleState)
        {
            if(code==KeyEvent.VK_W){
                gp.ui.commandNum--;
                gp.playSE(3);
                if(gp.ui.commandNum<0)
                {
                    gp.ui.commandNum=3;
                }
            }
            if(code==KeyEvent.VK_S){
                gp.ui.commandNum++;
                gp.playSE(3);
                if(gp.ui.commandNum>3)
                {
                    gp.ui.commandNum=0;
                }
            }
            if(code==KeyEvent.VK_ENTER)
            {
                if(gp.ui.commandNum==0)
                {
                    gp.gameState=gp.playState;
                    gp.playMusc(0);
                }
                if(gp.ui.commandNum==1)
                {
                    JFrame frame = new JFrame("Leaderboards!");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setSize(600, 400);
                    frame.setLocation(200,100);
                    // Kreiramo JTextArea za prikazivanje sadržaja
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);
                    // Učitaj sadržaj fajla i postavi ga u JTextArea
                    try {
                        Path path = Paths.get("C:\\Users\\User\\IdeaProjects\\Vjezba 2d\\res\\leaderboard\\stats.txt");
                        String content = Files.readString(path);
                        textArea.setText(content);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                    frame.setVisible(true);
                }
                if(gp.ui.commandNum==2)
                {
                    JFrame frame = new JFrame("Highest score!");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setSize(400, 100);
                    frame.setLocation(400,200);
                    // Kreiramo JTextArea za prikazivanje sadržaja
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);
                    // Učitaj sadržaj fajla i postavi ga u JTextArea
                    try {
                        Path path = Paths.get("C:\\Users\\User\\IdeaProjects\\Vjezba 2d\\res\\leaderboard\\highscore.txt");
                        String content = Files.readString(path);
                        textArea.setText(content);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                    frame.setVisible(true);
                }
                if(gp.ui.commandNum==3)
                {
                    System.exit(0);
                }
            }
        }
        if(code==KeyEvent.VK_W){
            upPressed=true;
        }
        if(code==KeyEvent.VK_S){
            downPressed=true;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=true;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=true;
        }
        if(code==KeyEvent.VK_P){
            if(gp.gameState==gp.playState)
            {
                gp.gameState=gp.pauseState;
            }else if(gp.gameState==gp.pauseState)
            {
                gp.gameState=gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_W){
            upPressed=false;
        }
        if(code==KeyEvent.VK_S){
            downPressed=false;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=false;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=false;
        }
    }
}
