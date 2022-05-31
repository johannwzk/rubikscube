import GLOOP.*;

import java.util.concurrent.TimeUnit;

public class Main {
    private static final String axises = "wyrogb";

    // Wuerfel[x][y][z]
    static GLWuerfel[][][] cubes = new GLWuerfel[3][3][3];

    public static void main(String[] args) {
        GLKamera kamera = new GLKamera(1280, 720);
        GLLicht licht = new GLLicht();
        kamera.setzePosition(500, 300, -200);


        for (int z = 0; z < 3; z++) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    cubes[x][y][z] = new GLWuerfel((x-1)*100, (y-1)*100, (z-1)*100, 95);
                }
            }
        }

        while (true){
            for (int z = 0; z < 3; z++) {
                for (int y = 0; y < 3; y++) {
                    cubes[0][y][z].rotiere(3, 1, 0, 0,(-1)*100, 0, 0);
                }
            }

            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}