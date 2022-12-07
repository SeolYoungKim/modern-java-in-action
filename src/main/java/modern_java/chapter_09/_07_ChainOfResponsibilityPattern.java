package modern_java.chapter_09;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 의무 체인 패턴
 * - 작업 처리 객체의 체인을 만들 때는 의무 체인 패턴을 이용한다.
 * - 한 객체가 어떤 작업을 처리한 다음 다른 객체로 결과를 전달하고, 다른 객체도 해야 할 작업을 처리한 다음, 또 다른 객체로 전달하는 식이다.
 * - 다음으로 처리할 객체 정보를 유지하는 필드를 포함하는 작업 처리 추상 클래스로 의무 체인 패턴을 구성함
 * - 작업 처리 객체가 자신의 작업을 끝내면 -> 다음 작업 처리 객체로 결과 전달
 */
public class _07_ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        HeaderTextProcessing p1 = new HeaderTextProcessing();
        SpellCheckerProcessing p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);

        //TODO 람다 표현식 사용해보기 : 함수 andThen 체인으로 의무 체인 패턴 구현
        UnaryOperator<String> func1 = (String text) -> "From Raoul, Mario and Alan : " + text;
        UnaryOperator<String> func2 = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> combinationFunc = func2.andThen(func1);

        String text = "Aren't labdas really sexy?!!";
        String resultOfLambda = combinationFunc.apply(text);

        System.out.println(resultOfLambda);

    }

    static abstract class ProcessingObject<T> {
        protected ProcessingObject<T> successor;

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);  // 해당 ProcessingObject의 handleWork를 먼저 수행.
            if (successor != null) {
                return successor.handle(r);  // successor의 handle을 내부에서 호출하여 먼저 수행함. (예시에서는 spell check)
            }

            return r;  // 다른 successor의 handle이 끝나면, 호출한 ProcessingObject의 메서드가 완료된다.
        }

        abstract protected T handleWork(T text);
    }

    static class HeaderTextProcessing extends ProcessingObject<String> {
        @Override
        protected String handleWork(String text) {
            return "From Raoul, Mario and Alan : " + text;
        }
    }

    static class SpellCheckerProcessing extends ProcessingObject<String> {
        @Override
        protected String handleWork(String text) {
            return text.replaceAll("labda", "lambda");
        }

    }


}
