/* 
 * ±à³Ì·¶Ê½£¬µÚ6,7½²
 * 2012-1-25
 * generic stack
 */

#include <assert.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
/* generic stack */
typedef struct {
	void *elems;
	int elemSize;
	int allocLength;
	int logicLength;

	void (*freeFn)(void*);
} Stack;

void StackNew(Stack *s, int eles, void (*freeFn)(void*))
{
	s->allocLength = 4; // initial 4
	s->logicLength = 0;
	s->elemSize = eles;
	s->freeFn = freeFn;
	s->elems = malloc(s->elemSize * s->allocLength);
	assert(s->elems != NULL);
}

void StackDispose(Stack *s)
{
	if(s->freeFn != NULL) {
		int i;
		for(i = 0; i < s->logicLength; ++i)
			s->freeFn((char*)s->elems + i * s->elemSize);
	}
	free(s->elems);
}

void StackPush(Stack *s, void *elemAddr)
{
	if(s->logicLength == s->allocLength) {
		s->allocLength *= 2;
		s->elems = realloc(s->elems, s->allocLength * s->elemSize);
		assert(s->elems != NULL);
//		grow(s);
	}
//	memcpy(s->elems[s->logicLength], elemAddr, s->elemSize); // wrong, can not deference void*
	void *target = (char*)s->elems + s->logicLength * s->elemSize;
	memcpy(target, elemAddr, s->elemSize);
	s->logicLength++;
}

void StackPop(Stack *s, void *elemAddr)
{
	assert(s->logicLength > 0);
	s->logicLength--;
	void *source = (char*)s->elems + s->logicLength * s->elemSize;
	memcpy(elemAddr, source, s->elemSize);
}

void freeFn(void *p)
{
	char *cp = *(char**)p;
	free(cp);
}

int main()
{
	const char *friends[] = {"xx", "jj", "kk"};
	Stack stringStack;
	StackNew(&stringStack, sizeof(char*), freeFn);

	int i;
	for(i = 0; i < 3; ++i) {
		char *copy = strdup(friends[i]);
		StackPush(&stringStack, &copy);
	}

	char *name;
	for(i = 0; i < 3; ++i) {
		StackPop(&stringStack, &name);
		printf("%s\n", name);
		free(name);
	}

	StackDispose(&stringStack);
	return 0;
}

