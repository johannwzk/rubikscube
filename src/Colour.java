public final class Colour {
    public static final Colour RED = new Colour(ColourPosition.RED, ColourFactor.RED, CubePartPosition.RED_CENTER);
    public static final Colour ORANGE = new Colour(ColourPosition.ORANGE, ColourFactor.ORANGE, CubePartPosition.ORANGE_CENTER);
    public static final Colour WHITE = new Colour(ColourPosition.WHITE, ColourFactor.WHITE, CubePartPosition.WHITE_CENTER);
    public static final Colour YELLOW = new Colour(ColourPosition.YELLOW, ColourFactor.YELLOW, CubePartPosition.YELLOW_CENTER);
    public static final Colour GREEN = new Colour(ColourPosition.GREEN, ColourFactor.GREEN, CubePartPosition.GREEN_CENTER);
    public static final Colour BLUE = new Colour(ColourPosition.BLUE, ColourFactor.BLUE, CubePartPosition.BLUE_CENTER);

    // position on colour axis (1 or -1)
    public final int colourPosition;
    // unique prime factor of each colour easy recognition of appearance of the color in a cube part position
    public final int colourFactor;
    // position of the centerpiece of the colour
    public final CubePartPosition centerPosition;

    public Colour(int colourPosition, int colourFactor, CubePartPosition centerPosition) {
        this.colourPosition = colourPosition;
        this.colourFactor = colourFactor;
        this.centerPosition = centerPosition;
    }

    public static Colour getColourFromFactor(int colourFactor) {
        if (colourFactor == ColourFactor.RED) return Colour.RED;
        if (colourFactor == ColourFactor.ORANGE) return Colour.ORANGE;
        if (colourFactor == ColourFactor.WHITE) return Colour.WHITE;
        if (colourFactor == ColourFactor.YELLOW) return Colour.YELLOW;
        if (colourFactor == ColourFactor.GREEN) return Colour.GREEN;
        if (colourFactor == ColourFactor.BLUE) return Colour.BLUE;
        else throw new IllegalArgumentException("colourFactor must be specific (either 2, 3, 5, 7, 11 or 13)");
    }
}
