//This code shows the Water Jug Problem.
// The Water Jug Problem targets to make a certain quantity with two existing jugs of a certain quantity that have no markings.


import java.util.*;

class WaterJugBFS {

    // Represents one state of the two jugs
    static class State {
        int jug1Amount;
        int jug2Amount;
        String path;

        State(int jug1Amount, int jug2Amount, String path) {
            this.jug1Amount = jug1Amount;
            this.jug2Amount = jug2Amount;
            this.path = path;
        }
    }

    // BFS solution for the Water Jug Problem
    static void solveWaterJug(int capacityJug1, int capacityJug2, int targetAmount) {

        // Check whether the target is possible
        if (targetAmount > Math.max(capacityJug1, capacityJug2) ||
            targetAmount % findGCD(capacityJug1, capacityJug2) != 0) {

            System.out.println("\nNo solution possible.");
            return;
        }

        // Queue for BFS
        Queue<State> queue = new ArrayDeque<>();

        // Stores already visited states
        Set<String> visitedStates = new HashSet<>();

        // Initial state
        queue.add(new State(0, 0, "(0, 0)"));
        visitedStates.add("0,0");

        // BFS
        while (!queue.isEmpty()) {

            State currentState = queue.poll();

            int currentJug1 = currentState.jug1Amount;
            int currentJug2 = currentState.jug2Amount;

            // Check whether target is reached
            if (currentJug1 == targetAmount ||
                currentJug2 == targetAmount) {

                System.out.println("\n========== SOLUTION FOUND ==========");
                System.out.println("Target Amount: " + targetAmount);
                System.out.println("\nSteps:");
                System.out.println(currentState.path);
                System.out.println("====================================");

                return;
            }

            // 1. Fill Jug 1
            addState(
                capacityJug1,
                currentJug2,
                currentState,
                queue,
                visitedStates,
                "Fill Jug 1"
            );

            // 2. Fill Jug 2
            addState(
                currentJug1,
                capacityJug2,
                currentState,
                queue,
                visitedStates,
                "Fill Jug 2"
            );

            // 3. Empty Jug 1
            addState(
                0,
                currentJug2,
                currentState,
                queue,
                visitedStates,
                "Empty Jug 1"
            );

            // 4. Empty Jug 2
            addState(
                currentJug1,
                0,
                currentState,
                queue,
                visitedStates,
                "Empty Jug 2"
            );

            // 5. Pour Jug 1 into Jug 2
            int amountToPour = Math.min(
                currentJug1,
                capacityJug2 - currentJug2
            );

            addState(
                currentJug1 - amountToPour,
                currentJug2 + amountToPour,
                currentState,
                queue,
                visitedStates,
                "Pour Jug 1 -> Jug 2"
            );

            // 6. Pour Jug 2 into Jug 1
            amountToPour = Math.min(
                currentJug2,
                capacityJug1 - currentJug1
            );

            addState(
                currentJug1 + amountToPour,
                currentJug2 - amountToPour,
                currentState,
                queue,
                visitedStates,
                "Pour Jug 2 -> Jug 1"
            );
        }

        System.out.println("\nNo solution found.");
    }

    // Adds a new state to the BFS queue
    static void addState(
        int newJug1Amount,
        int newJug2Amount,
        State currentState,
        Queue<State> queue,
        Set<String> visitedStates,
        String operation
    ) {

        String stateKey = newJug1Amount + "," + newJug2Amount;

        // Add only if the state has not been visited
        if (!visitedStates.contains(stateKey)) {

            visitedStates.add(stateKey);

            String newPath = currentState.path
                    + " -> "
                    + operation
                    + " ("
                    + newJug1Amount
                    + ", "
                    + newJug2Amount
                    + ")";

            queue.add(
                new State(
                    newJug1Amount,
                    newJug2Amount,
                    newPath
                )
            );
        }
    }

    // Finds the Greatest Common Divisor
    static int findGCD(int firstNumber, int secondNumber) {

        while (secondNumber != 0) {

            int remainder = firstNumber % secondNumber;

            firstNumber = secondNumber;
            secondNumber = remainder;
        }

        return firstNumber;
    }

    // Main method
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("       WATER JUG PROBLEM - BFS");
        System.out.println("======================================");

        System.out.print("Enter capacity of Jug 1: ");
        int capacityJug1 = scanner.nextInt();

        System.out.print("Enter capacity of Jug 2: ");
        int capacityJug2 = scanner.nextInt();

        System.out.print("Enter target amount: ");
        int targetAmount = scanner.nextInt();

        System.out.println("\nSearching for solution...");

        solveWaterJug(
            capacityJug1,
            capacityJug2,
            targetAmount
        );

        scanner.close();
    }
}