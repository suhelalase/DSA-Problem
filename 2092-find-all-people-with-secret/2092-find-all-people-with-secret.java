import java.util.*;

class Solution {

    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }

        void reset(int x) {
            parent[x] = x;
            rank[x] = 0;
        }
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        Arrays.sort(meetings, Comparator.comparingInt(a -> a[2]));

        DSU dsu = new DSU(n);

        dsu.union(0, firstPerson);

        int i = 0;
        while (i < meetings.length) {
            int time = meetings[i][2];
            List<int[]> sameTimeMeetings = new ArrayList<>();

            while (i < meetings.length && meetings[i][2] == time) {
                sameTimeMeetings.add(meetings[i]);
                i++;
            }

            for (int[] m : sameTimeMeetings) {
                dsu.union(m[0], m[1]);
            }

            for (int[] m : sameTimeMeetings) {
                if (dsu.find(m[0]) != dsu.find(0)) {
                    dsu.reset(m[0]);
                    dsu.reset(m[1]);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int person = 0; person < n; person++) {
            if (dsu.find(person) == dsu.find(0)) {
                result.add(person);
            }
        }

        return result;
    }
}
