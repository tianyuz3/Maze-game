package utilities;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor extends Object{
    public ObjectDoor(){

        name = "door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/utilities/door.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
