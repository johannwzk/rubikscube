import GLOOP.*;

public class UI {
    public static final double SHIFT_LOCK_TIME = 250; // ms
    public static final double CAMERA_ROTATION_SPEED = 60; // degree/s

    // ENVIRONMENT

    // window
    private final GLKamera camera;

    // light sources
    private final GLLicht light;
    private final GLLicht light2;
    // sky
    private final GLHimmel sky;

// DISPLAYS
    // UI on the right side
    private final GLTafel ui;
    // shift marker in the top-left corner
    private final GLTafel shiftMarker;

// USER INPUT
    // keyboard
    private final GLTastatur keyboard;
    // when true, pressing shift will not do anything
    private boolean shiftLock;
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
        light = new GLLicht(10000, 10000, 10000);
        light2 = new GLLicht(-10000, -10000, -10000);
        sky = new GLHimmel(Texture.SKY_BLUE);

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
        keyboard = new GLTastatur();
        shiftLock = false;
        shiftTimeoutPassed = 0;

        // better camera angle
        camera.setzePosition(0, 200, -500);

        // create cube
        cube = new Cube(200, 4);
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
        if (keyboard.istGedrueckt('r')) {
            cube.rotate(Colour.RED, rotationModifier);
            rotationModifier = 1;
            shiftLock = false;
            shiftMarker.setzeTextur(Texture.BLACK);
        }
        else if (keyboard.istGedrueckt('o')) {
            cube.rotate(Colour.ORANGE, rotationModifier);
            rotationModifier = 1;
            shiftLock = false;
            shiftMarker.setzeTextur(Texture.BLACK);
        }
        else if (keyboard.istGedrueckt('w')) {
            cube.rotate(Colour.WHITE, rotationModifier);
            rotationModifier = 1;
            shiftLock = false;
            shiftMarker.setzeTextur(Texture.BLACK);
        }
        else if (keyboard.istGedrueckt('y')) {
            cube.rotate(Colour.YELLOW, rotationModifier);
            rotationModifier = 1;
            shiftLock = false;
            shiftMarker.setzeTextur(Texture.BLACK);
        }
        else if (keyboard.istGedrueckt('g')){
            cube.rotate(Colour.GREEN, rotationModifier);
            rotationModifier = 1;
            shiftLock = false;
            shiftMarker.setzeTextur(Texture.BLACK);
        }
        else if (keyboard.istGedrueckt('b')){
            cube.rotate(Colour.BLUE, rotationModifier);
            rotationModifier = 1;
            shiftLock = false;
            shiftMarker.setzeTextur(Texture.BLACK);
        }
    }

    // reset cube
    public void reset() {
        cube = new Cube(200, 4);
    }
}
