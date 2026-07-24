class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int t = 1; t <= 3; t++) {
            for (int x = 0; x < MAX; x++) {
                if (!dp[t - 1][x]) continue;
                for (int v : nums) {
                    dp[t][x ^ v] = true;
                }
            }
        }

        int ans = 0;
        for (boolean b : dp[3]) {
            if (b) ans++;
        }
        return ans;
    }
}