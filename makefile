JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
  Breakout.java \
  BreakoutObject.java \
  Ball.java \
  Paddle.java \
  Brick.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class