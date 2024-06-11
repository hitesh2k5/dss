public class ms {

    // Method to sort an array using Merge Sort
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;  // Base case: arrays with less than 2 elements are already sorted
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // Divide the array into two halves
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        // Recursively sort each half
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(array, left, right);
    }

    // Method to merge two sorted subarrays into a single sorted array
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Merge elements from left and right arrays into the original array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copy any remaining elements of left array
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Copy any remaining elements of right array
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Main method to test the merge sort
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        System.out.println("Original Array:");
        printArray(array);

        mergeSort(array);

        System.out.println("Sorted Array:");
        printArray(array);
    }

    // Method to print an array
    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
