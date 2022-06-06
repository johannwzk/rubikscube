import GLOOP.*;

public class UI {
    public static final double SHIFT_LOCK_TIME = 250; // ms
    public static final double CAMERA_ROTATION_SPEED = 60; // degree/s

    // ENVIRONMENT

    // window
    private final GLKamera camera;
    private int rotationModifier = 1;
    private final GLTafel ui;


    // light sources
    private final GLLicht light;
    private final GLLicht light2;
    // sky
    private final GLHimmel sky;

// DISPLAYS
    // UI on the right side
    private final GLTafel ui;
    // shift marker in the top-left corner


    private Cube cube;
    private final GLTafel shiftMarker;

// USER INPUT
    // keyboard
    private final GLTastatur keyboard;
    private GLMaus mouse;
    private boolean shiftLock;
    private int shiftLockTimeout;
    private int[][] positions;
    private int[] keys;
    // time shift lock is active after pressing shift -> no update of rotation modifier every cycle
    private int shiftTimeoutPassed;
    // modifies the rotation of cube sides clockwise/counterclockwise (1 = clockwise, -1 = counterclockwise)
    private int rotationModifier = 1;
    // mouse
    private GLMaus mouse;

    // cube
    private Cube cube;

    public UI(){
        // environment
        camera = new GLKamera(1920, 1080);

        // UI
        ui = new GLTafel(408, -0, 0,200, 500 );
        ui.setzeTextur(Texture.BLACK);
        ui.setzeKamerafixierung(true);
        ui.setzeAutodrehung(true);

        // shift marker
        shiftMarker = new GLTafel(-465, 270, 0, 90, 30);
        shiftMarker.setzeTextur(Texture.BLACK);
        shiftMarker.setzeKamerafixierung(true);
        shiftMarker.setzeAutodrehung(true);

        // controls
        mouse = new GLMaus();
        keyboard = new GLTastatur();
        shiftLock = false;
        shiftTimeoutPassed = 0;

        // better camera angle
        camera.setzePosition(0, 200, -500);

        light = new GLLicht(10000, 10000, 10000);
        light2 = new GLLicht(-10000, -10000, -10000);
        sky = new GLHimmel(Texture.SKY_BLUE);

        // create cube
        cube = new Cube(200, 4);

        //Positions of buttons. posiotions[x][0] = begin of button on x axis, positions[x][2] = begin of button on y axis; positions[x][3] = end of button on x axis, positions[x][4] = end of button on y axis.
        positions = new int[][]{{1547, 90, 1665, 123}, //Reset
                {1752, 89, 1851, 123}, //Quit
                {1554, 217, 1646, 309}, //Y
                {1672, 220, 1763, 307}, //W
                {1786, 218, 1880, 308}, //O
                {1554, 330, 1644, 423}, //R
                {1672, 330, 1763, 423}, //G
                {1786, 330, 1880, 423}, //B
                {1671, 524, 1761, 611}, //UP
                {1553, 638, 1645, 726}, //LEFT
                {1672, 638, 1763, 726}, //DOWN
                {1786, 638, 1880, 726}, //RIGHT
                {1555, 763, 1878, 824}, //Shuffle
                {1553, 847, 1882, 914}, //Quicksolve
                {1555, 932, 1880, 995} //Bogosolve
        };
    }

    public void update() {
        // move camera with up, down, right, left
        if (keyboard.oben()) camera.rotiere(1, -1, 0, 0, 0, 0, 0);
        if (keyboard.unten()) camera.rotiere(1, 1, 0, 0, 0, 0, 0);
        if (keyboard.rechts()) camera.rotiere(1, 0, 1, 0, 0, 0, 0);
        if (keyboard.links()) camera.rotiere(1, 0, -1, 0, 0, 0, 0);

        // if shift is pressed, change rotation modifier for cube rotations, update shift marker, reset shift timeout
        if (keyboard.shift() && !shiftLock) {
            rotationModifier = rotationModifier*(-1);
            shiftLock = true;
            if (rotationModifier == 1) shiftMarker.setzeTextur(Texture.BLACK);
            else if (rotationModifier == -1) shiftMarker.setzeTextur(Texture.SHIFT);
            shiftTimeoutPassed = 0;
        }

        // if shift timeout passed, unlock shift
        if (shiftTimeoutPassed > SHIFT_LOCK_TIME) shiftLock = false;

        // add passed milliseconds in cycle to shiftTimeoutPassed
        shiftTimeoutPassed += (1000/Main.MAX_CYCLES_PER_SECOND);
        // if key with first letter of colour is pressed, rotate the side of the color with rotation modifier, unlock shift
        if (keyboard.istGedrueckt('r')) rotateCube(Colour.RED, rotationModifier);
        else if (keyboard.istGedrueckt('o')) rotateCube(Colour.ORANGE, rotationModifier);
        else if (keyboard.istGedrueckt('w')) rotateCube(Colour.WHITE, rotationModifier);
        else if (keyboard.istGedrueckt('y')) rotateCube(Colour.YELLOW, rotationModifier);
        else if (keyboard.istGedrueckt('g'))rotateCube(Colour.GREEN, rotationModifier);
        else if (keyboard.istGedrueckt('b'))rotateCube(Colour.BLUE, rotationModifier);
        else if (keyboard.esc())System.exit(0);
        else if (keyboard.backspace()) reset();
        else if (keyboard.istGedrueckt('s'))cube.shuffle();
        else if (keyboard.istGedrueckt('l')) cube.bogosolve();
        else if (keyboard.istGedrueckt('h')) cube.solve();

        if(mouse.gedruecktLinks()){
            System.out.println("X: " + mouse.gibX() + " Y: " + mouse.gibY());
            int button = buttonPressed(positions, mouse.gibX(), mouse.gibY());
            System.out.println(button);
            if (button == 8) camera.rotiere(1, -1, 0, 0, 0, 0, 0);
            else if (button == 10) camera.rotiere(1, 1, 0, 0, 0, 0, 0);
            else if (button == 11) camera.rotiere(1, 0, 1, 0, 0, 0, 0);
            else if (button == 9)camera.rotiere(1, 0, -1, 0, 0, 0, 0);
            else if (button == 5) rotateCube(Colour.RED, rotationModifier);
            else if (button == 4) rotateCube(Colour.ORANGE, rotationModifier);
            else if (button == 3) rotateCube(Colour.WHITE, rotationModifier);
            else if (button == 2) rotateCube(Colour.YELLOW, rotationModifier);
            else if (button == 6)rotateCube(Colour.GREEN, rotationModifier);
            else if (button == 7)rotateCube(Colour.BLUE, rotationModifier);
            else if (button == 1)System.exit(0);
            else if (button == 0) reset();
            else if (button == 12)cube.shuffle();
            else if (button == 14) cube.bogosolve();
            else if (button == 13) cube.solve();
        }
        if(cube.isSolved())victory();
    }

    public void rotateCube(Colour colour, int modifier){
        cube.rotate(colour, modifier);
        rotationModifier = 1;
        shiftLock = false;
        shiftMarker.setzeTextur(Texture.BLACK);
    }

    // reset cube
    public void reset() {
        cube = new Cube(200, 4);
    }

    //Checks which button is pressed
    public int buttonPressed(int[][] positions, int x, int y){
        for(int i = 0; i < 15; i++)if(x > positions[i][0] && x < positions[i][2] && y > positions[i][1] && y < positions[i][3]) return i;
        return -1;
    }
    //TODO Victory amimation if cube is solved with display how long it took to solve it
    public void victory(){

    }
}
