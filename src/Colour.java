public final class Colour {
    public static final Colour RED = new Colour(ColourPosition.RED, ColourFactor.RED, CubePartPosition.RED_CENTER, CubePartPosition.RO_AXIS);
    public static final Colour ORANGE = new Colour(ColourPosition.ORANGE, ColourFactor.ORANGE, CubePartPosition.ORANGE_CENTER, CubePartPosition.RO_AXIS);
    public static final Colour WHITE = new Colour(ColourPosition.WHITE, ColourFactor.WHITE, CubePartPosition.WHITE_CENTER, CubePartPosition.WY_AXIS);
    public static final Colour YELLOW = new Colour(ColourPosition.YELLOW, ColourFactor.YELLOW, CubePartPosition.YELLOW_CENTER, CubePartPosition.WY_AXIS);
    public static final Colour GREEN = new Colour(ColourPosition.GREEN, ColourFactor.GREEN, CubePartPosition.GREEN_CENTER, CubePartPosition.GB_AXIS);
    public static final Colour BLUE = new Colour(ColourPosition.BLUE, ColourFactor.BLUE, CubePartPosition.BLUE_CENTER, CubePartPosition.GB_AXIS);

    public final int colourPosition;
    public final int colourFactor;
    public final CubePartPosition centerPosition;
    public final CubePartPosition axis;

    public Colour(int colourPosition, int colourFactor, CubePartPosition centerPosition, CubePartPosition axis) {
        this.colourPosition = colourPosition;
        this.colourFactor = colourFactor;
        this.centerPosition = centerPosition;
        this.axis = axis;
    }
}
