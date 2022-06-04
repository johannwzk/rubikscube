import GLOOP.GLVektor;
import exceptions.CorruptedCubePositionException;

import java.util.Objects;

//TODO: Add rotation marker
public class CubePosition {
    public static final CubePosition RED_CENTER = new CubePosition(ColorPosition.RED, ColorPosition.NONE, ColorPosition.NONE);
    public static final CubePosition ORANGE_CENTER = new CubePosition(ColorPosition.ORANGE, ColorPosition.NONE, ColorPosition.NONE);
    public static final CubePosition WHITE_CENTER = new CubePosition(ColorPosition.NONE, ColorPosition.WHITE, ColorPosition.NONE);
    public static final CubePosition YELLOW_CENTER = new CubePosition(ColorPosition.NONE, ColorPosition.YELLOW, ColorPosition.NONE);
    public static final CubePosition GREEN_CENTER = new CubePosition(ColorPosition.NONE, ColorPosition.NONE, ColorPosition.GREEN);
    public static final CubePosition BLUE_CENTER = new CubePosition(ColorPosition.NONE, ColorPosition.NONE, ColorPosition.BLUE);
    public static final CubePosition RO_AXIS = ORANGE_CENTER;
    public static final CubePosition WY_AXIS = YELLOW_CENTER;
    public static final CubePosition GB_AXIS = BLUE_CENTER;



    private int wy;
    private int ro;
    private int gb;
    public CubePosition(int ro, int wy, int gb) {
        if (ro < ColorPosition.RED || ro > ColorPosition.ORANGE) throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");
        else if (wy < ColorPosition.WHITE || wy > ColorPosition.YELLOW) throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");
        else if (gb < ColorPosition.GREEN || gb > ColorPosition.BLUE) throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");
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
        CubePosition that = (CubePosition) o;
        return wy == that.wy && ro == that.ro && gb == that.gb;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wy, ro, gb);
    }

    public void setPosition(int ro, int wy, int gb) {
        if (ro < ColorPosition.RED || ro > ColorPosition.ORANGE) throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");
        else if (wy < ColorPosition.WHITE || wy > ColorPosition.YELLOW) throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");
        else if (gb < ColorPosition.GREEN || gb > ColorPosition.BLUE) throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");
        this.gb = gb;
        this.wy = wy;
        this.ro = ro;
    }

    public void setPosition(int position) {
        if (position % ColorFactor.RED == 0 && (position & ColorFactor.ORANGE) == 0) throw new IllegalArgumentException("Position cannot contain factors for white and yellow");
        if (position % ColorFactor.WHITE == 0 && (position & ColorFactor.YELLOW) == 0) throw new IllegalArgumentException("Position cannot contain factors for white and yellow");
        if (position % ColorFactor.GREEN == 0 && (position & ColorFactor.BLUE) == 0) throw new IllegalArgumentException("Position cannot contain factors for white and yellow");

        if (position % ColorFactor.RED == 0) this.ro = ColorPosition.RED;
        else if (position % ColorFactor.ORANGE == 0) this.ro = ColorPosition.ORANGE;
        if (position % ColorFactor.WHITE == 0) this.wy = ColorPosition.WHITE;
        else if (position % ColorFactor.YELLOW == 0) this.wy = ColorPosition.YELLOW;
        if (position % ColorFactor.GREEN == 0) this.gb = ColorPosition.GREEN;
        else if (position % ColorFactor.BLUE == 0) this.gb = ColorPosition.BLUE;
    }

    public int toInt() {
        int roFactor;
        int wyFactor;
        int gbFactor;

        if (this.ro == ColorPosition.RED) roFactor = ColorFactor.RED;
        else if (this.ro == ColorPosition.ORANGE) roFactor = ColorFactor.ORANGE;
        else if (this.ro == ColorPosition.NONE) roFactor = ColorFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");

        if (this.wy == ColorPosition.WHITE) wyFactor = ColorFactor.WHITE;
        else if (this.wy == ColorPosition.YELLOW) wyFactor = ColorFactor.YELLOW;
        else if (this.wy == ColorPosition.NONE) wyFactor = ColorFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");

        if (this.gb == ColorPosition.GREEN) gbFactor = ColorFactor.GREEN;
        else if (this.gb == ColorPosition.BLUE) gbFactor = ColorFactor.BLUE;
        else if (this.gb == ColorPosition.NONE) gbFactor = ColorFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");

        return roFactor*wyFactor*gbFactor;
    }

    public GLVektor toVector() {
        return new GLVektor(this.x(), this.y(), this.z());
    }

}
