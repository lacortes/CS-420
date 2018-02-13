package cortes.luis;

public class Main {

    public static void main(String[] args) {
	    // Algorithms
        Algorithm classical = new Classical();
        Algorithm divideAndConquer = new DivideAndConquer();
        Algorithm strassen = new Strassen();

        int[][] m1 = {{2, 3},
                      {4, 1}};

        int[][] m2 = {{5, 7},
                      {6, 8}};

        System.out.println("Length: "+ m2.length);
        System.out.println(classical.multiply(m1, m2)[0][0]);

        int[][] m3 = {{1, 2, 3, 4},
                      {5, 6, 7, 8},
                      {9, 1, 2, 3},
                      {4, 5, 6, 7}};

        int[][] m4 = {{1,2,3,4},
                      {5,6,7,8},
                      {9,1,2,3},
                      {4,5,6,7}};

        int[][] m5 = {{9,8,7,6},
                      {5,4,3,2},
                      {1,9,8,7},
                      {6,5,4,3}};

        System.out.println(displayMatrix(m3));

        int[][] answer = strassen.multiply(m4, m5);
        System.out.println( displayMatrix(answer) );
    }

    public static String displayMatrix(int[][] matrix) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                string.append(matrix[i][j]);
                string.append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }
}
