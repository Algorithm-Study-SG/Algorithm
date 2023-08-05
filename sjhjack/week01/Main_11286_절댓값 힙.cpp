#include<iostream>
#include<queue>
#include<cmath>

using namespace std;

int main(int arg, char** argc) {
	int N, x;
	priority_queue<int, vector<int>, greater<int>> plusQ;	// ��� �������� ����
	priority_queue<int, vector<int>> minusQ;				// ���� �������� ����

	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> x;
		if (x == 0) {
			if (plusQ.empty() && minusQ.empty()) {
				cout << 0 << "\n";
				continue;
			}

			if (plusQ.empty()) {
				cout << minusQ.top() << "\n";
				minusQ.pop();
			} else if (minusQ.empty()) {
				cout << plusQ.top() << "\n";
				plusQ.pop();
			}
			else {
				if ( plusQ.top() < abs(minusQ.top()) ) {
					cout << plusQ.top() << "\n";
					plusQ.pop();
				}
				else {
					cout << minusQ.top() << "\n";
					minusQ.pop();
				}
			}

		}
		else {
			if (x > 0) plusQ.push(x);
			else minusQ.push(x);
		}
	}


	return 0;
}