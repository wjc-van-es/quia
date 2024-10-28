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

