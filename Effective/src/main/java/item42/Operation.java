package item42;

import java.util.function.DoubleBinaryOperator;

import static item42.Main.b;

enum Operation {

    PLUS("+", (x, y) -> x + y),//컴파일 타임 때 만들어짐
    MINUS("-", (x, y) -> x - y),//컴파일 타임 때 만들어짐
    TIMES("*", (x, y) -> x * y),//컴파일 타임 때 만들어짐
    DIVIDE("/", (x, y) -> x / y);//컴파일 타임 때 만들어짐

    public final String symbol; // 런타임에 생성
    private final DoubleBinaryOperator op; // 런타임에 생성

    Operation(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override
    public String toString() { return symbol; }

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}

