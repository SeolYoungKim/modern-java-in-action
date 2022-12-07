package modern_java.chapter_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class _03_ExecuteAround {
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            return p.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        //TODO 매번 같은 준비, 종료 과정을 반복적으로 수행하는 코드를 람다로 변환
        processFile(BufferedReader::readLine);
        processFile((BufferedReader b) -> b.readLine() + b.readLine());

        intToString(String::valueOf, 10);
        stringToInt(Integer::valueOf, "12345");
    }

    public static String intToString(Function<Integer, String> func, Integer i) {
        return func.apply(i);
    }

    public static Integer stringToInt(Function<String, Integer> func, String str) {
        return func.apply(str);
    }
}
