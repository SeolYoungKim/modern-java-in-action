package modern_java.chapter_19;

import java.util.function.DoubleUnaryOperator;

public class Currying {
    public static void main(String[] args) {
        DoubleUnaryOperator doubleUnaryOperator = curriedConverter(10.0, 20.0);
        double result = doubleUnaryOperator.applyAsDouble(2.0);
        System.out.println(result);

        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);
    }

    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }
}
