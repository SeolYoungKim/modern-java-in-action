package modern_java.appendix;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user = new User(2);
        int number = 1;
        List<Integer> numbers = new LinkedList<>();
    }

    static class User {
        private int num;

        public User(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}
