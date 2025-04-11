package main;

import object.OBJ_Book;
import object.OBJ_Key;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;

public class UI {
    main.GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    BufferedImage bookImage;
    public boolean messageON = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int commandNum = 0;
    public int br=0;
    public double pom;
    public int globalStopLoop=0;
    public UI(main.GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        OBJ_Book book = new OBJ_Book(gp);
        keyImage = key.image;
        bookImage = book.image;
    }
    public void showMessage(String text) {
        message = text;
        messageON = true;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState) {
            drawTitleScrean();
        }
        if (gp.gameState == gp.playState) {
            if (gameFinished == true) {
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                String text;
                int textLenght;
                int x, y;
                playTime=Math.round(playTime*100.0)/100.0;
                pom=playTime;
                text = "You colected all books, well done!";
                textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screanWidth / 2 - textLenght / 2;
                y = gp.screanHeight / 2 - gp.tileSize * 3;
                g2.drawString(text, x, y);
                text = "Your time is: " + dFormat.format(playTime) + "!";
                textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screanWidth / 2 - textLenght / 2;
                y = gp.screanHeight / 2 + gp.tileSize * 4;
                g2.drawString(text, x, y);
                wrightScore("C:\\Users\\User\\IdeaProjects\\Vjezba 2d\\res\\leaderboard\\stats.txt", pom);
                g2.setFont(arial_80B);
                g2.setColor(Color.red);
                text = "Congratulations";
                textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screanWidth / 2 - textLenght / 2;
                y = gp.screanHeight / 2 + gp.tileSize * 2;
                g2.drawString(text, x, y);
                gp.gameThread = null;
                double highScore=readHighScore();
                if(highScore>=pom)
                {
                    String text1;
                    g2.setFont(arial_40);
                    g2.setColor(Color.blue);
                    text1 = "YOU GOT NEW HIGH SCORE!";
                    textLenght = (int) g2.getFontMetrics().getStringBounds(text1, g2).getWidth();
                    int x1 = gp.screanWidth / 2 - textLenght / 2-30;
                    int y2 = gp.screanHeight / 2 - gp.tileSize * 2-30;
                    g2.drawString(text1, x1+30, y2-100);
                    br++;
                    wrightNewHighScore(playTime);
                }
            } else {
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
                g2.drawString("x =" + gp.player.hasKey, 74, 65);
                g2.drawImage(bookImage, gp.tileSize / 2, gp.tileSize / 2 + 60, gp.tileSize, gp.tileSize, null);
                g2.drawString("x =" + gp.player.hasBook, 74, 125);
                playTime += (double) 1 / 60;
                g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize * 11, 65);
                if (messageON == true) {
                    g2.setFont(g2.getFont().deriveFont(30F));
                    g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                    messageCounter++;
                    if (messageCounter > 150) {
                        messageCounter = 0;
                        messageON = false;
                    }
                }
            }
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }
    public double readHighScore() {
        double numb;
        try {
            BufferedReader reader = new BufferedReader(new FileReader
                    ("C:\\Users\\User\\IdeaProjects\\Vjezba 2d\\res\\leaderboard\\highscore.txt"));
            String line;
            line=reader.readLine();
            numb=Double.parseDouble(line);
            reader.close();
            return numb;
        } catch(IOException e)
        {
        e.printStackTrace();
        return 0;
        }
    }
    public void wrightNewHighScore(double score)
    {
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter
                    ("C:\\Users\\User\\IdeaProjects\\Vjezba 2d\\res\\leaderboard\\highscore.txt"));
            writer.flush();
            String newScore=Double.toString(score);
            writer.write(newScore);
            writer.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void wrightScore(String source, double score)
    {
        if(globalStopLoop==0) {
            try {
                BufferedReader read = new BufferedReader(new FileReader(source));
                String results[] = new String[200];
                int increment = 0;
                String line;
                while ((line = read.readLine()) != null) {
                    results[increment] = line;
                    System.out.println("ISpis 11" + increment + " rijeci11: " + results[increment]);
                    increment++;
                }
                read.close();
                try {
                    BufferedWriter wright = new BufferedWriter(new FileWriter(source));
                    if (globalStopLoop == 0) {
                        for (int i = 0; i < increment; i++) {
                            wright.write(results[i] + "\n");
                            System.out.println("ISpis " + i + " rijeci: " + results[i]);
                        }
                        wright.append(Double.toString(score));
                        globalStopLoop++;
                        wright.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void drawTitleScrean()
    {
        g2.setColor(new Color(70,120,80));
        g2.fillRect(0,0,gp.screanWidth,gp.screanHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text="Student fakulteta Apeiron";
        String text2="na zadatku";
        int x=getXforCenterTex(text);
        int y=gp.tileSize*3;
        g2.setColor(Color.black);
        g2.drawString(text,x+5,y-70);
        g2.setColor(Color.white);
        int x1=getXforCenterTex(text2);
        int y1=gp.tileSize*5;
        g2.drawString(text,x,y-70);
        g2.setColor(Color.black);
        g2.drawString(text2,x1+5,y1-70);
        g2.setColor(Color.white);
        g2.drawString(text2,x1,y1-70);
        x=gp.screanWidth/2;
        y+=gp.tileSize*3;
        g2.drawImage(titlePic("/player/student_slika1.png"),
                x+50,y-75,gp.tileSize*7,gp.tileSize*7,null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));
        text="NEW GAME";
        x=getXforCenterTex(text);
        y+=gp.tileSize;
        g2.drawString(text,x-50,y);
        if(commandNum==0)
        {
            g2.drawImage(titlePic("/player/navigacija_student1.png"),
                    x-50-gp.tileSize*2,y-35,gp.tileSize,gp.tileSize,null);
        }
        text="LEADERBOARD";
        x=getXforCenterTex(text);
        y+=gp.tileSize;
        g2.drawString(text,x-50,y);
        if(commandNum==1)
        {
            g2.drawImage(titlePic("/player/navigacija_student1.png"),
                    x-50-gp.tileSize*2,y-35,gp.tileSize,gp.tileSize,null);
        }
        text="HIGHES SCORE";
        x=getXforCenterTex(text);
        y+=gp.tileSize;
        g2.drawString(text,x-50,y);
        if(commandNum==2)
        {
            g2.drawImage(titlePic("/player/navigacija_student1.png"),
                    x-50-gp.tileSize*2,y-35,gp.tileSize,gp.tileSize,null);
        }
        text="QUIT";
        x=getXforCenterTex(text);
        y+=gp.tileSize;
        g2.drawString(text,x-50,y);
        if(commandNum==3)
        {
            g2.drawImage(titlePic("/player/navigacija_student1.png"),
                    x-50-gp.tileSize*2,y-35,gp.tileSize,gp.tileSize,null);
        }
    }
    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,90F));
        String text="Paused!";
        int x=getXforCenterTex(text);
        int y=gp.screanHeight/2;
        g2.drawString(text,x,y);
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,40F));
        g2.drawString("Interact with wizards and ",x/2+20,y/2-30);
        g2.drawString("pick up books to unlock final reward!",x/2-20,y/2+30);
        g2.drawString("You need "+(5-gp.player.hasBook)+" more books!",x-60,y+80);
        g2.drawString("Press 'P' key to resume!",x-60,y+150);
    }
    public int getXforCenterTex(String text)
    {
        int lenght=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screanWidth/2-lenght/2;
        return x;
    }
    public BufferedImage titlePic(String name)
    {
        main.UtilityTool uTool =new main.UtilityTool();
        BufferedImage image=null;
        try{
            image=ImageIO.read(getClass().getResourceAsStream(name));
            image=uTool.scaleImage(image,gp.tileSize*25,gp.tileSize*25);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
}
