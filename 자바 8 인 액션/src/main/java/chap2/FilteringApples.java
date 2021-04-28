package main.java.chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilteringApples {
    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));


        //2.3.2 다섯 번재 시도 : 익명 클래스 사용
        List<Apple> redApples = filterApples(inventory,new ApplePredicate() {
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });



        //2.3.3 여섯 번째 시도 : 람다 표현식 사용
        List<Apple> result = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        //2.3.4
        List<Apple> redApples2 = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);

        //Comparator를 구현해서 sort 메서드의 동작을 다양화
        //익명 클래스를 무게가 적은 순으로 목록에서 사과를 정렬
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        inventory.sort(
                (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
        );

    }


    //2.1.1 첫 번째 시도: 녹색 사과 필터링
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    //좀 더 다양한 색의 사과를 필터링 하는 조건을 추가한다면?
    //2.1.2 두 번째 시도 : 색을 파라미터화
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    //색 이외에 다른 조건, 일정 무게 이상의 필터링을 원한다면?
    public static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    //2.1.3 세 번째 시도 : 가능한 모든 속성으로 필터링
    //형편없는 코드! true와 false는 무엇을 의미하는가?
    //요구사항이 바뀌었을 때 유연하게 대처 불가능
    public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    //2.2 동작 파라미터화
    //사과의 어떤 속성에 기초해서 불린값을 반환하는 방법이 있다. 이와같은 동작을 프레디케이트(predicate)라고 한다.
    public interface ApplePredicate {
        boolean test(Apple apple);
    }

    public class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    public class AppleGreenColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    //2.2.1 네 번째 시도 : 추상적 조건으로 필터링
    //전달한 ApplePredicate 객체에 의해 filterApples 메서드의 동작이 결정된다. 즉 메서드의 동작을 파라미터화 한것이다.
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    //2.3 복잡한 과정 간소화
    //자바는 클래스의 선언과 인스턴스화를 동시에 수행할 수 있도록 익명 클래스 라는 기법을 제공한다.

    //2.3.1 익명 클래스
    //익명 클래스는 자바의 지역 클래스 (블록 내부에 선언된 클래스)와 비스한 개념이다. 익명 클래스는 말 그대로 이름이 없는 클래스이다. 익명 클래스를 이용하면 클래스 선언과
    //인스턴스화를 동시에 할 수 잇다. 즉, 즉석에서 필요한 구현을 만들어서 사용할 수 있다.

    //2.3.2 익명 클래스 부족한 점
    // 여전히 많은 공간 차지
    // 익명 클래스의 사용에 익숙하지 않다.
    // 결국 코드 조각을 전달하는 과정에서 결국은 객체를 만들고 명ㄷ시적으로 새로운 동작을 정의하는 메서드를 구현해야 한다는 점은 변하지 않는다.

    //2.3.3 여섯 번째 시도 : 람다 표현식 사용

    //2.3.4 일곱 번째 시도 : 리스트 형식으로 추상화
    // Apple과 관련된 동작에서 Apple 이외의 다양한 물건에서 필터링이 작동하도록 리스트 형식을 추상화
    public interface Predicate<T>{
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        ArrayList<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }









    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

}
