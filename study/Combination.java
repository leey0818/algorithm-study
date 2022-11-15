public class Combination {
    public static void main(String[] args) {
        // 1 2 3 4
        // 2개
        // 4c2
        int[] num = {1, 2, 3, 4};
        boolean[] visit = new boolean[4];
        int[] result = new int[2];

        comb(result, num, visit, 0, 0);
    }

    private static void comb(int[] result, int[] num, boolean[] visit, int depth, int start) {
        if (depth == 2) {
            for (int i = 0; i < 2; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < 4; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            result[depth] = num[i];
            comb(result, num, visit, depth + 1, i + 1);
            visit[i] = false;
        }
    }
}
