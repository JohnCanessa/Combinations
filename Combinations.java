// **** imports ****
import java.util.*;


/**
 * Factorial
 */
class Factorial {

    /**
     * Constructor.
     */
    Factorial() {
    }

    /**
     * Compute the factorial of the specified number.
     * Recursive call.
     */
    public long factorial(long n) {

        // **** check if we are done ****
        if (n == 1)
            return 1;

        // **** recurse ****
        return n * factorial(n - 1);
    }

    /**
     * Compute n * (n - 1) * ... * (r - 1)
     */
    public long multNtoR(long n, long r) {
        return multUtil(n, r);
    }

    /**
     * Recursive call.
     */
    private long multUtil(long n, long r) {

        // **** check if we are done ****
        if (n <= r)
            return 1;

        // **** recurse ****
        return n * multUtil(n - 1, r);
    }
}


/**
 * Generate combinations.
 */
public class Combinations {

    // **** ****
    private long data[];
    private long comb[][];
    private int count;

    /**
     * Get the generated number of combination.
     * We keep track of this count to verify our calculation.
     */
    public long getCount() {
        return this.count;
    }

    /**
     * Return a string with all the generated combinations.
     */
    @Override
    public String toString() {

        // **** ****
        StringBuilder sb = new StringBuilder();

        // **** ****
        for (int i = 0; i < this.comb.length; i++) {
            for (int j = 0; j < this.comb[i].length; j++)
                sb.append(this.comb[i][j] + " ");
            sb.append("\n");
        }

        // **** ****
        return sb.toString();
    }

    /**
     * Computes the count of combinations.
     * 
     *                n!          n * (n - 1) ... (r - 1) * r!     n * (n - 1) ... (r - 1)
     * count = --------------- = ------------------------------ = -------------------------
     *          r! * (n - r)!           r! * (n - r)!                    (n - r)!
     */
    public int computeCount(long n, long r) {

        // **** value to return ****
        long count = 0;

        // **** ****
        Factorial fact = new Factorial();

        // **** brute force ****
        count = fact.factorial(n);
        count /= fact.factorial(r);
        count /= fact.factorial(n - r);

        // **** better approach ****
        count = fact.multNtoR(n, r);
        count /= fact.factorial(n - r);

        // **** return the computed count ****
        return (int)count;
    }

    /**
     * Recursive method.
     */
    private void combinationUtil(int arr[], int start, int end, int index, int r) {

        // **** determine if we have a complete combination ****
        if (index == r) {

            // **** copy data to array of combinations ****
            this.comb[this.count] = Arrays.copyOf(this.data, this.data.length);

            // **** increment the count ****
            count++;

            // **** nothing else to do ****
            return;
        }

        // **** ****
        for (int i = start; (i <= end) && (end - i + 1) >= (r - index); i++) {
            this.data[index] = arr[i];
            combinationUtil(arr, i + 1, end, index + 1, r);
        }
    }

    /**
     * Constructor
     */
    Combinations(int arr[], int n, int r) {

        // **** ****
        this.data = new long[r];
        this.count = 0;

        // **** compute the number of combinations ****
        int count = computeCount(n, r);

        // **** allocate an array to hold the combinations ****
        this.comb = new long[count][r];

        // **** generate all combinations ****
        combinationUtil(arr, 0, n - 1, 0, r);
    }

    /**
     * Test scaffolding 
     */
    public static void main(String[] args) {
        
        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** get the number of elements for the integer array ****
        int n = sc.nextInt();

        // ???? ????
        System.out.println("main <<<   n: " + n);

        // **** read r ****
        int r = sc.nextInt();

        // ???? ????
        System.out.println("main <<<   r: " + r);

        // **** ****
        int arr[] = new int[n];

        // **** read the values ****
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // ???? ????
        System.out.println("main <<< arr: " + Arrays.toString(arr));
        System.out.println();

         // **** close scanner ****
         sc.close();

        // **** ****
        Factorial fact = new Factorial();
        System.out.println("main <<< " + n + "!: " + fact.factorial(n));
        System.out.println("main <<< " + r + "!: " + fact.factorial(r));
        System.out.println();

        // **** generate all combinations ****
        Combinations combinations = new Combinations(arr, n, r);

        // **** display all combinations ****
        System.out.println("main <<< combinations:\n" + combinations.toString());

        // **** ****
        System.out.println("main <<< computeCount(): " + combinations.computeCount(n, r));
        System.out.println("main <<<     getCount(): " + combinations.getCount());
    }
}