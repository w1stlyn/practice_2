package part3.part3_2;
import java.util.Arrays;

public class GradeJournal {
    static double average(int[] grades) {
        return Arrays.stream(grades).average().orElse(0.0);
    }
    static int max(int[] grades) {
        return Arrays.stream(grades).max().orElse(0);
    }
    static int min(int[] grades) {
        return Arrays.stream(grades).min().orElse(0);
    }

    public static void main(String[] args) {
        String[] names = {"Алиса", "Борис", "Вера", "Глеб"};
        int[][] grades = {
            {5, 4, 5, 5, 3},
            {3, 3, 4},
            {5, 5, 5, 5, 5, 4},
            {4, 3, 4, 5}
        };

        System.out.println("=== Журнал оценок ===");
        double bestAvg = -1;
        String bestStudent = "";

        for (int i = 0; i < names.length; i++) {
            double avg = average(grades[i]);
            System.out.printf("%-7s | Оценок: %d | Средний: %.2f | Мин: %d | Макс: %d%n",
                    names[i], grades[i].length, avg, min(grades[i]), max(grades[i]));
            if (avg > bestAvg) {
                bestAvg = avg;
                bestStudent = names[i];
            }
        }
        System.out.printf("%nЛучший студент: %s (средний балл: %.2f)%n", bestStudent, bestAvg);
    }
}
