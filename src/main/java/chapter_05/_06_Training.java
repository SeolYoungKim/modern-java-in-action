package chapter_05;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class _06_Training {

    static final Trader raoul = new Trader("Raoul", "Cambridge");
    static final Trader mario = new Trader("Mario", "Milan");
    static final Trader alan = new Trader("Alan", "Cambridge");
    static final Trader brian = new Trader("Brian", "Cambridge");

    static final List<Transaction> transactions = List.of(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        //TODO 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리
        List<Transaction> result1 = transactions.stream()
                .filter(transaction -> transaction.year() == 2011)
                .sorted(Comparator.comparing(Transaction::value))
                .collect(Collectors.toList());

        System.out.println("result1 = " + result1);

        //TODO 2. 거래자가 근무하는 모든 도시를 중복 없이 나열
        List<String> result2 = transactions.stream()
                .map(transaction -> transaction.trader().city())
                .distinct()
                .collect(Collectors.toList());  //distinct 대신 toSet()도 가능.

        System.out.println("result2 = " + result2);

        //TODO 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        List<Trader> result3 = transactions.stream()
                .map(Transaction::trader)
                .filter(trader -> trader.city().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::name))
                .collect(Collectors.toList());

        System.out.println("result3 = " + result3);

        //TODO 4. 모든 거래자의 이름을 알파벳순으로 정렬 후, String으로 반환하시오.
        String result4 = transactions.stream()
                .map(transaction -> transaction.trader().name())
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));  // 내부적으로 StringBuilder 이용

        System.out.println("result4 = " + result4);

        //TODO 5. 밀라노에 거래자가 있는가?
        boolean result5 = transactions.stream()
                .anyMatch(trader -> trader.trader().city().equals("Milan"));

        System.out.println("result5 = " + result5);

        //TODO 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
        transactions.stream()
                .filter(transaction -> transaction.trader().city().equals("Cambridge"))
                .map(Transaction::value)
                .forEach(value -> System.out.println("result6 = " + value));

        //TODO 7. 전체 트랜잭션 중 최댓값은 얼마인가?
        transactions.stream()
                .map(Transaction::value)
                .reduce(Integer::max)
                .ifPresent(val -> System.out.println("result7 = " + val));

        // upgrade
        transactions.stream()
                .max(Comparator.comparing(Transaction::value))
                .ifPresent(val -> System.out.println("result7Ver2 = " + val));

        //TODO 8. 전체 트랜잭션 중 최솟값은 얼마인가?
        transactions.stream()
                .map(Transaction::value)
                .reduce(Integer::min)
                .ifPresent(val -> System.out.println("result8 = " + val));

        // upgrade
        transactions.stream()
                .min(Comparator.comparing(Transaction::value))
                .ifPresent(val -> System.out.println("result8Ver2 = " + val));
    }
}
