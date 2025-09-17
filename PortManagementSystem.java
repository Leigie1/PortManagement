package dsa;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Container {
    private String id;
    private String description;
    private int weightKg;

    public Container(String id, String description, int weightKg) {
        this.id = id;
        this.description = description;
        this.weightKg = weightKg;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %dkg", id, description, weightKg);
    }
}

class Ship {
    private String name;
    private String captain;

    public Ship(String name, String captain) {
        this.name = name;
        this.captain = captain;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", name, captain);
    }
}

public class PortManagementSystem {
    static Scanner scan = new Scanner(System.in);
    static Deque<Container> containerStack = new ArrayDeque<>();
    static Deque<Ship> shipQueue = new ArrayDeque<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("=== Port Container Management System ===" +
                    "\n[1] Store container (push)" +
                    "\n[2] View port containers" +
                    "\n[3] Register arriving ship (enqueue)" +
                    "\n[4] View waiting ships" +
                    "\n[5] Load next ship (pop container + poll ship)" +
                    "\n[0] Exit" +
                    "\nEnter your choice: ");
            int choice = readInt();

            switch (choice) {
                case 1:
                    pushStack();
                    break;
                case 2:
                    viewContainers();
                    break;
                case 3:
                    enqueueQueue();
                    break;
                case 4:
                    viewShips();
                    break;
                case 5:
                    loadResult();
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice, Try again");
            }
            System.out.println();
        }
    }

    static void pushStack() {
        while (true) {
            System.out.print("Enter Container id: ");
            String id = scan.next();
            System.out.print("Enter Container description: ");
            String description = scan.next();
            System.out.print("Enter Container weight (kg): ");
            int weight = readInt();

            Container containee = new Container(id, description, weight);
            containerStack.push(containee);
            System.out.println("Stored: " + containee);

            System.out.print("Enter another container? y/n: ");
            char choice = scan.next().charAt(0);
            if (choice == 'n') break;
        }
    }

    static void enqueueQueue() {
        while (true) {
            System.out.print("Enter Ship name: ");
            String name = scan.next();
            System.out.print("Enter Captain name: ");
            String capname = scan.next();

            Ship shipee = new Ship(name, capname);
            shipQueue.offer(shipee);
            System.out.println("Registered: " + shipee);

            System.out.print("Enter another ship? y/n: ");
            char choice = scan.next().charAt(0);
            if (choice == 'n') break;
        }
    }

    static void viewContainers() {
        if (containerStack.isEmpty()) {
            System.out.println("No containers in port.");
            return;
        }
        System.out.println("TOP →");
        for (Container c : containerStack) {
            System.out.println(c);
        }
        System.out.println("← BOTTOM");
    }

    static void viewShips() {
        if (shipQueue.isEmpty()) {
            System.out.println("No ships waiting at the dock.");
            return;
        }
        System.out.println("FRONT →");
        for (Ship s : shipQueue) {
            System.out.println(s);
        }
        System.out.println("← REAR");
    }

    static void loadResult() {
        if (containerStack.isEmpty() && shipQueue.isEmpty()) {
            System.out.println("No containers and no ships to load.");
            return;
        }
        if (containerStack.isEmpty()) {
            System.out.println("No containers available to load.");
            return;
        }
        if (shipQueue.isEmpty()) {
            System.out.println("No ships waiting to be loaded.");
            return;
        }

        Container res = containerStack.pop();
        Ship ult = shipQueue.poll();
        System.out.println("Loaded: " + res + " → " + ult +
                "\nRemaining containers: " + containerStack.size() +
                "\nRemaining ships waiting: " + shipQueue.size());
    }

    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scan.next());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }
}
