import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1012_유기농배추_DFS {
    private final static int[][] drt = new int[][]{{0, 0, -1, 1}, {-1, 1, 0, 0}}; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine());
            int xSize = Integer.parseInt(st.nextToken());
            int ySize = Integer.parseInt(st.nextToken());
            int cabCount = Integer.parseInt(st.nextToken());
            boolean[][] farmland = new boolean[ySize][xSize];
            boolean[][] visitPos = new boolean[ySize][xSize];

            for (int n = 0; n < cabCount; n++) {
                st = new StringTokenizer(br.readLine());
                int cabX = Integer.parseInt(st.nextToken());
                int cabY = Integer.parseInt(st.nextToken());

                farmland[cabY][cabX] = true;
            }

            int wormCount = 0;
            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < xSize; x++) {
                    // 배추가 있으면서 방문한 위치가 아닐때
                    if (farmland[y][x] && !visitPos[y][x]) {
                        // 인접한 배추를 찾아 방문
                        findAroundCabbage(farmland, visitPos, x, y);

                        // 다 찾으면 지렁이 추가
                        wormCount += 1;
                    }
                }
            }

            System.out.println(wormCount);
        }
    }

    private static void findAroundCabbage(boolean[][] farmland, boolean[][] visitPos, int x, int y) {
        // 현재 위치 저장
        visitPos[y][x] = true;

        // 4방탐색 (상하좌우)
        for (int d = 0; d < 4; d++) {
            int nx = x + drt[0][d];
            int ny = y + drt[1][d];

            // 다음으로 이동할 위치가 농장지 밖이면 무시
            if (ny >= farmland.length || nx >= farmland[0].length || nx < 0 || ny < 0) continue;

            // 다음으로 이동할 위치가 이미 방문한 위치이면 무시
            if (visitPos[ny][nx]) continue;

            // 다음으로 이동할 위차가 배추위치가 아니면 무시
            if (!farmland[ny][nx]) continue;

            // 다음 위치로 이동
            findAroundCabbage(farmland, visitPos, nx, ny);
        }
    }
}