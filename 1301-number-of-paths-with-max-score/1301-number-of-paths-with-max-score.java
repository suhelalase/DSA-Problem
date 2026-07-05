class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1_000_000_007;

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(score[i], -1);
        }

        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (board.get(i).charAt(j) == 'X' || (i == n - 1 && j == n - 1)) continue;

                int best = -1;
                long cnt = 0;

                int[][] prev = {{i + 1, j}, {i, j + 1}, {i + 1, j + 1}};

                for (int[] p : prev) {
                    int r = p[0], c = p[1];
                    if (r >= n || c >= n || score[r][c] == -1) continue;

                    if (score[r][c] > best) {
                        best = score[r][c];
                        cnt = ways[r][c];
                    } else if (score[r][c] == best) {
                        cnt = (cnt + ways[r][c]) % mod;
                    }
                }

                if (best == -1) continue;

                char ch = board.get(i).charAt(j);
                if (ch >= '1' && ch <= '9') best += ch - '0';

                score[i][j] = best;
                ways[i][j] = (int) (cnt % mod);
            }
        }

        if (ways[0][0] == 0) return new int[]{0, 0};
        return new int[]{score[0][0], ways[0][0]};
    }
}