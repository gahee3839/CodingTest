package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//제일헷갈린거 :  n과m 가로와 세로!
//어려웠던거 : 여러군데서 동시에 시작할수있다는점!

public class N7576 {
	static int m; // 가로
	static int n; // 세로
	static int[][] graph;
	static Queue<int[]> que = new LinkedList<>();
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		graph = new int[n][m]; // 여기헷갈림!!! int[m][n]??

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int x = Integer.parseInt(st.nextToken());
				graph[i][j] = x;
				if (x == 1) { // 토마토가 익은거면 큐에넣는다
					que.add(new int[] { i, j });
				}
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		
		while (!que.isEmpty()) {
			int[] now = que.poll();
			int px = now[0];// 세로
			int py = now[1];// 가로

			for (int i = 0; i < 4; i++) { // 상하좌우 토마토
				int nx = px + dx[i]; // 세로
				int ny = py + dy[i]; // 가로
				if (nx >= 0 && ny >= 0 && nx < n && ny < m) { // 범위안에서
					// 토마토가 안익었으면
					if (graph[nx][ny] == 0) {
						que.add(new int[] { nx, ny }); // 익은토마토 추가
						graph[nx][ny] = graph[px][py] + 1;// 지금토마토보다 하루뒤에 익음 (+1)
					}
				}
			}
		}

		// 제일늦게익은 토마토날짜
		int result = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 토마토가 안익은게 있으면
				if (graph[i][j] == 0) {
					return -1;
				}
				// 날짜 최댓값 구하기
				result = Math.max(result, graph[i][j]);
			}
		}
		// 토마토가 처음부터 모두 익은 상태인 경우
		if (result == 1) {
			return 0;
		}
		// 걸린일수는 최대 날짜에서 1을빼줘야함
		else {
			return result - 1;
		}
	}

}
