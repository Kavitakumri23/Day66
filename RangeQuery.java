public class RangeQuery {
    // 🔹 Query Function
    public static int query(int[] blocks, int[] arr, int l, int r, int sqrt) {
        int ans = 0;

        // Step 1️⃣: Handle partial block (left side)
        while (l % sqrt != 0 && l <= r) {
            ans += arr[l];
            l++;
        }

        // Step 2️⃣: Handle full blocks
        while (l + sqrt - 1 <= r) {
            ans += blocks[l / sqrt];
            l += sqrt;
        }

        // Step 3️⃣: Handle remaining elements
        while (l <= r) {
            ans += arr[l];
            l++;
        }

        return ans;
    }

    // 🔹 Update Function
    public static void update(int[] blocks, int[] arr, int i, int val, int sqrt) {
        int block_id = i / sqrt;
        blocks[block_id] += (val - arr[i]);  // adjust block sum
        arr[i] = val;                        // update array value
    }

    // 🔹 Main Function
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 7, 6, 3, 1, 4, 8};
        int n = arr.length;

        int sqrt = (int) Math.sqrt(n);
        int[] blocks = new int[sqrt + 1];

        // ✅ Preprocessing: Build blocks
        int block_id = -1;
        for (int i = 0; i < n; i++) {
            if (i % sqrt == 0) {
                block_id++;
            }
            blocks[block_id] += arr[i];
        }

        // 🔹 Test Queries
        System.out.println("Sum of [1,4] = " + query(blocks, arr, 1, 4, sqrt));
        System.out.println("Sum of [2,8] = " + query(blocks, arr, 2, 8, sqrt));

        // 🔹 Update element at index 3
        update(blocks, arr, 3, 10, sqrt);
        System.out.println("After update: Sum of [1,4] = " + query(blocks, arr, 1, 4, sqrt));
    }
}

