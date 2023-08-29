package Week04;

/**
 * 메모리 : 23852 KB
 * 시간 : 248 ms
 * 
 * # 접근 : 모든 좌표가 연결되어야 하고, 간선의 가중치(좌표 간 거리)가 최소가 되어야하기 때문에 최소신장트리를 떠올렸다.
 *         간선이 따로 주어지지 않았고, 각 좌표는 다른 모든 좌표와 연결될 수 있기 때문에 최대 N(N-1)/2 = 499,500개의 간선을 가질 수 있다.
 *         간선의 개수가 많을 때는 Kruskal보다 Prim 알고리즘이 더 효율적이라고 해서 Prim으로 구현했다. (많다의 기준은 모르고, 개인적으로 많아 보였다)
 *         우선순위큐가 아닌 인접행렬로 구현한 이유는 아직 우선순위큐로 구현하는 것은 공부를 하지 않았고,
 *         메모리 공간이 충분하며, 배열이 값 접근하기 편하기 때문이다.
 *         
 *         좌표 간의 거리를 모두 구해 두었다.
 *         입력으로 들어오는 이미 연결된 통로에 대해서는 간선의 가중치를 0으로 저장해서, 추후 해당 통로가 최소 거리의 통로일 경우 다시 연결할 필요가 없도록 했다.
 *         임의의 시작점(여기서는 1)을 통로에 연결하며, Prim 알고리즘대로 구현했다.
 *         
 *         !! 잘못된 접근 !!
 *         1. 문제에서 입력으로 주어지는 이미 연결 되어있는 통로를 무조건 가지고 가야 한다고 생각했다.
 *            -> 이 생각때문에 존재하는 통로의 가중치를 Double.MAX_VALUE로 저장해서 아예 고려하지 않도록 했었다.
 *            -> 또한, visit 처리를 하게되면 존재하는 통로들이 연결되지 않는 반례가 존재했다.
 *            -> "새롭게" 만들어야 할 통로 길이의 최소값이므로, 최소거리의 좌표를 통로에 연결하되, 이미 존재하는 통로면 새롭게 만들지 않고 연결만 하면 될 뿐이다.
 *            -> 따라서, 가중치는 0으로, visit은 false로 두어서 모두 탐색 가능하도록 하는 것이 올바른 접근이다.
 *            
 * ++ 2차원 좌표계에서 좌표 위치 띄워볼 수 있는 사이트 : https://www.desmos.com/calculator/zdv2kras4n?lang=ko
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1774_우주신과의교감 {
	
	static int N, M;
	static Point[] points;			// 좌표 저장
	static double[] minDist;		// 각 좌표마다 통로 연결된 애들과의 최소 거리 저장
	static double[][] distance;		// 좌표 간 거리 저장
	static boolean[] isSelected;	// 통로에 연결된 좌표인지 확인
	static double ans;				// 추가된 통로 길이
	
	static class Point{	// 좌표 담는 클래스
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		points = new Point[N+1];
		minDist = new double[N+1];
		distance = new double[N+1][N+1];
		isSelected = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {			// 좌표 입력
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			points[i] = new Point(x, y);
		}
		
		for(int i = 1; i <= N; i++) {			// 좌표 간 거리 전처리
			for(int j = i+1; j <= N; j++) {
				double d = calDist(i, j);
				distance[i][j] = d;
				distance[j][i] = d;
			}
		}
		
		for(int i = 0; i < M; i++) {			// 통로 입력
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			distance[a][b] = 0;					// 이미 연결되어 있는 통로가 최소 거리의 통로이면
			distance[b][a] = 0;					// 추가로 연결할 필요가 없으므로 0 저장
		}
	}
	
	static double calDist(int a, int b) {		// a번, b번 좌표 간 거리 계산
		Point A = points[a];
		Point B = points[b];
		
		return Math.sqrt(Math.pow((A.x-B.x), 2) + Math.pow((A.y-B.y), 2));	// 피타고라스 정리
	}
	
	static void Prim() {					// MST를 Prim 알고리즘으로 구현
		int cnt = 0;						// 통로에 연결된 좌표 개수 저장하는 변수
		Arrays.fill(minDist, Double.MAX_VALUE);
		
		int minVertex;
		double min;
		
		minDist[1] = 0;						// 1번 좌표를 임의로 시작점 설정
		while(true) {
			if(cnt == N) break;
			
			min = Double.MAX_VALUE;
			minVertex = 0;
			
			for(int i = 1; i <= N; i++) {	// 미연결 좌표 중 통로에 연결할 수 있는 최소 비용 좌표 선택
				if(!isSelected[i] && min > minDist[i]) {
					min = minDist[i];
					minVertex = i;
				}
			}
			
			cnt++;
			ans += min;
			isSelected[minVertex] = true;
			
			for(int i = 1; i <= N; i++) {	// 새롭게 연결한 좌표와 연결되지 않은 좌표의 최소 거리 업데이트
				if(!isSelected[i] && minDist[i] > distance[minVertex][i]) {
					minDist[i] = distance[minVertex][i];
				}
			}
		}
		
		System.out.printf("%.2f", ans);
	}

	public static void main(String[] args) throws IOException {
		input();
		Prim();
	}

}

