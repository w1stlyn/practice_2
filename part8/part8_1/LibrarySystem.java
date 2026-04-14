package part8.part8_1;
import java.util.*;
import java.util.stream.Collectors;

enum Genre {
    FICTION("Художественная литература"), SCIENCE("Наука"), HISTORY("История"),
    PROGRAMMING("Программирование"), ART("Искусство");
    
    final String russianName;
    Genre(String russianName) { this.russianName = russianName; }
    
    static Genre fromString(String name) {
        return Arrays.stream(values()).filter(g -> g.russianName.equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}

record Book(String title, String author, int year, Genre genre, double price) {
    public Book {
        if (title.isBlank() || author.isBlank()) throw new IllegalArgumentException("Пустые поля");
        if (year < 1450 || year > 2026) throw new IllegalArgumentException("Неверный год");
        if (price < 0) throw new IllegalArgumentException("Цена < 0");
    }
}

sealed interface LibraryItem permits PhysicalBook, EBook {
    String getInfo();
    Book getBook();
}

record PhysicalBook(Book book, String shelf) implements LibraryItem {
    @Override public String getInfo() { return "Физ. книга [" + shelf + "] " + book.title(); }
    @Override public Book getBook() { return book; }
}

record EBook(Book book, String format, double sizeMB) implements LibraryItem {
    @Override public String getInfo() { return "E-Book [" + format + " " + sizeMB + "MB] " + book.title(); }
    @Override public Book getBook() { return book; }
}

interface Searchable {
    default boolean matches(String query) { return false; }
    static <T extends Searchable> List<T> search(List<T> items, String query) {
        return items.stream().filter(i -> i.matches(query)).toList();
    }
}

class Library {
    private List<LibraryItem> items = new ArrayList<>();
    public void add(LibraryItem item) { items.add(item); }

    public void printCatalog() {
        items.forEach(item -> {
            switch (item) {
                case PhysicalBook pb -> System.out.println("Физика: " + pb.getInfo());
                case EBook eb -> System.out.println("Цифра: " + eb.getInfo());
            }
        });
    }

    public Map<Genre, List<LibraryItem>> groupByGenre() {
        return items.stream().collect(Collectors.groupingBy(
            i -> i.getBook().genre(),() -> new EnumMap<>(Genre.class), Collectors.toList()
        ));
    }

    public double totalValue() {
        return items.stream().map(i -> i.getBook().price()).reduce(0.0, Double::sum);
    }

    public Optional<LibraryItem> mostExpensive() {
        return items.stream().max(Comparator.comparingDouble(i -> i.getBook().price()));
    }

    public List<String> authorsByGenre(Genre genre) {
        return items.stream()
                .filter(i -> i.getBook().genre() == genre).map(i -> i.getBook().author())
                .distinct().sorted().toList();
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.add(new PhysicalBook(new Book("Java 21", "Иванов", 2023, Genre.PROGRAMMING, 1500), "A1"));
        lib.add(new EBook(new Book("Spring", "Петров", 2022, Genre.PROGRAMMING, 800), "PDF", 5.5));
        
        lib.printCatalog();
        System.out.println("Сгруппировано по жанру: " + lib.groupByGenre());
        System.out.println("Общая стоимость: " + lib.totalValue());
        System.out.println("Самая дорогая: " + lib.mostExpensive().map(LibraryItem::getInfo).orElse(""));
        System.out.println("Авторы ПРОГ: " + lib.authorsByGenre(Genre.PROGRAMMING));
    }
}
