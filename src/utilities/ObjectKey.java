package utilities;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends Object {
    public ObjectKey(){

        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/utilities/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
