#include<iostream>
#include<vector>

using namespace std;

int main(int argc, char** argv) {
	int n, idx = 0;
	int target[100001];
	vector<int> stack;
	vector<char> ans;

	cin >> n;
	for (int i = 0; i < n; i++) cin >> target[i];

	for (int i = 1; i <= n; i++) {
		stack.push_back(i);
		ans.push_back('+');

		while (!stack.empty() && stack.back() == target[idx]) {
			stack.pop_back();
			ans.push_back('-');
			idx++;
		}
	}

	if (!stack.empty()) cout << "NO";
	else {
		for (int i = 0; i < ans.size(); i++) cout << ans[i] << "\n";
	}

	return 0;
}