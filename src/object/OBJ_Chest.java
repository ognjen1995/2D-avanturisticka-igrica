package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    main.GamePanel gp;
    public OBJ_Chest(main.GamePanel gp)
    {
        this.gp=gp;
        name="Chest";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
