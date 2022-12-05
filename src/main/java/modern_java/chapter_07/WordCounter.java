package modern_java.chapter_07;

public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        // 두 WordCounter의 conter 값을 더하고, 마지막 공백은 신경 안써도 됨.
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int counter() {
        return counter;
    }

}
