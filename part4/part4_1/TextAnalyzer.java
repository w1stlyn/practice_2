package part4.part4_1;
import java.util.Arrays;
import java.util.Comparator;

public class TextAnalyzer {
    private String text;

    public TextAnalyzer(String text) { this.text = text; }

    public int wordCount() {
        if (text.trim().isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }

    public String longestWord() {
        return Arrays.stream(text.split("\\s+"))
                .max(Comparator.comparingInt(String::length)).orElse("");
    }

    public String reverseWords() {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(i > 0 ? " " : "");
        }
        return sb.toString();
    }

    public int countOccurrences(String target) {
        String lowerText = text.toLowerCase();
        String lowerTarget = target.toLowerCase();
        int count = 0, idx = 0;
        while ((idx = lowerText.indexOf(lowerTarget, idx)) != -1) {
            count++;
            idx += lowerTarget.length();
        }
        return count;
    }

    public boolean isPalindrome() {
        String clean = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toLowerCase();
        String reversed = new StringBuilder(clean).reverse().toString();
        return !clean.isEmpty() && clean.equals(reversed);
    }

    @Override
    public String toString() { return text; }

    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer("Java Programming is Fun and Java is Powerful");
        System.out.println("Текст: " + ta);
        System.out.println("Слов: " + ta.wordCount());
        System.out.println("Самое длинное слово: " + ta.longestWord());
        System.out.println("Слова наоборот: " + ta.reverseWords());
        System.out.println("'Java' встречается: " + ta.countOccurrences("java") + " раз(а)");
        System.out.println("'is' встречается: " + ta.countOccurrences("is") + " раз(а)");
        System.out.println("Палиндром: " + ta.isPalindrome());

        System.out.println();
        TextAnalyzer palindrome = new TextAnalyzer("А роза упала на лапу Азора");
        System.out.println("Текст: " + palindrome);
        System.out.println("Палиндром: " + palindrome.isPalindrome());
    }
}
