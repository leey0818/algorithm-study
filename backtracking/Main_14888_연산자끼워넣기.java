import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14888_연산자끼워넣기 {
    private static final char[] operChars = {'+', '-', '*', '/'};
    private static int N;
    private static int O;
    private static int minNum = Integer.MAX_VALUE;
    private static int maxNum = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 정수 읽기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 읽기
        Integer[] indexes = readOperatorIndexes(br.readLine());
        O = indexes.length;

        // long startTime = System.currentTimeMillis();

        // 값 계산
        boolean[] visit = new boolean[O];
        dfs(numArr, indexes, visit, numArr[0], 0);

        // System.out.println("Duration: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println(maxNum);
        System.out.println(minNum);
    }


    private static void dfs(int[] numArr, Integer[] indexes, boolean[] visit, int acc, int depth) {
        if (depth == O) {
            if (acc > maxNum) maxNum = acc;
            if (acc < minNum) minNum = acc;
            return;
        }

        for (int i = 0; i < O; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            dfs(numArr, indexes, visit, calculateNumber(indexes[i], acc, numArr[depth + 1]), depth + 1);
            visit[i] = false;
        }
    }


    private static int calculateNumber(int operIdx, int acc, int num) {
        char oper = operChars[operIdx];
        if (oper == '+') return acc + num;
        if (oper == '-') return acc - num;
        if (oper == '*') return acc * num;
        if (oper == '/') return acc / num;
        return acc;
    }


    private static Integer[] readOperatorIndexes(String line) {
        StringTokenizer st = new StringTokenizer(line);
        List<Integer> indexes = new ArrayList<>();
        int idx = 0;
        int max = 0;
        for (int i = 0; i < 4; i++) {
            max = idx + Integer.parseInt(st.nextToken());
            for (; idx < max; idx++) {
                indexes.add(i);
            }
        }
        return indexes.toArray(new Integer[0]);
    }
}
