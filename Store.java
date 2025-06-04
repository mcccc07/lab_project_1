package main.Game;

import java.util.Scanner;

public class Store {
    public static void openStore(Inventory inventory, Scanner scanner) {
        boolean shopping = true;
        while (shopping) {
            System.out.println("\n--- Store ---");
            System.out.println("Health Potion ----- 10 gold");
            System.out.println("Increase Damage ---- 15 gold");
            System.out.println("Exit the Store");
            System.out.print("Choose an item to buy: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "Health Potion":
                    if (inventory.getGold() >= 20) {
                        inventory.addItem("Health Potion");
                        inventory.addGold(-10);
                        System.out.println("You bought a Health Potion!");
                    } else {
                        System.out.println("Not enough gold.");
                    }
                    break;
                case "Increase Damage":
                    if (inventory.getGold() >= 20) {
                        inventory.addItem("Increase Damage");
                        inventory.addGold(-15);
                        System.out.println("You bought a Health Potion!");
                    } else {
                        System.out.println("Not enough gold.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

