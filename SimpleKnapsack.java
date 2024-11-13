public class SimpleKnapsack {
    public static void main(String[] args) {
        // Items weights and values
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;
        
        // Calculate value per unit weight
        double[] ratio = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            ratio[i] = (double)values[i] / weights[i];
        }
        
        // Simple sort based on ratio (highest to lowest)
        for (int i = 0; i < ratio.length; i++) {
            for (int j = i + 1; j < ratio.length; j++) {
                if (ratio[i] < ratio[j]) {
                    // Swap ratios
                    double tempR = ratio[i];
                    ratio[i] = ratio[j];
                    ratio[j] = tempR;
                    
                    // Swap weights and values too
                    int tempW = weights[i];
                    weights[i] = weights[j];
                    weights[j] = tempW;
                    
                    int tempV = values[i];
                    values[i] = values[j];
                    values[j] = tempV;
                }
            }
        }
        
        double totalValue = 0;
        int currentWeight = 0;
        
        // Fill the knapsack
        for (int i = 0; i < weights.length; i++) {
            if (currentWeight + weights[i] <= capacity) {
                // Take whole item
                currentWeight += weights[i];
                totalValue += values[i];
                System.out.println("Take item: weight = " + weights[i] + ", value = " + values[i]);
            } else {
                // Take fraction of item
                int remainingWeight = capacity - currentWeight;
                totalValue += ratio[i] * remainingWeight;
                System.out.println("Take partial item: weight = " + remainingWeight + 
                                 ", value = " + (ratio[i] * remainingWeight));
                break;
            }
        }
        
        System.out.println("\nTotal value = " + totalValue);
    }
}