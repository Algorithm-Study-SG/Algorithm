#include<iostream>
#include<vector>
#include<cmath>

using namespace std;

int N, M, totalMin = 2147483647;
vector<pair<int, int>> house;
vector<pair<int, int>> chicken;
vector<pair<int, int>> selectedChicken;

void input() {
	int x;

	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> x;

			if (x == 0) continue;
			else if (x == 1) house.push_back(make_pair(i, j));		// 집 추가
			else if (x == 2) chicken.push_back(make_pair(i, j));	// 치킨집 추가
		}
	}
}

int calDistance() {
	int targetMin, dist, totalDist = 0;
	for (int i = 0; i < house.size(); i++) {
		targetMin = 2147483647;
		for (int j = 0; j < selectedChicken.size(); j++) {			// 각 집의 치킨 거리 구하기
			dist = abs(house[i].first - selectedChicken[j].first) + abs(house[i].second - selectedChicken[j].second);
			targetMin = dist < targetMin ? dist : targetMin;
		}
		totalDist += targetMin;
	}

	return totalDist;
}

void select(int index) {
	if (selectedChicken.size() == M) {				// 치킨집 M개 모두 고른 경우
		int totalDist = calDistance();				// 치킨 거리 구하기
		totalMin = totalDist < totalMin ? totalDist : totalMin;	// 최소 치킨 거리 구하기

		return;
	}

	for (int i = index; i < chicken.size(); i++) {	// 치킨집 조합
		selectedChicken.push_back(chicken[i]);
		select(i + 1);
		selectedChicken.pop_back();
	}
}

void print() {
	cout << totalMin << "\n";
}

int main(int arg, char** argc) {

	input();
	select(0);
	print();

	return 0;
}