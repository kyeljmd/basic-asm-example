
#Running on a Standalone Java application

1.) mvn package

2.) java -javaagent:agent/target/agent-0.1-SNAPSHOT.jar -jar other/target/other-0.1-SNAPSHOT.jar


#Running on a Spring boot 2, Application

1.) mvn package

2.) java -javaagent:agent/target/agent-0.1-SNAPSHOT.jar -jar boot2\target\boot2-0.0.1-SNAPSHOT.jar


TODO:

1.) Add Configuration file to identify which Class, and method to be injected by the loginjector
