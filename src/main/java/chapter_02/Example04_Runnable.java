package chapter_02;

public class Example04_Runnable {

    static class RunnableEx implements Runnable {

        @Override
        public void run() {
            System.out.println("하잉!!!!!!!");
        }
    }

    public static void main(String[] args) {
        Thread threadRunnable = new Thread(new RunnableEx());

        Thread threadAnonymous = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("하잉!!!!!!");
            }
        });

        Thread threadLambda = new Thread(() -> System.out.println("하잉!!!!!!"));
    }
}
