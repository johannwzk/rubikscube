import GLOOP.GLKamera;
import GLOOP.GLLicht;
import GLOOP.GLQuader;

public class UI {
    GLKamera camera;
    GLLicht light;
    GLLicht light2;
    GLQuader ui;

    public UI(){
        camera = new GLKamera(1920, 1080); camera.setzePosition(0, 200, -500);
        light = new GLLicht(10000, 10000, 10000);
        light2 = new GLLicht(-10000, -10000, -10000);
        ui = new GLQuader(-300, -0, 0,200, 500,1 ); ui.rotiere(-30, 0, 1, 0, 0, 0, 0); ui.rotiere(21.8, 1, 0, 0, 0, 250, 0);
    }

    public void rotate(double pWinkel, double pNX, double pNY, double pNZ,double pRX, double pRY, double pRZ){
        camera.rotiere(pWinkel, pNX, pNY, pNZ, pRX, pRY, pRZ);
        light.rotiere(pWinkel, pNX, pNY, pNZ, pRX, pRY, pRZ);
        light2.rotiere(pWinkel, pNX, pNY, pNZ, pRX, pRY, pRZ);
        ui.rotiere(pWinkel, pNX, pNY, pNZ, pRX, pRY, pRZ);
    }

}
