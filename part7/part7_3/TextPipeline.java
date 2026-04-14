package part7.part7_3;
import java.util.*;
import java.util.function.Function;

public class TextPipeline {
    public static void main(String[] args) {
        // Часть A
        Function<String, String> trim = String::trim;
        Function<String, String> lower = String::toLowerCase;
        Function<String, String> removeSpaces = s -> s.replaceAll("\\s+", " ");
        Function<String, String> cap = s -> s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);

        Function<String, String> normalize = trim.andThen(lower).andThen(removeSpaces).andThen(cap);
        String txt = normalize.apply("   ОЧЕНЬ   Грязный    текст   тут! ");
        System.out.println("Нормализовано: " + txt);

        // Часть B
        class WordCounter {
            private String data;
            WordCounter(String data) { this.data = data; }

            Map<String, Integer> count() {
                Map<String, Integer> map = new HashMap<>();
                for (String w : data.split("\\s+")) map.put(w, map.getOrDefault(w, 0) + 1);
                return map;
            }

            String mostFrequent() {
                return count().entrySet().stream()
                        .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("");
            }
        }
        
        WordCounter wc = new WordCounter(normalize.apply(" кот собака   кот мышь КОт   "));
        System.out.println("Алгоритм: " + wc.count() + " Словцо: " + wc.mostFrequent());
    }
}
