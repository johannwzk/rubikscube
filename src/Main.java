import GLOOP.GLKamera;
import GLOOP.GLLicht;

import javax.crypto.Cipher;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GLKamera camera = new GLKamera(1920, 1080); camera.setzePosition(0, 200, -500);
        GLLicht light = new GLLicht(10000, 10000, 10000);
        GLLicht light2 = new GLLicht(-10000, -10000, -10000);
        Cube cube = new Cube(200, 4);

        int i = 0;
        while (true){
            ++i;
            camera.rotiere(0.3, 0, 1, 0, 0, 0, 0);
            light.rotiere(0.3, 0, 1, 0, 0 , 0, 0);
            light2.rotiere(0.3, 0, 1, 0, 0 , 0, 0);
            if (i % 370 == 0) {
                if (i == 1) return;

                // rotation modifier = Richtung
                cube.rotate(Colour.RED, 1);

                // gibt Seitenposition, man braucht den Index des kleinen Würfels (mit getCubePartIndex) und die Seite deren Orientation man haben will
                // geht auch für "schwarze" Seiten, aber das ist eigentlich egal
                cube.getSidePosition(cube.getCubePartIndex(new CubePartPosition(ColourPosition.RED, ColourPosition.WHITE, ColourPosition.BLUE)), Colour.WHITE);




                System.out.println("Position: " + cube.getSidePosition(cube.getCubePartIndex(new CubePartPosition(ColourPosition.RED, ColourPosition.WHITE, ColourPosition.BLUE)), Colour.WHITE).x() + ", " + cube.getSidePosition(cube.getCubePartIndex(new CubePartPosition(ColourPosition.RED, ColourPosition.WHITE, ColourPosition.BLUE)), Colour.WHITE).y() + ", " + cube.getSidePosition(cube.getCubePartIndex(new CubePartPosition(ColourPosition.RED, ColourPosition.WHITE, ColourPosition.BLUE)), Colour.WHITE).z());
            }
            try { TimeUnit.MILLISECONDS.sleep(16); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
    }
}