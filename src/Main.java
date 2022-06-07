import GLOOP.*;

import java.util.concurrent.TimeUnit;

public class Main {
    // Maximum and also (i no rotating event is happening) approximately the number of "ticks" per second
    public static final int MAX_CYCLES_PER_SECOND = 60; // 1/s
    public static final UI ui = new UI();

    public static void main(String[] args) {


        while (true){
            ui.update();
            try { TimeUnit.MILLISECONDS.sleep(1000/ MAX_CYCLES_PER_SECOND); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
    }
}