all:
	cd binarysearch; make
	cd lsearch; make
	cd stack; make

clean:
	-rm *~
	cd binarysearch; make clean
	cd lsearch; make clean
	cd stack; make clean
runtest:
	cd binarysearch; make runtest
	cd lsearch; make runtest
	cd stack; make runtest
