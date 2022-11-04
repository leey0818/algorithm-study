import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_4358_생태학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        Map<String, Integer> treeMap = new HashMap<>();
        int totalCount = 0;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if ("".equals(line)) break;

            totalCount += 1;

            treeMap.put(line, treeMap.getOrDefault(line, 0) + 1);
        }

        String[] treeNames = treeMap.keySet().toArray(new String[0]);
        Arrays.sort(treeNames);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < treeNames.length; i++) {
            line = treeNames[i];
            double ratio = ((double)treeMap.get(line)) / ((double)totalCount);

            sb.append(String.format("%s %.4f", line, ratio * 100))
                .append(System.lineSeparator());
        }

        System.out.print(sb.toString());
    }
}
