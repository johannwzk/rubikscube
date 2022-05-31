import java.util.Arrays;

public class Cube {
    CubePart[] cubeParts = new CubePart[27];

    public Cube(double size, double spacing) {
        int i = 0;
        for (int wy = -1; wy <= 1; wy++) {
            for (int ro = -1; ro <= 1; ro++) {
                for (int gb = -1; gb <= 1; gb++) {
                    cubeParts[i] = new CubePart(new CubePosition(wy, ro, gb), (size - 2*spacing)/3, spacing);
                    i++;
                }
            }
        }
    }

    public void rotateWhite() {
    }

    public void rotateYellow() {

    }

    public void rotateRed() {

    }

    public void rotateOrange() {

    }

    public void rotateGreen() {

    }

    public void rotateBlue() {

    }
}
