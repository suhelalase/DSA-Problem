class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;

        int left = n;
        int right = -1;

        int maxSeen = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxSeen = Math.max(maxSeen, nums[i]);
            if (nums[i] < maxSeen) {
                right = i;
            }
        }

        int minSeen = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            minSeen = Math.min(minSeen, nums[i]);
            if (nums[i] > minSeen) {
                left = i;
            }
        }

        return right == -1 ? 0 : right - left + 1;
    }
}