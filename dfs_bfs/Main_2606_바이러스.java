import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Main_2606_바이러스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pcCount = Integer.parseInt(br.readLine());
        int readCount = Integer.parseInt(br.readLine());
        Computer[] pcArr = new Computer[pcCount];

        StringTokenizer st;
        int left, right;
        for (int i = 0; i < readCount; i++) {
            st = new StringTokenizer(br.readLine());
            left = Integer.parseInt(st.nextToken()) - 1;
            right = Integer.parseInt(st.nextToken()) - 1;

            if (pcArr[left] == null) pcArr[left] = new Computer(left);
            if (pcArr[right] == null) pcArr[right] = new Computer(right);

            pcArr[left].addNode(pcArr[right]);
            pcArr[right].addNode(pcArr[left]);
        }

        AtomicInteger count = new AtomicInteger();
        boolean[] visit = new boolean[pcCount];
        visit[0] = true;
        findLinkComputer(pcArr[0], visit, count);

        System.out.println(count.get());
    }

    private static void findLinkComputer(Computer pc, boolean[] visit, AtomicInteger count) {
        for (int i = 0, max = pc.nodes.size(); i < max; i++) {
            Computer node = pc.nodes.get(i);
            int idx = node.getIndex();

            if (visit[idx]) continue;

            visit[idx] = true;

            count.incrementAndGet();
            findLinkComputer(node, visit, count);
        }
    }

    private static class Computer {
        public final List<Computer> nodes = new LinkedList<>();
        private final int idx;

        public Computer(int idx) {
            this.idx = idx;
        }

        public void addNode(Computer node) {
            this.nodes.add(node);
        }

        public int getIndex() {
            return this.idx;
        }
    }
}
