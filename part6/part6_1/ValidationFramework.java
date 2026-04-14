package part6.part6_1;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Часть A: Заполнение пропусков TestInfo
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TestInfo {
    String author();
    String date();
    String description() default "";
    int priority() default 5;
}

// Часть B: Мини-фреймворк
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotEmpty { String message() default "Поле не может быть пустым"; }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min();
    int max();
    String message() default "Значение вне допустимого диапазона";
}

class RegistrationForm {
    @NotEmpty(message = "Имя обязательно") String name;
    @NotEmpty String email;
    @Range(min = 18, max = 120, message = "Возраст должен быть от 18 до 120") int age;

    public RegistrationForm(String name, String email, int age) {
        this.name = name; this.email = email; this.age = age;
    }
}

class Validator {
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object val = f.get(obj);
                if (f.isAnnotationPresent(NotEmpty.class)) {
                    if (val == null || val.toString().trim().isEmpty()) {
                        errors.add(f.getAnnotation(NotEmpty.class).message());
                    }
                }
                if (f.isAnnotationPresent(Range.class)) {
                    if (val instanceof Integer v) {
                        Range r = f.getAnnotation(Range.class);
                        if (v < r.min() || v > r.max()) errors.add(r.message());
                    }
                }
            } catch (IllegalAccessException ignored) {}
        }
        return errors;
    }
}

public class ValidationFramework {
    public static void main(String[] args) {
        RegistrationForm valid = new RegistrationForm("Иван", "ivan@mail.ru", 25);
        RegistrationForm invalid = new RegistrationForm("", null, 15);
        System.out.println("=== Валидация корректной формы ===");
        List<String> errors1 = Validator.validate(valid);
        System.out.println(errors1.isEmpty() ? "Все поля валидны!" : errors1);

        System.out.println("\n=== Валидация некорректной формы ===");
        List<String> errors2 = Validator.validate(invalid);
        errors2.forEach(e -> System.out.println("  - " + e));
    }
}
