package modern_java.chapter_19;

public class Empty<T> implements MyList<T> {
    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }
}
