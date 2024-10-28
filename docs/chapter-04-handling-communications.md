<style>
body {
  font-family: "Gentium Basic", Cardo, "Linux Libertine o", "Palatino Linotype", Cambria, serif;
  font-size: 130% !important;
}
code {
	padding: 0 .25em;
	
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

img {
  width: auto; 
  height: 80%;
  max-height: 100%; 
}
</style>

# 4 Handling communications

## Change of setup
- It occurred to me that we will have several (micro)services under the `~/git/quia` root dir. We, therefore, moved our
  efforts from chapter 2 & 3 and move everything into `~/git/quia/services/quia/`. 
- All new services will be established under `~/git/quia/services/` and the git repo remains under `~/git/quia`.
- For IntelliJ projects to be easily navigable and keep everything under a single quia-parent project umbrella, we also
  introduced a parent pom for a `nl.vea.quia:quia-parent` project residing under the `~/git/quia` root.
- To keep all the services as independent as possible we decided to only move the properties containing the versions
  of the various dependencies to the parent pom and keep all the other structure under the service module pom files.
- we can still run the quia service module in dev mode: `~/git/quia/services/quia$ quarkus dev`
- we can still build the quia service module in isolation: `~/git/quia/services/quia$ mvn clean package -e`
- we should be able to run a jvm version of production with 
  `~/git/quia/services/quia$ java -jar target/quarkus-app/quarkus-run.jar`, but we experimented with oidc in dev mode
  therefore we get '`quarkus.oidc.auth-server-url' property must be configured`. For now this isn't very important to 
  solve.

## §4.2 Car rental Service
- If we look at [quarkus-installable-extensions-list.txt](quarkus-installable-extensions-list.txt) we notice that we 
  have to specify different extension names then mentioned in the book MEAP V09:
  - `quarkus-rest-jackson` instead of `quarkus-resteasy-reactive-jackson`
  - `quarkus-rest-client-jackson` instead of `rest-client-reactive-jackson`
  - `quarkus-smallrye-openapi` seems to be ok.
- This is also confirmed by looking at [https://github.com/xstefank/quarkus-in-action/blob/main/chapter-04/4_2/reservation-service/pom.xml]
(https://github.com/xstefank/quarkus-in-action/blob/main/chapter-04/4_2/reservation-service/pom.xml
  - This also still has the same versions set in properties that we moved to the quia-parent pom for uniformity.
- We therefore arrive at the following CLI command:
  ```bash
  willem@linux-laptop:~/git/quia/services$ quarkus create app \
  --extension quarkus-rest-jackson,quarkus-rest-client-jackson,quarkus-smallrye-openapi --no-code
  Looking for the newly published extensions in registry.quarkus.io
  -----------
  selected extensions:
  - io.quarkus:quarkus-smallrye-openapi
    - io.quarkus:quarkus-rest-jackson
    - io.quarkus:quarkus-rest-client-jackson
  
  
  applying codestarts...
  📚 java
  🔨 maven
  📦 quarkus
  📝 config-properties
  🔧 tooling-dockerfiles
  🔧 tooling-maven-wrapper
  
  -----------
  [SUCCESS] ✅  quarkus project has been successfully generated in:
  --> /home/willem/git/quia/services/code-with-quarkus
  -----------
  Navigate into this directory and get started: quarkus dev
  willem@linux-laptop:~/git/quia/services$ quarkus create app nl.vea.quia:reservation-service --extension quarkus-rest-jackson,quarkus-rest-client-jackson,quarkus-smallrye-openapi --no-code
  -----------
  selected extensions:
  - io.quarkus:quarkus-smallrye-openapi
    - io.quarkus:quarkus-rest-jackson
    - io.quarkus:quarkus-rest-client-jackson
  
  
  applying codestarts...
  📚 java
  🔨 maven
  📦 quarkus
  📝 config-properties
  🔧 tooling-dockerfiles
  🔧 tooling-maven-wrapper
  
  -----------
  [SUCCESS] ✅  quarkus project has been successfully generated in:
  --> /home/willem/git/quia/services/reservation-service
  -----------
  Navigate into this directory and get started: quarkus dev
  willem@linux-laptop:~/git/quia/services$

  ```
- The resulting pom still has the same version values as those from the parent pom except for `surefire-plugin.version`,
  which has been updated in the parent pom to `3.5.0` whilst the generated code still uses `3.3.1`.