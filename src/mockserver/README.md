# Mockerserver Hello World

There are multiple ways to start mockserver and can be found on https://www.mock-server.com/mock_server/running_mock_server.html. We are going to cover the following for this hellow world:

- Maven
- Command Line
- From a java class

## Maven:
pom.xml needs the mockserver plugin. Please refer to https://www.mock-server.com/mock_server/running_mock_server.html#maven_plugin.
```
<plugin>
    <groupId>org.mock-server</groupId>
    <artifactId>mockserver-maven-plugin</artifactId>
    <version>5.11.1</version>
    <configuration>
        <serverPort>1080</serverPort>
        <logLevel>DEBUG</logLevel>
        <initializationClass>org.mockserver.maven.ExampleInitializationClass</initializationClass>
    </configuration>
    <executions>
        <execution>
            <id>process-test-classes</id>
            <phase>process-test-classes</phase>
            <goals>
                <goal>start</goal>
            </goals>
        </execution>
        <execution>
            <id>verify</id>
            <phase>verify</phase>
            <goals>
                <goal>stop</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
The mentioned plugin is already present in the pom.xml. The port can be updated from the tag ```<serberPort>```.
The server can be started:
 - Synchronously ```mvn mockserver:run```
 - Asynchronously as forked JVM ```mvn mockserver:runForked```

The server can be stopped by:
 - ^C for the synchronous instance on the command line where the server was started
 - ```mvn mockserver:stopForked``` for the asynchronous server

## Command line

To start the server from command line, there are two options available:
 - Install mockserver on Linux/OS X:
   - The service can installed using Homebrew using the following command
     ```brew install mockserver```
   - Once installed, start the server using ```mockserver -logLevel INFO -serverPort 1080```
   - More details are available on https://www.mock-server.com/mock_server/running_mock_server.html#running_from_command_line under the section ```Running From Command Line - Using Homebrew```  
 - Download the mockserver jarhttp://search.maven.org/remotecontent?filepath=org/mock-server/mockserver-netty/5.11.2/mockserver-netty-5.11.2-jar-with-dependencies.jar
   - run ```java -jar <path to mockserver-netty-5.11.1-jar-with-dependencies.jar> -serverPort <port>```
   - More details are available on  https://www.mock-server.com/mock_server/running_mock_server.html#running_from_command_line under the `Running From Command Line - Using Java` section
    
To stop the server, use ^C on the command line on which the server was started.

## From java class

 - To start and stop the server in a java class, you need to create a test. Please refer to the "TestMockServerCreatesServer.java". You can run the test directly without starting the mockserver explicitly.
 - In order to use an existing instance of mockserver, refer to "TestMockServer.java". Update the host name and the port number and the test class can be run.