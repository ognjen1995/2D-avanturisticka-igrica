package main;

import object.*;

public class AssetSetter {
    main.GamePanel gp;
    public AssetSetter(main.GamePanel gp)
    {
        this.gp=gp;
    }
    public void setObject()
    {
        gp.obj[0]=new OBJ_Key(gp);
        gp.obj[0].worldX=23*gp.tileSize;
        gp.obj[0].worldY=7*gp.tileSize;

        gp.obj[1]=new OBJ_Key(gp);
        gp.obj[1].worldX=23*gp.tileSize;
        gp.obj[1].worldY=40*gp.tileSize;

        gp.obj[2]=new OBJ_Key(gp);
        gp.obj[2].worldX=40*gp.tileSize;
        gp.obj[2].worldY=7*gp.tileSize;

        gp.obj[3]=new OBJ_Door(gp);
        gp.obj[3].worldX=10*gp.tileSize;
        gp.obj[3].worldY=10*gp.tileSize;

        gp.obj[4]=new OBJ_Door(gp);
        gp.obj[4].worldX=8*gp.tileSize;
        gp.obj[4].worldY=21*gp.tileSize;

        gp.obj[5]=new OBJ_Door(gp);
        gp.obj[5].worldX=12*gp.tileSize;
        gp.obj[5].worldY=21*gp.tileSize;

        gp.obj[6]=new OBJ_Chest(gp);
        gp.obj[6].worldX=10*gp.tileSize;
        gp.obj[6].worldY=7*gp.tileSize;

        gp.obj[7]=new OBJ_Boots(gp);
        gp.obj[7].worldX=37*gp.tileSize;
        gp.obj[7].worldY=40*gp.tileSize;

        gp.obj[8]=new OBJ_Character(gp);
        gp.obj[8].worldX=18*gp.tileSize;
        gp.obj[8].worldY=28*gp.tileSize;

        gp.obj[9]=new OBJ_Arrowrefresh(gp);
        gp.obj[9].worldX=43*gp.tileSize;
        gp.obj[9].worldY=21*gp.tileSize;

        gp.obj[10]=new OBJ_Arrowrefresh(gp);
        gp.obj[10].worldX=6*gp.tileSize;
        gp.obj[10].worldY=40*gp.tileSize;

        gp.obj[11]=new OBJ_Book(gp);
        gp.obj[11].worldX=31*gp.tileSize;
        gp.obj[11].worldY=11*gp.tileSize;

        gp.obj[12]=new OBJ_Wizard(gp);
        gp.obj[12].worldX=38*gp.tileSize;
        gp.obj[12].worldY=9*gp.tileSize;

        gp.obj[17]=new OBJ_Wizard(gp);
        gp.obj[17].worldX=20*gp.tileSize;
        gp.obj[17].worldY=39*gp.tileSize;

        gp.obj[18]=new OBJ_Help(gp);
        gp.obj[18].worldX=25*gp.tileSize;
        gp.obj[18].worldY=22*gp.tileSize;

        gp.obj[19]=new OBJ_Key(gp);
        gp.obj[19].worldX=32*gp.tileSize;
        gp.obj[19].worldY=28*gp.tileSize;

        gp.obj[20]=new OBJ_Door(gp);
        gp.obj[20].worldX=38*gp.tileSize;
        gp.obj[20].worldY=12*gp.tileSize;

        gp.obj[21]=new OBJ_Clock(gp);
        gp.obj[21].worldX=4*gp.tileSize;
        gp.obj[21].worldY=13*gp.tileSize;

        gp.obj[22]=new OBJ_Clock(gp);
        gp.obj[22].worldX=6*gp.tileSize;
        gp.obj[22].worldY=38*gp.tileSize;
    }
}
