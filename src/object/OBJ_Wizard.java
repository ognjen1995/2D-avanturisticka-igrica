package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Wizard extends SuperObject {
    main.GamePanel gp;
    public OBJ_Wizard(main.GamePanel gp)
    {
        this.gp=gp;
        name="wizard";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/wizard.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}