import java.util.Scanner;

public class AlternatingSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                result += numbers[i];
            } else {
                result -= numbers[i];
            }
        }

        System.out.println(result);
    }
}