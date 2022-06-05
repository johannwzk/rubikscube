import GLOOP.GLKamera;
import GLOOP.GLLicht;

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
                cube.rotate(Colour.RED, 1);
                cube.rotate(Colour.ORANGE, 1);
                cube.rotate(Colour.WHITE, 1);
                cube.rotate(Colour.YELLOW, 1);
                cube.rotate(Colour.GREEN, 1);
                cube.rotate(Colour.BLUE, 1);
            }
            try { TimeUnit.MILLISECONDS.sleep(16); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
    }
}