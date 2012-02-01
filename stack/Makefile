CC=gcc
OBJ=stack
OFILE=$(OBJ).o
CPPFILE=$(OBJ).c

$(OBJ): $(OFILE)
	$(CC) -o $(OBJ) $(OFILE)

# $(OFILE): $(CPPFILE)

clean:
	-rm *~ *.o

runtest:
	./stack.exe
