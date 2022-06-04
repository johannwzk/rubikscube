import GLOOP.GLVektor;

import java.util.concurrent.TimeUnit;

public class Cube {
    private final CubePart[] cubeParts = new CubePart[27];

    public Cube(double size, double spacing) {
        int i = 0;
        for (int wy = ColorPosition.WHITE; wy <= ColorPosition.YELLOW; wy++) {
            for (int ro = ColorPosition.ORANGE; ro <= ColorPosition.RED; ro++) {
                for (int gb = ColorPosition.BLUE; gb <= ColorPosition.GREEN; gb++) {
                    cubeParts[i] = new CubePart(new CubePosition(wy, ro, gb), (size - 2*spacing)/3, spacing);
                    i++;
                }
            }
        }
    }

    public void rotateWhite(int rotationModifier) {
        int[] toBeRotated = new int[9]; // 9 = cubes on one side
        int whitePartIndex = 0;
        int foundIndex = 0;
        for (int i = 0; i < cubeParts.length; i++) {
            if (cubeParts[i].currentPosition.wy() == ColorPosition.WHITE) {
                toBeRotated[foundIndex] = i;
                foundIndex++;
                if (cubeParts[i].currentPosition.x() == ColorPosition.NONE && cubeParts[i].currentPosition.y() == ColorPosition.WHITE && cubeParts[i].currentPosition.z() == ColorPosition.NONE) {
                    whitePartIndex = i;
                }
            }
        }
        for (int i = 0; i < 90; i++) { //one degree each cycle
            for (int k : toBeRotated) {
                cubeParts[k].rotate(1, new GLVektor(ColorPosition.NONE, rotationModifier* ColorPosition.WHITE, ColorPosition.NONE), cubeParts[whitePartIndex].currentVektorPosition);
            }
            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { throw new RuntimeException(e); }
        }
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
