package item54;


import java.util.*;

public class Main {

    private final static List<Cheese> cheesesInStock = new ArrayList<>();

    public List<Cheese> getCheeses() {
        return cheesesInStock.isEmpty() ? null : new ArrayList<>(cheesesInStock);
    }

    public List<Cheese> getCheeses2() {
        return cheesesInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheesesInStock);
    }

    public Cheese[] getCheeses3() {
        return cheesesInStock.toArray(new Cheese[0]);
    }


    public static void main(String[] args) {

        Main shop = new Main();
        cheesesInStock.add(Cheese.STILTON);

        List<Cheese> cheeses = shop.getCheeses();

        if(cheeses != null && cheeses.contains(Cheese.STILTON)){
            System.out.println("좋았어 바로 그거야");
        }
    }
}

enum Cheese {
    STILTON
}
