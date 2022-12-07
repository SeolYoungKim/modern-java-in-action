package modern_java.chapter_09;

/**
 * 전략 패턴 - 한 유형의 알고리즘을 보유한 상태에서 런타임에 적절한 알고리즘을 선택하는 기법 -
 */
public class _04_StrategyPattern {
    public static void main(String[] args) {
        //TODO 원래는 구현체를 만들어줘야 헀던 전략 패턴이..
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("iiii");

        //TODO 람다를 이용하여 쉽게 적용할 수 있게 되었다.
        Validator numericValidatorLambda = new Validator((str) -> str.matches("\\d+"));
        Validator lowerCaseValidatorLambda = new Validator((str) -> str.matches("[a-z]+"));
    }

    @FunctionalInterface
    public interface ValidationStrategy {
        boolean execute(String s);  // String을 받아서 boolean으로 반환해주는 함수 디스크립터 (Predicate와 동일)
    }

    public static class IsAllLowerCase implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    public static class IsNumeric implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    public static class Validator {
        private final ValidationStrategy validationStrategy;

        public Validator(ValidationStrategy validationStrategy) {
            this.validationStrategy = validationStrategy;
        }

        public boolean validate(String s) {
            return validationStrategy.execute(s);
        }
    }

}
