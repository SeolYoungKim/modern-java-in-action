package modern_java.chapter_09;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 템플릿 메서드 패턴
 * - 알고리즘의 개요를 제시한 후, 알고리즘 일부를 고칠 수 있는 유연함을 제공해야 할 때 사용
 * - "이 알고리즘을 사용하고 싶은데, 그대로는 안되고 조금 고쳐야 하는"상황에 적합
 */
public class _05_TemplateMethodPattern {
    public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello"));
    }

    //TODO 람다 방식 : 클래스를 이거 하나만 구현하면 됨
    static class OnlineBankingLambda {
        public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
            Customer c = Database.getCustomerWithId(id);
            makeCustomerHappy.accept(c);
        }
    }

    //TODO 기존 방식 : 해당 클래스를 상속 받아, 추상 메서드를 구현한 클래스를 만들어야 함
    static abstract class OnlineBanking {  //TODO 은행의 각 지점은 makeCustomerHappy가 원하는 동작을 수행하도록 할 수 있따.
        public void processCustomer(int id) {
            Customer c = Database.getCustomerWithId(id);
            makeCustomerHappy(c);
        }

        abstract void makeCustomerHappy(Customer c);
    }

    static class Customer {
        private final int id;

        public Customer(int id) {
            this.id = id;
        }
    }

    static class Database {
        public static Map<Integer, Customer> map = new HashMap<>();

        public static Customer getCustomerWithId(int id) {
            return map.get(id);
        }
    }

}
