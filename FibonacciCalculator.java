public class FibonacciCalculator {
    // Iterative approach
    public static long fibIterative(int n) {
        if (n <= 1) return n;
        
        long prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    // Recursive approach
    public static long fibRecursive(int n) {
        if (n <= 1) return n;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    // Main method with example usage
    public static void main(String[] args) {
        int n = 5;
        System.out.println("Iterative result for n=" + n + ": " + fibIterative(n));
        System.out.println("Recursive result for n=" + n + ": " + fibRecursive(n));
    }
}