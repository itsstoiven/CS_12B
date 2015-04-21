#Steven Cruz
#Lab 2
#CMPS012B
#a simple makefile
#sicruz

FileReverse: FileReverse.class
	echo Main-class: FileReverse > Manifest
	jar cvfm FileReverse Manifest FileReverse.class
	rm Manifest
	chmod +x FileReverse

FileReverse.class: FileReverse.java
	javac -Xlint FileReverse.java

clean:
	rm -f FileReverse.class FileReverse

submit: README makefile Extrema.java
	submit cmps012b-pt.u14 lab2 README makefile FileReverse.java
