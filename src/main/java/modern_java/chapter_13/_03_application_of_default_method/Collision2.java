package modern_java.chapter_13._03_application_of_default_method;

public interface Collision2 {
    interface A {
        default Number getNumber() {
            return 10;
        }
    }

    interface B {
        default Integer getNumber() {  // 이름이 같으면 충돌 남
            return 42;
        }
    }

    class C implements A, B {  // 인터페이스 간에 상속관계가 없어 충돌이 발생한다. (구별할 기준이 없음!!)
        @Override
        public Integer getNumber() {
            return B.super.getNumber();
        }
    }

}
