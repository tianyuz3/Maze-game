package utilities;

import javax.imageio.ImageIO;
import java.io.IOException;

public class SpeedPotion extends Object {
    public SpeedPotion() {
    name ="speedpotion";
        try
    {
        image = ImageIO.read(getClass().getResourceAsStream("/utilities/speedpotion.png"));
    }catch(
    IOException e)

    {
        e.printStackTrace();
    }
}
}
