package present.programmer.multithreading.memory.consistency;

class LeakingConstructor {

    private final int y;
    private static LeakingConstructor objectBeingConstructed;

    private LeakingConstructor() {
        objectBeingConstructed = this;
        waitSeconds(5);
        this.y = 3;
    }

    public static void main(final String[] args) {
        new Thread(LeakingConstructor::new).start();
        waitForConstructorToStartExecuting();
        monitorObjectBeingConstructed();
    }

    // Auxiliary Methods

    private static void waitForConstructorToStartExecuting() {
        while (objectBeingConstructed == null) {
            Thread.yield();
        }
    }

    private static void monitorObjectBeingConstructed() {
        do {
            waitSeconds(1);
            System.out.println("objectBeingConstructed.y == " + objectBeingConstructed.y);
        } while (objectBeingConstructed.y == 0);
    }

    private static void waitSeconds(final int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
