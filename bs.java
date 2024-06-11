public class bs{

    // Binary Search function
    public static int binarySearch(int[] array, int target) {
        return binarySearch(array, target, 0, array.length - 1);
    }

    // Helper function that performs the recursive binary search
    private static int binarySearch(int[] array, int target, int left, int right) {
        if (left <= right) {
            int middle = left + (right - left) / 2;  // Calculate the middle index

            // Check if the middle element is the target
            if (array[middle] == target) {
                return middle;
            }

            // If target is less than the middle element, search the left subarray
            if (array[middle] > target) {
                return binarySearch(array, target, left, middle - 1);
            }

            // If target is greater than the middle element, search the right subarray
            return binarySearch(array, target, middle + 1, right);
        }

        // Return -1 if the target is not found
        return -1;
    }

    // Main method to test the binary search
    public static void main(String[] args) {
        int[] sortedArray = {2, 3, 4, 10, 40};
        int target = 10;
        int result = binarySearch(sortedArray, target);

        if (result == -1) {
            System.out.println("Element not present in the array");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }
}
