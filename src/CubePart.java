import GLOOP.GLQuader;
import GLOOP.GLVektor;
import GLOOP.GLWuerfel;

public class CubePart {
    public final GLQuader[] cubeSides;
    private final GLWuerfel middle;

    public final CubePosition originalPosition;
    public CubePosition currentPosition;

    public final GLVektor originalVectorPosition;
    public GLVektor currentVectorPosition;

    public CubePart(CubePosition cubePosition, double partSize, double spacing) {
        middle = new GLWuerfel(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing), 0);

        cubeSides = new GLQuader[6];
        cubeSides[0] = new GLQuader(cubePosition.x()*(partSize+spacing)-partSize/2, cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing), 0, partSize, partSize);
        cubeSides[1] = new GLQuader(cubePosition.x()*(partSize+spacing)+partSize/2, cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing), 0, partSize, partSize);
        cubeSides[2] = new GLQuader(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing)-partSize/2, cubePosition.z()*(partSize+spacing), partSize, 0, partSize);
        cubeSides[3] = new GLQuader(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing)+partSize/2, cubePosition.z()*(partSize+spacing), partSize, 0, partSize);
        cubeSides[4] = new GLQuader(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing)-partSize/2, partSize, partSize, 0);
        cubeSides[5] = new GLQuader(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing)+partSize/2, partSize, partSize, 0);


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
}
