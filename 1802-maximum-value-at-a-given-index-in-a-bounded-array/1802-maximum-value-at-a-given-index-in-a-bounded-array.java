class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (requiredSum(n, index, mid) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private long requiredSum(int n, int index, int peak) {
        return calc(index, peak - 1) + peak + calc(n - index - 1, peak - 1);
    }

    private long calc(int len, int peak) {
        if (len == 0) {
            return 0;
        }

        if (peak >= len) {
            long first = peak - len + 1;
            return (first + peak) * (long) len / 2;
        }

        return (1L + peak) * peak / 2 + (len - peak);
    }
}