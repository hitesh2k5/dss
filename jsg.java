import java.util.Arrays;

public class jsg {

    // Job class to store id, deadline, and profit of jobs
    static class Job {
        int id;
        int deadline;
        int profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    // Method to schedule jobs to maximize profit
    public static int[] jobScheduling(Job[] jobs, int n) {
        // Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Array to keep track of free time slots
        boolean[] slot = new boolean[n];
        // Array to store result (sequence of jobs)
        int[] result = new int[n];

        // Initialize all slots to be free
        Arrays.fill(slot, false);

        // Iterate through all given jobs
        for (int i = 0; i < jobs.length; i++) {
            // Find a free slot for this job (we start from the last possible slot)
            for (int j = Math.min(n, jobs[i].deadline) - 1; j >= 0; j--) {
                // Free slot found
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = jobs[i].id;
                    break;
                }
            }
        }

        return result;
    }

    // Main method to test the job scheduling
    public static void main(String[] args) {
        Job[] jobs = {
            new Job(1, 2, 100),
            new Job(2, 1, 19),
            new Job(3, 2, 27),
            new Job(4, 1, 25),
            new Job(5, 3, 15)
        };

        int n = 3; // Maximum number of time slots

        int[] result = jobScheduling(jobs, n);

        System.out.println("Job sequence to maximize profit:");
        for (int job : result) {
            if (job != 0) { // Ignore unused slots
                System.out.print(job+"--> ");
            }
        }
    }
}
