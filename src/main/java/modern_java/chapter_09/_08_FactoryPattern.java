package modern_java.chapter_09;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 팩토리 패턴 - 인스턴스화 로직을 클라이언트에 노출하지 않고 객체를 만들 때 사용
 */
public class _08_FactoryPattern {
    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static void main(String[] args) {
        Product loan = ProductFactory.createProduct("loan");
        Product stock = ProductFactory.createProduct("stock");
        Product bond = ProductFactory.createProduct("bond");

        //TODO 람다 이용하기
        Product loanLambda = ProductFactory.createProduct("loan");
        Product stockLambda = ProductFactory.createProduct("stock");
        Product bondLambda = ProductFactory.createProduct("bond");
    }

    static class ProductFactory {
        public static Product createProductLambda(String name) {
            Supplier<Product> productSupplier = map.get(name);
            if (productSupplier != null) {
                return productSupplier.get();
            }

            throw new IllegalArgumentException();
        }

        public static Product createProduct(String name) {
            switch (name) {
                case "loan":
                    return new Loan();
                case "stock":
                    return new Stock();
                case "bond":
                    return new Bond();
                default:
                    throw new RuntimeException("No such Product " + name);
            }
        }
    }

    interface Product {
    }

    static class Loan implements Product {
    }

    static class Stock implements Product {
    }

    static class Bond implements Product {
    }


}
