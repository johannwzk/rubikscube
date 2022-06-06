import GLOOP.*;

public class CubePart {
    public final GLQuader[] cubeSides;
    public final GLKugel middle;

    public final CubePartPosition originalPosition;
    public CubePartPosition currentPosition;

    public final GLVektor originalVectorPosition;
    public GLVektor currentVectorPosition;

    public CubePartPosition[] sidePositions = {CubePartPosition.RED_CENTER, CubePartPosition.ORANGE_CENTER, CubePartPosition.WHITE_CENTER, CubePartPosition.YELLOW_CENTER, CubePartPosition.GREEN_CENTER, CubePartPosition.BLUE_CENTER};

    public CubePart(CubePartPosition cubePosition, double partSize, double spacing, boolean ball) {
        if (!ball) {
            middle = new GLKugel(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), 0);
            cubeSides = new GLQuader[6];
            cubeSides[0] = new GLQuader(cubePosition.x() * (partSize + spacing) - partSize / 2, cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), 0, partSize, partSize);
            cubeSides[1] = new GLQuader(cubePosition.x() * (partSize + spacing) + partSize / 2, cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), 0, partSize, partSize);
            cubeSides[2] = new GLQuader(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing) - partSize / 2, cubePosition.z() * (partSize + spacing), partSize, 0, partSize);
            cubeSides[3] = new GLQuader(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing) + partSize / 2, cubePosition.z() * (partSize + spacing), partSize, 0, partSize);
            cubeSides[4] = new GLQuader(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing) - partSize / 2, partSize, partSize, 0);
            cubeSides[5] = new GLQuader(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing) + partSize / 2, partSize, partSize, 0);
        } else {
            middle = new GLKugel(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), partSize);
            cubeSides = new GLQuader[6];
            for (int i = 0; i < cubeSides.length; i++) {
                cubeSides[i] = new GLWuerfel(cubePosition.x() * (partSize + spacing), cubePosition.y() * (partSize + spacing), cubePosition.z() * (partSize + spacing), 0);
            }
        }


        this.originalPosition = cubePosition;
        this.currentPosition = originalPosition;

        this.originalVectorPosition = middle.gibPosition();
        this.currentVectorPosition = originalVectorPosition;

    }

    public void rotate(double pWinkel, GLVektor pRichtung, GLVektor pOrt) {
        for (GLQuader cubeSide : cubeSides) cubeSide.rotiere(pWinkel, pRichtung, pOrt);
        middle.rotiere(pWinkel, pRichtung, pOrt);

        currentVectorPosition.x = middle.gibX();
        currentVectorPosition.y = middle.gibY();
        currentVectorPosition.z = middle.gibZ();
    }

    public CubePartPosition getSidePosition(int colourFactor) {
        if (colourFactor % ColourFactor.RED == 0) return sidePositions[0];
        else if (colourFactor % ColourFactor.ORANGE == 0) return sidePositions[1];
        else if (colourFactor % ColourFactor.WHITE == 0) return sidePositions[2];
        else if (colourFactor % ColourFactor.YELLOW == 0) return sidePositions[3];
        else if (colourFactor % ColourFactor.GREEN == 0) return sidePositions[4];
        else if (colourFactor % ColourFactor.BLUE == 0) return sidePositions[5];
        else throw new IllegalArgumentException("colourFactor must be a valid colourFactor (2, 3, 5, 7, 11, 13)");
    }
}
