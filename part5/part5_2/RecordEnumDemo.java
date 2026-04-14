package part5.part5_2;

record Temperature(double value, Unit unit) {
    enum Unit { CELSIUS, FAHRENHEIT, KELVIN }

    public Temperature {
        double kelvin = switch (unit) {
            case CELSIUS -> value + 273.15;
            case FAHRENHEIT -> (value - 32) * 5.0 / 9.0 + 273.15;
            case KELVIN -> value;
        };
        if (kelvin < 0) throw new IllegalArgumentException("Температура ниже абсолютного нуля");
    }

    public Temperature convertTo(Unit targetUnit) {
        if (unit == targetUnit) return this;
        double kelvin = switch (unit) {
            case CELSIUS -> value + 273.15;
            case FAHRENHEIT -> (value - 32) * 5.0 / 9.0 + 273.15;
            case KELVIN -> value;
        };
        double target = switch (targetUnit) {
            case CELSIUS -> kelvin - 273.15;
            case FAHRENHEIT -> (kelvin - 273.15) * 9.0 / 5.0 + 32;
            case KELVIN -> kelvin;
        };
        return new Temperature(target, targetUnit);
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US, "%.2f %s", value, switch (unit) {
            case CELSIUS -> "°C"; case FAHRENHEIT -> "°F"; case KELVIN -> "K";
        });
    }
}

enum MathOperation {
    ADD { public double apply(double a, double b) { return a + b; } },
    SUBTRACT { public double apply(double a, double b) { return a - b; } },
    MULTIPLY { public double apply(double a, double b) { return a * b; } },
    DIVIDE { 
        public double apply(double a, double b) { 
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b; 
        } 
    };
    public abstract double apply(double a, double b);
}

public class RecordEnumDemo {
    public static void main(String[] args) {
        Temperature body = new Temperature(36.6, Temperature.Unit.CELSIUS);
        System.out.println(body);
        System.out.println(body.convertTo(Temperature.Unit.FAHRENHEIT));
        System.out.println(body.convertTo(Temperature.Unit.KELVIN));

        double a = 10, b = 3;
        for (MathOperation op : MathOperation.values()) {
            System.out.printf("%s(%g, %g) = %g%n", op.name(), a, b, op.apply(a, b));
        }
    }
}
