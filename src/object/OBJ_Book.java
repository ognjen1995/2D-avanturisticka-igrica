package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Book extends SuperObject{
    main.GamePanel gp;
    public OBJ_Book(main.GamePanel gp)//knjiga učitavanje
    {
        this.gp=gp;
        name="book";
        try {
            image= ImageIO.read
                    (getClass().getResourceAsStream
                            ("/objects/book.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}
