package modern_java.chapter_13._03_application_of_default_method;

public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }

    interface B extends A { // 인터페이스끼리 대치되는 경우, 결과적으로 서브 인터페이스인 B가 이긴다!
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    class D implements A {  // 클래스에 충돌하는 메서드가 없으므로 A의 메서드가 선택된다.
    }

    class E implements A {  // E는 클래스이므로 E가 이긴다
        @Override
        public void hello() {
            System.out.println("Hello from E");
        }
    }


    class C extends E implements B {

        public static void main(String[] args) {
            new C().hello();
        }

    }

}
