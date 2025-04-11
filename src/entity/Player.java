package entity;

import object.OBJ_Book;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Image;
public class Player extends Entity{
    main.GamePanel gp;
    main.KeyHandler keyH;
    public final int screanX;
    public final int screanY;
    public int hasKey=0;
    public int rememberSpeed=0;
    public int hasBook=0;
    public int wizardCount=0;

    public Player(main.GamePanel gp, main.KeyHandler keyH)
    {
       this.gp=gp;
       this.keyH=keyH;
       screanX=gp.screanWidth/2-(gp.tileSize/2);
       screanY=gp.screanHeight/2-(gp.tileSize/2);
       //sudaranje sa zidom
       solidArea=new Rectangle();
       solidArea.x=8;
       solidArea.y=16;
       solidAreaDefaultX=solidArea.x;
       solidAreaDefaultY=solidArea.y;
       solidArea.width=32;
       solidArea.height=32;
       setDefaultValues();
       getPlayerImage();
    }
    public void setDefaultValues()
    {
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*21;
        speed=4;
        direction="down";
    }
    public void setDefaultGameValues()
    {
        hasKey=0;
        rememberSpeed=0;
        hasBook=0;
        wizardCount=0;
    }
    public void  getPlayerImage()
    {
        up1=setup("boy_up_1");
        up2=setup("boy_up_2");
        down1=setup("boy_down_1");
        down2=setup("boy_down_2");
        left1=setup("boy_left_1");
        left2=setup("boy_left_2");
        right1=setup("boy_right_1");
        right2=setup("boy_right_2");
    }
    public BufferedImage setup(String imageName)
    {
        main.UtilityTool uTool =new main.UtilityTool();
        BufferedImage image=null;
        try{
            image=ImageIO.read(getClass()
                    .getResourceAsStream
                            ("/player/"+imageName+".png"));
            image=uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
    public  void update()
    {
        if(keyH.rightPressed==true ||
                keyH.leftPressed==true ||
                keyH.upPressed==true || keyH.downPressed==true) {
            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";


            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }
            // provjera sudara
            colisionOn=false;
            gp.cChecker.checkTile(this);
            //provjera sudaranja objekata
            int objIndex= gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);
            // ako nema kolizije krece se
            if(colisionOn==false)
            {
                switch (direction)
                {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if (spriteCounter > 9) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int i)
    {
        if(i!=999)
        {
            String objectName=gp.obj[i].name;
            switch (objectName)
            {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey>0)
                    {
                        gp.playSE(3);
                        gp.obj[i]=null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else{
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed+=1;
                    rememberSpeed++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("Speed up!!!");
                    break;
                case "Chest":
                    if(hasBook==5) {
                        gp.ui.gameFinished = true;
                        gp.playSE(4);
                        gp.stopMusic();
                    }
                    else{
                        gp.ui.showMessage("Not enough books," +
                                " you need "+(5-hasBook)+"more!");
                    //gp.playSE(4);
                    }
                    break;
                case "characteradd":
                    gp.playSE(2);

                    solidArea.x=6;
                    solidArea.y=14;
                    solidArea.width=26;
                    solidArea.height=26;
                    gp.obj[i]=null;
                    gp.ui.showMessage("Smaller character!");
                    break;
                case "arrowrefresh":
                    gp.playSE(2);
                    setDefaultValues();
                    if(rememberSpeed>0)
                        speed+=1;
                    break;
                case "book":
                    gp.playSE(3);
                    hasBook++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("You got the book!");
                    break;
                case "wizard":
                    gp.playSE(2);
                    gp.ui.showMessage("Wizdom has been" +
                            " spread out, pick up the books!");
                    if(wizardCount==0)
                    {
                        gp.obj[13]=new OBJ_Book(gp);
                        gp.obj[13].worldX=34*gp.tileSize;
                        gp.obj[13].worldY=7*gp.tileSize;

                        gp.obj[14]=new OBJ_Book(gp);
                        gp.obj[14].worldX=17*gp.tileSize;
                        gp.obj[14].worldY=14*gp.tileSize;
                    }
                    if(wizardCount==1)
                    {
                        gp.obj[15]=new OBJ_Book(gp);
                        gp.obj[15].worldX=34*gp.tileSize;
                        gp.obj[15].worldY=40*gp.tileSize;
                        gp.obj[16]=new OBJ_Book(gp);
                        gp.obj[16].worldX=11*gp.tileSize;
                        gp.obj[16].worldY=40*gp.tileSize;
                    }
                    wizardCount++;
                    gp.obj[i]=null;
                    break;
                case "help":
                    gp.playSE(2);
                    gp.gameState=gp.pauseState;
                    gp.ui.drawPauseScreen();
                    break;
                case "clock":
                    gp.playSE(2);
                    gp.ui.showMessage("You saved 15s!");
                    gp.ui.playTime-=15;
                    gp.obj[i]=null;
                    break;
            }
        }
    }
    public void draw(Graphics2D g2)
    {
        BufferedImage image =null;
        switch (direction)
        {
            case "up":
                if(spriteNum==1)
                {
                    image=up1;
                }
                if(spriteNum==2)
                {
                    image=up2;
                }
                break;
            case "down":
                if(spriteNum==1)
                {
                    image=down1;
                }
                if(spriteNum==2)
                {
                    image=down2;
                }
                break;
            case "left":
                if(spriteNum==1)
                {
                    image=left1;
                }
                if(spriteNum==2)
                {
                    image=left2;
                }
                break;
            case "right":
                if(spriteNum==1)
                {
                    image=right1;
                }
                if(spriteNum==2)
                {
                    image=right2;
                }
                break;
        }g2.drawImage(image,screanX,screanY,null);
    }
    }

