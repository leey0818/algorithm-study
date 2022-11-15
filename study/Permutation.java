/**
 * 순열
 * 1 2 3 4 5
 * 3개
 * 5p3
 */
public class Permutation {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5};
        boolean[] visit = new boolean[5];
        int[] result = new int[3];

        perm(result, num, visit, 0);
    }

    private static void perm(int[] result, int[] num, boolean[] visit, int depth) {
        if (depth == 3) {
            for (int i = 0; i < 3; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            result[depth] = num[i];
            perm(result, num, visit, depth + 1);
            visit[i] = false;
        }
    }
}
