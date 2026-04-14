package part5.part5_1;
import java.util.*;

enum Grade {
    A("Отлично", 4.0), B("Хорошо", 3.0), C("Удовлетворительно", 2.0),
    D("Неудовлетворительно", 1.0), F("Провал", 0.0);

    private final String description;
    private final double gpaValue;

    Grade(String description, double gpaValue) {
        this.description = description;
        this.gpaValue = gpaValue;
    }

    public String getDescription() { return description; }
    public double getGpaValue() { return gpaValue; }

    public boolean isPassing() { return this != F && this != D; }

    public static Grade fromScore(int score) {
        if (score >= 90) return A;
        if (score >= 80) return B;
        if (score >= 70) return C;
        if (score >= 60) return D;
        return F;
    }
}

record Student(String name, int id) {
    public Student {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Invalid name");
        if (id <= 0) throw new IllegalArgumentException("Invalid id");
    }
}

public class GradeSystem {
    public static void main(String[] args) {
        Map<Student, Grade> records = Map.of(
            new Student("Иван", 1), Grade.fromScore(95),
            new Student("Мария", 2), Grade.fromScore(82),
            new Student("Антон", 3), Grade.fromScore(75),
            new Student("Олег", 4), Grade.fromScore(55),
            new Student("Анна", 5), Grade.fromScore(68),
            new Student("Юра", 6), Grade.fromScore(90)
        );

        EnumMap<Grade, List<Student>> grouped = new EnumMap<>(Grade.class);
        for (Grade g : Grade.values()) grouped.put(g, new ArrayList<>());
        records.forEach((s, g) -> grouped.get(g).add(s));

        EnumSet<Grade> passingGrades = EnumSet.of(Grade.A, Grade.B, Grade.C);

        double totalGpa = 0;
        System.out.println("Сводка оценок:");
        for (Grade g : Grade.values()) {
            List<Student> list = grouped.get(g);
            System.out.println(g.name() + " (" + g.getDescription() + ") - Студентов: " + list.size() + " -> " + list);
            totalGpa += g.getGpaValue() * list.size();
        }

        System.out.printf("Средний GPA: %.2f%n", totalGpa / records.size());
        System.out.println("Проходные оценки: " + passingGrades);
    }
}
