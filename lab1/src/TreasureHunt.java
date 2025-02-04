import java.util.Scanner;

public class TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int xTreasure = scanner.nextInt();
        int yTreasure = scanner.nextInt();


        int x = 0, y = 0;


        int stepsCount = 0;


        boolean found = false;


        while (true) {
            String direction = scanner.next();
            if (direction.equals("стоп")) {
                break;
            }

            int distance = scanner.nextInt();
            stepsCount++;


            switch (direction) {
                case "север":
                    y += distance;
                    break;
                case "юг":
                    y -= distance;
                    break;
                case "восток":
                    x += distance;
                    break;
                case "запад":
                    x -= distance;
                    break;
            }

            if (x == xTreasure && y == yTreasure && !found) {
                System.out.println(stepsCount);
                found = true;
            }
        }
    }
}