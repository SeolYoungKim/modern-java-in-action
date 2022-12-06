package modern_java.chapter_08;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class _03_Example {

    public static void main(String[] args) {
        //TODO 1. 최댓값 찾기
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        long parallelismThreshold = 1;

        Optional<Long> maxValue = Optional.ofNullable(
                map.reduceValues(parallelismThreshold, Long::max));

        int size = map.size();
        long mappingCount = map.mappingCount();
    }
}
