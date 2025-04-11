package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Clock extends SuperObject{
    main.GamePanel gp;
    public OBJ_Clock(main.GamePanel gp)
    {
        this.gp=gp;
        name="clock";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/clock.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}
