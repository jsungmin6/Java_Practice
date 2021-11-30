package item42;

enum OperationOriginal {
    PLUS("+") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; }
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            System.out.println(num);
            System.out.println(symbol);
            testMethod();
            return x * y;
        }
    };
    public static final boolean num = false;
    public final String symbol;
    private final String test = "t";

    OperationOriginal(String symbol) { this.symbol = symbol; }

    @Override public String toString() { return symbol; }

    public abstract double apply(double x, double y);

    public void testMethod(){
        System.out.println("testMethod");
        System.out.println(test);
    }
}