package modern_java.chapter_10;

import static modern_java.chapter_10.NestedFunctionOrderBuilder.*;

public class _04_NestedFunctionPattern {
    public static void main(String[] args) {
        Order order = order("BigBank",
                buy(80,
                        stock("IBM", on("NYSE")), at(125.00)),
                sell(50,
                        stock("GOOGLE", on("NASDAQ")), at(375.00))
        );
    }
}
