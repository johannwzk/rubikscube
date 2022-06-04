public final class Color {
    public static final Color RED = new Color(ColorPosition.RED, ColorFactor.RED, CubePosition.RED_CENTER, CubePosition.RO_AXIS);
    public static final Color ORANGE = new Color(ColorPosition.ORANGE, ColorFactor.ORANGE, CubePosition.ORANGE_CENTER, CubePosition.RO_AXIS);
    public static final Color WHITE = new Color(ColorPosition.WHITE, ColorFactor.WHITE, CubePosition.WHITE_CENTER, CubePosition.WY_AXIS);
    public static final Color YELLOW = new Color(ColorPosition.YELLOW, ColorFactor.YELLOW, CubePosition.YELLOW_CENTER, CubePosition.WY_AXIS);
    public static final Color GREEN = new Color(ColorPosition.GREEN, ColorFactor.GREEN, CubePosition.GREEN_CENTER, CubePosition.GB_AXIS);
    public static final Color BLUE = new Color(ColorPosition.BLUE, ColorFactor.BLUE, CubePosition.BLUE_CENTER, CubePosition.GB_AXIS);

    public final int colorPosition;
    public final int colorFactor;
    public final CubePosition centerPosition;
    public final CubePosition axis;

    public Color(int colorPosition, int colorFactor, CubePosition centerPosition, CubePosition axis) {
        this.colorPosition = colorPosition;
        this.colorFactor = colorFactor;
        this.centerPosition = centerPosition;
        this.axis = axis;
    }
}
