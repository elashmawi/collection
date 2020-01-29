#include <cstdlib>
#include <iostream>

using namespace std;

int main() {
    int t;
    cin >> t;
    for (int i = 0; i < t; i++) {
	int n;
	cin >> n;
	int x = 0;
	int f = 5;
	while (f <= n) {
	    x += n / f;
	    f *= 5;
	}
	cout << x;
	cout << "\n";
    }
    return 0;
}