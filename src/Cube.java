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
                    if (wy == 0 && ro == 0 && gb == 0) {
                        //TODO: Cube mid is sphere
                        cubeParts[i] = new CubePart(new CubePosition(wy, ro, gb), 2.1*((size - 2*spacing)/3), spacing);
                    }
                    i++;
                }
            }
        }
    }

    public void rotateWhite(int preRotationModifier) {
        int rotationModifier = -preRotationModifier;
        int[] toBeRotated = new int[9]; // 9 = cubes on one side
        int whitePartIndex = 0;
        int foundIndex = 0;
        for (int i = 0; i < cubeParts.length; i++) {
            if (cubeParts[i].currentPosition.wy() == ColorPosition.WHITE) {
                toBeRotated[foundIndex] = i;
                foundIndex++;
                if (cubeParts[i].currentPosition.equals(CubePosition.WHITE_CENTER)) {
                    whitePartIndex = i;
                }
            }
        }
        for (int i = 0; i < 90; i++) { //one degree each cycle
            for (int j : toBeRotated) {
                cubeParts[j].rotate(1, new GLVektor(ColorPosition.NONE, rotationModifier*ColorPosition.WHITE, ColorPosition.NONE), cubeParts[whitePartIndex].currentVektorPosition);
            }
            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { throw new RuntimeException(e); }
        }

        //TODO: This code could be trash
        for (int i = 0; i < Math.sqrt(Math.pow(preRotationModifier, 2)); i++) {
            for (int j : toBeRotated) {
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 0, -1)))  cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    -rotationModifier,  0);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 0, 1)))   cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    rotationModifier,   0);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, -1, 0)))  cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    0,              rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 1, 0)))   cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    0,              -rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, -1, -1))) cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    -rotationModifier,  rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, -1, 1)))  cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    rotationModifier,   rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 1, -1)))  cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    -rotationModifier,  -rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 1, 1)))   cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    rotationModifier,   -rotationModifier);
            }
        }
    }

    public void rotateYellow(int preRotationModifier) {
        int[] toBeRotated = new int[9]; // 9 = cubes on one side
        int yellowPartIndex = 0;
        int foundIndex = 0;
        for (int i = 0; i < cubeParts.length; i++) {
            if (cubeParts[i].currentPosition.wy() == ColorPosition.YELLOW) {
                toBeRotated[foundIndex] = i;
                foundIndex++;
                if (cubeParts[i].currentPosition.equals(CubePosition.YELLOW_CENTER)) {
                    yellowPartIndex = i;
                }
            }
        }
        for (int i = 0; i < 90; i++) { //one degree each cycle
            for (int j : toBeRotated) {
                cubeParts[j].rotate(1, new GLVektor(ColorPosition.NONE, preRotationModifier *ColorPosition.YELLOW, ColorPosition.NONE), cubeParts[yellowPartIndex].currentVektorPosition);
            }
            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { throw new RuntimeException(e); }
        }

        //TODO: This code could be trash
        for (int i = 0; i < Math.sqrt(Math.pow(preRotationModifier, 2)); i++) {
            for (int j : toBeRotated) {
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 0, -1)))  cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    -preRotationModifier,  0);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 0, 1)))   cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE, preRotationModifier,   0);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, -1, 0)))  cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    0, preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 1, 0)))   cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    0,              -preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, -1, -1))) cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    -preRotationModifier, preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, -1, 1)))  cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE, preRotationModifier, preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 1, -1)))  cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE,    -preRotationModifier,  -preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(ColorPosition.WHITE, 1, 1)))   cubeParts[j].currentPosition = new CubePosition(ColorPosition.WHITE, preRotationModifier,   -preRotationModifier);
            }
        }
    }

    public void rotateRed(int preRotationModifier) {
        int[] toBeRotated = new int[9]; // 9 = cubes on one side
        int redPartIndex = 0;
        int foundIndex = 0;
        for (int i = 0; i < cubeParts.length; i++) {
            if (cubeParts[i].currentPosition.ro() == ColorPosition.RED) {
                toBeRotated[foundIndex] = i;
                foundIndex++;
                if (cubeParts[i].currentPosition.equals(CubePosition.RED_CENTER)) {
                    redPartIndex = i;
                }
            }
        }
        for (int i = 0; i < 90; i++) { //one degree each cycle
            for (int j : toBeRotated) {
                cubeParts[j].rotate(1, new GLVektor(preRotationModifier*ColorPosition.RED, ColorPosition.NONE, ColorPosition.NONE), cubeParts[redPartIndex].currentVektorPosition);
            }
            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { throw new RuntimeException(e); }
        }

        //TODO: This code could be trash
        for (int i = 0; i < Math.sqrt(Math.pow(preRotationModifier, 2)); i++) {
            for (int j : toBeRotated) {
                if (cubeParts[j].currentPosition.equals(new CubePosition(0, ColorPosition.RED, -1)))  cubeParts[j].currentPosition = new CubePosition(-preRotationModifier,  ColorPosition.RED,  0);
                if (cubeParts[j].currentPosition.equals(new CubePosition(0, ColorPosition.RED, 1)))   cubeParts[j].currentPosition = new CubePosition(preRotationModifier, ColorPosition.RED,   0);
                if (cubeParts[j].currentPosition.equals(new CubePosition(-1, ColorPosition.RED, 0)))  cubeParts[j].currentPosition = new CubePosition(0,    ColorPosition.RED, preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(1, ColorPosition.RED, 0)))   cubeParts[j].currentPosition = new CubePosition(0,    ColorPosition.RED,              -preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(-1, ColorPosition.RED, -1))) cubeParts[j].currentPosition = new CubePosition(-preRotationModifier,    ColorPosition.RED, preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(-1, ColorPosition.RED, 1)))  cubeParts[j].currentPosition = new CubePosition(preRotationModifier, ColorPosition.RED, preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(1, ColorPosition.RED, -1)))  cubeParts[j].currentPosition = new CubePosition(-preRotationModifier,   ColorPosition.RED ,  -preRotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(1, ColorPosition.RED, 1)))   cubeParts[j].currentPosition = new CubePosition(preRotationModifier, ColorPosition.RED,   -preRotationModifier);
            }
        }
    }

    public void rotateOrange(int preRrotationModifier) {
        int rotationModifier = -preRrotationModifier;
        int[] toBeRotated = new int[9]; // 9 = cubes on one side
        int orangePartIndex = 0;
        int foundIndex = 0;
        for (int i = 0; i < cubeParts.length; i++) {
            if (cubeParts[i].currentPosition.ro() == ColorPosition.ORANGE) {
                toBeRotated[foundIndex] = i;
                foundIndex++;
                if (cubeParts[i].currentPosition.equals(CubePosition.ORANGE_CENTER)) {
                    orangePartIndex = i;
                }
            }
        }
        for (int i = 0; i < 90; i++) { //one degree each cycle
            for (int j : toBeRotated) {
                cubeParts[j].rotate(1, new GLVektor(rotationModifier*ColorPosition.ORANGE, ColorPosition.NONE, ColorPosition.NONE), cubeParts[orangePartIndex].currentVektorPosition);
            }
            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException e) { throw new RuntimeException(e); }
        }

        //TODO: This code could be trash
        for (int i = 0; i < Math.sqrt(Math.pow(rotationModifier, 2)); i++) {
            for (int j : toBeRotated) {
                if (cubeParts[j].currentPosition.equals(new CubePosition(0, ColorPosition.ORANGE, -1)))  cubeParts[j].currentPosition = new CubePosition(-rotationModifier,     ColorPosition.ORANGE,  0);
                if (cubeParts[j].currentPosition.equals(new CubePosition(0, ColorPosition.ORANGE, 1)))   cubeParts[j].currentPosition = new CubePosition(rotationModifier,      ColorPosition.ORANGE,   0);
                if (cubeParts[j].currentPosition.equals(new CubePosition(-1, ColorPosition.ORANGE, 0)))  cubeParts[j].currentPosition = new CubePosition(0,                 ColorPosition.ORANGE, rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(1, ColorPosition.ORANGE, 0)))   cubeParts[j].currentPosition = new CubePosition(0,                 ColorPosition.ORANGE,              -rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(-1, ColorPosition.ORANGE, -1))) cubeParts[j].currentPosition = new CubePosition(-rotationModifier,     ColorPosition.ORANGE, rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(-1, ColorPosition.ORANGE, 1)))  cubeParts[j].currentPosition = new CubePosition(rotationModifier,      ColorPosition.ORANGE, rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(1, ColorPosition.ORANGE, -1)))  cubeParts[j].currentPosition = new CubePosition(-rotationModifier,     ColorPosition.ORANGE ,  -rotationModifier);
                if (cubeParts[j].currentPosition.equals(new CubePosition(1, ColorPosition.ORANGE, 1)))   cubeParts[j].currentPosition = new CubePosition(rotationModifier,      ColorPosition.ORANGE,   -rotationModifier);
            }
        }
    }

    public void rotateGreen(int rotationModifier) {

    }

    public void rotateBlue(int rotationModifier) {

    }
}
