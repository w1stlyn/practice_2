package part1.part1_3;
import java.util.ArrayList;

public class VarDemo {
    // var field = 10; // Не компилируется — var нельзя использовать для полей класса

    public static void main(String[] args) {
        var a = 42;
        var b = "Java";
        var c = new ArrayList<String>();
        c.add("один"); c.add("два");
        var d = new int[]{1, 2, 3};
        var e = new VarDemo();

        System.out.println(a + " -> " + ((Object)a).getClass().getSimpleName());
        System.out.println(b + " -> " + b.getClass().getSimpleName());
        System.out.println(c + " -> " + c.getClass().getSimpleName());
        System.out.println((d.length > 0 ? "[1, 2, 3]" : "") + " -> int[]");
        System.out.println("VarDemo -> " + e.getClass().getSimpleName());

        /*
        Ошибки компиляции при использовании var:
        // 1. var без инициализации: (компилятор не может вывести тип)
        var uninit; 
        
        // 2. var как параметр метода: (типы параметров должны быть строго заданы)
        void method(var x) { } 
        
        // 3. var как поле класса: (var доступен только для локальных переменных)
        class Test { var field = "123"; } 
        
        // 4. var с null: (тип не определён)
        var nothing = null;
        */
    }
}
