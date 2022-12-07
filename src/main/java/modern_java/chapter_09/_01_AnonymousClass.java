package modern_java.chapter_09;

/**
 * - 익명 클래스 -> 람다 표현식
 *   - 모든 익명 클래스를 바꿀 수 있는 것은 아님
 *   - 익명 클래스의 `this`와 `super`는 람다 표현식에서 다른 의미를 가짐
 *     - 익명 클래스의 `this` : 자기 자신
 *     - 람다 표현식의 `this` : 람다를 감싸는 클래스
 *   - 익명 클래스는 감싸고 있는 클래스의 변수를 가릴 수 있음(섀도 변수)
 *   - 람다 표현식은 익명 클래스보다 모호하다.
 */
public class _01_AnonymousClass {
    public static void main(String[] args) {
        int a = 10;
        Runnable r1 = () -> {
//            int a = 2;  // 바깥 클래스와 중복 선언 안됨!! scope가 겹침
            int b = 2;
            System.out.println(a);
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                int a = 2;  // 바깥 클래스와 중복 선언 가능. 왜냐면 scope가 익명클래스 내로 한정 됨 .
                System.out.println(2);
            }
        };


        //TODO 람다 표현식의 모호함
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("hi");
            }
        });

//        doSomething(() -> System.out.println("hi"));  // 모호하여 호출되지 않는다. 아래와 같이 호출해야 한다.
        doSomething((Task) () -> System.out.println("hi"));
        doSomething((Runnable) () -> System.out.println("hi"));
    }

    public static void doSomething(Runnable r) {
        r.run();
    }

    public static void doSomething(Task a) {
        a.execute();
    }

    interface Task {
        public void execute();
    }

}
