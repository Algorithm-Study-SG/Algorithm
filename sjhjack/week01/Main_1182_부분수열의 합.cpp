/*
	�κ� ���� -> {1, 2, 3, 4} ���� {1, 3}, {1, 4}, {2, 4}, {1, 3, 4}�� �κ� �����̴�. 
	���� �Ʒ� �ּ� ó���� �ڵ�� Ǯ�� ���ӵ� ������ �κ� ������ ����ϰ� ���� ���ô� ������� ���Ѵ�.

	5 0
	0 0 0 0 0
	-> 31 ����
	�� ���ð� ������ ���;� �Ѵ�.
*/
#include<iostream>
#include<vector>
#include<numeric>

using namespace std;

int N, S, ans = 0;
int arr[20];
vector<int> v;

void dfs(int level) {
	int sum = accumulate(v.begin(), v.end(), 0);
	if (!v.empty() && sum == S) {
		ans++;
	}
	for (int i = level; i < N; i++) {
		v.push_back(arr[i]);
		dfs(i + 1);
		v.pop_back();
	}
}

int main(int arg, char** argc) {
	cin >> N >> S;
	for (int i = 0; i < N; i++) cin >> arr[i];

	dfs(0);

	cout << ans;


	return 0;
}

/*
	int N, S, x, ans = 0;
	int preSum[20] = { 0, };

	cin >> N >> S >> preSum[0];
	if (preSum[0] == S) ans++;
	for (int i = 1; i < N; i++) {
		cin >> x;
		preSum[i] = preSum[i - 1] + x;
		if (x == S) ans++;
		if (preSum[i] == S) ans++;
	}

	for (int i = 1; i < N; i++) {
		for (int j = i + 1; j < N; j++) {
			if (preSum[j] - preSum[i - 1] == S) ans++;
		}
	}

	cout << ans;
*/