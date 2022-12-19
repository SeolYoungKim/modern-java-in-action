package modern_java.chapter_19;

public class MyLinkedList<T> implements MyList<T> {
    private final T head;
    private final MyList<T> tail;

    public MyLinkedList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return null;
    }

    @Override
    public MyList<T> tail() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> integerMyLinkedList = new MyLinkedList<>(5,
                new MyLinkedList<>(10, new Empty<>()));
    }
}
