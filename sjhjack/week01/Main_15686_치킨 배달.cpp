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
			else if (x == 1) house.push_back(make_pair(i, j));		// �� �߰�
			else if (x == 2) chicken.push_back(make_pair(i, j));	// ġŲ�� �߰�
		}
	}
}

int calDistance() {
	int targetMin, dist, totalDist = 0;
	for (int i = 0; i < house.size(); i++) {
		targetMin = 2147483647;
		for (int j = 0; j < selectedChicken.size(); j++) {			// �� ���� ġŲ �Ÿ� ���ϱ�
			dist = abs(house[i].first - selectedChicken[j].first) + abs(house[i].second - selectedChicken[j].second);
			targetMin = dist < targetMin ? dist : targetMin;
		}
		totalDist += targetMin;
	}

	return totalDist;
}

void select(int index) {
	if (selectedChicken.size() == M) {				// ġŲ�� M�� ��� �� ���
		int totalDist = calDistance();				// ġŲ �Ÿ� ���ϱ�
		totalMin = totalDist < totalMin ? totalDist : totalMin;	// �ּ� ġŲ �Ÿ� ���ϱ�

		return;
	}

	for (int i = index; i < chicken.size(); i++) {	// ġŲ�� ����
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