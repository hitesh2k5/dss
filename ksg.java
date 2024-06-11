import java.util.Arrays;
import java.util.Comparator;

public class ksg {

    // Item class to store weight and value of items
    static class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    // Method to get the maximum value we can carry in the knapsack
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            public int compare(Item o1, Item o2) {
                double r1 = (double) o1.value / o1.weight;
                double r2 = (double) o2.value / o2.weight;
                return Double.compare(r2, r1);
            }
        });

        double totalValue = 0;

        for (Item item : items) {
            if (capacity == 0) break;

            // If the item can fit in the knapsack, take all of it
            if (item.weight <= capacity) {
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // Otherwise, take the fraction of the remaining capacity
                totalValue += item.value * ((double) capacity / item.weight);
                capacity = 0;
            }
        }

        return totalValue;
    }

    // Main method to test the greedy knapsack
    public static void main(String[] args) {
        Item[] items = {
            new Item(10, 60),
            new Item(20, 100),
            new Item(30, 120)
        };
        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);
    }
}
