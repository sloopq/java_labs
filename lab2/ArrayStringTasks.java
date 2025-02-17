import java.util.*;

public class ArrayStringTasks {

    public static String longestUniqueSubstring(String s) {
        Map<Character, Integer> charIndex = new HashMap<>();
        int left = 0, maxLength = 0, start = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                left = charIndex.get(c) + 1;
            }
            charIndex.put(c, right);
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
        }
        return s.substring(start, start + maxLength);
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            merged[k++] = arr1[i] < arr2[j] ? arr1[i++] : arr2[j++];
        }
        while (i < arr1.length) merged[k++] = arr1[i++];
        while (j < arr2.length) merged[k++] = arr2[j++];
        return merged;
    }

    public static int maxSubarraySum(int[] arr) {
        int maxSum = Integer.MIN_VALUE, currentSum = 0;
        for (int num : arr) {
            currentSum = Math.max(num, currentSum + num);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static int[][] rotateMatrixClockwise(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }

    public static int[] findPairWithSum(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return new int[]{complement, num};
            }
            seen.add(num);
        }
        return null;
    }

    public static int sum2DArray(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sum += num;
            }
        }
        return sum;
    }

    public static int[] maxInRows(int[][] matrix) {
        int[] maxValues = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            maxValues[i] = Arrays.stream(matrix[i]).max().orElse(Integer.MIN_VALUE);
        }
        return maxValues;
    }

    public static int[][] rotateMatrixCounterclockwise(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[n - 1 - j][i] = matrix[i][j];
            }
        }
        return rotated;
    }

    public static void main(String[] args) {
        System.out.println(longestUniqueSubstring("abcabcbbdafck"));
        System.out.println(Arrays.toString(mergeSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6})));
        System.out.println(maxSubarraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(Arrays.deepToString(rotateMatrixClockwise(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
        System.out.println(Arrays.toString(findPairWithSum(new int[]{2,7,11,15}, 9)));
        System.out.println(sum2DArray(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        System.out.println(Arrays.toString(maxInRows(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
        System.out.println(Arrays.deepToString(rotateMatrixCounterclockwise(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
    }
}
