import GLOOP.GLTafel;
import GLOOP.GLTextur;
import GLOOP.GLVektor;
import GLOOP.GLWuerfel;

public class CubePart {
    public final CubePosition originalPosition;
    public CubePosition currentPosition;

    private final GLTafel[] cubeSides;

    public CubePart(CubePosition cubePosition, double partSize, double spacing) {
        //TODO: automated texture

        //TODO: QUADER
        cubeSides = new GLTafel[6];
        cubeSides[0] = new GLTafel(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing)-partSize/2, cubePosition.z()*(partSize+spacing), partSize, partSize);
        cubeSides[0].setzeAutodrehung(false);
        cubeSides[0].rotiere(90, 1, 0, 0, cubeSides[0].gibX(), cubeSides[0].gibY(), cubeSides[0].gibZ());
        cubeSides[0].setzeTextur("textures/Testtextur.png");
        cubeSides[1] = new GLTafel(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing)+partSize/2, cubePosition.z()*(partSize+spacing), partSize, partSize);
        cubeSides[1].setzeAutodrehung(false);
        cubeSides[1].rotiere(90, 1, 0, 0, cubeSides[1].gibX(), cubeSides[1].gibY(), cubeSides[0].gibZ());
        cubeSides[2] = new GLTafel(cubePosition.x()*(partSize+spacing)-partSize/2, cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing), partSize, partSize);
        cubeSides[2].setzeAutodrehung(false);
        cubeSides[2].rotiere(90, 0, 1, 0, cubeSides[2].gibX(), cubeSides[2].gibY(), cubeSides[0].gibZ());
        cubeSides[3] = new GLTafel(cubePosition.x()*(partSize+spacing)+partSize/2, cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing), partSize, partSize);
        cubeSides[3].setzeAutodrehung(false);
        cubeSides[3].rotiere(90, 0, 1, 0, cubeSides[3].gibX(), cubeSides[3].gibY(), cubeSides[0].gibZ());
        cubeSides[4] = new GLTafel(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing)-partSize/2, partSize, partSize);
        cubeSides[4].setzeAutodrehung(false);
        cubeSides[4].rotiere(0, 0, 1, 0, cubeSides[4].gibX(), cubeSides[4].gibY(), cubeSides[0].gibZ());
        cubeSides[5] = new GLTafel(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing)+partSize/2, partSize, partSize);
        cubeSides[5].setzeAutodrehung(false);
        cubeSides[5].rotiere(0, 0, 1, 0, cubeSides[5].gibX(), cubeSides[5].gibY(), cubeSides[0].gibZ());


        this.originalPosition = cubePosition;
        this.currentPosition = cubePosition;
    }
}
