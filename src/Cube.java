import GLOOP.GLVektor;
import GLOOP.Sys;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Cube{
    // length of the rotation animation
    public static final double ROTATION_ANIMATION_LENGTH = 20; //ms

    // array of all the small cubes the cube contains
    private final CubePart[] cubeParts = new CubePart[27];

    public Cube(double size, double spacing) {
        // index to put each cubePart in the cubeParts array despite using 3 for-loops
        int cubePartsIndex = 0;

        // cycle through all 3 dimensions of the cube from -1 to 1
        for (int ro = ColourPosition.RED; ro <= ColourPosition.ORANGE; ro++) {
            for (int wy = ColourPosition.WHITE; wy <= ColourPosition.YELLOW; wy++) {
                for (int gb = ColourPosition.GREEN; gb <= ColourPosition.BLUE; gb++) {
                    // spawn new cube at given position with part size of [cube size minus the two gaps, all divided by three]
                    cubeParts[cubePartsIndex] = new CubePart(new CubePartPosition(ro, wy, gb), (size - 2*spacing)/3, spacing, false);
                    // if cubePart is center of the cube, create a Sphere with size of the cube without spacing
                    if (ro == 0 && wy == 0 && gb == 0) {
                        cubeParts[cubePartsIndex] = new CubePart(new CubePartPosition(ro, wy, gb), /*2.1*((size - 2*spacing)/3)*/size*0.5, spacing, true);
                        cubeParts[cubePartsIndex].middle.setzeTextur(Texture.BLACK);
                    } else {
                        // assign each side a colour based on the position, cube sides inside the cube are black
                        if (ro < 0) cubeParts[cubePartsIndex].cubeSides[0].setzeTextur(Texture.RED);
                        else cubeParts[cubePartsIndex].cubeSides[0].setzeTextur(Texture.BLACK);
                        if (ro > 0) cubeParts[cubePartsIndex].cubeSides[1].setzeTextur(Texture.ORANGE);
                        else cubeParts[cubePartsIndex].cubeSides[1].setzeTextur(Texture.BLACK);
                        if (wy < 0) cubeParts[cubePartsIndex].cubeSides[2].setzeTextur(Texture.WHITE);
                        else cubeParts[cubePartsIndex].cubeSides[2].setzeTextur(Texture.BLACK);
                        if (wy > 0) cubeParts[cubePartsIndex].cubeSides[3].setzeTextur(Texture.YELLOW);
                        else cubeParts[cubePartsIndex].cubeSides[3].setzeTextur(Texture.BLACK);
                        if (gb < 0) cubeParts[cubePartsIndex].cubeSides[4].setzeTextur(Texture.GREEN);
                        else cubeParts[cubePartsIndex].cubeSides[4].setzeTextur(Texture.BLACK);
                        if (gb > 0) cubeParts[cubePartsIndex].cubeSides[5].setzeTextur(Texture.BLUE);
                        else cubeParts[cubePartsIndex].cubeSides[5].setzeTextur(Texture.BLACK);

                        // give the white cube in the center the logo texture
                        if (ro == ColourPosition.NONE && wy == ColourPosition.WHITE && gb == ColourPosition.NONE) cubeParts[cubePartsIndex].cubeSides[2].setzeTextur(Texture.LOGO);
                    }

                    cubePartsIndex++;
                }
            }
        }
    }

    // get index of cube in cubeParts[] based on position, if it does not exist, return -1
    public int getCubePartIndex(CubePartPosition cubePartPosition) {
        for (int i = 0; i < cubeParts.length; i++) {
            if (cubeParts[i].currentPosition.equals(cubePartPosition)) return i;
        }
        return -1;
    }

    //rotate colour side with direction modifier
    public void rotate(Colour colour, int rotationModifier) {
        // get center position of the side to rotate around it
        GLVektor sideCenter = Arrays.stream(cubeParts).filter(cubePart1 -> cubePart1.currentPosition.equals(colour.centerPosition)).collect(Collectors.toList()).get(0).originalVectorPosition;

        List<CubePart> toBeRotated = Arrays.stream(cubeParts).filter(cubePart -> cubePart.currentPosition.toInt() % colour.colourFactor == 0).collect(Collectors.toList());
        // animate side rotation, 1 degree 90 times
        for (int i = 0; i < 90; i++) {
            // rotate each small cube around the center by 1 degree, direction modified by rotationModifier
            for (CubePart cubePart : toBeRotated)
                cubePart.rotate(1, new GLVektor(-colour.centerPosition.toVector().x * rotationModifier, -colour.centerPosition.toVector().y * rotationModifier, -colour.centerPosition.toVector().z * rotationModifier), sideCenter);
            // wait ROTATION_ANIMATION_LENGTH/90 90 times -> animation length ~= ROTATION_ANIMATION_LENGTH
            try { TimeUnit.NANOSECONDS.sleep((long)(ROTATION_ANIMATION_LENGTH/90*1000)); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }

        // update "logical" positions of the cubes
        for (CubePart cubePart : Arrays.stream(cubeParts).filter(cubePart -> cubePart.currentPosition.toInt() % colour.colourFactor == 0).collect(Collectors.toList())) {
            // for each colour, there is a slightly different method of rotating, because one of the three positions always stays the same, the other two are basically a matrix rotation or a multiplication of complex numbers, where rotationModifier is i or -i
            if (colour.colourFactor % ColourFactor.RED == 0) {
                cubePart.currentPosition = new CubePartPosition(ColourPosition.RED, -rotationModifier * cubePart.currentPosition.z(), rotationModifier * cubePart.currentPosition.y());
                for (int i = 0; i < cubePart.sidePositions.length; i++)
                    cubePart.sidePositions[i] = new CubePartPosition(cubePart.sidePositions[i].x(), -rotationModifier * cubePart.sidePositions[i].z(), rotationModifier * cubePart.sidePositions[i].y());
            }
            else if (colour.colourFactor % ColourFactor.ORANGE == 0) {
                cubePart.currentPosition = new CubePartPosition(ColourPosition.ORANGE, rotationModifier * cubePart.currentPosition.z(), -rotationModifier * cubePart.currentPosition.y());
                for (int i = 0; i < cubePart.sidePositions.length; i++)
                    cubePart.sidePositions[i] = new CubePartPosition(cubePart.sidePositions[i].x(), rotationModifier*cubePart.sidePositions[i].z(), -rotationModifier * cubePart.sidePositions[i].y());
            }
            else if (colour.colourFactor % ColourFactor.WHITE == 0) {
                cubePart.currentPosition = new CubePartPosition(rotationModifier * cubePart.currentPosition.z(), ColourPosition.WHITE, -rotationModifier * cubePart.currentPosition.x());
                for (int i = 0; i < cubePart.sidePositions.length; i++)
                    cubePart.sidePositions[i] = new CubePartPosition(rotationModifier * cubePart.sidePositions[i].z(), cubePart.sidePositions[i].y(), -rotationModifier * cubePart.sidePositions[i].x());
            }
            else if (colour.colourFactor % ColourFactor.YELLOW == 0) {
                cubePart.currentPosition = new CubePartPosition(-rotationModifier * cubePart.currentPosition.z(), ColourPosition.YELLOW, rotationModifier * cubePart.currentPosition.x());
                for (int i = 0; i < cubePart.sidePositions.length; i++)
                    cubePart.sidePositions[i] = new CubePartPosition(-rotationModifier * cubePart.sidePositions[i].z(), cubePart.sidePositions[i].y(), rotationModifier * cubePart.sidePositions[i].x());
            }
            else if (colour.colourFactor % ColourFactor.GREEN == 0) {
                cubePart.currentPosition = new CubePartPosition(-rotationModifier * cubePart.currentPosition.y(), rotationModifier * cubePart.currentPosition.x(), ColourPosition.GREEN);
                for (int i = 0; i < cubePart.sidePositions.length; i++)
                    cubePart.sidePositions[i] = new CubePartPosition(-rotationModifier * cubePart.sidePositions[i].y(), rotationModifier * cubePart.sidePositions[i].x(), cubePart.sidePositions[i].z());
            }
            else if (colour.colourFactor % ColourFactor.BLUE == 0) {
                cubePart.currentPosition = new CubePartPosition(rotationModifier * cubePart.currentPosition.y(), -rotationModifier * cubePart.currentPosition.x(), ColourPosition.BLUE);
                for (int i = 0; i < cubePart.sidePositions.length; i++)
                    cubePart.sidePositions[i] = new CubePartPosition(rotationModifier * cubePart.sidePositions[i].y(), -rotationModifier * cubePart.sidePositions[i].x(), cubePart.sidePositions[i].z());
            }
        }
    }

    // get the side position of a colour on a small cube in cubeParts
    public CubePartPosition getSidePosition(CubePartPosition cubePartPosition, Colour colour) {
        return cubeParts[getCubePartIndex(cubePartPosition)].getSidePosition(colour.colourFactor);
    }
}
