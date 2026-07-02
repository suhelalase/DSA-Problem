class Solution {
    private final String[] map = {
        "", "", "abc", "def", "ghi", "jkl",
        "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;
        backtrack(digits, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backtrack(String digits, int idx, StringBuilder sb, List<String> ans) {
        if (idx == digits.length()) {
            ans.add(sb.toString());
            return;
        }

        String letters = map[digits.charAt(idx) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            backtrack(digits, idx + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}