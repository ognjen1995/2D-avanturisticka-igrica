package object;
import main.UtilityTool;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image; //slika objekta
    public String name; //ime objekta
    public boolean collision=false;
    public int worldX,worldY; //koordinate u mapi
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
    main.UtilityTool uTool=new UtilityTool();
    public void draw(Graphics2D g2, main.GamePanel gp)//crtanje objekta
    {
        int screanX=worldX-gp.player.worldX+gp.player.screanX;
        int screanY=worldY-gp.player.worldY+gp.player.screanY;
        //iscrtava samo mapu oko igraca a ne cijelu zbog brzine
        if(worldX+gp.tileSize>gp.player.worldX-gp.player.screanX &&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screanX &&
                worldY+gp.tileSize>gp.player.worldY-gp.player.screanY &&
                worldY-gp.tileSize<gp.player.worldY+gp.player.screanY)
        {
            g2.drawImage(image, screanX, screanY, gp.tileSize, gp.tileSize, null);
        }
    }
}
