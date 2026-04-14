package part3.part3_1;

public class MatrixOperations {
    static void print(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    static int[][] transpose(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        int[][] res = new int[c][r];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                res[j][i] = matrix[i][j];
        return res;
    }

    static int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            System.out.println("Ошибка несовместимости.");
            return null;
        }
        int r = a.length, c = b[0].length, kLen = b.length;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                for (int k = 0; k < kLen; k++)
                    res[i][j] += a[i][k] * b[k][j];
        return res;
    }

    static int diagonalSum(int[][] matrix) {
        int sum = 0, n = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < n; i++) sum += matrix[i][i];
        return sum;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = {{7, 8}, {9, 10}, {11, 12}};
        System.out.println("Матрица A (2x3):"); print(a);
        System.out.println("\nТранспонированная A (3x2):"); print(transpose(a));
        System.out.println("\nМатрица B (3x2):"); print(b);
        int[][] c = multiply(a, b);
        System.out.println("\nA * B (2x2):"); print(c);
        System.out.println("\nСумма диагонали A*B: " + diagonalSum(c));
    }
}
