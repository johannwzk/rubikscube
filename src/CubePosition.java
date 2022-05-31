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

    public int toInt() {
        int wyfactor;
        int rofactor;
        int gbfactor;

        if (wy == 1) wyfactor = ColorFactor.WHITE;
        else if (wy == -1) wyfactor = ColorFactor.YELLOW;
        else if (wy == 0) wyfactor = ColorFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'wy' corrupted: " + wy + " (should be either -1, 0 or 1)");

        if (ro == 1) rofactor = ColorFactor.RED;
        else if (ro == -1) rofactor = ColorFactor.ORANGE;
        else if (ro == 0) rofactor = ColorFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'ro' corrupted: " + ro + " (should be either -1, 0 or 1)");

        if (gb == 1) gbfactor = ColorFactor.GREEN;
        else if (gb == -1) gbfactor = ColorFactor.BLUE;
        else if (gb == 0) gbfactor = ColorFactor.NONE;
        else throw new CorruptedCubePositionException("Cube position 'gb' corrupted: " + gb + " (should be either -1, 0 or 1)");

        return wyfactor*rofactor*gbfactor;
    }
}
