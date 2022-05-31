import GLOOP.GLTextur;
import GLOOP.GLVektor;
import GLOOP.GLWuerfel;

public class CubePart extends GLWuerfel{
    public final CubePosition originalPosition;
    public CubePosition currentPosition;

    public CubePart(CubePosition cubePosition, double partSize, double spacing) {
        //TODO: automated texture
        super(cubePosition.x()*(partSize+spacing), cubePosition.y()*(partSize+spacing), cubePosition.z()*(partSize+spacing), partSize);
        this.originalPosition = cubePosition;
        this.currentPosition = cubePosition;
    }
}
