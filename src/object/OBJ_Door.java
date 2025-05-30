package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject
{
    main.GamePanel gp;
    public OBJ_Door(main.GamePanel gp)
    {
        this.gp=gp;
        name="Door";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}
