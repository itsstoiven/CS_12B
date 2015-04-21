#makefile------------------------------------------------------------------------------
# Steven Cruz
# PA4
# CS 12B 
# Makefile for CMPS 12B pa4
#------------------------------------------------------------------------------

JAVAC      = javac 
MAINCLASS  = Simulation
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) makefile README Simulation.java Queueinterface.java QueueEmptyException.java Queue.java QueueTest.java Job.java
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 
SUBMIT     = submit cmps012b-pt.u14 pa4


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

