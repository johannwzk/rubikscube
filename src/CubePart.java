import GLOOP.GLTextur;
import GLOOP.GLVektor;
import GLOOP.GLWuerfel;

public class CubePart extends GLWuerfel{
    public CubePosition cubePosition;

    public CubePart(double pX, double pY, double pZ, double pSeitenlaenge, GLTextur pT) {
        super(pX, pY, pZ, pSeitenlaenge, pT);
    }

    public CubePart(double pX, double pY, double pZ, double pSeitenlaenge, String pT) {
        super(pX, pY, pZ, pSeitenlaenge, pT);
    }

    public CubePart(double pX, double pY, double pZ, double pSeitenlaenge) {
        super(pX, pY, pZ, pSeitenlaenge);
    }

    public CubePart(GLVektor pPosition, double pSeitenlaenge) {
        super(pPosition, pSeitenlaenge);
    }

    public CubePart(GLVektor pPosition, double pSeitenlaenge, GLTextur pT) {
        super(pPosition, pSeitenlaenge, pT);
    }

    public CubePart(GLVektor pPosition, double pSeitenlaenge, String pT) {
        super(pPosition, pSeitenlaenge, pT);
    }
}
