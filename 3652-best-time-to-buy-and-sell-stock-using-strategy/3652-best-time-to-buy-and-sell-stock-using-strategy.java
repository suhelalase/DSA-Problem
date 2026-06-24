class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;

        long baseProfit = 0;
        for (int i = 0; i < n; i++) {
            baseProfit += (long) strategy[i] * prices[i];
        }

        long[] prefixContribution = new long[n + 1];
        long[] prefixPrices = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefixContribution[i + 1] =
                    prefixContribution[i] + (long) strategy[i] * prices[i];
            prefixPrices[i + 1] =
                    prefixPrices[i] + prices[i];
        }

        long maxProfit = baseProfit;
        int half = k / 2;

        for (int l = 0; l + k <= n; l++) {
            int r = l + k;

            long oldWindow =
                    prefixContribution[r] - prefixContribution[l];

            long newWindow =
                    prefixPrices[r] - prefixPrices[l + half];

            long modifiedProfit =
                    baseProfit - oldWindow + newWindow;

            maxProfit = Math.max(maxProfit, modifiedProfit);
        }

        return maxProfit;
    }
}
