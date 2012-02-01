#include <iostream>
#include <vector>
#include <cstdlib>
using namespace std;

// return the right most
// [low, up)
template<class T>
size_t bs1(T *p, size_t N, T key)
{
  size_t low = 0, up = N; /* assert: p[low] <= key && p[up] > key && low < up */
  while(low + 1 != up) {
    size_t mid = low + (up - low) / 2;
    if(p[mid] > key)
      up = mid;
    else
      low = mid;
  }
  if(p[low] == key)
    return low;
  return -1;
}

// return the left most
// [low up)
template<class T>
size_t bs2(T *p, size_t N, T key)
{
  size_t low = -1, up = N - 1; /* assert: p[low] < key && p[up] >= key && low  < up */
  while(low + 1 != up) {
    size_t mid = low + (up - low) / 2;
    if(p[mid] < key)
      low = mid;
    else
      up = mid;
   }
  if(p[up] == key)
    return up;
  return -1;
}

// return a random position of key in T[]
template<class T>
size_t bs3(T *p, size_t N, T key)
{
  size_t low = 0, up = N; /* assert: p[low] <= key && p[up] > key && low < up */
  while(low < up) { 
    size_t mid = low + (up - low) / 2;
    if(p[mid] > key) 
      up = mid;
    else if(p[mid] < key)
      low = mid + 1;
    else
      return mid;
  }
  return -1;
}

template <class T>
T* create(size_t size, T initv)
{
  T *p = new T[size];
  for(size_t i = 0; i < size; ++i)
    p[i] = initv;
  return p;
}

int main(int argc, char*argv[])
{
  if(argc > 1) {
    int N = atoi(argv[1]);
    int k = 1;
    int *p = create(N, k);
    int ret1 = bs1(p, N, k);
    int ret2 = bs2(p, N, k);
    int ret3 = bs3(p, N, k);
    cout << ret1 << " " << ret2 << " " << ret3 << endl;
  } else {
    cout << "Usage: bs N" << endl;
  }
  return 0;
}
