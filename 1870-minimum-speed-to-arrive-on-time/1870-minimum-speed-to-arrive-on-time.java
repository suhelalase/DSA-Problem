class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;

        if (hour <= n - 1) {
            return -1;
        }

        int left = 1, right = 10000000;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canArrive(dist, hour, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canArrive(int[] dist, double hour, int speed) {
        double time = 0;

        for (int i = 0; i < dist.length - 1; i++) {
            time += (dist[i] + speed - 1) / speed;
        }

        time += (double) dist[dist.length - 1] / speed;

        return time <= hour;
    }
}