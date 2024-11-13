import java.util.*;

public class MinimalHuffman {
    static class Node {
        char ch;
        int freq;
        Node left, right;
        
        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }
    
    public static void main(String[] args) {
        String text = "AABBCCDDEE";  // Simple test string
        
        // Count frequencies
        HashMap<Character, Integer> freq = new HashMap<>();
        for(char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        
        // Create priority queue and add nodes
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.freq - b.freq);
        for(Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        
        // Greedy strategy: Keep combining two smallest frequencies
        while(pq.size() > 1) {
            Node first = pq.poll();    // Get smallest frequency
            Node second = pq.poll();   // Get second smallest frequency
            
            // Create new node with combined frequency
            Node combined = new Node('$', first.freq + second.freq);
            combined.left = first;
            combined.right = second;
            
            pq.add(combined);          // Add back to queue
        }
        
        // Print the structure to see how characters are arranged
        Node root = pq.poll();
        System.out.println("Frequencies: " + freq);
        
    }
}