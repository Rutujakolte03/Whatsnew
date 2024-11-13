public class MatrixMultiplication {

    static class MultiplyTask extends Thread {
        int[][] A, B, result;
        int row, col, size;

        // Constructor
        public MultiplyTask(int[][] A, int[][] B, int[][] result, int row, int col, int size) {
            this.A = A;
            this.B = B;
            this.result = result;
            this.row = row;
            this.col = col;
            this.size = size;
        }

        // Each thread calculates one element of the result matrix
        @Override
        public void run() {
            int sum = 0;
            for (int i = 0; i < size; i++) {
                sum += A[row][i] * B[i][col];
            }
            result[row][col] = sum;
        }
    }

    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] B = {
            {1, 0, 2},
            {0, 1, 2},
            {1, 0, 1}
        };

        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] result = new int[rowsA][colsB];

        // Creating and starting threads for each element in the result matrix
        Thread[][] threads = new Thread[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                threads[i][j] = new MultiplyTask(A, B, result, i, j, colsA);
                threads[i][j].start();
            }
        }

        // Wait for all threads to complete
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                try {
                    threads[i][j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Print the result matrix
        System.out.println("Result Matrix:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
