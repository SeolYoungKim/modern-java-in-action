package modern_java.chapter_03;

public class _07_Quiz {

    // 인수가 세개인 생성자의 참조는 어떻게 사용할까?

    static class Triple {
        private final int one;
        private final int two;
        private final int three;

        public Triple(int one, int two, int three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "one=" + one +
                    ", two=" + two +
                    ", three=" + three +
                    '}';
        }
    }

    @FunctionalInterface
    public interface TripleParam<T, U, V, R> {
        R apply(T t, U u, V v);
    }


    public static void main(String[] args) {
        TripleParam<Integer, Integer, Integer, Triple> tripleParam = Triple::new;
        Triple triple = tripleParam.apply(1, 2, 3);
        System.out.println(triple);
    }
}
