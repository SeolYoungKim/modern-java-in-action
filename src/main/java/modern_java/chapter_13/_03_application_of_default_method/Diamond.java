package modern_java.chapter_13._03_application_of_default_method;

public interface Diamond {
    interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    interface B extends A {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    interface C extends A {
        void hello();
    }

    class D implements B, C {
        public static void main(String[] args) {
            new D().hello();
        }

        @Override
        public void hello() {
            B.super.hello();
        }
    }
}
