class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        int[] values = prefix.clone();
        java.util.Arrays.sort(values);

        int m = 1;
        for (int i = 1; i < values.length; i++) {
            if (values[i] != values[m - 1]) {
                values[m++] = values[i];
            }
        }

        Fenwick bit = new Fenwick(m);
        long ans = 0;

        for (int x : prefix) {
            int idx = lowerBound(values, m, x) + 1;
            ans += bit.query(idx - 1);
            bit.add(idx, 1);
        }

        return ans;
    }

    private int lowerBound(int[] a, int n, int target) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (a[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    static class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 2];
        }

        void add(int i, int v) {
            while (i < tree.length) {
                tree[i] += v;
                i += i & -i;
            }
        }

        int query(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i -= i & -i;
            }
            return res;
        }
    }
}