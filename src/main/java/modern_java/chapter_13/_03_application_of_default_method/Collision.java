package modern_java.chapter_13._03_application_of_default_method;

public interface Collision {
    interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    interface B {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    class C implements A, B {  // 인터페이스 간에 상속관계가 없어 충돌이 발생한다. (구별할 기준이 없음!!)
        @Override
        public void hello() {  // 그래서 직접 구현체를 선택 해줘야 함
            A.super.hello();  // 명시적으로 A를 선택. (자바 8에서 제공하는 새로운 문법. X.super.m(...) : X는 호출하려는 메서드의 인터페이스다
        }
    }

}
