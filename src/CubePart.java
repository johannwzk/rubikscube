import GLOOP.*;

public class CubePart {
    // sides of the small cube. The cube is not just one big cube because GLOOP does not allow giving each side a specific texture
    public final GLQuader[] cubeSides;
    // middle position of the small cube
    public final GLKugel middle;

    // original "logical" position of the small cube, not needed yet, but maybe in an update
    public final CubePartPosition originalPosition;
    // current "logical" position of the small cube. Used to detect cubes to be rotated and for the solving algorithm
    public CubePartPosition currentPosition;

    // original "graphical" position of the small cube, used for center cubes to rotate around them
    public final GLVektor originalVectorPosition;
    // current "graphical" position, also not really needed yet
    public GLVektor currentVectorPosition;

    // "logical" positions of the cube sides (for the solving algorithm)
    public CubePartPosition[] sidePositions = {CubePartPosition.RED_CENTER, CubePartPosition.ORANGE_CENTER, CubePartPosition.WHITE_CENTER, CubePartPosition.YELLOW_CENTER, CubePartPosition.GREEN_CENTER, CubePartPosition.BLUE_CENTER};

    public CubePart(CubePartPosition cubePosition, double partSize, double spacing, boolean sphere) {
        // if the small "cube" is not a sphere (in the middle) create sphere in the middle with cuboids as sides around it
        if (!sphere) {
            middle = new GLKugel(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), 0);
            cubeSides = new GLQuader[6];
            cubeSides[0] = new GLQuader(cubePosition.x() * (partSize + spacing) - partSize / 2, cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), 0, partSize, partSize);
            cubeSides[1] = new GLQuader(cubePosition.x() * (partSize + spacing) + partSize / 2, cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), 0, partSize, partSize);
            cubeSides[2] = new GLQuader(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing) - partSize / 2, cubePosition.z() * (partSize + spacing), partSize, 0, partSize);
            cubeSides[3] = new GLQuader(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing) + partSize / 2, cubePosition.z() * (partSize + spacing), partSize, 0, partSize);
            cubeSides[4] = new GLQuader(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing) - partSize / 2, partSize, partSize, 0);
            cubeSides[5] = new GLQuader(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing) + partSize / 2, partSize, partSize, 0);
        }
        // if it is a sphere (the cube part in the middle), make the sides a size of 0, create a big sphere
        else {
            middle = new GLKugel(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), partSize);
            cubeSides = new GLQuader[6];
            for (int i = 0; i < cubeSides.length; i++) {
                cubeSides[i] = new GLWuerfel(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), 0);
            }
        }

        // set current "logical" positions
        this.originalPosition = cubePosition;
        this.currentPosition = originalPosition;
        // set current "graphical" positions
        this.originalVectorPosition = middle.gibPosition();
        this.currentVectorPosition = originalVectorPosition;

    }

    // basically rotation of a cube, but for every part (the middle and the 6 sides)
    public void rotate(double pWinkel, GLVektor pRichtung, GLVektor pOrt) {
        for (GLQuader cubeSide : cubeSides) cubeSide.rotiere(pWinkel, pRichtung, pOrt);
        middle.rotiere(pWinkel, pRichtung, pOrt);

        currentVectorPosition.x = middle.gibX();
        currentVectorPosition.y = middle.gibY();
        currentVectorPosition.z = middle.gibZ();
    }

    // get position of colour side on a small cube, also used for solving algorithm
    public CubePartPosition getSidePosition(int colourFactor) {
        if (colourFactor % ColourFactor.RED == 0) return sidePositions[0];
        else if (colourFactor % ColourFactor.ORANGE == 0) return sidePositions[1];
        else if (colourFactor % ColourFactor.WHITE == 0) return sidePositions[2];
        else if (colourFactor % ColourFactor.YELLOW == 0) return sidePositions[3];
        else if (colourFactor % ColourFactor.GREEN == 0) return sidePositions[4];
        else if (colourFactor % ColourFactor.BLUE == 0) return sidePositions[5];
        else throw new IllegalArgumentException("colourFactor must be a valid colourFactor (2, 3, 5, 7, 11, 13)");
    }

    public boolean hasOriginalOrientation() {
        // go through every side and check if position is right
        for (int i = 0; i < this.sidePositions.length; i++)
            if (!this.sidePositions[i].equals(CubePartPosition.CENTER_POSITIONS[i])) return false;
        return true;
    }

    public void delete() {
        for (GLQuader cubeSide : cubeSides) cubeSide.loesche();
        middle.loesche();
    }
}
