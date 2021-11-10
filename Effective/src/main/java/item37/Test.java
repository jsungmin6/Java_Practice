package item37;

import java.util.*;

public class Test {

    public static void usingOrdinalArray(List<Plant> garden) {
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[LifeCycle.values().length];
        for (int i = 0 ; i < plantsByLifeCycle.length ; i++) {
            plantsByLifeCycle[i] = new HashSet<>();
        }

        for (Plant plant : garden) {
            plantsByLifeCycle[plant.lifeCycle.ordinal()].add(plant);
        }

        for (int i = 0 ; i < plantsByLifeCycle.length ; i++) {
            System.out.printf("%s : %s%n",
                    LifeCycle.values()[i], plantsByLifeCycle[i]);
        }
    }

    public static void usingEnumMap(List<Plant> garden) {
        Map<LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(LifeCycle.class);

        for (LifeCycle lifeCycle : LifeCycle.values()) {
            plantsByLifeCycle.put(lifeCycle,new HashSet<>());
        }

        for (Plant plant : garden) {
            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
        }

        //EnumMap은 toString을 재정의하였다.
        System.out.println(plantsByLifeCycle);
    }


    public static void main(String[] args) {
        Plant corn = new Plant("옥수수",LifeCycle.ANNUAL);
        Plant pea = new Plant("완두",LifeCycle.ANNUAL);
        Plant potato = new Plant("감자",LifeCycle.PERNNIAL);
        Plant gona = new Plant("고나꽃",LifeCycle.BIENNIAL);

        List<Plant> garden = Arrays.asList(corn, pea, potato,gona);

        usingOrdinalArray(garden);

        usingEnumMap(garden);

    }

}
