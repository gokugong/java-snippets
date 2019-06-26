package enums;

enum TLight {
    // Each instance provides its implementation to abstract method
    RED(30) {
        public TLight next() {
            return GREEN;
        }
    },
    AMBER(10) {
        public TLight next() {
            return RED;
        }
    },
    GREEN(30) {
        public TLight next() {
            return AMBER;
        }
    };

    public abstract TLight next(); // An abstract method

    private final int seconds;     // Private variable

    TLight(int seconds) {          // Constructor
        this.seconds = seconds;
    }

    int getSeconds() {             // Getter
        return seconds;
    }

    @Override
    public String toString() {
        return this.name();
    }
}

