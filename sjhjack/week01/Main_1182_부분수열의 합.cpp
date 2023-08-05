/*
	부분 수열 -> {1, 2, 3, 4} 에서 {1, 3}, {1, 4}, {2, 4}, {1, 3, 4}도 부분 수열이다. 
	따라서 아래 주석 처리한 코드로 풀면 연속된 순서의 부분 수열만 고려하고 위의 예시는 고려하지 못한다.

	5 0
	0 0 0 0 0
	-> 31 정답
	이 예시가 정답이 나와야 한다.
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