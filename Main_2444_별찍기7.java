import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_2444_별찍기7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        printStar(bw, size);
    }

    public static void printStar(BufferedWriter bw, int size) throws IOException {
        int lineCount = (size * 2) - 1;

        for (int i = 0; i < lineCount; i++) {
            int starCount = i >= size ? (lineCount - i - 1) : i;
            starCount += starCount + 1;

            int spaceSize = (lineCount - starCount) / 2;
            for (int n = 0; n < lineCount; n++) {
                if (n < spaceSize) {
                    bw.write(" ");
                } else if (n < spaceSize + starCount) {
                    bw.write("*");
                } else {
                    break;
                }
            }

            bw.write("\n");
        }

        bw.flush();
    }
}
