import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        int test = in.nextInt();
        int temp;
        for (int i = 0; i < test; i++) {
            int numOfNodes = in.nextInt();
            int numOfEdges = in.nextInt();
            Map<Integer, Map<Integer, Integer>> list = new HashMap<>();

            for (int f = 1; f <= numOfNodes; f++) {

                list.put(f, new HashMap<>());

            }
            /*for (int q=1; q<=numOfNodes; q++) {
                for (int w=1; w<=numOfNodes; w++) {
                    list.get(q).put(w,Integer.MAX_VALUE);
                    list.get(w).put(q,Integer.MAX_VALUE);
                }
            }*/

            for (int j = 1; j <= numOfEdges; j++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                if (list.get(u).get(v) == null) {
                    list.get(u).put(v, w);
                    list.get(v).put(u, w);
                } else if (w < list.get(u).get(v)) {
                    list.get(u).put(v, w);
                    list.get(v).put(u, w);
                }

            }
            int start = in.nextInt();
            int[] distance = new int[numOfNodes + 1];
            boolean[] visited = new boolean[numOfNodes + 1];
            visited[0] = true;
            for (int k = 1; k <= numOfNodes; k++) {
                distance[k] = Integer.MAX_VALUE;
            }

            distance[start] = 0;
            int min = Integer.MAX_VALUE;
            int minIndex = 1;
            int z;
            for (int m = 0; m < numOfNodes; m++) {
                /*z=1;
                while(z < distance.length)
                {
                    if (!visited[z])
                    {
                        min = distance[z];
                        minIndex = z;
                        break;
                    }
                    z++;
                }*/

                min = Integer.MAX_VALUE;
                //int min = Integer.MAX_VALUE;
                //int minIndex = start;
                for (int l = 1; l <= numOfNodes; l++) {
                    if ((distance[l] < min) && !visited[l]) {
                        min = distance[l];
                        minIndex = l;
                    }
                }
                visited[minIndex] = true;

                for (int n = 1; n <= numOfNodes; n++) {
                    if ((list.get(minIndex).get(n) != null) && !visited[n]) {

                        temp = distance[minIndex] + list.get(minIndex).get(n);
                        if (temp < distance[n]) {
                            distance[n] = temp;
                        }
                    }
                }
            }
            for (int o = 0; o <= numOfNodes; o++) {
                if (distance[o] == Integer.MAX_VALUE)
                    distance[o] = -1;
            }
            distance[start] = distance[0] = 0;
            for (int h = 1; h <= numOfNodes; h++) {
                if (distance[h] != 0)
                    System.out.print(distance[h] + " ");
            }
            System.out.println();
        }
    }
}