//Steven Cruz
//CS 12B
//PA5
//sicruz


#------------------------------------------------------------------------------
# makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient.c
OBJECTS = Dictionary.o DictionaryClient.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient
SUBMIT	=  README Dictionary.c Dictionary.h DictionaryTest.c DictionaryClient.c makefile


all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

	
submit : ${SOURCES}
	${SUBMIT} ${SOURCES}