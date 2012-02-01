#include <stdio.h>

void* lsearch(void *key, void *base, int n, int size, 
  	      int (*cmpfn)(void*, void*))
{
	int i;
	for(i = 0; i < n; ++i) {
		void *elemp = (char*)base + i * size;
		if(cmpfn(key, elemp) == 0)
			return elemp;
	}
	return NULL;
}

int cmpInt(void *vp1, void *vp2)
{
	int *cp1 = (int*) vp1;
	int *cp2 = (int*) vp2;
	return *cp1 - *cp2;
}

int main()
{
	int array[] = {4, 3, 2, 5, 7, 6};
	int key = 7;
	int *found = lsearch(&key, array, 6, sizeof(int), cmpInt);
	const char *pcc[] = {"xx", "jj"};
	if(found != NULL) {
		printf("%d\n", *found);
	}
	return 0;
}
