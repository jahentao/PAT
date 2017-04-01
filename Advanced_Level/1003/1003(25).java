import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final int N, M, C1, C2;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        C1 = scanner.nextInt();
        C2 = scanner.nextInt();
        int[] teams = new int[N];
        for(int i=0; i<N; i++) {
            teams[i] = scanner.nextInt();
        }
        int[][] w = new int[N][N];
        for(int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if(i==j)
                    w[i][i] = 0;
                else
                    w[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int k=0; k<M; k++) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            w[i][j] = w[j][i] =scanner.nextInt();
        }

        int[] S =  new int[N];
        int[] dist = new int[N];
        for (int i=0; i<N; i++)
            dist[i] = Integer.MAX_VALUE;

        int pathcount[] = new int[N];
        int maxteams[] = new int[N];
        dist[C1] = 0;
        pathcount[C1]=1;
        maxteams[C1] = teams[C1];

        int cnt = C1;
        for (int k=0; k<N; k++) {
            int min = Integer.MAX_VALUE;
            for (int i=0; i<N; i++)
                if (S[i]==0 && dist[i]<min)
                    min = dist[cnt = i];
            if (min==Integer.MAX_VALUE || cnt==C2) break;
            S[cnt] = 1;
            for (int i=0; i<N; i++)
                if (S[i]==0 && dist[cnt]!=Integer.MAX_VALUE && w[cnt][i]!=Integer.MAX_VALUE) {
                    int tmp = dist[cnt] + w[cnt][i];
                    if (tmp == dist[i]) {
                        pathcount[i] += pathcount[cnt];
                        if (maxteams[cnt] + teams[i] > maxteams[i])
                            maxteams[i] = maxteams[cnt] + teams[i];
                    }else if (tmp < dist[i]) {
                        dist[i] = tmp;
                        pathcount[i] = pathcount[cnt];
                        maxteams[i] = maxteams[cnt] + teams[i];
                    }
                }
        }
        System.out.print(pathcount[C2]+" "+maxteams[C2]);
    }
}