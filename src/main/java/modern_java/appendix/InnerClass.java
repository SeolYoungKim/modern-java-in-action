package modern_java.appendix;

import java.util.function.Function;

public class InnerClass {
    Function<Object, String> f = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            return o.toString();
        }
    };

    Function<Object, String> ff = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            return o.toString();
        }
    };

    Function<Object, String> f2 = o -> o.toString();

    Function<Object, String> f3 = Object::toString;
}
