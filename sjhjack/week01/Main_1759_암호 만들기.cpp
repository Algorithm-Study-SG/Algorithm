#include<iostream>
#include<string>
#include<vector>
#include<algorithm>

using namespace std;

int L, C;
vector<string> ans;
vector<char> chars;


void input() {
	cin >> L >> C;

	for (int i = 0; i < C; i++) {
		char c;
		cin >> c;
		chars.push_back(c);
	}
	sort(chars.begin(), chars.end());
}

int isVowel(char c) {
	if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
		return 1;
	else return 0;
}

void dfs(string s, int level, int vowelN, int consN) {
	if (s.size() == L) {
		if(vowelN >= 1 && consN >= 2)
			ans.push_back(s);
		return;
	}

	for (int i = level; i < chars.size(); i++) {
		if (isVowel(chars[i])) {
			dfs(s + chars[i], i + 1, vowelN + 1, consN);
		}
		else {
			dfs(s + chars[i], i + 1, vowelN, consN + 1);
		}
	}
}

int main(int arg, char** argc) {
	string s = "";

	input();
	dfs(s, 0, 0, 0);

	for (int i = 0; i < ans.size(); i++) {
		cout << ans[i] << "\n";
	}

	return 0;
}