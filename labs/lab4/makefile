# makefile for charType.c with Macros 
#Steven Cruz
#lab4
#sicruz

FLAGS = -std=c99 -Wall 
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN = charType
SUBMIT = submit cmps012b-pt.u14 lab4 charType.c README makefile
 
all: ${EXEBIN} 
 
$(EXEBIN) : ${OBJECTS} 
	gcc -o ${EXEBIN} ${OBJECTS}
 
$(OBJECTS) : ${SOURCES}
	gcc -c ${FLAGS} ${SOURCES} 
 
clean : 
	rm -f ${EXEBIN} ${OBJECTS} 

submit : ${SOURCES}
	${SUBMIT} ${SOURCES}
