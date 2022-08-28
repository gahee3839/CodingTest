package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N2606 {
	static int n;
	static int[][] graph;
	static boolean[] visited;
	static int cnt=0;	//전파된 컴퓨터수 (시작노드제외)
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//노드수 입력받기
		n = Integer.parseInt(br.readLine());
		//간선수 입력받기
		int m = Integer.parseInt(br.readLine());
		//그래프, 방문배열 초기화
		graph = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		StringTokenizer st;
		//연결관계 입력받기
		for(int i=0; i<m; i++) {
			st = new  StringTokenizer(br.readLine());
			int x =Integer.parseInt(st.nextToken());
			int y =Integer.parseInt(st.nextToken());
			graph[x][y] = graph[y][x] = 1;	//양방향 연결
		}
		bfs(1);
		System.out.println(cnt);
	}
	
	private static void dfs(int node) {
		visited[node] = true;
		for(int i=1; i<=n; i++) {
			if(!visited[i] && graph[node][i]==1) {
				cnt++;
				dfs(i);
			}
		}
	}
	
	private static void bfs(int node) {
		Queue<Integer> q = new LinkedList<>();
		visited[node] = true;
		q.add(node);
		
		while(!q.isEmpty()) {
			int p = q.poll();
			for(int i=1; i<=n; i++) {
				if(!visited[i] && graph[p][i]==1) {
					visited[i]=true;
					q.add(i);
					cnt++;
				}
			}
		}
		
	}
}
