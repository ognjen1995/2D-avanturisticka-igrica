package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Character extends SuperObject{
    main.GamePanel gp;
    public OBJ_Character(main.GamePanel gp)
    {
        this.gp=gp;
        name="characteradd";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/characteradd.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}
