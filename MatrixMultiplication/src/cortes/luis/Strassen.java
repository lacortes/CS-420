package cortes.luis;

public class Strassen implements Algorithm {
    Algorithm classical = new Classical();

    @Override
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = strassen(a.length, a, b);
        return c;
    }

    private int[][] strassen(int n, int[][] a, int[][] b) {
        if (n <= 2) {
             return classical.multiply(a, b);
        } else {
            // Partition A into four submatrices
            int[][] a11 = partition(a, 0, n/2, 0, n/2);
            int[][] a12 = partition(a, 0, n/2, n/2, n);
            int[][] a21 = partition(a, n/2, n, 0, n/2);
            int[][] a22 = partition(a, n/2, n, n/2, n);

            // Partition B into four submatrices
            int[][] b11 = partition(b, 0, n/2, 0, n/2);
            int[][] b12 = partition(b, 0, n/2, n/2, n);
            int[][] b21 = partition(b, n/2, n, 0, n/2);
            int[][] b22 = partition(b, n/2, n, n/2, n);

            int[][] m1 = strassen(n/2, add(a11, a22), add(b11, b22) );
            int[][] m2 = strassen(n/2, add(a21, a22), b11);
            int[][] m3 = strassen(n/2, a11, subtract(b12, b22));
            int[][] m4 = strassen(n/2, a22, subtract(b21, b11));
            int[][] m5 = strassen(n/2, add(a11, a12), b22);
            int[][] m6 = strassen(n/2, subtract(a21, a11), add(b11, b12));
            int[][] m7 = strassen(n/2, subtract(a12, a22), add(b21, b22));

            // Calculate C
            int[][] c11 = add(subtract(add(m1, m4), m5), m7);
            int[][] c12 = add(m3, m5);
            int[][] c21 = add(m2, m4);
            int[][] c22 = add(subtract( add(m1, m3), m2), m6);

            int[][] c = new int[n][n];
            c = join(c, c11, 0, n/2, 0, n/2);
            c = join(c, c12, 0, n/2, n/2, n);
            c = join(c, c21, n/2, n, 0, n/2);
            c = join(c, c22, n/2, n, n/2, n);
            return c;
        }
    }

    private int[][] partition(int[][] subMatrix, int rowStart, int rowEnd, int colStart, int colEnd) {
        int partRowIndex = 0;
        int partColIndex = 0;
        int dimension = subMatrix.length / 2;
        int[][] partitioned = new int[dimension][dimension];
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                partColIndex %= dimension;
                partitioned[partRowIndex][partColIndex++] = subMatrix[i][j];
            }
            partRowIndex++;
        }
        return partitioned;
    }

    private int[][] join(int[][] c, int[][] partition, int rowStart, int rowEnd, int colStart, int colEnd) {
        int partRowIndex = 0;
        int partColIndex = 0;
        int dimension = c.length / 2;
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                partColIndex %= dimension;
                c[i][j] = partition[partRowIndex][partColIndex++];
            }
            partRowIndex++;
        }
        return c;
    }

    private int[][] subtract(int[][] a, int[][] b)
    {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] - b[i][j];
        return c;
    }

    private int[][] add(int[][] a, int[][] b)
    {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }
}
