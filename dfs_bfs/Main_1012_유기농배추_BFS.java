import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1012_유기농배추_BFS {
    private final static int[][] drt = new int[][]{{0, 0, -1, 1}, {-1, 1, 0, 0}}; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine());
            int nSize = Integer.parseInt(st.nextToken());
            int mSize = Integer.parseInt(st.nextToken());
            int cabCount = Integer.parseInt(st.nextToken());
            boolean[][] farmland = new boolean[mSize][nSize];
            boolean[][] visitPos = new boolean[mSize][nSize];

            for (int n = 0; n < cabCount; n++) {
                st = new StringTokenizer(br.readLine());
                int cabN = Integer.parseInt(st.nextToken());
                int cabM = Integer.parseInt(st.nextToken());

                farmland[cabM][cabN] = true;
            }

            int wormCount = 0;
            for (int m = 0; m < mSize; m++) {
                for (int n = 0; n < nSize; n++) {
                    if (farmland[m][n] && !visitPos[m][n]) {
                        // 현재 위치 저장
                        visitPos[m][n] = true;

                        // 인접한 배추 찾기
                        findAroundCabbage(farmland, visitPos, n, m);

                        wormCount += 1;
                    }
                }
            }

            System.out.println(wormCount);
        }
    }

    private static void findAroundCabbage(boolean[][] farmland, boolean[][] visitPos, int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n, m});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + drt[0][d];
                int ny = cur[1] + drt[1][d];

                // 다음으로 이동할 위치가 농장지 밖이면 무시
                if (ny >= farmland.length || nx >= farmland[0].length || nx < 0 || ny < 0) continue;

                // 다음으로 이동할 위치를 이미 방문했으면 무시
                if (visitPos[ny][nx]) continue;

                // 다음으로 이동할 위치에 배추가 없으면 무시
                if (!farmland[ny][nx]) continue;

                // 다음 위치를 Queue에 저장
                queue.offer(new int[]{nx, ny});

                // Queue에 저장한 위치를 방문으로 표시
                visitPos[ny][nx] = true;
            }
        }
    }
}
