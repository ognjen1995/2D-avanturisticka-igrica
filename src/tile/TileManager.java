package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    main.GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public TileManager(main.GamePanel gp)
    {
        this.gp=gp;
        tile=new Tile[80];
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }
    public void getTileImage()
    {

            setup(0,"grass",false);
            setup(1,"wall",true);
            setup(2,"water",true);
            setup(3,"earth",false);
            setup(4,"tree",true);
            setup(5,"sand",false);
            setup(6,"mountin",true);
            setup(7,"snowtree",true);
            setup(8,"path-tilemap",false);

    }
    public void setup(int index,String imageName,boolean colision)
    {
        main.UtilityTool uTool=new main.UtilityTool();
        try{
            tile[index]=new Tile();
            tile[index].image= ImageIO.read
                    (getClass().getResourceAsStream
                            ("/tiles/"+imageName+".png"));
            tile[index].image=uTool.scaleImage
                    (tile[index].image,gp.tileSize,gp.tileSize);
            tile[index].colision=colision;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath)
    {
        try {
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<gp.maxWorldCol && row<gp.maxWorldRow)
            {
                String line=br.readLine();
                while (col<gp.maxWorldCol)
                {
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col== gp.maxWorldCol)
                {
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {
        int worldCol=0;
        int worldRow=0;
        while (worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow)
        {
            int tileNum=mapTileNum[worldCol][worldRow];
            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;
            int screanX=worldX-gp.player.worldX+gp.player.screanX;
            int screanY=worldY-gp.player.worldY+gp.player.screanY;
            //iscrtava samo mapu oko igraca a ne cijelu zbog brzine
            if(worldX+gp.tileSize>gp.player.worldX-gp.player.screanX &&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screanX &&
                worldY+gp.tileSize>gp.player.worldY-gp.player.screanY &&
                worldY-gp.tileSize<gp.player.worldY+gp.player.screanY)
            {
                g2.drawImage(tile[tileNum].image, screanX, screanY, null);
            }
            worldCol++;
            if(worldCol==gp.maxWorldCol)
            {
                worldCol=0;
                worldRow++;
            }
        }
    }
}
