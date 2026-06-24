class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int s = 2 * m;

        long[] dp = new long[s];

        for (int v = 1; v <= m; v++) {
            dp[v - 1] = v - 1;
            dp[m + v - 1] = m - v;
        }

        long[][] mat = new long[s][s];

        for (int v = 1; v <= m; v++) {
            int up = v - 1;
            int down = m + v - 1;

            for (int w = 1; w < v; w++) {
                mat[up][m + w - 1] = 1;
            }

            for (int w = v + 1; w <= m; w++) {
                mat[down][w - 1] = 1;
            }
        }

        long p = n - 2;

        while (p > 0) {
            if ((p & 1) == 1) {
                dp = multiply(dp, mat);
            }
            mat = multiply(mat, mat);
            p >>= 1;
        }

        long ans = 0;

        for (long x : dp) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[] vec, long[][] mat) {
        int n = vec.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            if (vec[i] == 0) continue;

            long v = vec[i];

            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 0) {
                    res[j] = (res[j] + v * mat[i][j]) % MOD;
                }
            }
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                long v = a[i][k];

                for (int j = 0; j < n; j++) {
                    if (b[k][j] != 0) {
                        res[i][j] = (res[i][j] + v * b[k][j]) % MOD;
                    }
                }
            }
        }

        return res;
    }
}