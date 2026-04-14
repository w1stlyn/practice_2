package part2.part2_3;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

interface Loggable {
    String getComponentName();

    default void log(String message) {
        System.out.println("[" + formatTimestamp() + "] [" + getComponentName() + "] " + message);
    }

    default void logError(String message) {
        System.out.println("[" + formatTimestamp() + "] [" + getComponentName() + "] ОШИБКА: " + message);
    }

    private String formatTimestamp() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    static String getLogLevel() { return "INFO"; }
}

class DatabaseService implements Loggable {
    @Override public String getComponentName() { return "DatabaseService"; }
    public void connect(String url) {
        log("Подключение к " + url);
        log("Подключение установлено");
    }
}

class AuthService implements Loggable {
    @Override public String getComponentName() { return "AuthService"; }
    public void login(String username, boolean success) {
        if (success) log("Вход пользователя: " + username + " — успешно");
        else logError("Вход пользователя: " + username + " — отказано");
    }
}

public class LoggableDemo {
    public static void main(String[] args) {
        DatabaseService db = new DatabaseService();
        AuthService auth = new AuthService();
        System.out.println("Уровень логирования: " + Loggable.getLogLevel() + "\n");
        db.connect("jdbc:postgresql://localhost/mydb");
        System.out.println();
        auth.login("admin", true);
        auth.login("hacker", false);
    }
}
