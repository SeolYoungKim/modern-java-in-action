package chapter_02;

import java.util.ArrayList;
import java.util.Comparator;

public class Example03_Comparator {

    public static void main(String[] args) {
        ArrayList<Apple> apples = new ArrayList<>();
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight() - o2.weight();
            }
        });
        apples.sort((apple1, apple2) -> apple1.weight() - apple2.weight());
        apples.sort(Comparator.comparingInt(Apple::weight));


    }
}
