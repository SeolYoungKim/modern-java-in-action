package modern_java.chapter_07;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Spliterator는 첫 번째 탐색 시점, 첫 번째 분할 시점, 또는 첫 번째 예상 크기 요청 시점에 요소의 소스를 바인딩 할 수 있다.
 * -> Lazy binding Spliterator!
 */
public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {  // 분석 대상 문자열로 Spliterator 생성
        this.string = string;
    }

    /**
     * 1. 문자열에서 현재 인덱스에 해당하는 문자를 Consumer에 제공
     * 2. 인덱스 증가
     * 3. 인수로 전달된 Consumer는 스트림을 탐색하면서 적용해야 하는 함수 집합이 작업을 처리할 수 있도록 소비한 문자를 전달하는 자바 내부 클래스임.
     * - 예제에서는 스트림을 탐색하면서 하나의 리듀싱 함수인 "WordCounter의 accumulate 메서드"만 적용함
     * - 새로운 커서 위치가 전체 문자열 길이보다 작으면 true를 반환하고, 이는 탐색해야 할 문자가 남아있음을 의미함
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        // 연산을 수행할 것이 있는지 계속 탐색
        action.accept(string.charAt(currentChar++));  // 현재 문자를 소비
        return currentChar < string.length();  // 소비할 문자가 남아있으면 true
    }

    /**
     * 반복될 자료구조를 분할하는 로직을 포함 -> 가장 중요한 메서드
     * - 실전의 애플리케이션에서는 너무 많은 태스크를 만들지 않도록 한계 값을 높게 설정해야 함.
     * - 분할 과정에서 남은 문자 수가 한계 값 이하 -> null 반환 (분할 중지)
     * - 분할이 필요한 상황 -> 파싱해야 할 문자열 청크의 중간 위치를 기준으로 분할
     * - 이 때, 단어 중간을 분할하지 않도록 빈 문자가 나올때 까지 분할 위치를 이동시킴.
     * - 분할 위치를 찾았다면, 새로운 Spliterator를 만듬
     * - 새로 만든 Spliterator는 현재 위치(currentChar)부터 분할된 위치까지의 문자를 탐색해야 함.
     */
    @Override
    public Spliterator<Character> trySplit() {  // 현재 탐색 중인 문자를 가리키는 인덱스를 이용해서 모든 문자를 반복 탐색
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;  // 파싱할 문자열을 순차 처리할 수 있을 만큼 충분히 작아졌음을 알리는 null
        }

        // 문자열의 중간을 분할 위치로 설정
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {

            // 다음 공백이 나올 때 까지 분할 위치를 뒤로 이동시킴
            if (Character.isWhitespace(string.charAt(splitPos))) {
                WordCounterSpliterator spliterator = new WordCounterSpliterator(
                        string.substring(currentChar, splitPos));  // 처음부터 분할 위치까지 문자열을 파싱할 새로운 객체 생성

                currentChar = splitPos;  // 해당 WordCounterSpliterator의 시작 위치를 분할 위치로 설정
                return spliterator;  // 공백을 찾았고 문자열을 분리했으므로 루프 종료
            }
        }

        return null;
    }

    /**
     * Spliterator가 파싱할 문자열 전체 길이와 현재 반복충인 위치의 차이
     */
    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    /**
     * 특성을 안내함
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
