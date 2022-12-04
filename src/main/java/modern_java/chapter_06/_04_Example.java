package modern_java.chapter_06;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import modern_java.Dish;
import modern_java.Dish.Type;
import modern_java.Menu;

public class _04_Example {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;

        Map<Boolean, List<Dish>> partitionedMenu = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian));

        System.out.println("partitionedMenu = " + partitionedMenu);


        //TODO 1. 분할의 장점
        Map<Boolean, Map<Type, List<Dish>>> vegetarianByType = dishes.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::type)));

        System.out.println("vegetarianByType = " + vegetarianByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = dishes.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::calories)),
                                Optional::get)));

        System.out.println(
                "mostCaloricPartitionedByVegetarian = " + mostCaloricPartitionedByVegetarian);


        //TODO 2. 소수와 비소수로 분할하기
        Map<Boolean, List<Integer>> primesOrNot = partitionPrimes(2);
        List<Integer> primes = primesOrNot.get(true);
        System.out.println("primes = " + primes);
    }

    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrimeRoot(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(_04_Example::isPrimeRoot));
    }

}