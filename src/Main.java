import GLOOP.*;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GLKamera kamera = new GLKamera(1280, 720);
        GLLicht licht = new GLLicht();
        kamera.setzePosition(500, 300, -200);

        // Wuerfel[x][y][z]
        GLWuerfel[][][] cubes = new GLWuerfel[3][3][3];

        for (int z = 0; z < 3; z++) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {

                }
            }
        }

        GLVektor cube_w_start = new GLVektor(0, -100, 0);
        GLWuerfel cube_w = new GLWuerfel(cube_w_start, 100);

        GLVektor cube_y_start = new GLVektor(0, 100, 0);
        GLWuerfel cube_y = new GLWuerfel(cube_y_start, 100);

        GLVektor cube_r_start = new GLVektor(-100, 0, 0);
        GLWuerfel cube_r = new GLWuerfel(cube_r_start, 100);

        GLVektor cube_o_start = new GLVektor(100, 0, 0);
        GLWuerfel cube_o = new GLWuerfel(cube_o_start, 100);

        GLVektor cube_g_start = new GLVektor(0, 0, -100);
        GLWuerfel cube_g = new GLWuerfel(cube_g_start, 100);

        GLVektor cube_b_start = new GLVektor(0, 0, 100);
        GLWuerfel cube_b = new GLWuerfel(cube_b_start, 100);

        GLVektor cube_wr_start = new GLVektor(-100, -100, 0);
        GLWuerfel cube_wr = new GLWuerfel(cube_wr_start, 100);

        GLVektor cube_wo_start = new GLVektor(100, -100, 0);
        GLWuerfel cube_wo = new GLWuerfel(cube_wo_start, 100);

        GLVektor cube_wg_start = new GLVektor(0, -100, -100);
        GLWuerfel cube_wg = new GLWuerfel(cube_wg_start, 100);

        GLVektor cube_wb_start = new GLVektor(0, -100, 100);
        GLWuerfel cube_wb = new GLWuerfel(cube_wb_start, 100);

        GLVektor cube_yr_start = new GLVektor(-100, 100, 0);
        GLWuerfel cube_yr = new GLWuerfel(cube_yr_start, 100);

        GLVektor cube_yo_start = new GLVektor(100, 100, 0);
        GLWuerfel cube_yo = new GLWuerfel(cube_yo_start, 100);

        GLVektor cube_yg_start = new GLVektor(0, 100, -100);
        GLWuerfel cube_yg = new GLWuerfel(cube_yg_start, 100);

        GLVektor cube_yb_start = new GLVektor(0, 100, 100);
        GLWuerfel cube_yb = new GLWuerfel(cube_yb_start, 100);

        GLVektor cube_rg_start = new GLVektor(-100, 0, -100);
        GLWuerfel cube_rg = new GLWuerfel(cube_rg_start, 100);

        GLVektor cube_go_start = new GLVektor(100, 0, -100);
        GLWuerfel cube_go = new GLWuerfel(cube_go_start, 100);

        GLVektor cube_ob_start = new GLVektor(100, 0, 100);
        GLWuerfel cube_ob = new GLWuerfel(cube_ob_start, 100);

        GLVektor cube_br_start = new GLVektor(-100, 0, 100);
        GLWuerfel cube_br = new GLWuerfel(cube_br_start, 100);

        GLVektor cube_wrg_start = new GLVektor(-100, -100, -100);
        GLWuerfel cube_wrg = new GLWuerfel(cube_wrg_start, 100);

        GLVektor cube_wrb_start = new GLVektor(-100, -100, 100);
        GLWuerfel cube_wrb = new GLWuerfel(cube_wrb_start, 100);

        GLVektor cube_wog_start = new GLVektor(100, -100, -100);
        GLWuerfel cube_wog = new GLWuerfel(cube_wog_start, 100);

        GLVektor cube_wob_start = new GLVektor(100, -100, 100);
        GLWuerfel cube_wob = new GLWuerfel(cube_wob_start, 100);

        GLVektor cube_yrg_start = new GLVektor(-100, 100, -100);
        GLWuerfel cube_yrg = new GLWuerfel(cube_yrg_start, 100);

        GLVektor cube_yrb_start = new GLVektor(-100, 100, 100);
        GLWuerfel cube_yrb = new GLWuerfel(cube_yrb_start, 100);

        GLVektor cube_yog_start = new GLVektor(100, 100, -100);
        GLWuerfel cube_yog = new GLWuerfel(cube_yog_start, 100);

        GLVektor cube_yob_start = new GLVektor(100, 100, 100);
        GLWuerfel cube_yob = new GLWuerfel(cube_yob_start, 100);


        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}