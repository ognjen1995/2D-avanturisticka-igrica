package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Arrowrefresh extends SuperObject{
    main.GamePanel gp;
    public OBJ_Arrowrefresh(main.GamePanel gp)
    {
        this.gp=gp;
        name="arrowrefresh";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/arrowrefresh.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }

}
