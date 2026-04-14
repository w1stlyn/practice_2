package part7.part7_2;
import java.util.*;
import java.util.stream.*;

record Order(String customer, String product, double price, int quantity, String category) {
    double total() { return price * quantity; }
}

public class OrderAnalytics {
    public static void main(String[] args) {
        List<Order> orders = List.of(
            new Order("Аня", "Кофеварка", 4500, 1, "Техника"),
            new Order("Олег", "Ноутбук", 80000, 1, "Техника"),
            new Order("Аня", "Чай", 200, 5, "Продукты"),
            new Order("Игорь", "Стол", 6000, 2, "Мебель")
        );

        System.out.println("Дороже 5000: ");
        orders.stream().filter(o -> o.total() > 5000).forEach(System.out::println);

        List<String> clients = orders.stream().map(Order::customer).distinct().sorted().toList();
        System.out.println("Клиенты: " + clients);

        double totalRevenue = orders.stream().mapToDouble(Order::total).sum();
        System.out.println("Выручка: " + totalRevenue);

        Order expensive = orders.stream().max(Comparator.comparingDouble(Order::total)).orElse(null);
        System.out.println("Самый дорогой: " + expensive);

        Map<String, Long> catCounts = orders.stream().collect(Collectors.groupingBy(Order::category, Collectors.counting()));
        System.out.println("Категории: " + catCounts);

        Map<String, Double> avgByClient = orders.stream().collect(Collectors.groupingBy(Order::customer, Collectors.averagingDouble(Order::total)));
        System.out.println("Средний чек: " + avgByClient);

        Map<Boolean, List<Order>> partitioned = orders.stream().collect(Collectors.partitioningBy(o -> o.total() > 3000));
        System.out.println("Дорогие/дешевые: " + partitioned);

        List<String> prodNames = orders.stream()
            .filter(o -> o.price > 1000).map(Order::product)
            .distinct().map(String::toUpperCase).toList();
        System.out.println("Товары >1000: " + prodNames);
    }
}
