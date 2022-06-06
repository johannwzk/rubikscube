import GLOOP.*;

public class UI {
    public static final double SHIFT_LOCK_TIME = 250; // ms
    public static final double CAMERA_ROTATION_SPEED = 60; // degree/s

    private final GLKamera camera;
    private int rotationModifier = 1;


    private final GLTafel ui;

    private final GLLicht light;
    private final GLLicht light2;
    private final GLHimmel sky;



    private Cube cube;

    private final GLTafel shiftMarker;
    private final GLTastatur keyboard;
    private GLMaus mouse;
    private boolean shiftLock;
    private int shiftLockTimeout;

    public UI(){
        camera = new GLKamera(1920, 1080);

        keyboard = new GLTastatur();
        shiftLock = false;
        shiftLockTimeout = 0;

        ui = new GLTafel(408, -0, 0,200, 500 );
        ui.setzeTextur(Texture.BLACK);
        ui.setzeKamerafixierung(true);
        ui.setzeAutodrehung(true);

        shiftMarker = new GLTafel(-465, 270, 0, 90, 30);
        shiftMarker.setzeTextur(Texture.BLACK);
        shiftMarker.setzeKamerafixierung(true);
        shiftMarker.setzeAutodrehung(true);

        camera.setzePosition(0, 200, -500);


        light = new GLLicht(10000, 10000, 10000);
        light2 = new GLLicht(-10000, -10000, -10000);
        sky = new GLHimmel(Texture.SKY_BLUE);


        cube = new Cube(200, 4);
    }

    public void update() {
        if (keyboard.oben()) camera.rotiere(1, -1, 0, 0, 0, 0, 0);
        if (keyboard.unten()) camera.rotiere(1, 1, 0, 0, 0, 0, 0);
        if (keyboard.rechts()) camera.rotiere(1, 0, 1, 0, 0, 0, 0);
        if (keyboard.links()) camera.rotiere(1, 0, -1, 0, 0, 0, 0);


        if (keyboard.shift() && !shiftLock) {
            rotationModifier = rotationModifier*(-1);
            shiftLock = true;
            if (rotationModifier == 1) shiftMarker.setzeTextur(Texture.BLACK);
            else if (rotationModifier == -1) shiftMarker.setzeTextur(Texture.SHIFT);
            shiftLockTimeout = 0;
        }

        if (shiftLockTimeout > SHIFT_LOCK_TIME/1000*Main.MAX_CYCLES_PER_SECOND) {
            shiftLock = false;
        }

        shiftLockTimeout++;

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

    public void reset() {
        cube = new Cube(200, 4);
    }
}
