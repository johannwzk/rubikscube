import GLOOP.GLVektor;
import exceptions.CorruptedCubePositionException;

import java.util.Objects;

//TODO: Add rotation marker
public class CubePartPosition {
    public static final CubePartPosition RED_CENTER = new CubePartPosition(ColourPosition.RED, ColourPosition.NONE, ColourPosition.NONE);
    public static final CubePartPosition ORANGE_CENTER = new CubePartPosition(ColourPosition.ORANGE, ColourPosition.NONE, ColourPosition.NONE);
    public static final CubePartPosition WHITE_CENTER = new CubePartPosition(ColourPosition.NONE, ColourPosition.WHITE, ColourPosition.NONE);
    public static final CubePartPosition YELLOW_CENTER = new CubePartPosition(ColourPosition.NONE, ColourPosition.YELLOW, ColourPosition.NONE);
    public static final CubePartPosition GREEN_CENTER = new CubePartPosition(ColourPosition.NONE, ColourPosition.NONE, ColourPosition.GREEN);
    public static final CubePartPosition BLUE_CENTER = new CubePartPosition(ColourPosition.NONE, ColourPosition.NONE, ColourPosition.BLUE);
    public static final CubePartPosition RO_AXIS = ORANGE_CENTER;
    public static final CubePartPosition WY_AXIS = YELLOW_CENTER;
    public static final CubePartPosition GB_AXIS = BLUE_CENTER;



    private int wy;
    private int ro;
    private int gb;
    public CubePartPosition(int ro, int wy, int gb) {
        if (ro < ColourPosition.RED || ro > ColourPosition.ORANGE) throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");
        else if (wy < ColourPosition.WHITE || wy > ColourPosition.YELLOW) throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");
        else if (gb < ColourPosition.GREEN || gb > ColourPosition.BLUE) throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");
        this.gb = gb;
        this.wy = wy;
        this.ro = ro;
    }

    public int x() { return this.ro; }
    public int ro() { return this.ro; }
    public int y() { return this.wy; }
    public int wy() { return this.wy; }
    public int z() { return this.gb; }
    public int gb() { return this.gb; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubePartPosition that = (CubePartPosition) o;
        return wy == that.wy && ro == that.ro && gb == that.gb;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wy, ro, gb);
    }

    public void setPosition(int ro, int wy, int gb) {
        if (ro < ColourPosition.RED || ro > ColourPosition.ORANGE) throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");
        else if (wy < ColourPosition.WHITE || wy > ColourPosition.YELLOW) throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");
        else if (gb < ColourPosition.GREEN || gb > ColourPosition.BLUE) throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");
        this.gb = gb;
        this.wy = wy;
        this.ro = ro;
    }

    public void setPosition(int position) {
        if (position % ColourFactor.RED == 0 && (position & ColourFactor.ORANGE) == 0) throw new IllegalArgumentException("Position cannot contain factors for white and yellow");
        if (position % ColourFactor.WHITE == 0 && (position & ColourFactor.YELLOW) == 0) throw new IllegalArgumentException("Position cannot contain factors for white and yellow");
        if (position % ColourFactor.GREEN == 0 && (position & ColourFactor.BLUE) == 0) throw new IllegalArgumentException("Position cannot contain factors for white and yellow");

        if (position % ColourFactor.RED == 0) this.ro = ColourPosition.RED;
        else if (position % ColourFactor.ORANGE == 0) this.ro = ColourPosition.ORANGE;
        if (position % ColourFactor.WHITE == 0) this.wy = ColourPosition.WHITE;
        else if (position % ColourFactor.YELLOW == 0) this.wy = ColourPosition.YELLOW;
        if (position % ColourFactor.GREEN == 0) this.gb = ColourPosition.GREEN;
        else if (position % ColourFactor.BLUE == 0) this.gb = ColourPosition.BLUE;
    }

    public int toInt() {
        int roFactor;
        int wyFactor;
        int gbFactor;

        if (this.ro == ColourPosition.RED) roFactor = ColourFactor.RED;
        else if (this.ro == ColourPosition.ORANGE) roFactor = ColourFactor.ORANGE;
        else if (this.ro == ColourPosition.NONE) roFactor = ColourFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");

        if (this.wy == ColourPosition.WHITE) wyFactor = ColourFactor.WHITE;
        else if (this.wy == ColourPosition.YELLOW) wyFactor = ColourFactor.YELLOW;
        else if (this.wy == ColourPosition.NONE) wyFactor = ColourFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");

        if (this.gb == ColourPosition.GREEN) gbFactor = ColourFactor.GREEN;
        else if (this.gb == ColourPosition.BLUE) gbFactor = ColourFactor.BLUE;
        else if (this.gb == ColourPosition.NONE) gbFactor = ColourFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");

        return roFactor*wyFactor*gbFactor;
    }

    public GLVektor toVector() {
        return new GLVektor(this.x(), this.y(), this.z());
    }

}
