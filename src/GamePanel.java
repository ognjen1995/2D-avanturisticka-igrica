package main;
import entity.Player;
import object.SuperObject;
import tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI;

public class GamePanel extends JPanel implements Runnable
{
    final int originalTileSize=16;
    final int scale=3;
    public final int tileSize=originalTileSize*scale;
    public final int maxScreenCol=16;
    public final int maxScreanRow=12;
    public final int screanWidth=tileSize*maxScreenCol;
    public final int screanHeight=tileSize*maxScreanRow;
    //world settings
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;
    int FPS=60;
    //vrijeme
    public long currentTime;
    public long lastTime=0;
    TileManager tileM=new TileManager(this);
    main.KeyHandler keyH=new main.KeyHandler( this);
    //muzika
    main.Sound se=new main.Sound();
    main.Sound music=new main.Sound();
    public main.CollisonChecker cChecker= new main.CollisonChecker(this);
    public main.AssetSetter aSetter=new main.AssetSetter(this);
    public main.UI ui=new main.UI(this);
    Thread gameThread;
    //igrac
    public Player player=new Player(this,keyH);
    public SuperObject obj[]=new SuperObject[40];
    //status igre
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screanWidth,screanHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame()
    {
        aSetter.setObject();
        gameState=titleState;
    }
    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }
    @Override
    public void run()
    {
        double drawInterval=1000000000/FPS;
        double delta=0;
        lastTime=System.nanoTime();
        while (gameThread!=null)
        {
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            lastTime=currentTime;
            if(delta>=1)
            {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update(){
        if(gameState==playState) {
            player.update();
        }
        if(gameState==pauseState)
        {
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D) g;
        if(gameState==titleState)
        {
           ui.draw(g2);
        }else {
            tileM.draw(g2);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            player.draw(g2);
            ui.draw(g2);
            g2.dispose();
        }
    }
    public void playMusc(int i)
    {
        music.setFIle(i);
        music.play();
        music.loop();
    }
    public void stopMusic()
    {
        music.stop();
    }
    public void playSE(int i)
        {
            se.setFIle(i);
            se.play();
        }
}
