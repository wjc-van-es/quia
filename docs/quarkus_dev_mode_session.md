<style>
body {
  font-family: "Gentium Basic", Cardo , "Linux Libertine o", "Palatino Linotype", Cambria, serif;
  font-size: 100% !important;
  padding-right: 12%;
}
code {
	padding: 0.25em;
	
	white-space: pre;
	font-family: "Tlwg mono", Consolas, "Liberation Mono", Menlo, Courier, monospace;
	
	background-color: #ECFFFA;
	//border: 1px solid #ccc;
	//border-radius: 3px;
}

kbd {
	display: inline-block;
	padding: 3px 5px;
	font-family: "Tlwg mono", Consolas, "Liberation Mono", Menlo, Courier, monospace;
	line-height: 10px;
	color: #555;
	vertical-align: middle;
	background-color: #ECFFFA;
	border: solid 1px #ccc;
	border-bottom-color: #bbb;
	border-radius: 3px;
	box-shadow: inset 0 -1px 0 #bbb;
}

h1,h2,h3,h4,h5 {
  color: #269B7D; 
  font-family: "fira sans", "Latin Modern Sans", Calibri, "Trebuchet MS", sans-serif;
}

</style>

# Running Quarkus dev mode with both `mvn quarkus: dev` and `quarkus dev`

