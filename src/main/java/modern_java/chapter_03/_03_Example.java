package modern_java.chapter_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _03_Example {

    public static void main(String[] args) throws IOException {
        // BufferedReader가 process()메서드에 전달됐을 때, 어떻게 행동할 것인지를 정의!
        String strForLambda = processFile((BufferedReader br) -> (br.readLine() + br.readLine()));
        System.out.println(strForLambda);

        String strForAbstractMethod = processFile(new BufferedReaderProcessor() {
            @Override
            public String process(BufferedReader br) throws IOException {
                return br.readLine() + br.readLine();
            }
        });
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            return p.process(br);  // process 호출하여 BufferedReader 객체 처리!
        }
    }
}
