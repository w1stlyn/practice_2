package part4.part4_2;

public class StringPoolLab {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = new String("Hello");
        String s5 = s3.intern();
        String s6 = "Hel" + "lo";
        String half = "Hel";
        String s7 = half + "lo";

        // Прогноз s1 и s2: == true (ссылаются на один пул), equals true
        System.out.println("s1 == s2: " + (s1 == s2) + " | equals: " + s1.equals(s2));
        
        // Прогноз s1 и s3: == false (s3 это новый объект в heap), equals true
        System.out.println("s1 == s3: " + (s1 == s3) + " | equals: " + s1.equals(s3));
        
        // Прогноз s3 и s4: == false (разные объекты), equals true
        System.out.println("s3 == s4: " + (s3 == s4) + " | equals: " + s3.equals(s4));
        
        // Прогноз s1 и s5: == true (intern() возвращает ссылку из пула), equals true
        System.out.println("s1 == s5: " + (s1 == s5) + " | equals: " + s1.equals(s5));
        
        // Прогноз s1 и s6: == true (конкатенация литералов вычисляется на этапе компиляции), equals true
        System.out.println("s1 == s6: " + (s1 == s6) + " | equals: " + s1.equals(s6));
        
        // Прогноз s1 и s7: == false (динамическая конкатенация в рантайме создает новый объект), equals true
        System.out.println("s1 == s7: " + (s1 == s7) + " | equals: " + s1.equals(s7));

        StringBuilder sb = new StringBuilder();
        sb.append('H').append('e').append('l').append('l').append('o');
        String builderResult = sb.toString();
        
        // Прогноз: == false (toString создает новую строку в heap), equals true
        System.out.println("Builder == s1: " + (builderResult == s1) + " | equals: " + builderResult.equals(s1));
    }
}
