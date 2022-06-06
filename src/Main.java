import GLOOP.*;

import java.util.concurrent.TimeUnit;

public class Main {
    public static final int MAX_CYCLES_PER_SECOND = 60; // 1/s



    public static void main(String[] args) {
        UI ui = new UI();

        while (true){
            ui.update();
            try { TimeUnit.MILLISECONDS.sleep(1000/ MAX_CYCLES_PER_SECOND); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
    }
}