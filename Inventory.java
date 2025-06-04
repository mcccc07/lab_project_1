package main.Game;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<String> items;
    private int gold;

    public Inventory() {
        items = new ArrayList<>();
        gold = 0;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void showInventory() {
        System.out.println("\n--- Inventory ---");
        System.out.println("Gold: " + gold);
        System.out.println("Items: " + items);
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public int getGold() {
        return gold;
    }

    public boolean useItem(String item) {
        return items.remove(item);
    }
}
