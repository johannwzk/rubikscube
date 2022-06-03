import GLOOP.*;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GLKamera camera = new GLKamera(1280, 720); camera.setzePosition(0, 200, 500);
        GLLicht light = new GLLicht(5000, 10000, 2500);
//        Cube cube = new Cube(200, 4);
//        cube.cubeParts[0].setzeTextur("/home/planetbuilder/orgel.png");

        CubePart cubePart = new CubePart(new CubePosition(-1, 0, 1), 100, 5);
        while (true){
            camera.rotiere(0.01, 0, 1, 0, 0, 0, 0);
            light.rotiere(0.01, 0, 1, 0, 0 , 0, 0);

            try { TimeUnit.NANOSECONDS.sleep(1); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
    }
}//Temp
