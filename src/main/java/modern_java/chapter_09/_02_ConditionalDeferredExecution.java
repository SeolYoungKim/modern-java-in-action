package modern_java.chapter_09;

import java.util.logging.Level;
import java.util.logging.Logger;

public class _02_ConditionalDeferredExecution {

    public static void main(String[] args) {
        //TODO 조건부 연기 실행

        Logger logger = Logger.getLogger("logger");
        logger.log(Level.FINE, () -> "Problem!!");  //TODO logger의 log 메서드는 Supplier를 인수로 받아, 실행 시점을 연기할 수 있다.
    }
}
