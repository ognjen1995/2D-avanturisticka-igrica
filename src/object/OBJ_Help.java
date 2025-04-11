package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Help extends SuperObject{
    main.GamePanel gp;
    public OBJ_Help(main.GamePanel gp)
    {
        this.gp=gp;
        name="help";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/help.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}
