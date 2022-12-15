package modern_java.chapter_15;

import static modern_java.chapter_15._03_Example2.ReactiveApi.f;
import static modern_java.chapter_15._03_Example2.ReactiveApi.g;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.IntConsumer;
import modern_java.chapter_15._03_Example.Result;

public class _03_Example2 {
    static abstract class FutureApi {
        abstract Future<Integer> f(int x);
        abstract Future<Integer> g(int x);

        void printResult(int x) throws ExecutionException, InterruptedException {
            Future<Integer> y = f(x);  // 호출 즉시 태스크를 포함하는 Future 반환
            Future<Integer> z = g(x);  // 호출 즉시 태스크를 포함하는 Future 반환

            System.out.println(y.get() + z.get());
        }
    }


    static class ReactiveApi {
        static void f(int x, IntConsumer dealWithResult) {
            System.out.println("inF : " + x);
            dealWithResult.accept(x);
        }

        static void g(int x, IntConsumer dealWithResult) {
            System.out.println("inG : " + x);
            dealWithResult.accept(x);
        }
    }

    public static void main(String[] args) {
        int x = 1337;
        Result result = new Result();

        f(x, (int y) -> {
            result.setLeft(y);
            System.out.println("sum(F) : " + (result.left() + result.right()));
        });

        g(x, (int z) -> {
            result.setRight(z);
            System.out.println("sum(G) : " + (result.left() + result.right()));  // println이 호출되기 전에 계산될 수도?
        });


    }
}

