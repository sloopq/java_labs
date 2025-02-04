import java.util.Scanner;

public class LogisticMaximin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numRoads = scanner.nextInt();
        int bestRoad = 0;
        int bestHeight = 0;

        for (int i = 0; i < numRoads; i++) {
            int numTunnels = scanner.nextInt();
            int minHeight = Integer.MAX_VALUE;

            for (int j = 0; j < numTunnels; j++) {
                int height = scanner.nextInt();
                if (height < minHeight) {
                    minHeight = height;
                }
            }

            if (minHeight > bestHeight) {
                bestHeight = minHeight;
                bestRoad = i + 1;
            }
        }

        System.out.println(bestRoad + " " + bestHeight);
    }
}