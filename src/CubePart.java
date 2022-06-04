import GLOOP.*;

public class CubePart {
    public final GLVektor originalVektorPosition;
    public GLVektor currentVektorPosition;

    public final CubePosition originalPosition;
    public CubePosition currentPosition;


    private final GLQuader[] cubeSides;

    private final GLWuerfel middle;

    public CubePart(CubePosition cubePosition, double partSize, double spacing) {
        //TODO: automated texture
        middle = new GLWuerfel(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing), 0);


        cubeSides = new GLQuader[6];
        cubeSides[0] = new GLQuader(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing)-partSize/2, cubePosition.z()*(partSize+spacing), partSize, 0, partSize);
        cubeSides[0].setzeTextur("textures/Testtextur.png");
        cubeSides[1] = new GLQuader(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing)+partSize/2, cubePosition.z()*(partSize+spacing), partSize, 0, partSize);
        cubeSides[2] = new GLQuader(cubePosition.x()*(partSize+spacing)-partSize/2, cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing), 0, partSize, partSize);
        cubeSides[3] = new GLQuader(cubePosition.x()*(partSize+spacing)+partSize/2, cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing), 0, partSize, partSize);
        cubeSides[4] = new GLQuader(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing)-partSize/2, partSize, partSize, 0);
        cubeSides[5] = new GLQuader(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing)+partSize/2, partSize, partSize, 0);


        this.originalPosition = cubePosition;
        this.currentPosition = originalPosition;

        this.originalVektorPosition = middle.gibPosition();
        this.currentVektorPosition = originalVektorPosition;
    }

    public void rotate(double pWinkel, double pNX, double pNY, double pNZ, double pRX, double pRY, double pRZ) {
        for (GLQuader cubeSide : cubeSides) cubeSide.rotiere(pWinkel, pNX, pNY, pNZ, pRX, pRY, pRZ);
        middle.rotiere(pWinkel, pNX, pNY, pNZ, pRX, pRY, pRZ);

        currentVektorPosition.x = middle.gibX();
        currentVektorPosition.y = middle.gibY();
        currentVektorPosition.z = middle.gibZ();
    }


    public void rotate(double pWinkel, GLVektor pRichtung, GLVektor pOrt) {
        for (GLQuader cubeSide : cubeSides) cubeSide.rotiere(pWinkel, pRichtung, pOrt);
        middle.rotiere(pWinkel, pRichtung, pOrt);

        currentVektorPosition.x = middle.gibX();
        currentVektorPosition.y = middle.gibY();
        currentVektorPosition.z = middle.gibZ();
    }
}
