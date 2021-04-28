package main.java.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Lambdas {
	public static void main(String ...args){



		// Simple example
		Runnable r = () -> System.out.println("Hello!");
		r.run();

		// Filtering with lambdas
		List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]	
		List<Apple> greenApples = filter(inventory, (Apple a) -> "green".equals(a.getColor()));
		System.out.println(greenApples);


		int w = 150;
		List<Apple> heavierThan150g = filter(
				inventory, (Apple a) -> a.getWeight() > w);






		Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
		Comparator<Apple> c2 = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());


		// [Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
		inventory.sort(c1);
		System.out.println(inventory);


		//생성자 레퍼런스
		Supplier<Apple> t1 = Apple::new;
		Apple a1 = t1.get();

		Supplier<Apple> t2 = () -> new Apple();
		Apple a2 = t2.get();

		Function<Integer,Apple> k1 = Apple::new;
		Apple a3 = k1.apply(110);

		Function<Integer, Apple> k2 = (weight) -> new Apple(weight);
		Apple a4 = k1.apply(110);

		List<Integer> weights = Arrays.asList(7, 7, 6, 2, 5);
		List<Apple> apples =  map(weights, Apple::new);


	}

	public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
		List<Apple> result = new ArrayList<>();
		for (Integer e : list) {
			result.add(f.apply(e));
		}
		return result;
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
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

		public Apple() {

		}

		public Apple(Integer integer) {
			this.weight = integer;
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

	interface ApplePredicate{
		public boolean test(Apple a);
	}
}