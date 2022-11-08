import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2644_촌수계산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Person[] arr = new Person[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T1 = Integer.parseInt(st.nextToken()) - 1;
        int T2 = Integer.parseInt(st.nextToken()) - 1;
        int M = Integer.parseInt(br.readLine());

        int n1, n2;
        Person np1, np2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());

            np1 = arr[n1 - 1];
            np2 = arr[n2 - 1];
            if (np1 == null) {
                np1 = new Person(n1);
                arr[n1 - 1] = np1;
            }
            if (np2 == null) {
                np2 = new Person(n2);
                arr[n2 - 1] = np2;
            }

            np2.setParent(np1);
        }

        System.out.println(bfs(arr[T1], arr[T2]));
    }

    private static int bfs(Person p1, Person p2) {
        Queue<Person> queue = new LinkedList<>();
        queue.offer(p1);
        p1.visit = true;

        int distance = -1;
        while (!queue.isEmpty()) {
            Person cur = queue.poll();

            // 원하던 사람을 찾음
            if (cur.no == p2.no) {
                distance = cur.distance;
                break;
            }

            // 내 상위 확인
            if (cur.parent != null && !cur.parent.visit) {
                cur.parent.distance = cur.distance + 1;
                cur.parent.visit = true;
                queue.offer(cur.parent);
            }

            // 내 하위 확인
            int count = cur.getChildren().size();
            for (int i = 0; i < count; i++) {
                Person child = cur.getChild(i);
                if (!child.visit) {
                    child.distance = cur.distance + 1;
                    child.visit = true;
                    queue.offer(child);
                }
            }
        }

        return distance;
    }

    static class Person {
        final List<Person> children = new LinkedList<>();
        final int no;
        Person parent;
        int distance = 0;
        boolean visit = false;

        public Person(int no) {
            this.no = no;
        }

        public void setParent(Person parent) {
            this.parent = parent;
            parent.addChild(this);
        }

        public void addChild(Person child) {
            this.children.add(child);
        }

        public List<Person> getChildren() {
            return this.children;
        }

        public Person getChild(int idx) {
            return this.children.get(idx);
        }
    }
}
