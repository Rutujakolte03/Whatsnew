import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class KnapsackBranchBound {

    static class Item {
        int weight, value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    static class Node {
        int level, profit, weight;

        public Node(int level, int profit, int weight) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
        }
    }

    // Function to calculate bound for the current node
    static int calculateBound(Node node, int capacity, Item[] items) {
        if (node.weight >= capacity) {
            return 0;
        }

        int bound = node.profit;
        int totalWeight = node.weight;
        int i = node.level + 1;

        // Calculate bound by adding items as possible within the capacity
        while (i < items.length && totalWeight + items[i].weight <= capacity) {
            totalWeight += items[i].weight;
            bound += items[i].value;
            i++;
        }

        // If there are remaining items, add fractional part for upper bound estimate
        if (i < items.length) {
            bound += (capacity - totalWeight) * items[i].value / items[i].weight;
        }

        return bound;
    }

    // Function to solve the 0-1 Knapsack problem using Branch and Bound
    public static int knapsack(Item[] items, int capacity) {
        Arrays.sort(items, (a, b) -> (b.value / b.weight) - (a.value / a.weight));
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(-1, 0, 0));
        int maxProfit = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.level == items.length - 1) continue;

            // Check with including the next item
            Node includeNode = new Node(node.level + 1, 
                                        node.profit + items[node.level + 1].value, 
                                        node.weight + items[node.level + 1].weight);

            if (includeNode.weight <= capacity) {
                maxProfit = Math.max(maxProfit, includeNode.profit);
                if (calculateBound(includeNode, capacity, items) > maxProfit) {
                    queue.add(includeNode);
                }
            }

            // Check with excluding the next item
            Node excludeNode = new Node(node.level + 1, node.profit, node.weight);
            if (calculateBound(excludeNode, capacity, items) > maxProfit) {
                queue.add(excludeNode);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;

        Item[] items = new Item[weights.length];
        for (int i = 0; i < weights.length; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        int maxProfit = knapsack(items, capacity);
        System.out.println("Maximum profit = " + maxProfit);
    }
}
