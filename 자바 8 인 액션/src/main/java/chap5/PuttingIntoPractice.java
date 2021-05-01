package main.java.chap5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice{
    public static void main(String ...args){    
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

		//1 2011년에 일어난 모든 트랜잭션 값을 찾아 값을 오름차순 정리
        List<Transaction> collect = transactions.stream()
                .filter((T) -> T.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        //2 거래자가 근무하는 모든 도시를 중복 없이 나열
        List<String> collect1 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect((toList()));

        //3 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순 정렬
        List<Trader> cambridge = transactions.stream()
                .map(t -> t.getTrader())
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        //4 모든 거래자의 이름을 알파벳순으로 정렬해서 반환
        String reduce = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + b);

        //5 밀라노에 거래자가 있는가?
        boolean milan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        //6 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Milan"))
                .forEach(t-> System.out.println(t.getValue()));

        //7 전체 트랜잭션 중 최댓값은 얼마인가
        Integer reduce1 = transactions.stream()
                .map(t -> t.getValue())
                .reduce(0, Integer::max);

        //8 전체 트랜잭션 중 최솟값은 얼마인가
        Integer reduce2 = transactions.stream()
                .map(t -> t.getValue())
                .reduce(987654321, Integer::min);

        Optional<Transaction> reduce3 = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        Optional<Transaction> reduce4 = transactions.stream()
                .min(comparing(Transaction::getValue));

    }
}