## In one terminal session
- we first tried maven `mvn quarkus: dev`
- we found out port 8080 wasn't free and modified the other project that occupies this port
- then we tried [http://localhost:8080/hello](http://localhost:8080/hello)
- then changed [`nl.vea.GreetingResource`](../src/main/java/nl/vea/GreetingResource.java)
  and saw the change with [http://localhost:8080/hello](http://localhost:8080/hello), but also found out that the test
  succeeded no more
- then changed [`nl.vea.GreetingResourceTest`](../src/test/java/nl/vea/GreetingResourceTest.java), which fixed the test
  based on
  [https://stackoverflow.com/questions/51064444/rest-assured-verify-that-a-json-object-contains-all-strings-from-a-list](https://stackoverflow.com/questions/51064444/rest-assured-verify-that-a-json-object-contains-all-strings-from-a-list)
- `Press [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the
   terminal, [h] for more options>`gives a number of options if you choose `h` you get a more elaborate list.
- then we stopped the run with `Ctrl+C` in the terminal, we could also have used `q`.
- then restarted with `mvn quarkus:dev` again, refreshing [http://localhost:8080/hello](http://localhost:8080/hello)
- then we stopped again with `Ctrl+C` in the terminal, we could also have used `q`.
- we retried with `quarkus dev` followed with refreshing [http://localhost:8080/hello](http://localhost:8080/hello)
- finally we stopped again with `Ctrl+C` in the terminal again.

### Open details for bash session logging

<details>

```bash
willem@willem-Latitude-5590:~/git/quia$ mvn quarkus:dev
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------------< nl.vea:quia >-----------------------------
[INFO] Building quia 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- quarkus:3.14.1:dev (default-cli) @ quia ---
[INFO] Invoking resources:3.3.1:resources (default-resources) @ quia
[INFO] Copying 2 resources from src/main/resources to target/classes
[INFO] Invoking quarkus:3.14.1:generate-code (default) @ quia
[INFO] Invoking compiler:3.13.0:compile (default-compile) @ quia
[INFO] Recompiling the module because of added or removed source files.
[INFO] Compiling 1 source file with javac [debug parameters release 21] to target/classes
[INFO] Invoking resources:3.3.1:testResources (default-testResources) @ quia
[INFO] skip non existing resourceDirectory /home/willem/git/quia/src/test/resources
[INFO] Invoking quarkus:3.14.1:generate-code-tests (default) @ quia
[INFO] Invoking compiler:3.13.0:testCompile (default-testCompile) @ quia
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 2 source files with javac [debug parameters release 21] to target/test-classes

----------------------------
--- Help improve Quarkus ---
----------------------------
* Learn more: https://quarkus.io/usage/
* Do you agree to contribute anonymous build time data to the Quarkus community? (y/n and enter)
  y
  [info] [Quarkus build analytics] Quarkus Build Analytics enabled by the user.

Listening for transport dt_socket at address: 5005
__  ____  __  _____   ___  __ ____  ______ 
--/ __ \/ / / / _ | / _ \/ //_/ / / / __/
-/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2024-09-23 21:56:28,126 ERROR [io.qua.run.Application] (Quarkus Main Thread) Port 8080 seems to be in use by another process. Quarkus may already be running or the port is used by another application.

2024-09-23 21:56:28,131 WARN  [io.qua.run.Application] (Quarkus Main Thread) Use 'ss -anop | grep 8080' or 'netstat -anop | grep 8080' to identify the process occupying the port.
2024-09-23 21:56:28,132 WARN  [io.qua.run.Application] (Quarkus Main Thread) You can try to kill it with 'kill -9 <pid>'.
2024-09-23 22:26:05,942 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (Aesh InputStream Reader) Restarting as requested by the user.
2024-09-23 22:26:05,942 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (Aesh InputStream Reader) Restarting as requested by the user.
__  ____  __  _____   ___  __ ____  ______ 
--/ __ \/ / / / _ | / _ \/ //_/ / / / __/
-/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2024-09-23 22:26:06,396 INFO  [io.quarkus] (Quarkus Main Thread) quia 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.14.1) started in 0.450s. Listening on: http://localhost:8080

2024-09-23 22:26:06,397 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2024-09-23 22:26:06,398 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, rest, smallrye-context-propagation, vertx]
2024-09-23 22:26:06,400 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (Aesh InputStream Reader) Live reload total time: 0.465s
2024-09-23 23:26:00,963 ERROR [io.qua.test] (Test runner thread) ==================== TEST REPORT #2 ====================
2024-09-23 23:26:00,964 ERROR [io.qua.test] (Test runner thread) Test GreetingResourceTest#testHelloEndpoint() failed
: java.lang.AssertionError: 1 expectation failed.
Response body doesn't match expectation.
Expected: is "Hello from Quarkus REST"
Actual: <h1>Hello willem,</h1><h2>welcome to Quarkus REST</h2><h2>The current time is 2024-09-23T23:26:00.924435017</h2><p>Running with Java 21.0.4, Temurin-21.0.4+7 of Eclipse Adoptium at /home/willem/.sdkman/candidates/java/21.0.4-tem.</p><p>OS/Arch/version: Linux/amd64/6.8.0-45-generic</p>

        at io.restassured.internal.ValidatableResponseOptionsImpl.body(ValidatableResponseOptionsImpl.java:238)
        at nl.vea.GreetingResourceTest.testHelloEndpoint(GreetingResourceTest.java:17)


2024-09-23 23:26:00,971 ERROR [io.qua.test] (Test runner thread) >>>>>>>>>>>>>>>>>>>> Summary: <<<<<<<<<<<<<<<<<<<<
nl.vea.GreetingResourceTest#testHelloEndpoint(GreetingResourceTest.java:17) GreetingResourceTest#testHelloEndpoint() 1 expectation failed.
Response body doesn't match expectation.
Expected: is "Hello from Quarkus REST"
Actual: <h1>Hello willem,</h1><h2>welcome to Quarkus REST</h2><h2>The current time is 2024-09-23T23:26:00.924435017</h2><p>Running with Java 21.0.4, Temurin-21.0.4+7 of Eclipse Adoptium at /home/willem/.sdkman/candidates/java/21.0.4-tem.</p><p>OS/Arch/version: Linux/amd64/6.8.0-45-generic</p>

2024-09-23 23:26:00,974 ERROR [io.qua.test] (Test runner thread) >>>>>>>>>>>>>>>>>>>> 1 TEST FAILED <<<<<<<<<<<<<<<<<<<<
2024-09-23 23:26:02,401 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (vert.x-worker-thread-2) Restarting quarkus due to changes in GreetingResource.class.
2024-09-23 23:26:02,410 INFO  [io.quarkus] (Quarkus Main Thread) quia stopped in 0.006s
__  ____  __  _____   ___  __ ____  ______ 
--/ __ \/ / / / _ | / _ \/ //_/ / / / __/
-/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2024-09-23 23:26:03,089 INFO  [io.quarkus] (Quarkus Main Thread) quia 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.14.1) started in 0.676s. Listening on: http://localhost:8080

2024-09-23 23:26:03,091 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2024-09-23 23:26:03,092 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, rest, smallrye-context-propagation, vertx]
2024-09-23 23:26:03,093 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (vert.x-worker-thread-2) Live reload total time: 0.695s
2024-09-23 23:31:57,066 ERROR [io.qua.dep.dev.JavaCompilationProvider] (Quarkus Test Watcher - 0) no suitable method found for body(java.lang.String)
method io.restassured.response.ValidatableResponseOptions.body(java.util.List<io.restassured.specification.Argument>,org.hamcrest.Matcher,java.lang.Object...) is not applicable
(argument mismatch; java.lang.String cannot be converted to java.util.List<io.restassured.specification.Argument>)
method io.restassured.response.ValidatableResponseOptions.body(org.hamcrest.Matcher<?>,org.hamcrest.Matcher<?>...) is not applicable
(argument mismatch; java.lang.String cannot be converted to org.hamcrest.Matcher<?>), line 17 in /home/willem/git/quia/src/test/java/nl/vea/GreetingResourceTest.java

2024-09-23 23:31:57,067 WARN  [io.qua.dep.dev.JavaCompilationProvider] (Quarkus Test Watcher - 0) Some messages have been simplified; recompile with -Xdiags:verbose to get full output, line -1 in [unknown source]

--
1 test failed (0 passing, 0 skipped), 1 test was run in 551ms. Tests completed at 23:26:00 due to changes to GreetingResource.class.
Press [e] to edit command line args (currently ''), [r] to re-run, [o] Toggle test output, [:] for the terminal, [h] for more options>







--
Compilation Failed:
/home/willem/git/quia/src/test/java/nl/vea/GreetingResourceTest.java:17: error: no suitable method found for body(java.lang.String)
.body(("Hello from Quarkus REST"));
^
method io.restassured.response.ValidatableResponseOptions.body(java.util.List<io.restassured.specification.Argument>,org.hamcrest.Matcher,java.lang.Object...) is not applicable
-
2024-09-23 23:31:57,173 ERROR [io.qua.dep.dev.JavaCompilationProvider] (Quarkus Test Watcher - 0) no suitable method found for body(java.lang.String)
method io.restassured.response.ValidatableResponseOptions.body(java.util.List<io.restassured.specification.Argument>,org.hamcrest.Matcher,java.lang.Object...) is not applicable
(argument mismatch; java.lang.String cannot be converted to java.util.List<io.restassured.specification.Argument>)
method io.restassured.response.ValidatableResponseOptions.body(org.hamcrest.Matcher<?>,org.hamcrest.Matcher<?>...) is not applicable
(argument mismatch; java.lang.String cannot be converted to org.hamcrest.Matcher<?>), line 17 in /home/willem/git/quia/src/test/java/nl/vea/GreetingResourceTest.java






--
Compilation Failed:
/home/willem/git/quia/src/test/java/nl/vea/GreetingResourceTest.java:17: error: no suitable method found for body(java.lang.String)
.body(("Hello from Quarkus REST"));
^
method io.restassured.response.ValidatableResponseOptions.body(java.util.List<io.restassured.specification.Argument>,org.hamcrest.Matcher,java.lang.Object...) is not applicable
-
2024-09-23 23:31:57,175 WARN  [io.qua.dep.dev.JavaCompilationProvider] (Quarkus Test Watcher - 0) Some messages have been simplified; recompile with -Xdiags:verbose to get full output, line -1 in [unknown source]





--
Compilation Failed:
/home/willem/git/quia/src/test/java/nl/vea/GreetingResourceTest.java:17: error: no suitable method found for body(java.lang.String)
.body(("Hello from Quarkus REST"));
^
method io.restassured.response.ValidatableResponseOptions.body(java.util.List<io.restassured.specification.Argument>,org.hamcrest.Matcher,java.lang.Object...) is not applicable

--
Compilation Failed:
/home/willem/git/quia/src/test/java/nl/vea/GreetingResourceTest.java:17: error: no suitable method found for body(java.lang.String)
.body(("Hello from Quarkus REST"));
^
method io.restassured.response.ValidatableResponseOptions.body(java.util.List<io.restassured.specification.Argument>,org.hamcrest.Matcher,java.lang.Object...) is not applicable

2024-09-23 23:35:50,907 INFO  [io.quarkus] (Shutdown thread) quia stopped in 0.008spassing

--
All 1 test is passing (0 skipped), 1 test was run in 431ms. Tests completed at 23:34:56 due to changes to GreetingResourceTest.class.
--

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:39 h
[INFO] Finished at: 2024-09-23T23:35:51+02:00
[INFO] ------------------------------------------------------------------------
willem@willem-Latitude-5590:~/git/quia$ mvn quarkus:dev
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------------------< nl.vea:quia >-----------------------------
[INFO] Building quia 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- quarkus:3.14.1:dev (default-cli) @ quia ---
[INFO] Invoking resources:3.3.1:resources (default-resources) @ quia
[INFO] Copying 2 resources from src/main/resources to target/classes
[INFO] Invoking quarkus:3.14.1:generate-code (default) @ quia
[INFO] Invoking compiler:3.13.0:compile (default-compile) @ quia
[INFO] Nothing to compile - all classes are up to date.
[INFO] Invoking resources:3.3.1:testResources (default-testResources) @ quia
[INFO] skip non existing resourceDirectory /home/willem/git/quia/src/test/resources
[INFO] Invoking quarkus:3.14.1:generate-code-tests (default) @ quia
[INFO] Invoking compiler:3.13.0:testCompile (default-testCompile) @ quia
[INFO] Nothing to compile - all classes are up to date.
Listening for transport dt_socket at address: 5005
__  ____  __  _____   ___  __ ____  ______ 
--/ __ \/ / / / _ | / _ \/ //_/ / / / __/
-/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2024-09-23 23:36:22,728 INFO  [io.quarkus] (Quarkus Main Thread) quia 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.14.1) started in 2.391s. Listening on: http://localhost:8080

2024-09-23 23:36:22,732 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2024-09-23 23:36:22,733 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, rest, smallrye-context-propagation, vertx]

--
Tests paused

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  23.670 s
[INFO] Finished at: 2024-09-23T23:36:37+02:00
[INFO] ------------------------------------------------------------------------
willem@willem-Latitude-5590:~/git/quia$ quarkus dev
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------------------< nl.vea:quia >-----------------------------
[INFO] Building quia 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- quarkus:3.14.1:dev (default-cli) @ quia ---
[INFO] Invoking resources:3.3.1:resources (default-resources) @ quia
[INFO] Copying 2 resources from src/main/resources to target/classes
[INFO] Invoking quarkus:3.14.1:generate-code (default) @ quia
[INFO] Invoking compiler:3.13.0:compile (default-compile) @ quia
[INFO] Nothing to compile - all classes are up to date.
[INFO] Invoking resources:3.3.1:testResources (default-testResources) @ quia
[INFO] skip non existing resourceDirectory /home/willem/git/quia/src/test/resources
[INFO] Invoking quarkus:3.14.1:generate-code-tests (default) @ quia
[INFO] Invoking compiler:3.13.0:testCompile (default-testCompile) @ quia
[INFO] Nothing to compile - all classes are up to date.
Listening for transport dt_socket at address: 5005
__  ____  __  _____   ___  __ ____  ______ 
--/ __ \/ / / / _ | / _ \/ //_/ / / / __/
-/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2024-09-23 23:40:05,111 INFO  [io.quarkus] (Quarkus Main Thread) quia 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.14.1) started in 2.464s. Listening on: http://localhost:8080

2024-09-23 23:40:05,115 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2024-09-23 23:40:05,116 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, rest, smallrye-context-propagation, vertx]

--
Tests paused
Press [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the terminal, [h] for more options>h
The following commands are available:

== Continuous Testing

[r] - Resume testing
[o] - Toggle test output (disabled)

== Exceptions

[x] - Open last exception (or project) in IDE (none)

== HTTP

[w] - Open the application in a browser
[d] - Open the Dev UI in a browser

== System

[s] - Force restart
[e] - Edits the command line parameters and restarts ()
[i] - Toggle instrumentation based reload (disabled)
[l] - Toggle live reload (enabled)
[j] - Toggle log levels (INFO)
[h] - Show this help
[:] - Enter terminal mode
[q] - Quit the application

--
Tests paused
Press [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the terminal, [h] for more options>q

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:22 min
[INFO] Finished at: 2024-09-23T23:41:18+02:00
[INFO] ------------------------------------------------------------------------
willem@willem-Latitude-5590:~/git/quia$

```

</details>

---
### Note IT failure after changed test

After the change of [`nl.vea.GreetingResourceTest`](../src/test/java/nl/vea/GreetingResourceTest.java), the
execution of [`nl.vea.GreetingResourceIT`](../src/test/java/nl/vea/GreetingResourceIT.java) failed. As it turns out
this test looks at the packaged artifacts inside `target/`. When I repeated `mvn clean package` and then rerun the
integration test it completed successful.

<details>


```bash
/home/willem/.sdkman/candidates/java/21.0.4-tem/bin/java -ea -Djava.util.logging.manager=org.jboss.logmanager.LogManager -Dmaven.home=/home/willem/.sdkman/candidates/maven/current -Dnative.image.path=/home/willem/git/quia/target/quia-1.0.0-SNAPSHOT-runner -Didea.test.cyclic.buffer.size=1048576 -javaagent:/home/willem/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/lib/idea_rt.jar=39499:/home/willem/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /home/willem/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/lib/idea_rt.jar:/home/willem/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/plugins/junit/lib/junit5-rt.jar:/home/willem/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/plugins/junit/lib/junit-rt.jar:/home/willem/git/quia/target/test-classes:/home/willem/git/quia/target/classes:/home/willem/.m2/repository/io/quarkus/quarkus-rest/3.14.1/quarkus-rest-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-rest-common/3.14.1/quarkus-rest-common-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/resteasy/reactive/resteasy-reactive-common/3.14.1/resteasy-reactive-common-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/resteasy/reactive/resteasy-reactive-common-types/3.14.1/resteasy-reactive-common-types-3.14.1.jar:/home/willem/.m2/repository/org/reactivestreams/reactive-streams/1.0.4/reactive-streams-1.0.4.jar:/home/willem/.m2/repository/io/smallrye/reactive/mutiny-zero-flow-adapters/1.1.0/mutiny-zero-flow-adapters-1.1.0.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-annotation/2.5.0/smallrye-common-annotation-2.5.0.jar:/home/willem/.m2/repository/io/quarkus/quarkus-mutiny/3.14.1/quarkus-mutiny-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-smallrye-context-propagation/3.14.1/quarkus-smallrye-context-propagation-3.14.1.jar:/home/willem/.m2/repository/io/smallrye/smallrye-context-propagation/2.1.2/smallrye-context-propagation-2.1.2.jar:/home/willem/.m2/repository/io/smallrye/smallrye-context-propagation-api/2.1.2/smallrye-context-propagation-api-2.1.2.jar:/home/willem/.m2/repository/io/smallrye/smallrye-context-propagation-storage/2.1.2/smallrye-context-propagation-storage-2.1.2.jar:/home/willem/.m2/repository/io/smallrye/reactive/mutiny-smallrye-context-propagation/2.6.2/mutiny-smallrye-context-propagation-2.6.2.jar:/home/willem/.m2/repository/io/quarkus/quarkus-vertx/3.14.1/quarkus-vertx-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-netty/3.14.1/quarkus-netty-3.14.1.jar:/home/willem/.m2/repository/io/netty/netty-codec/4.1.111.Final/netty-codec-4.1.111.Final.jar:/home/willem/.m2/repository/com/aayushatharva/brotli4j/brotli4j/1.16.0/brotli4j-1.16.0.jar:/home/willem/.m2/repository/com/aayushatharva/brotli4j/service/1.16.0/service-1.16.0.jar:/home/willem/.m2/repository/com/aayushatharva/brotli4j/native-linux-x86_64/1.16.0/native-linux-x86_64-1.16.0.jar:/home/willem/.m2/repository/io/netty/netty-codec-haproxy/4.1.111.Final/netty-codec-haproxy-4.1.111.Final.jar:/home/willem/.m2/repository/io/quarkus/quarkus-vertx-latebound-mdc-provider/3.14.1/quarkus-vertx-latebound-mdc-provider-3.14.1.jar:/home/willem/.m2/repository/io/smallrye/smallrye-fault-tolerance-vertx/6.4.0/smallrye-fault-tolerance-vertx-6.4.0.jar:/home/willem/.m2/repository/io/quarkus/resteasy/reactive/resteasy-reactive-vertx/3.14.1/resteasy-reactive-vertx-3.14.1.jar:/home/willem/.m2/repository/io/vertx/vertx-web/4.5.9/vertx-web-4.5.9.jar:/home/willem/.m2/repository/io/vertx/vertx-web-common/4.5.9/vertx-web-common-4.5.9.jar:/home/willem/.m2/repository/io/vertx/vertx-auth-common/4.5.9/vertx-auth-common-4.5.9.jar:/home/willem/.m2/repository/io/vertx/vertx-bridge-common/4.5.9/vertx-bridge-common-4.5.9.jar:/home/willem/.m2/repository/io/smallrye/reactive/smallrye-mutiny-vertx-core/3.14.0/smallrye-mutiny-vertx-core-3.14.0.jar:/home/willem/.m2/repository/io/smallrye/reactive/smallrye-mutiny-vertx-runtime/3.14.0/smallrye-mutiny-vertx-runtime-3.14.0.jar:/home/willem/.m2/repository/io/smallrye/reactive/vertx-mutiny-generator/3.14.0/vertx-mutiny-generator-3.14.0.jar:/home/willem/.m2/repository/io/vertx/vertx-codegen/4.5.9/vertx-codegen-4.5.9.jar:/home/willem/.m2/repository/io/quarkus/resteasy/reactive/resteasy-reactive/3.14.1/resteasy-reactive-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/vertx/utils/quarkus-vertx-utils/3.14.1/quarkus-vertx-utils-3.14.1.jar:/home/willem/.m2/repository/jakarta/enterprise/jakarta.enterprise.cdi-api/4.1.0/jakarta.enterprise.cdi-api-4.1.0.jar:/home/willem/.m2/repository/jakarta/enterprise/jakarta.enterprise.lang-model/4.1.0/jakarta.enterprise.lang-model-4.1.0.jar:/home/willem/.m2/repository/jakarta/el/jakarta.el-api/5.0.1/jakarta.el-api-5.0.1.jar:/home/willem/.m2/repository/jakarta/interceptor/jakarta.interceptor-api/2.2.0/jakarta.interceptor-api-2.2.0.jar:/home/willem/.m2/repository/jakarta/ws/rs/jakarta.ws.rs-api/3.1.0/jakarta.ws.rs-api-3.1.0.jar:/home/willem/.m2/repository/jakarta/annotation/jakarta.annotation-api/3.0.0/jakarta.annotation-api-3.0.0.jar:/home/willem/.m2/repository/org/jboss/logging/commons-logging-jboss-logging/1.0.0.Final/commons-logging-jboss-logging-1.0.0.Final.jar:/home/willem/.m2/repository/jakarta/xml/bind/jakarta.xml.bind-api/4.0.2/jakarta.xml.bind-api-4.0.2.jar:/home/willem/.m2/repository/jakarta/activation/jakarta.activation-api/2.1.3/jakarta.activation-api-2.1.3.jar:/home/willem/.m2/repository/org/jboss/logging/jboss-logging/3.6.0.Final/jboss-logging-3.6.0.Final.jar:/home/willem/.m2/repository/io/quarkus/quarkus-vertx-http/3.14.1/quarkus-vertx-http-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-security-runtime-spi/3.14.1/quarkus-security-runtime-spi-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-tls-registry/3.14.1/quarkus-tls-registry-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-credentials/3.14.1/quarkus-credentials-3.14.1.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-vertx-context/2.5.0/smallrye-common-vertx-context-2.5.0.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-constraint/2.5.0/smallrye-common-constraint-2.5.0.jar:/home/willem/.m2/repository/io/quarkus/security/quarkus-security/2.1.0/quarkus-security-2.1.0.jar:/home/willem/.m2/repository/io/smallrye/reactive/smallrye-mutiny-vertx-web/3.14.0/smallrye-mutiny-vertx-web-3.14.0.jar:/home/willem/.m2/repository/io/smallrye/reactive/smallrye-mutiny-vertx-web-common/3.14.0/smallrye-mutiny-vertx-web-common-3.14.0.jar:/home/willem/.m2/repository/io/smallrye/reactive/smallrye-mutiny-vertx-auth-common/3.14.0/smallrye-mutiny-vertx-auth-common-3.14.0.jar:/home/willem/.m2/repository/io/smallrye/reactive/smallrye-mutiny-vertx-bridge-common/3.14.0/smallrye-mutiny-vertx-bridge-common-3.14.0.jar:/home/willem/.m2/repository/io/smallrye/reactive/smallrye-mutiny-vertx-uri-template/3.14.0/smallrye-mutiny-vertx-uri-template-3.14.0.jar:/home/willem/.m2/repository/io/vertx/vertx-uri-template/4.5.9/vertx-uri-template-4.5.9.jar:/home/willem/.m2/repository/io/github/crac/org-crac/0.1.3/org-crac-0.1.3.jar:/home/willem/.m2/repository/io/quarkus/quarkus-jsonp/3.14.1/quarkus-jsonp-3.14.1.jar:/home/willem/.m2/repository/org/eclipse/parsson/parsson/1.1.7/parsson-1.1.7.jar:/home/willem/.m2/repository/jakarta/json/jakarta.json-api/2.1.3/jakarta.json-api-2.1.3.jar:/home/willem/.m2/repository/io/quarkus/quarkus-virtual-threads/3.14.1/quarkus-virtual-threads-3.14.1.jar:/home/willem/.m2/repository/io/vertx/vertx-core/4.5.9/vertx-core-4.5.9.jar:/home/willem/.m2/repository/io/netty/netty-common/4.1.111.Final/netty-common-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-buffer/4.1.111.Final/netty-buffer-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-transport/4.1.111.Final/netty-transport-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-handler/4.1.111.Final/netty-handler-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-transport-native-unix-common/4.1.111.Final/netty-transport-native-unix-common-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-handler-proxy/4.1.111.Final/netty-handler-proxy-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-codec-socks/4.1.111.Final/netty-codec-socks-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-codec-http/4.1.111.Final/netty-codec-http-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-codec-http2/4.1.111.Final/netty-codec-http2-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-resolver/4.1.111.Final/netty-resolver-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-resolver-dns/4.1.111.Final/netty-resolver-dns-4.1.111.Final.jar:/home/willem/.m2/repository/io/netty/netty-codec-dns/4.1.111.Final/netty-codec-dns-4.1.111.Final.jar:/home/willem/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.17.2/jackson-core-2.17.2.jar:/home/willem/.m2/repository/io/quarkus/quarkus-arc/3.14.1/quarkus-arc-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/arc/arc/3.14.1/arc-3.14.1.jar:/home/willem/.m2/repository/jakarta/transaction/jakarta.transaction-api/2.0.1/jakarta.transaction-api-2.0.1.jar:/home/willem/.m2/repository/io/smallrye/reactive/mutiny/2.6.2/mutiny-2.6.2.jar:/home/willem/.m2/repository/org/jctools/jctools-core/4.0.5/jctools-core-4.0.5.jar:/home/willem/.m2/repository/io/quarkus/quarkus-core/3.14.1/quarkus-core-3.14.1.jar:/home/willem/.m2/repository/jakarta/inject/jakarta.inject-api/2.0.1/jakarta.inject-api-2.0.1.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-os/2.5.0/smallrye-common-os-2.5.0.jar:/home/willem/.m2/repository/io/quarkus/quarkus-ide-launcher/3.14.1/quarkus-ide-launcher-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-development-mode-spi/3.14.1/quarkus-development-mode-spi-3.14.1.jar:/home/willem/.m2/repository/io/smallrye/config/smallrye-config/3.9.1/smallrye-config-3.9.1.jar:/home/willem/.m2/repository/io/smallrye/config/smallrye-config-core/3.9.1/smallrye-config-core-3.9.1.jar:/home/willem/.m2/repository/org/eclipse/microprofile/config/microprofile-config-api/3.1/microprofile-config-api-3.1.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-classloader/2.5.0/smallrye-common-classloader-2.5.0.jar:/home/willem/.m2/repository/io/smallrye/config/smallrye-config-common/3.9.1/smallrye-config-common-3.9.1.jar:/home/willem/.m2/repository/org/jboss/logmanager/jboss-logmanager/3.0.6.Final/jboss-logmanager-3.0.6.Final.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-cpu/2.5.0/smallrye-common-cpu-2.5.0.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-expression/2.5.0/smallrye-common-expression-2.5.0.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-function/2.5.0/smallrye-common-function-2.5.0.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-net/2.5.0/smallrye-common-net-2.5.0.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-ref/2.5.0/smallrye-common-ref-2.5.0.jar:/home/willem/.m2/repository/org/jboss/logging/jboss-logging-annotations/3.0.1.Final/jboss-logging-annotations-3.0.1.Final.jar:/home/willem/.m2/repository/org/jboss/threads/jboss-threads/3.6.1.Final/jboss-threads-3.6.1.Final.jar:/home/willem/.m2/repository/org/slf4j/slf4j-api/2.0.6/slf4j-api-2.0.6.jar:/home/willem/.m2/repository/org/jboss/slf4j/slf4j-jboss-logmanager/2.0.0.Final/slf4j-jboss-logmanager-2.0.0.Final.jar:/home/willem/.m2/repository/org/wildfly/common/wildfly-common/1.7.0.Final/wildfly-common-1.7.0.Final.jar:/home/willem/.m2/repository/io/quarkus/quarkus-bootstrap-runner/3.14.1/quarkus-bootstrap-runner-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-fs-util/0.0.10/quarkus-fs-util-0.0.10.jar:/home/willem/.m2/repository/org/eclipse/microprofile/context-propagation/microprofile-context-propagation-api/1.3/microprofile-context-propagation-api-1.3.jar:/home/willem/.m2/repository/io/quarkus/quarkus-junit5/3.14.1/quarkus-junit5-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-bootstrap-core/3.14.1/quarkus-bootstrap-core-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-classloader-commons/3.14.1/quarkus-classloader-commons-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-bootstrap-app-model/3.14.1/quarkus-bootstrap-app-model-3.14.1.jar:/home/willem/.m2/repository/io/smallrye/common/smallrye-common-io/2.5.0/smallrye-common-io-2.5.0.jar:/home/willem/.m2/repository/org/eclipse/sisu/org.eclipse.sisu.inject/0.9.0.M3/org.eclipse.sisu.inject-0.9.0.M3.jar:/home/willem/.m2/repository/io/quarkus/quarkus-test-common/3.14.1/quarkus-test-common-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-core-deployment/3.14.1/quarkus-core-deployment-3.14.1.jar:/home/willem/.m2/repository/org/aesh/readline/2.6/readline-2.6.jar:/home/willem/.m2/repository/org/fusesource/jansi/jansi/2.4.0/jansi-2.4.0.jar:/home/willem/.m2/repository/org/aesh/aesh/2.8.2/aesh-2.8.2.jar:/home/willem/.m2/repository/io/quarkus/gizmo/gizmo/1.8.0/gizmo-1.8.0.jar:/home/willem/.m2/repository/org/ow2/asm/asm-util/9.7/asm-util-9.7.jar:/home/willem/.m2/repository/org/ow2/asm/asm-analysis/9.7/asm-analysis-9.7.jar:/home/willem/.m2/repository/org/ow2/asm/asm/9.7/asm-9.7.jar:/home/willem/.m2/repository/org/ow2/asm/asm-commons/9.7/asm-commons-9.7.jar:/home/willem/.m2/repository/org/ow2/asm/asm-tree/9.7/asm-tree-9.7.jar:/home/willem/.m2/repository/io/quarkus/quarkus-hibernate-validator-spi/3.14.1/quarkus-hibernate-validator-spi-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-class-change-agent/3.14.1/quarkus-class-change-agent-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-devtools-utilities/3.14.1/quarkus-devtools-utilities-3.14.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-builder/3.14.1/quarkus-builder-3.14.1.jar:/home/willem/.m2/repository/org/graalvm/sdk/nativeimage/23.1.2/nativeimage-23.1.2.jar:/home/willem/.m2/repository/org/graalvm/sdk/word/23.1.2/word-23.1.2.jar:/home/willem/.m2/repository/org/junit/platform/junit-platform-launcher/1.10.3/junit-platform-launcher-1.10.3.jar:/home/willem/.m2/repository/io/quarkus/quarkus-bootstrap-maven-resolver/3.14.1/quarkus-bootstrap-maven-resolver-3.14.1.jar:/home/willem/.m2/repository/io/smallrye/beanbag/smallrye-beanbag-maven/1.5.2/smallrye-beanbag-maven-1.5.2.jar:/home/willem/.m2/repository/io/smallrye/beanbag/smallrye-beanbag-sisu/1.5.2/smallrye-beanbag-sisu-1.5.2.jar:/home/willem/.m2/repository/io/smallrye/beanbag/smallrye-beanbag/1.5.2/smallrye-beanbag-1.5.2.jar:/home/willem/.m2/repository/javax/inject/javax.inject/1/javax.inject-1.jar:/home/willem/.m2/repository/org/apache/maven/maven-artifact/3.9.8/maven-artifact-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/maven-builder-support/3.9.8/maven-builder-support-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/maven-model/3.9.8/maven-model-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/maven-model-builder/3.9.8/maven-model-builder-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/maven-repository-metadata/3.9.8/maven-repository-metadata-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/maven-settings/3.9.8/maven-settings-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/resolver/maven-resolver-api/1.9.22/maven-resolver-api-1.9.22.jar:/home/willem/.m2/repository/org/apache/maven/resolver/maven-resolver-impl/1.9.22/maven-resolver-impl-1.9.22.jar:/home/willem/.m2/repository/org/apache/maven/resolver/maven-resolver-named-locks/1.9.22/maven-resolver-named-locks-1.9.22.jar:/home/willem/.m2/repository/org/apache/maven/resolver/maven-resolver-spi/1.9.22/maven-resolver-spi-1.9.22.jar:/home/willem/.m2/repository/org/apache/maven/resolver/maven-resolver-util/1.9.22/maven-resolver-util-1.9.22.jar:/home/willem/.m2/repository/org/apache/maven/resolver/maven-resolver-transport-http/1.9.20/maven-resolver-transport-http-1.9.20.jar:/home/willem/.m2/repository/org/apache/maven/wagon/wagon-provider-api/3.5.3/wagon-provider-api-3.5.3.jar:/home/willem/.m2/repository/org/apache/maven/wagon/wagon-http-shared/3.5.3/wagon-http-shared-3.5.3.jar:/home/willem/.m2/repository/org/codehaus/plexus/plexus-interpolation/1.26/plexus-interpolation-1.26.jar:/home/willem/.m2/repository/org/codehaus/plexus/plexus-utils/3.5.1/plexus-utils-3.5.1.jar:/home/willem/.m2/repository/org/codehaus/plexus/plexus-xml/4.0.1/plexus-xml-4.0.1.jar:/home/willem/.m2/repository/org/apache/maven/maven-xml-impl/4.0.0-alpha-5/maven-xml-impl-4.0.0-alpha-5.jar:/home/willem/.m2/repository/org/apache/maven/maven-api-xml/4.0.0-alpha-5/maven-api-xml-4.0.0-alpha-5.jar:/home/willem/.m2/repository/org/apache/maven/maven-api-meta/4.0.0-alpha-5/maven-api-meta-4.0.0-alpha-5.jar:/home/willem/.m2/repository/org/codehaus/plexus/plexus-cipher/2.0/plexus-cipher-2.0.jar:/home/willem/.m2/repository/org/codehaus/plexus/plexus-sec-dispatcher/2.0/plexus-sec-dispatcher-2.0.jar:/home/willem/.m2/repository/org/apache/maven/maven-embedder/3.9.8/maven-embedder-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/maven-core/3.9.8/maven-core-3.9.8.jar:/home/willem/.m2/repository/org/codehaus/plexus/plexus-component-annotations/2.1.0/plexus-component-annotations-2.1.0.jar:/home/willem/.m2/repository/org/apache/maven/maven-plugin-api/3.9.8/maven-plugin-api-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/shared/maven-shared-utils/3.4.2/maven-shared-utils-3.4.2.jar:/home/willem/.m2/repository/com/google/inject/guice/5.1.0/guice-5.1.0.jar:/home/willem/.m2/repository/aopalliance/aopalliance/1.0/aopalliance-1.0.jar:/home/willem/.m2/repository/com/google/guava/guava/33.2.1-jre/guava-33.2.1-jre.jar:/home/willem/.m2/repository/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar:/home/willem/.m2/repository/javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar:/home/willem/.m2/repository/org/codehaus/plexus/plexus-classworlds/2.6.0/plexus-classworlds-2.6.0.jar:/home/willem/.m2/repository/commons-cli/commons-cli/1.8.0/commons-cli-1.8.0.jar:/home/willem/.m2/repository/org/eclipse/sisu/org.eclipse.sisu.plexus/0.9.0.M3/org.eclipse.sisu.plexus-0.9.0.M3.jar:/home/willem/.m2/repository/org/apache/maven/maven-settings-builder/3.9.8/maven-settings-builder-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/maven-resolver-provider/3.9.8/maven-resolver-provider-3.9.8.jar:/home/willem/.m2/repository/org/apache/maven/resolver/maven-resolver-connector-basic/1.9.22/maven-resolver-connector-basic-1.9.22.jar:/home/willem/.m2/repository/org/apache/maven/resolver/maven-resolver-transport-wagon/1.9.22/maven-resolver-transport-wagon-1.9.22.jar:/home/willem/.m2/repository/org/apache/maven/wagon/wagon-http/3.5.3/wagon-http-3.5.3.jar:/home/willem/.m2/repository/org/apache/maven/wagon/wagon-file/3.5.3/wagon-file-3.5.3.jar:/home/willem/.m2/repository/io/quarkus/quarkus-bootstrap-gradle-resolver/3.14.1/quarkus-bootstrap-gradle-resolver-3.14.1.jar:/home/willem/.m2/repository/io/smallrye/jandex/3.2.2/jandex-3.2.2.jar:/home/willem/.m2/repository/commons-io/commons-io/2.16.1/commons-io-2.16.1.jar:/home/willem/.m2/repository/io/quarkus/quarkus-junit5-properties/3.14.1/quarkus-junit5-properties-3.14.1.jar:/home/willem/.m2/repository/org/junit/jupiter/junit-jupiter/5.10.3/junit-jupiter-5.10.3.jar:/home/willem/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.10.3/junit-jupiter-api-5.10.3.jar:/home/willem/.m2/repository/org/opentest4j/opentest4j/1.3.0/opentest4j-1.3.0.jar:/home/willem/.m2/repository/org/junit/platform/junit-platform-commons/1.10.3/junit-platform-commons-1.10.3.jar:/home/willem/.m2/repository/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar:/home/willem/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.10.3/junit-jupiter-params-5.10.3.jar:/home/willem/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.10.3/junit-jupiter-engine-5.10.3.jar:/home/willem/.m2/repository/org/junit/platform/junit-platform-engine/1.10.3/junit-platform-engine-1.10.3.jar:/home/willem/.m2/repository/org/jboss/marshalling/jboss-marshalling/2.1.4.SP1/jboss-marshalling-2.1.4.SP1.jar:/home/willem/.m2/repository/io/rest-assured/rest-assured/5.5.0/rest-assured-5.5.0.jar:/home/willem/.m2/repository/org/apache/groovy/groovy/4.0.22/groovy-4.0.22.jar:/home/willem/.m2/repository/org/apache/groovy/groovy-xml/4.0.22/groovy-xml-4.0.22.jar:/home/willem/.m2/repository/org/apache/httpcomponents/httpclient/4.5.14/httpclient-4.5.14.jar:/home/willem/.m2/repository/org/apache/httpcomponents/httpcore/4.4.16/httpcore-4.4.16.jar:/home/willem/.m2/repository/commons-codec/commons-codec/1.17.1/commons-codec-1.17.1.jar:/home/willem/.m2/repository/org/apache/httpcomponents/httpmime/4.5.14/httpmime-4.5.14.jar:/home/willem/.m2/repository/org/hamcrest/hamcrest/2.2/hamcrest-2.2.jar:/home/willem/.m2/repository/org/ccil/cowan/tagsoup/tagsoup/1.2.1/tagsoup-1.2.1.jar:/home/willem/.m2/repository/io/rest-assured/json-path/5.5.0/json-path-5.5.0.jar:/home/willem/.m2/repository/org/apache/groovy/groovy-json/4.0.22/groovy-json-4.0.22.jar:/home/willem/.m2/repository/io/rest-assured/rest-assured-common/5.5.0/rest-assured-common-5.5.0.jar:/home/willem/.m2/repository/io/rest-assured/xml-path/5.5.0/xml-path-5.5.0.jar:/home/willem/.m2/repository/org/apache/commons/commons-lang3/3.14.0/commons-lang3-3.14.0.jar com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit5 nl.vea.GreetingResourceIT
Executing "/home/willem/.sdkman/candidates/java/21.0.4-tem/bin/java -Dquarkus.http.port=8081 -Dquarkus.http.ssl-port=8444 -Dtest.url=http://localhost:8081 -Dquarkus.log.file.path=/home/willem/git/quia/target/quarkus.log -Dquarkus.log.file.enable=true -Dquarkus.log.category."io.quarkus".level=INFO -jar /home/willem/git/quia/target/quarkus-app/quarkus-run.jar"
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2024-09-24 00:01:16,669 INFO  [io.quarkus] (main) quia 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.14.1) started in 0.827s. Listening on: http://0.0.0.0:8081
2024-09-24 00:01:16,679 INFO  [io.quarkus] (main) Profile prod activated. 
2024-09-24 00:01:16,679 INFO  [io.quarkus] (main) Installed features: [cdi, rest, smallrye-context-propagation, vertx]

Process finished with exit code 0
```
</details>

---

## Running the actual application in production mode
- execute `~/git/quia$ sdk env` to activate Java 21 in the terminal instead of the default Java 17
- execute `~/git/quia$ mvn clean package -e` to create all artifacts
- `target/quarkus-app` contains:
  - `app/quia-1.0.0-SNAPSHOT.jar` as the primary result of the `mvn package` command, this is NOT a fat jar,
    and it is NOT runnable as its `META-INF/` directory doesn't contain a manifest file.
  - therefore, there is a `lib/` directory as well with all the dependencies
  - `quarkus-run.jar` as a runnable jar, which only contains a `META-INF/MANIFEST.MF` that states all jars in
    `target/quarkus-app/lib` as part of the `Class-Path` and `Main-Class: io.quarkus.bootstrap.runner.QuarkusEntryPoint`
    This class probably knows that it has to use `target/quarkus-app/app/quia-1.0.0-SNAPSHOT.jar`
- Thus, running `~/git/quia$ java -jar target/quarkus-app/quarkus-run.jar` will execute the application in production 
  mode.
- This doesn't have the interactive CLI commands, like `q` for ending the program, the dev mode provides. Hence, we
  used `Ctrl+C` to quit.
- When you look at the generated [../src/main/docker/Dockerfile.jvm](../src/main/docker/Dockerfile.jvm), you see that
  the image the subdirectories described above copies into different layers (as each separate COPY command produces
  a separate layer)
- For the base image we probably want to move to `registry.access.redhat.com/ubi8/openjdk-21:1.20` see
  - [https://catalog.redhat.com/search?q=OpenJDK&searchType=containers&architecture=amd64&p=1](https://catalog.redhat.com/search?q=OpenJDK&searchType=containers&architecture=amd64&p=1)
  - [https://catalog.redhat.com/software/containers/ubi8/openjdk-21/653fb7e21b2ec10f7dfc10d0?image=66f23c1b2930d5fe46ed47fc](https://catalog.redhat.com/software/containers/ubi8/openjdk-21/653fb7e21b2ec10f7dfc10d0?image=66f23c1b2930d5fe46ed47fc)
  - [https://catalog.redhat.com/software/containers/ubi9/openjdk-21/6501cdb5c34ae048c44f7814?gs=&q=OpenJDK%20&container-tabs=technical-information](https://catalog.redhat.com/software/containers/ubi9/openjdk-21/6501cdb5c34ae048c44f7814?gs=&q=OpenJDK%20&container-tabs=technical-information)
