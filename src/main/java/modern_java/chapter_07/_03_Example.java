package modern_java.chapter_07;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class _03_Example {

    public static void main(String[] args) {
        final String SENTENCE =
                "Nel        mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        "ch la dritta via era smarrita  ";

        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        Stream<Character> characterStream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        System.out.println("Found " + countWords(characterStream.parallel()) + " words");

        Spliterator<Character> parallelCounter = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(parallelCounter, true);
        System.out.println("Found " + countWords(stream) + " words");

    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);

        return wordCounter.counter();
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;  // 공백 문자를 만나면, 지금까지 탐색한 문자를 문자로 간주하여 단어 수를 증가시킴.
                }

                lastSpace = false;
            }
        }

        return counter;
    }
}
