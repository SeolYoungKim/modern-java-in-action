package chapter_03;

public class Pet {

    private final int age;

    public Pet(int age) {
        this.age = age;
    }

    public int age() {
        return age;
    }

    static class Dog extends Pet {
        public Dog(int age) {
            super(age);
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "age=" + age() +
                    '}';
        }
    }

    static class Cat extends Pet {
        public Cat(int age) {
            super(age);
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "age=" + age() +
                    '}';
        }
    }
}
