import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2573_빙산 {
    private static final int[] dh = {-1, 1, 0, 0};
    private static final int[] dw = {0, 0, -1, 1};  // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] changeMap = new int[N][M];
        Queue<int[]> mountain = new LinkedList<>();

        for (int h = 0; h < N; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < M; w++) {
                map[h][w] = Integer.parseInt(st.nextToken());
                if (map[h][w] > 0) {
                    mountain.offer(new int[]{w, h});
                }
            }
        }

        boolean isSeparated = false;
        int meltYear = 0;

        while (!mountain.isEmpty()) {
            for (int max = mountain.size(); max > 0; max--) {
                int[] cur = mountain.poll();
                int curX = cur[0];
                int curY = cur[1];
                int curH = map[curY][curX];
    
                // 수위 증가...
                int nextH = getNextMountainHeight(map, curX, curY, curH);
                if (nextH <= 0) {
                    changeMap[curY][curX] = 0;
                } else {
                    changeMap[curY][curX] = nextH;
                    mountain.offer(new int[]{curX, curY});
                }
            }

            // 변경점이 있으면 수정
            for (int h = 0; h < N; h++) {
                for (int w = 0; w < M; w++) {
                    map[h][w] = changeMap[h][w];
                    changeMap[h][w] = 0;
                }
            }

            // 녹는 년수 + 1
            meltYear += 1;

            // 빙산이 2개로 나뉘어졌는지 확인
            int mountainCount = getMountainCount(map, N, M);
            if (mountainCount > 1) {
                isSeparated = true;
                break;
            }
        }

        System.out.println(isSeparated ? meltYear : 0);
    }

    private static int getNextMountainHeight(int[][] map, int x, int y, int h) {
        int nh = h;

        // 4방탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dw[d];
            int ny = y + dh[d];

            // 지도 밖이면 무시
            if (nx < 0 || ny < 0 || nx >= map[0].length || ny >= map.length) continue;

            // 위치가 바다일때 높이 - 1
            if (map[ny][nx] == 0) nh -= 1;
        }

        return nh;
    }

    private static int getMountainCount(int[][] map, int N, int M) {
        boolean[][] visit = new boolean[N][M];
        int islandCount = 0;

        for (int h = 0; h < N; h++) {
            for (int w = 0; w < M; w++) {
                if (map[h][w] > 0 && !visit[h][w]) {
                    // 주변 빙산 찾기
                    findNearMountain(map, visit, w, h);

                    // 빙산 갯수 추가
                    islandCount += 1;
                }
            }
        }

        return islandCount;
    }

    private static void findNearMountain(int[][] map, boolean[][] visit, int x, int y) {
        // 현재 위치 방문처리
        visit[y][x] = true;

        // 4방 탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dw[d];
            int ny = y + dh[d];

            // 지도 밖이면 무시
            if (nx < 0 || ny < 0 || nx >= map[0].length || ny >= map.length) continue;

            // 방문한 위치면 무시
            if (visit[ny][nx]) continue;

            // 빙산이 아니면 무시
            if (map[ny][nx] == 0) continue;

            // 다음 위치 이동
            findNearMountain(map, visit, nx, ny);
        }
    }
}
