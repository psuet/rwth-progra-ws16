import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FunctionalSet<Integer> s = new FunctionalSet<>();
        String line;
        System.out.println("Enter 'add i', 'remove i', 'min', or 'exit'!");
        do {
            line = scanner.next();
            switch (line) {
            case "exit": break;
            case "add":
                line = scanner.next();
                s.add(Integer.parseInt(line));
                System.out.println(s);
                break;
            case "remove":
                line = scanner.next();
                s.remove(Integer.parseInt(line));
                System.out.println(s);
                break;
            case "min":
                System.out.println(s.min(Comparator.<Integer>naturalOrder()));
                break;
            default:
                System.out.println("Unknown command.");
            }
        } while (!"exit".equals(line));
    }
}
