package present.programmer.multithreading.memory.consistency;

/**
 * Regretfully, the experiment with 'volatile' keyword was unsuccessful — removing 'volatile' does not
 * change the application's execution (at least visually). Note, that missing 'volatile' does NOT mean that
 * compiler will definitely optimize away memory access which will lead to main thread not seeing the changes.
 */
public class VolatileExample {

    private static final int PERIOD = 100;
    private static volatile int temperature = 0;

    public static void main(String[] args) throws Exception {
        startGlobalHeating();
        monitorTemperature();
    }

    private static void startGlobalHeating() {
        new Thread(new Heating()).start();
    }

    private static void monitorTemperature() throws InterruptedException {
        while (true) {
            Thread.sleep(PERIOD);
            System.out.println("Today's temperature is " + temperature);
        }
    }

    private static class Heating implements Runnable {

        @Override
        public void run() {
            while (true) {
                waitFewMoments();
                temperature++;
            }
        }

        private void waitFewMoments() {
            try {
                Thread.sleep(PERIOD);
            }
            catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
