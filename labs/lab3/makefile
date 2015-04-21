# makefile for FileReverse.c with Macros 
#Steven Cruz
#lab3
#sicruz

FLAGS = -std=c99 -Wall 
SOURCES = FileReverse.c README makefile
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.u14 lab3
 
all: ${EXEBIN} 
 
$(EXEBIN) : ${OBJECTS} 
	gcc -o ${EXEBIN} ${OBJECTS}
 
$(OBJECTS) : ${SOURCES}
	gcc -c ${FLAGS} ${SOURCES} 
 
clean : 
	rm -f ${EXEBIN} ${OBJECTS} 

submit : ${SOURCES}
	${SUBMIT} ${SOURCES}
