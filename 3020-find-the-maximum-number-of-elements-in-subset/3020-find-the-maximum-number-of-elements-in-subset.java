class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();

        for (int x : nums) {
            cnt.put((long) x, cnt.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        if (cnt.containsKey(1L)) {
            int c = cnt.get(1L);
            ans = Math.max(ans, c % 2 == 1 ? c : c - 1);
        }

        for (long start : cnt.keySet()) {
            if (start == 1) continue;

            long cur = start;
            int len = 0;

            while (true) {
                int f = cnt.getOrDefault(cur, 0);

                if (f < 2) {
                    if (f == 1) len++;
                    else if (len > 0) len--;
                    break;
                }

                if (cur > 1000000000L / cur || !cnt.containsKey(cur * cur)) {
                    len++;
                    break;
                }

                len += 2;
                cur *= cur;
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}