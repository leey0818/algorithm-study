import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2636_치즈 {
    private static final int[] dw = {0, 0, -1, 1};
    private static final int[] dh = {-1, 1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int boardH = Integer.parseInt(st.nextToken());
        int boardW = Integer.parseInt(st.nextToken());
        int cheezeCount = 0;
        boolean[][] cheeze = new boolean[boardH][boardW];

        for (int h = 0; h < boardH; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < boardW; w++) {
                cheeze[h][w] = "1".equals(st.nextToken());
                if (cheeze[h][w]) cheezeCount += 1;
            }
        }

        int meltHour = 0;
        int lastCheezeCount = 0;
        while (true) {
            // 외부공기 영역을 찾는다
            boolean[][] outline = new boolean[boardH][boardW];
            boolean[][] visit = new boolean[boardH][boardW];
            findCheezeOutline(cheeze, outline, visit, 0, 0);

            // 치즈 외곽선을 공기영역으로 변경
            int outlineCount = 0;
            for (int h = 0; h < boardH; h++) {
                for (int w = 0; w < boardW; w++) {
                    if (outline[h][w]) {
                        cheeze[h][w] = false;

                        // 외곽선 치즈 갯수 하나 늘림
                        outlineCount += 1;
                    }
                }
            }

            // 녹는 시간 추가
            meltHour += 1;

            // 치즈 갯수 빼기
            cheezeCount -= outlineCount;

            // 치즈 갯수가 0개로 되면 끝내기
            if (cheezeCount <= 0) {
                lastCheezeCount = outlineCount;
                break;
            }
        }

        System.out.println(meltHour);
        System.out.println(lastCheezeCount);
    }

    private static void findCheezeOutline(boolean[][] cheeze, boolean[][] outline, boolean[][] visit, int x, int y) {
        visit[y][x] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dw[d];
            int ny = y + dh[d];

            // 맵 밖이면 무시
            if (nx < 0 || ny < 0 || nx >= cheeze[0].length || ny >= cheeze.length) continue;

            // 이미 방문한 위치는 무시
            if (visit[ny][nx]) continue;

            // 이동할 위치가 치즈이면 외곽선으로 표시 후 건너뜀
            if (cheeze[ny][nx]) {
                outline[ny][nx] = true;
                continue;
            }

            findCheezeOutline(cheeze, outline, visit, nx, ny);
        }
    }

}
