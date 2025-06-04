package main.Game;

import java.util.*;
import main.Database.BookwyrmDB;

public class bookwyrmGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        
        // Game Intro
        System.out.println("\n ____   ___   ___   _  __ __            __ __   _______     ___   ___");
        System.out.println("| __ ) / _ \\ / _ \\ | |/ / \\ \\   ____   / / \\ \\ / /| |\\ \\   |   \\ /   |");
        System.out.println("|  _ \\| | | | | | || ' /   \\ \\ / /\\ \\ / /   \\ V / | |_) )  | |\\ V /| |");
        System.out.println("| |_) | |_| | |_| || . \\    \\ V /  \\ V /     | |  | | \\ \\  | | \\_/ | |");
        System.out.println("|____/ \\___/ \\___/ |_|\\_\\    \\_/    \\_/      |_|  |_|  |_| |_|     |_|");
        System.out.println();
        System.out.println("\t\t\tB  O  O  K  W  Y  R  M");
        System.out.println();
        System.out.println("\t\tWELCOME TO THE ADVENTURE, Bookworm!!!!");
        System.out.println("\tYou are about to explore and defeat the GREAT SPHINX!\n");

        // Initialize database connection
        BookwyrmDB db = new BookwyrmDB();
        UserAuth userAuth = new UserAuth(db);  // Create UserAuth with database dependency

         boolean loggedIn = false;
        String username;

        while (!loggedIn) {
            System.out.print("Do you want to [login] or [register]? ");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "login":
                    System.out.print("Enter username: ");
                    username = scanner.nextLine().trim();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine().trim();
                    loggedIn = userAuth.login(username, password);
                    if (!loggedIn) {
                        System.out.println("Invalid username or password. Try again.");
                    } else {
                        System.out.println("Login successful! Welcome back, " + username + "!");
                    }
                    break;
                case "register":
                    System.out.print("Choose a username: ");
                    username = scanner.nextLine().trim();
                    System.out.print("Choose a password: ");
                    String regPassword = scanner.nextLine().trim();
                    loggedIn = userAuth.createUser(username, regPassword);
                    if (!loggedIn) {
                        System.out.println("Registration failed. Username or email might already exist.");
                    } else {
                        System.out.println("Registration successful! Welcome, " + username + "!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please type 'login' or 'register'.");
            }
        }
        // Battle Setup
        int wormHP = 100;
        int sphinxHP = 100;

        System.out.println("\t      Battle begins between YOU and the SPHINX!");

        QuestionBank qb = new QuestionBank();

        // Choose difficulty
        System.out.print("Choose your difficulty (easy / medium / hard): ");
        String chosenDifficulty = scanner.nextLine().trim().toLowerCase();

        while (!chosenDifficulty.equals("easy") && !chosenDifficulty.equals("medium") && !chosenDifficulty.equals("hard")) {
            System.out.print("Invalid difficulty. Please choose easy, medium, or hard: ");
            chosenDifficulty = scanner.nextLine().trim().toLowerCase();
        }

        // Start battle loop
        while (wormHP > 0 && sphinxHP > 0) {
            Question question = qb.getRandomQuestion(chosenDifficulty);
            if (question == null) {
                System.out.println("No more questions available for this difficulty.");
                break;
            }

            System.out.println("\nSphinx asks: " + question.getRiddle());
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().trim();

            int damage = rand.nextInt(25) + 10;

            if (userAnswer.equalsIgnoreCase(question.getAnswer())) {
                sphinxHP -= damage;
                System.out.println("Correct! You attacked the Sphinx! Sphinx HP: " + Math.max(0, sphinxHP));
            } else {
                wormHP -= damage;
                System.out.println("Wrong! The Sphinx attacked you! Your HP: " + Math.max(0, wormHP));
            }

            System.out.println("----------------------------------------");
        }

        // Game Over
        if (wormHP <= 0) {
            System.out.println("You were defeated by the Sphinx! Try again, Bookworm.");
        } else if (sphinxHP <= 0) {
            System.out.println("You defeated the Sphinx! You are a Great Adventurer");
            // Here you could update the database with the win
        }

        scanner.close();
    }
}