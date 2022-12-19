package modern_java.chapter_19;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T>{
    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    public MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail()
                                .filter(n -> n % numbers.head() != 0))
        );
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ?
                this :
                p.test(head()) ?
                        new LazyList<>(head(), () -> tail().filter(p)) :
                        tail().filter(p);
    }
}
