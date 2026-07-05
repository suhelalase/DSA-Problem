class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int[] candidates, int target, int idx, List<Integer> cur, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (candidates[i] > target) continue;
            cur.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}