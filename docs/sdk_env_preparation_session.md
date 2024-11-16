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

# Preparing terminals starting in `~/git/quia` with Java 21

## session terminal 1
```bash
willem@willem-Latitude-5590:~/git/quia$ sdk current java

Using java version 17.0.12-tem
willem@willem-Latitude-5590:~/git/quia$ sdk list java | grep tem
 Temurin       |     | 23           | tem     |            | 23-tem              
               |     | 22.0.2       | tem     |            | 22.0.2-tem          
               |     | 21.0.4       | tem     | installed  | 21.0.4-tem          
               | >>> | 17.0.12      | tem     | installed  | 17.0.12-tem         
               |     | 11.0.24      | tem     | installed  | 11.0.24-tem         
               |     | 8.0.422      | tem     |            | 8.0.422-tem         
Omit Identifier to install default version 21.0.4-tem:
    $ sdk install java 21.0.4-tem
willem@willem-Latitude-5590:~/git/quia$ sdk use java 21.0.4-tem 

Using java version 21.0.4-tem in this shell.
willem@willem-Latitude-5590:~/git/quia$ sdk env init
.sdkmanrc created.
willem@willem-Latitude-5590:~/git/quia$ cat .sdkmanrc 
# Enable auto-env through the sdkman_auto_env config
# Add key=value pairs of SDKs to use below
java=21.0.4-tem
willem@willem-Latitude-5590:~/git/quia$ sdk env

Using java version 21.0.4-tem in this shell.
willem@willem-Latitude-5590:~/git/quia$ sdk current

Using:

java: 21.0.4-tem
maven: 3.9.9
quarkus: 3.14.4
spark: 3.5.1
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

--
Tests paused
Press [space] to restart, [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the terminal, [h] for more options>

```

## session terminal 2
```bash
willem@willem-Latitude-5590:~/git/quia$ sdk current

Using:

java: 17.0.12-tem
maven: 3.9.9
quarkus: 3.14.4
spark: 3.5.1
willem@willem-Latitude-5590:~/git/quia$ sdk env

Using java version 21.0.4-tem in this shell.
willem@willem-Latitude-5590:~/git/quia$ sdk current

Using:

java: 21.0.4-tem
maven: 3.9.9
quarkus: 3.14.4
spark: 3.5.1
willem@willem-Latitude-5590:~/git/quia$ 

```