import GLOOP.GLVektor;

import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Cube {
    private final CubePart[] cubeParts = new CubePart[27];

    public Cube(double size, double spacing) {
        int i = 0;

        for (int ro = ColorPosition.RED; ro <= ColorPosition.ORANGE; ro++) {
            for (int wy = ColorPosition.WHITE; wy <= ColorPosition.YELLOW; wy++) {
                for (int gb = ColorPosition.GREEN; gb <= ColorPosition.BLUE; gb++) {
                    cubeParts[i] = new CubePart(new CubePosition(ro, wy, gb), (size - 2*spacing)/3, spacing);
                    if (ro == 0 && wy == 0 && gb == 0) {
                        //TODO: Cube mid is sphere
                        cubeParts[i] = new CubePart(new CubePosition(ro, wy, gb), 2.1*((size - 2*spacing)/3), spacing);
                    }
                    if (ro < 0) cubeParts[i].cubeSides[0].setzeTextur("textures/red.png");    else cubeParts[i].cubeSides[0].setzeTextur("textures/black.png");
                    if (ro > 0) cubeParts[i].cubeSides[1].setzeTextur("textures/orange.png");   else cubeParts[i].cubeSides[1].setzeTextur("textures/black.png");
                    if (wy < 0) cubeParts[i].cubeSides[2].setzeTextur("textures/white.png");      else cubeParts[i].cubeSides[2].setzeTextur("textures/black.png");
                    if (wy > 0) cubeParts[i].cubeSides[3].setzeTextur("textures/yellow.png");   else cubeParts[i].cubeSides[3].setzeTextur("textures/black.png");
                    if (gb < 0) cubeParts[i].cubeSides[4].setzeTextur("textures/green.png");    else cubeParts[i].cubeSides[4].setzeTextur("textures/black.png");
                    if (gb > 0) cubeParts[i].cubeSides[5].setzeTextur("textures/blue.png");     else cubeParts[i].cubeSides[5].setzeTextur("textures/black.png");
                    i++;
                }
            }
        }
    }

    //TODO: add part rotation
    public void rotate(Color color, int rotationModifier) {
        for (int i = 0; i < 90; i++)
            for (CubePart cubePart : Arrays.stream(cubeParts).filter(cubePart -> cubePart.currentPosition.toInt() % color.colorFactor == 0).collect(Collectors.toList())) {
                cubePart.rotate(1, new GLVektor(color.axis.toVector().x * rotationModifier, color.axis.toVector().y * rotationModifier, color.axis.toVector().z * rotationModifier), Arrays.stream(cubeParts).filter(cubePart1 -> cubePart1.currentPosition.equals(color.centerPosition)).collect(Collectors.toList()).get(0).originalVectorPosition);
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        for (CubePart cubePart : Arrays.stream(cubeParts).filter(cubePart -> cubePart.currentPosition.toInt() % color.colorFactor == 0).collect(Collectors.toList())) {
            if (color.colorFactor % ColorFactor.RED == 0) cubePart.currentPosition = new CubePosition(ColorPosition.RED, -rotationModifier * cubePart.currentPosition.z(), rotationModifier * cubePart.currentPosition.y());
            if (color.colorFactor % ColorFactor.ORANGE == 0) cubePart.currentPosition = new CubePosition(ColorPosition.ORANGE, -rotationModifier * cubePart.currentPosition.z(), rotationModifier * cubePart.currentPosition.y());
            if (color.colorFactor % ColorFactor.WHITE == 0) cubePart.currentPosition = new CubePosition(rotationModifier * cubePart.currentPosition.z(), ColorPosition.WHITE, -rotationModifier * cubePart.currentPosition.x());
            if (color.colorFactor % ColorFactor.YELLOW == 0) cubePart.currentPosition = new CubePosition(rotationModifier * cubePart.currentPosition.z(), ColorPosition.YELLOW, -rotationModifier * cubePart.currentPosition.x());
            if (color.colorFactor % ColorFactor.GREEN == 0) cubePart.currentPosition = new CubePosition(-rotationModifier * cubePart.currentPosition.y(), rotationModifier * cubePart.currentPosition.x(), ColorPosition.GREEN);
            if (color.colorFactor % ColorFactor.BLUE == 0) cubePart.currentPosition = new CubePosition(-rotationModifier * cubePart.currentPosition.y(), rotationModifier * cubePart.currentPosition.x(), ColorPosition.BLUE);
        }
    }
}
