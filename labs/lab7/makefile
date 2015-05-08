#makefile------------------------------------------------------------------------------
# Steven Cruz
# lab7
# CS 12B 
# Makefile for CMPS 12B lab7
#------------------------------------------------------------------------------

JAVAC      = javac 
MAINCLASS  = DictionaryClient
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) makefile README DictionaryInterface.java KeyCollisionException.java KeyNotFoundException.java DictionaryClient.java Dictionary.java

CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 
SUBMIT     = submit cmps012b-pt.u14 lab7


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)

submit:
	${SUBMIT} ${SOURCES}

