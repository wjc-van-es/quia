<style>
body {
  font-family: "Gentium Basic", Cardo , "Linux Libertine o", "Palatino Linotype", Cambria, serif;
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

</style>

# 2 Your first Quarkus application

## Attempt at using the CLI tool to create an application
Without specifying the `-P 3.5.0` for the BOM
`quarkus create app nl.vea:quia --extension resteasy-reactive` let to a pom file that settled for version `3.8.6`
This however could not be build with `mvn clean package`:
`Could not find artifact io.quarkus:quarkus-rest:jar:3.8.6 in central (https://repo.maven.apache.org/maven2)`
Adding Redhat repo's to the pom file didn't work, settling for BOM version `3.8.5` or the `3.5.0` versions in the book
didn't work either.

---
### Note
I temporarily added [https://maven.repository.redhat.com/ga/](https://maven.repository.redhat.com/ga/) as dependency
and plugin-dependency repository to the project's pom.xml, but this didn't help and any dependency found was downloaded
from maven's default `central` not `red-hat-enterprise-maven-repository`
Instructions in
[https://docs.redhat.com/en/documentation/red_hat_build_of_quarkus/3.8/html-single/developing_and_compiling_your_red_hat_build_of_quarkus_applications_with_apache_maven/index#proc_online-maven_quarkus-maven](https://docs.redhat.com/en/documentation/red_hat_build_of_quarkus/3.8/html-single/developing_and_compiling_your_red_hat_build_of_quarkus_applications_with_apache_maven/index#proc_online-maven_quarkus-maven)
suggests to temper with ~/.m2/settings.xml, but I prefer everything to be configured in the project's pom.xml
to prevent any "works on my machine" exceptions. so I placed the repo for both plugins and regular dependencies there.
see: https://maven.apache.org/guides/mini/guide-multiple-repositories.html
---

Comparing the created pom with the ones in the https://github.com/xstefank/quarkus-in-action revealed several 
differences most notably `<quarkus.platform.version>3.14.1</quarkus.platform.version>` a far more advanced version
and the dependency `io.quarkus:resteasy-reactive` being absent from the repository's pom.

Now `mvn clean package` worked fine.
