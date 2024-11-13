public class KnapsackDP {

    // Function to solve 0-1 Knapsack problem
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity]; // Maximum value that can be obtained
    }

    public static void main(String[] args) {
        int[] weights = { 1, 3, 4, 5 }; // weights of items
        int[] values = { 1, 4, 5, 7 };  // values of items
        int capacity = 7;               // knapsack capacity

        int maxProfit = knapsack(weights, values, capacity);
        System.out.println("Maximum value in knapsack = " + maxProfit);
    }
}
