import exceptions.CorruptedCubePositionException;

public class CubePosition {
    private int wy; // 1=w, -1=y, 0=nc
    private int ro; // 1=r, -1=o, 0=n
    private int gb; // 1=g, -1=b, 0=n

    public CubePosition(int wy, int ro, int gb) {
        if (wy < -1 || wy > 1) throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");
        else if (ro < -1 || ro > 1) throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");
        else if (gb < -1 || gb > 1) throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");
        this.wy = wy;
        this.gb = gb;
        this.ro = ro;
    }

    public int x() { return this.ro; }
    public int y() { return this.wy; }
    public int z() { return this.gb; }

    public void setPosition(int wy, int ro, int gb) {
        if (wy < -1 || wy > 1) throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");
        else if (ro < -1 || ro > 1) throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");
        else if (gb < -1 || gb > 1) throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");
        this.wy = wy;
        this.gb = gb;
        this.ro = ro;
    }

    public void setPosition(int position) {
        if (position % ColorFactor.WHITE == 0 && (position & ColorFactor.YELLOW) == 0) throw new IllegalArgumentException("Position cannot contain factors for white and yellow");
        if (position % ColorFactor.RED == 0 && (position & ColorFactor.ORANGE) == 0) throw new IllegalArgumentException("Position cannot contain factors for white and yellow");
        if (position % ColorFactor.GREEN == 0 && (position & ColorFactor.BLUE) == 0) throw new IllegalArgumentException("Position cannot contain factors for white and yellow");

        if (position % ColorFactor.WHITE == 0) this.wy = 1;
        else if (position % ColorFactor.YELLOW == 0) this.wy = -1;
        if (position % ColorFactor.RED == 0) this.ro = 1;
        else if (position % ColorFactor.ORANGE == 0) this.ro = -1;
        if (position % ColorFactor.GREEN == 0) this.gb = 1;
        else if (position % ColorFactor.BLUE == 0) this.gb = -1;
    }

    public int toInt() {
        int wyFactor;
        int roFactor;
        int gbFactor;

        if (this.wy == 1) wyFactor = ColorFactor.WHITE;
        else if (this.wy == -1) wyFactor = ColorFactor.YELLOW;
        else if (this.wy == 0) wyFactor = ColorFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");

        if (this.ro == 1) roFactor = ColorFactor.RED;
        else if (this.ro == -1) roFactor = ColorFactor.ORANGE;
        else if (this.ro == 0) roFactor = ColorFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");

        if (this.gb == 1) gbFactor = ColorFactor.GREEN;
        else if (this.gb == -1) gbFactor = ColorFactor.BLUE;
        else if (this.gb == 0) gbFactor = ColorFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");

        return wyFactor*roFactor*gbFactor;
    }
}
