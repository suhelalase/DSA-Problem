class Solution {
    public int splitArray(int[] nums, int k) {
        int left = 0;
        long right = 0;

        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            int mid = (int) (left + (right - left) / 2);

            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canSplit(int[] nums, int k, int limit) {
        int parts = 1;
        int sum = 0;

        for (int num : nums) {
            if (sum + num > limit) {
                parts++;
                sum = num;
            } else {
                sum += num;
            }
        }

        return parts <= k;
    }
}