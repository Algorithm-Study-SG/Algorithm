package INSEA_99.week01.BJ;

import java.io.*;
import java.util.*;

public class Main_15686_치킨배달 {
	
	static int N, M, dist[][], minDist = Integer.MAX_VALUE;		// 도시 한 변 크기, 선택할 치킨 집 수, 치킨과 집 거리, 치킨 거리 최솟값
	static List<Pii> houses = new ArrayList<>();	// 집 좌표들
	static List<Pii> chikens = new ArrayList<>();	// 치킨 좌표들
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input(br);	// 입력 받기
		getDist();	// 치킨과 집들의 거리 계산
		findChickenStreet(0, 0, new int[houses.size()]); // 치킨 거리 최솟값 구하기
		System.out.println(minDist);
	}
	
	static class Pii{
		int r;	// 행
		int c;	// 열
		Pii(int r, int c){this.r = r; this.c = c;}
	}
	
	public static void input(BufferedReader br) throws IOException {
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 도시 한 변 크기
		M = Integer.parseInt(st.nextToken());	// 선택할 치킨 집 수
		
		for(int r = 0; r < N; ++r) {	// 치킨과 집들 좌표 추가
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; ++c) {
				String type = st.nextToken();
				if(type.equals("1")) houses.add(new Pii(r,c));
				else if(type.equals("2")) chikens.add(new Pii(r,c));
			}
		}
		dist = new int[chikens.size()][houses.size()];	// 치킨과 집들 사이 거리 배열 초기화
	}
	
	public static void getDist() {		// 치킨과 집들 사이 거리 저장
		for(int r = 0; r < chikens.size(); ++r) {
			for(int c = 0; c < houses.size(); ++c)
				dist[r][c] = Math.abs(chikens.get(r).r - houses.get(c).r) + Math.abs(chikens.get(r).c - houses.get(c).c);
		}
	}
	
	/***
	 * 완전 탐색으로 치킨 거리 최솟값 구하기
	 * @param n : 선택된 치킨 집 수
	 * @param chikenIdx : 추가할 피킨 집 수
	 * @param tempMinDist : 현재까지 선택된 치킨 집들 기준 집들의 최소 거리
	 */
	public static void findChickenStreet(int n, int chikenIdx, int[] tempMinDist) {
		if(chikenIdx == 0)	// 초기에 tempMinDist를 int의 MAX_VALUE로 초기화
			for(int i = 0; i < houses.size(); ++i) tempMinDist[i] = Integer.MAX_VALUE;
		if(n == M) {	// 치킨 집 선택이 완료됐다면 치킨 거리 최솟값 업데이트
			int sum = 0;
			for(int d : tempMinDist) sum += d;
			minDist = minDist < sum ? minDist : sum;
			return;
		}
		if(chikenIdx == chikens.size()) return;		// 더이상 선택할 치킨집이 없다면 종료
		
		findChickenStreet(n, chikenIdx + 1, Arrays.copyOf(tempMinDist, tempMinDist.length));	// 이번 치킨집 선택 안하는 경우
		for(int i = 0; i < houses.size(); ++i)	// 이번 치킨집 선택할 경우 minDist 업데이트
			tempMinDist[i] = tempMinDist[i] < dist[chikenIdx][i] ? tempMinDist[i] : dist[chikenIdx][i];
		findChickenStreet(n + 1, chikenIdx + 1, Arrays.copyOf(tempMinDist, tempMinDist.length));	// 이번 치킨집 선택하는 경우
	}
}


