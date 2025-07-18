<style>
body {
  font-family: Spectral, "Gentium Basic", Cardo , "Linux Libertine o", "Palatino Linotype", Cambria, serif;
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

# 3 Enhancing developer productivity with Quarkus

## § 3.2 Application configuration
- [https://quarkus.io/guides/config-reference](https://quarkus.io/guides/config-reference)
  - [https://quarkus.io/guides/config-reference#application-properties-file](https://quarkus.io/guides/config-reference#application-properties-file)
  - [https://quarkus.io/guides/config-reference#inject](https://quarkus.io/guides/config-reference#inject)
  - [https://quarkus.io/guides/config-reference#programmatically-access](https://quarkus.io/guides/config-reference#programmatically-access)
  - [https://quarkus.io/guides/config-reference#profiles](https://quarkus.io/guides/config-reference#profiles)
  - [https://quarkus.io/guides/config-reference#property-expressions](https://quarkus.io/guides/config-reference#property-expressions)
  - [https://quarkus.io/guides/config-reference#secret-keys-expressions](https://quarkus.io/guides/config-reference#secret-keys-expressions)
- [https://quarkus.io/guides/all-config](https://quarkus.io/guides/all-config)

## § 3.4 Dev Services

### § 3.4.1 Securing the Quarkus application using OpenID Connect and Dev Services

```bash
willem@willem-Latitude-5590:~/git/quia$ sdk current

Using:

java: 21.0.4-tem
maven: 3.9.9
quarkus: 3.14.4
spark: 3.5.1
willem@willem-Latitude-5590:~/git/quia$ QUIA_GREETING="Env var is greeting you" quarkus dev 
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------------< nl.vea:quia >-----------------------------
[INFO] Building quia 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- quarkus:3.15.1:dev (default-cli) @ quia ---
[INFO] Invoking resources:3.3.1:resources (default-resources) @ quia
[INFO] Copying 3 resources from src/main/resources to target/classes
[INFO] Invoking quarkus:3.15.1:generate-code (default) @ quia
[INFO] Invoking compiler:3.13.0:compile (default-compile) @ quia
[INFO] Nothing to compile - all classes are up to date.
[INFO] Invoking resources:3.3.1:testResources (default-testResources) @ quia
[INFO] skip non existing resourceDirectory /home/willem/git/quia/src/test/resources
[INFO] Invoking quarkus:3.15.1:generate-code-tests (default) @ quia
[INFO] Invoking compiler:3.13.0:testCompile (default-testCompile) @ quia
[INFO] Nothing to compile - all classes are up to date.
Listening for transport dt_socket at address: 5005
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2024-10-20 20:19:58,194 INFO  [io.quarkus] (Quarkus Main Thread) quia 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.15.1) started in 2.409s. Listening on: http://localhost:8080

2024-10-20 20:19:58,197 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2024-10-20 20:19:58,198 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, rest, smallrye-context-propagation, vertx]

--
Tests paused
Press [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the terminal, [h] for more options>
```

In another terminal
```bash
willem@willem-Latitude-5590:~/git/quia$ quarkus extension add oidc
Looking for the newly published extensions in registry.quarkus.io
[SUCCESS] ✅  Extension io.quarkus:quarkus-oidc has been installed
willem@willem-Latitude-5590:~/git/quia$
```

In [../pom.xml](../pom.xml) the dependency has been added:
```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-oidc</artifactId>
</dependency>
```

In the running dev mode terminal:

<details>

```bash
Press [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the terminal, [h] for more options>[INFO] Changes detected to [/home/willem/git/quia/pom.xml], restarting dev mode

[INFO] Invoking resources:3.3.1:resources (default-resources) @ quia
[INFO] Copying 3 resources from src/main/resources to target/classes
[INFO] Invoking quarkus:3.15.1:generate-code (default) @ quia
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc/3.15.1/quarkus-oidc-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc/3.15.1/quarkus-oidc-3.15.1.pom (3.5 kB at 19 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-parent/3.15.1/quarkus-oidc-parent-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-parent/3.15.1/quarkus-oidc-parent-3.15.1.pom (748 B at 36 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-security/3.15.1/quarkus-security-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-security/3.15.1/quarkus-security-3.15.1.pom (3.2 kB at 158 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common/3.15.1/quarkus-oidc-common-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common/3.15.1/quarkus-oidc-common-3.15.1.pom (3.2 kB at 176 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common-parent/3.15.1/quarkus-oidc-common-parent-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common-parent/3.15.1/quarkus-oidc-common-parent-3.15.1.pom (754 B at 54 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/smallrye/reactive/smallrye-mutiny-vertx-web-client/3.15.0/smallrye-mutiny-vertx-web-client-3.15.0.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/smallrye/reactive/smallrye-mutiny-vertx-web-client/3.15.0/smallrye-mutiny-vertx-web-client-3.15.0.pom (6.9 kB at 288 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/vertx/vertx-web-client/4.5.10/vertx-web-client-4.5.10.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/vertx/vertx-web-client/4.5.10/vertx-web-client-4.5.10.pom (2.9 kB at 145 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build/3.15.1/quarkus-smallrye-jwt-build-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build/3.15.1/quarkus-smallrye-jwt-build-3.15.1.pom (2.4 kB at 144 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build-parent/3.15.1/quarkus-smallrye-jwt-build-parent-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build-parent/3.15.1/quarkus-smallrye-jwt-build-parent-3.15.1.pom (765 B at 48 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc/3.15.1/quarkus-oidc-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-security/3.15.1/quarkus-security-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build/3.15.1/quarkus-smallrye-jwt-build-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common/3.15.1/quarkus-oidc-common-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/vertx/vertx-web-client/4.5.10/vertx-web-client-4.5.10.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/smallrye/reactive/smallrye-mutiny-vertx-web-client/3.15.0/smallrye-mutiny-vertx-web-client-3.15.0.jar
Progress (5): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (11/45 kB) | vertx-web-client-4.5.10.jar (16/117 kB) | quarkus-security-3.15.1.jar (1.4/106 kB) |Progress (5): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (9.7/45 kB) | vertx-web-client-4.5.10.jar (16/117 kB) | quarkus-security-3.15.1.jar (1.4/106 kB) Progress (5): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (11/45 kB) | vertx-web-client-4.5.10.jar (16/117 kB) | quarkus-security-3.15.1.jar (2.8/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (11/45 kB) | vertx-web-client-4.5.10.jar (16/117 kB) | quarkus-security-3.15.1.jar (2.8/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (11/45 kB) | vertx-web-client-4.5.10.jar (16/117 kB) | quarkus-security-3.15.1.jar (4.1/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (11/45 kB) | vertx-web-client-4.5.10.jar (33/117 kB) | quarkus-security-3.15.1.jar (4.1/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (12/45 kB) | vertx-web-client-4.5.10.jar (33/117 kB) | quarkus-security-3.15.1.jar (4.1/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (12/45 kB) | vertx-web-client-4.5.10.jar (33/117 kB) | quarkus-security-3.15.1.jar (5.5/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (33/117 kB) | quarkus-security-3.15.1.jar (6.9/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (11/45 kB) | vertx-web-client-4.5.10.jar (33/117 kB) | quarkus-security-3.15.1.jar (4.1/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (33/117 kB) | quarkus-security-3.15.1.jar (8.3/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (33/117 kB) | quarkus-security-3.15.1.jar (9.7/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (9.7/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (63/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (9.7/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (9.7/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (11/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (11/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (11/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (14/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (12/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (12/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (12/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (12/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (14/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (15/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (95/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (17/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (95/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (17/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (17/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (79/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (17/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (49/117 kB) | quarkus-security-3.15.1.jar (17/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (15/45 kB) | vertx-web-client-4.5.10.jar (66/117 kB) | quarkus-security-3.15.1.jar (17/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (17/45 kB) | vertx-web-client-4.5.10.jar (66/117 kB) | quarkus-security-3.15.1.jar (17/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (17/45 kB) | vertx-web-client-4.5.10.jar (66/117 kB) | quarkus-security-3.15.1.jar (17/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (17/45 kB) | vertx-web-client-4.5.10.jar (66/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (17/45 kB) | vertx-web-client-4.5.10.jar (66/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (18/45 kB) | vertx-web-client-4.5.10.jar (66/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (18/45 kB) | vertx-web-client-4.5.10.jar (82/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (17/45 kB) | vertx-web-client-4.5.10.jar (66/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (19/45 kB) | vertx-web-client-4.5.10.jar (82/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (98/346 kB) | quarkus-oidc-common-3.15.1.jar (19/45 kB) | vertx-web-client-4.5.10.jar (82/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) | Progress (6): quarkus-oidc-3.15.1.jar (114/346 kB) | quarkus-oidc-common-3.15.1.jar (21/45 kB) | vertx-web-client-4.5.10.jar (82/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (114/346 kB) | quarkus-oidc-common-3.15.1.jar (19/45 kB) | vertx-web-client-4.5.10.jar (82/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (114/346 kB) | quarkus-oidc-common-3.15.1.jar (21/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (114/346 kB) | quarkus-oidc-common-3.15.1.jar (21/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (18/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (21/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (21/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (21/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (21/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (22/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (22/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (23/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (23/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (23/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (23/45 kB) | vertx-web-client-4.5.10.jar (98/117 kB) | quarkus-security-3.15.1.jar (34/106 kB) |Progress (6): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (23/45 kB) | vertx-web-client-4.5.10.jar (115/117 kB) | quarkus-security-3.15.1.jar (34/106 kB)                                                                                                                                                                                     Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build/3.15.1/quarkus-smallrye-jwt-build-3.15.1.jar (10 kB at 63 kB/s)
Progress (5): quarkus-oidc-3.15.1.jar (128/346 kB) | quarkus-oidc-common-3.15.1.jar (23/45 kB) | vertx-web-client-4.5.10.jar (117 kB) | quarkus-security-3.15.1.jar (34/106 kB) | sm                                                                                                                                                                                    Downloaded from central: https://repo.maven.apache.org/maven2/io/vertx/vertx-web-client/4.5.10/vertx-web-client-4.5.10.jar (117 kB at 686 kB/s)
Progress (4): quarkus-oidc-3.15.1.jar (144/346 kB) | quarkus-oidc-common-3.15.1.jar (23/45 kB) | quarkus-security-3.15.1.jar (34/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (144/346 kB) | quarkus-oidc-common-3.15.1.jar (23/45 kB) | quarkus-security-3.15.1.jar (51/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (144/346 kB) | quarkus-oidc-common-3.15.1.jar (25/45 kB) | quarkus-security-3.15.1.jar (51/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (144/346 kB) | quarkus-oidc-common-3.15.1.jar (26/45 kB) | quarkus-security-3.15.1.jar (51/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (144/346 kB) | quarkus-oidc-common-3.15.1.jar (28/45 kB) | quarkus-security-3.15.1.jar (51/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (144/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (51/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (144/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (67/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (144/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (67/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (67/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (67/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (67/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (67/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (67/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (67/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (83/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (30/45 kB) | quarkus-security-3.15.1.jar (83/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (29/45 kB) | quarkus-security-3.15.1.jar (83/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (30/45 kB) | quarkus-security-3.15.1.jar (83/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (32/45 kB) | quarkus-security-3.15.1.jar (83/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (32/45 kB) | quarkus-security-3.15.1.jar (83/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (33/45 kB) | quarkus-security-3.15.1.jar (83/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (34/45 kB) | quarkus-security-3.15.1.jar (83/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (36/45 kB) | quarkus-security-3.15.1.jar (83/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jProgress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (36/45 kB) | quarkus-security-3.15.1.jar (100/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.Progress (4): quarkus-oidc-3.15.1.jar (158/346 kB) | quarkus-oidc-common-3.15.1.jar (37/45 kB) | quarkus-security-3.15.1.jar (100/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.Progress (4): quarkus-oidc-3.15.1.jar (174/346 kB) | quarkus-oidc-common-3.15.1.jar (37/45 kB) | quarkus-security-3.15.1.jar (100/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.Progress (4): quarkus-oidc-3.15.1.jar (174/346 kB) | quarkus-oidc-common-3.15.1.jar (39/45 kB) | quarkus-security-3.15.1.jar (100/106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.Progress (4): quarkus-oidc-3.15.1.jar (174/346 kB) | quarkus-oidc-common-3.15.1.jar (39/45 kB) | quarkus-security-3.15.1.jar (106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jar Progress (4): quarkus-oidc-3.15.1.jar (174/346 kB) | quarkus-oidc-common-3.15.1.jar (40/45 kB) | quarkus-security-3.15.1.jar (106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jar Progress (4): quarkus-oidc-3.15.1.jar (174/346 kB) | quarkus-oidc-common-3.15.1.jar (41/45 kB) | quarkus-security-3.15.1.jar (106 kB) | smallrye-mutiny-vertx-web-client-3.15.0.jar                                                                                                                                                                                     Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-security/3.15.1/quarkus-security-3.15.1.jar (106 kB at 552 kB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common/3.15.1/quarkus-oidc-common-3.15.1.jar (45 kB at 233 kB/s) 
Downloaded from central: https://repo.maven.apache.org/maven2/io/smallrye/reactive/smallrye-mutiny-vertx-web-client/3.15.0/smallrye-mutiny-vertx-web-client-3.15.0.jar (51 kB at 242 kB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc/3.15.1/quarkus-oidc-3.15.1.jar (346 kB at 1.5 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-deployment/3.15.1/quarkus-oidc-deployment-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-deployment/3.15.1/quarkus-oidc-deployment-3.15.1.pom (6.3 kB at 369 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common-deployment/3.15.1/quarkus-oidc-common-deployment-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common-deployment/3.15.1/quarkus-oidc-common-deployment-3.15.1.pom (2.5 kB at 180 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build-deployment/3.15.1/quarkus-smallrye-jwt-build-deployment-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build-deployment/3.15.1/quarkus-smallrye-jwt-build-deployment-3.15.1.pom (2.3 kB at 208 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-security-deployment/3.15.1/quarkus-security-deployment-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-security-deployment/3.15.1/quarkus-security-deployment-3.15.1.pom (2.7 kB at 194 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-deployment/3.15.1/quarkus-devservices-deployment-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-deployment/3.15.1/quarkus-devservices-deployment-3.15.1.pom (1.9 kB at 173 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-parent/3.15.1/quarkus-devservices-parent-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-parent/3.15.1/quarkus-devservices-parent-3.15.1.pom (1.2 kB at 90 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-common/3.15.1/quarkus-devservices-common-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-common/3.15.1/quarkus-devservices-common-3.15.1.pom (2.0 kB at 167 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-api/3.4.0/docker-java-api-3.4.0.pom
Downloaded from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-api/3.4.0/docker-java-api-3.4.0.pom (2.4 kB at 200 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-parent/3.4.0/docker-java-parent-3.4.0.pom
Downloaded from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-parent/3.4.0/docker-java-parent-3.4.0.pom (12 kB at 980 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/testcontainers/testcontainers/1.20.1/testcontainers-1.20.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/testcontainers/testcontainers/1.20.1/testcontainers-1.20.1.pom (2.6 kB at 218 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/jetbrains/annotations/24.1.0/annotations-24.1.0.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/jetbrains/annotations/24.1.0/annotations-24.1.0.pom (1.3 kB at 132 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-transport-zerodep/3.4.0/docker-java-transport-zerodep-3.4.0.pom
Downloaded from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-transport-zerodep/3.4.0/docker-java-transport-zerodep-3.4.0.pom (4.1 kB at 314 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-transport/3.4.0/docker-java-transport-3.4.0.pom
Downloaded from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-transport/3.4.0/docker-java-transport-3.4.0.pom (1.6 kB at 149 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/net/java/dev/jna/jna/5.8.0/jna-5.8.0.pom
Downloaded from central: https://repo.maven.apache.org/maven2/net/java/dev/jna/jna/5.8.0/jna-5.8.0.pom (1.6 kB at 144 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-junit4-mock/3.15.1/quarkus-junit4-mock-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-junit4-mock/3.15.1/quarkus-junit4-mock-3.15.1.pom (714 B at 60 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-datasource-common/3.15.1/quarkus-datasource-common-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-datasource-common/3.15.1/quarkus-datasource-common-3.15.1.pom (1.1 kB at 11 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-datasource-parent/3.15.1/quarkus-datasource-parent-3.15.1.pom
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-datasource-parent/3.15.1/quarkus-datasource-parent-3.15.1.pom (814 B at 68 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-core/25.0.6/keycloak-core-25.0.6.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-core/25.0.6/keycloak-core-25.0.6.pom (6.7 kB at 559 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-parent/25.0.6/keycloak-parent-25.0.6.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-parent/25.0.6/keycloak-parent-25.0.6.pom (86 kB at 4.3 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-common/25.0.6/keycloak-common-25.0.6.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-common/25.0.6/keycloak-common-25.0.6.pom (4.2 kB at 351 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/eclipse/angus/angus-activation/2.0.2/angus-activation-2.0.2.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/eclipse/angus/angus-activation/2.0.2/angus-activation-2.0.2.pom (4.0 kB at 399 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/eclipse/angus/angus-activation-project/2.0.2/angus-activation-project-2.0.2.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/eclipse/angus/angus-activation-project/2.0.2/angus-activation-project-2.0.2.pom (21 kB at 1.7 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-deployment/3.15.1/quarkus-oidc-deployment-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common-deployment/3.15.1/quarkus-oidc-common-deployment-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-common/3.15.1/quarkus-devservices-common-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-deployment/3.15.1/quarkus-devservices-deployment-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-security-deployment/3.15.1/quarkus-security-deployment-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build-deployment/3.15.1/quarkus-smallrye-jwt-build-deployment-3.15.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-datasource-common/3.15.1/quarkus-datasource-common-3.15.1.jar
Progress (4): quarkus-smallrye-jwt-build-deployment-3.15.1.jar (5.5/9.8 kB) | quarkus-security-deployment-3.15.1.jar (16/69 kB) | quarkus-devservices-common-3.15.1.jar (16/17 kB) |Progress (4): quarkus-smallrye-jwt-build-deployment-3.15.1.jar (6.9/9.8 kB) | quarkus-security-deployment-3.15.1.jar (16/69 kB) | quarkus-devservices-common-3.15.1.jar (16/17 kB) |Progress (4): quarkus-smallrye-jwt-build-deployment-3.15.1.jar (8.3/9.8 kB) | quarkus-security-deployment-3.15.1.jar (16/69 kB) | quarkus-devservices-common-3.15.1.jar (16/17 kB) |Progress (4): quarkus-smallrye-jwt-build-deployment-3.15.1.jar (9.7/9.8 kB) | quarkus-security-deployment-3.15.1.jar (16/69 kB) | quarkus-devservices-common-3.15.1.jar (16/17 kB) |Progress (4): quarkus-smallrye-jwt-build-deployment-3.15.1.jar (9.8 kB) | quarkus-security-deployment-3.15.1.jar (16/69 kB) | quarkus-devservices-common-3.15.1.jar (16/17 kB) | quaProgress (4): quarkus-smallrye-jwt-build-deployment-3.15.1.jar (9.8 kB) | quarkus-security-deployment-3.15.1.jar (16/69 kB) | quarkus-devservices-common-3.15.1.jar (17 kB) | quarku                                                                                                                                                                                    Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-common/3.15.1/quarkus-devservices-common-3.15.1.jar (17 kB at 947 kB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-smallrye-jwt-build-deployment/3.15.1/quarkus-smallrye-jwt-build-deployment-3.15.1.jar (9.8 kB at 490 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-core/25.0.6/keycloak-core-25.0.6.jar
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-common-deployment/3.15.1/quarkus-oidc-common-deployment-3.15.1.jar (7.3 kB at 348 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/eclipse/angus/angus-activation/2.0.2/angus-activation-2.0.2.jar
Downloading from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-common/25.0.6/keycloak-common-25.0.6.jar
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-devservices-deployment/3.15.1/quarkus-devservices-deployment-3.15.1.jar (23 kB at 871 kB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-security-deployment/3.15.1/quarkus-security-deployment-3.15.1.jar (69 kB at 2.6 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/org/testcontainers/testcontainers/1.20.1/testcontainers-1.20.1.jar
Downloading from central: https://repo.maven.apache.org/maven2/org/jetbrains/annotations/24.1.0/annotations-24.1.0.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/eclipse/angus/angus-activation/2.0.2/angus-activation-2.0.2.jar (27 kB at 1.1 MB/s)                          
Downloading from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-api/3.4.0/docker-java-api-3.4.0.jar     
Downloaded from central: https://repo.maven.apache.org/maven2/org/jetbrains/annotations/24.1.0/annotations-24.1.0.jar (31 kB at 1.2 MB/s)                                     
Downloading from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-transport-zerodep/3.4.0/docker-java-transport-zerodep-3.4.0.jar
Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-oidc-deployment/3.15.1/quarkus-oidc-deployment-3.15.1.jar (80 kB at 1.4 MB/s)
Downloading from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-transport/3.4.0/docker-java-transport-3.4.0.jar
Downloaded from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-common/25.0.6/keycloak-common-25.0.6.jar (176 kB at 2.6 MB/s)                           
Downloading from central: https://repo.maven.apache.org/maven2/net/java/dev/jna/jna/5.8.0/jna-5.8.0.jar                            
Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (98/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0/2.3Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (98/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0/2.3Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (115/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0/2.Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (115/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0/2.Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (115/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0/2.Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (131/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0/2.Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (131/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (131/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (147/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (147/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (164/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (164/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (5): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (164/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/                                                                                                                                                                                    Downloaded from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-transport/3.4.0/docker-java-transport-3.4.0.jar (39 kB at 820 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-junit4-mock/3.15.1/quarkus-junit4-mock-3.15.1.jar
Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (164/442 kB) | docker-java-api-3.4.0.jar (147/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (180/442 kB) | docker-java-api-3.4.0.jar (163/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (164/442 kB) | docker-java-api-3.4.0.jar (163/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (180/442 kB) | docker-java-api-3.4.0.jar (163/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (197/442 kB) | docker-java-api-3.4.0.jar (163/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (197/442 kB) | docker-java-api-3.4.0.jar (180/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (213/442 kB) | docker-java-api-3.4.0.jar (180/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (213/442 kB) | docker-java-api-3.4.0.jar (196/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (196/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (212/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (229/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.2/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (229/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (229/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (229/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (229/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (245/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (262/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (278/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (229/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (278/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (278/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.3/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (294/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (294/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (294/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (311/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (327/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (327/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (343/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (343/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (343/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (360/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (360/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.4/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (376/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (376/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (393/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (229/442 kB) | docker-java-api-3.4.0.jar (409/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (409/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (425/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (425/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (425/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (442/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (442/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.1/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (442/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (442/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (4): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (246/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (262/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (262/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (279/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (279/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (279/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (279/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.2/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (279/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (295/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (311/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (311/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (311/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (311/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (311/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (311/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (311/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (328/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (344/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (360/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (377/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (377/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (377/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (377/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.3/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (377/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (377/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (377/442 kB) | docker-java-api-3.4.0.jar (458/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (377/442 kB) | docker-java-api-3.4.0.jar (475/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (393/442 kB) | docker-java-api-3.4.0.jar (475/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/Progress (6): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (393/442 kB) | docker-java-api-3.4.0.jar (475/485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/Progress (6): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (393/442 kB) | docker-java-api-3.4.0.jar (485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 Progress (6): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (410/442 kB) | docker-java-api-3.4.0.jar (485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 Progress (6): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (426/442 kB) | docker-java-api-3.4.0.jar (485 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3                                                                                                                                                                                     Downloaded from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-api/3.4.0/docker-java-api-3.4.0.jar (485 kB at 3.2 MB/s)
Progress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (426/442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quProgress (6): testcontainers-1.20.1.jar (0.6/18 MB) | keycloak-core-25.0.6.jar (426/442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quProgress (5): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (426/442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quProgress (6): testcontainers-1.20.1.jar (0.6/18 MB) | keycloak-core-25.0.6.jar (426/442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quProgress (6): testcontainers-1.20.1.jar (0.5/18 MB) | keycloak-core-25.0.6.jar (426/442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quProgress (6): testcontainers-1.20.1.jar (0.6/18 MB) | keycloak-core-25.0.6.jar (442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quarkuProgress (6): testcontainers-1.20.1.jar (0.6/18 MB) | keycloak-core-25.0.6.jar (442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quarku                                                                                                                                                                                    Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-junit4-mock/3.15.1/quarkus-junit4-mock-3.15.1.jar (8.2 kB at 81 kB/s)
Progress (5): testcontainers-1.20.1.jar (0.6/18 MB) | keycloak-core-25.0.6.jar (442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quarkuProgress (5): testcontainers-1.20.1.jar (0.6/18 MB) | keycloak-core-25.0.6.jar (442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quarkuProgress (5): testcontainers-1.20.1.jar (0.6/18 MB) | keycloak-core-25.0.6.jar (442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quarkuProgress (5): testcontainers-1.20.1.jar (0.6/18 MB) | keycloak-core-25.0.6.jar (442 kB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quarku                                                                                                                                                                                    Downloaded from central: https://repo.maven.apache.org/maven2/org/keycloak/keycloak-core/25.0.6/keycloak-core-25.0.6.jar (442 kB at 2.3 MB/s)
Progress (4): testcontainers-1.20.1.jar (0.6/18 MB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quarkus-datasource-common-3.15.1.jar (12 kProgress (4): testcontainers-1.20.1.jar (0.6/18 MB) | docker-java-transport-zerodep-3.4.0.jar (0.4/2.3 MB) | jna-5.8.0.jar (0.1/1.7 MB) | quarkus-datasource-common-3.15.1.jar (12 k                                                                                                                                                                                    Downloaded from central: https://repo.maven.apache.org/maven2/io/quarkus/quarkus-datasource-common/3.15.1/quarkus-datasource-common-3.15.1.jar (12 kB at 56 kB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/net/java/dev/jna/jna/5.8.0/jna-5.8.0.jar (1.7 MB at 4.0 MB/s)            
Downloaded from central: https://repo.maven.apache.org/maven2/com/github/docker-java/docker-java-transport-zerodep/3.4.0/docker-java-transport-zerodep-3.4.0.jar (2.3 MB at 4.2 MB/s)
Downloaded from central: https://repo.maven.apache.org/maven2/org/testcontainers/testcontainers/1.20.1/testcontainers-1.20.1.jar (18 MB at 12 MB/s)
[INFO] Invoking compiler:3.13.0:compile (default-compile) @ quia
[INFO] Nothing to compile - all classes are up to date.
[INFO] Invoking resources:3.3.1:testResources (default-testResources) @ quia
[INFO] skip non existing resourceDirectory /home/willem/git/quia/src/test/resources
[INFO] Invoking quarkus:3.15.1:generate-code-tests (default) @ quia
[INFO] Invoking compiler:3.13.0:testCompile (default-testCompile) @ quia
[INFO] Nothing to compile - all classes are up to date.
Listening for transport dt_socket at address: 5005
2024-10-20 20:21:30,033 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Starting to pull image
2024-10-20 20:21:30,100 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pulling image layers:  0 pending,  0 downloaded,  0 extracted, (0 bytes/0 bytes)
2024-10-20 20:21:31,571 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pulling image layers:  3 pending,  1 downloaded,  0 extracted, (9 MB/? MB)
2024-10-20 20:21:31,928 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pulling image layers:  3 pending,  1 downloaded,  1 extracted, (13 MB/? MB)
2024-10-20 20:21:32,066 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pulling image layers:  2 pending,  2 downloaded,  1 extracted, (14 MB/? MB)
2024-10-20 20:21:42,910 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pulling image layers:  1 pending,  3 downloaded,  1 extracted, (165 MB/? MB)
2024-10-20 20:21:46,700 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pulling image layers:  1 pending,  3 downloaded,  2 extracted, (217 MB/? MB)
2024-10-20 20:21:47,103 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pulling image layers:  0 pending,  4 downloaded,  2 extracted, (223 MB/224 MB)
2024-10-20 20:21:49,096 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pulling image layers:  0 pending,  4 downloaded,  3 extracted, (224 MB/224 MB)
2024-10-20 20:21:49,135 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pulling image layers:  0 pending,  4 downloaded,  4 extracted, (224 MB/224 MB)
2024-10-20 20:21:49,163 INFO  [tc.qua.io/.0.6] (docker-java-stream--124753403) Pull complete. 4 layers, pulled in 19s (downloaded 224 MB at 11 MB/s)
2024-10-20 20:21:50,493 INFO  [tc.tes.8.1] (docker-java-stream--247725539) Starting to pull image
2024-10-20 20:21:50,497 INFO  [tc.tes.8.1] (docker-java-stream--247725539) Pulling image layers:  0 pending,  0 downloaded,  0 extracted, (0 bytes/0 bytes)
2024-10-20 20:21:51,326 INFO  [tc.tes.8.1] (docker-java-stream--247725539) Pulling image layers:  2 pending,  1 downloaded,  0 extracted, (350 KB/? MB)
2024-10-20 20:21:51,892 INFO  [tc.tes.8.1] (docker-java-stream--247725539) Pulling image layers:  1 pending,  2 downloaded,  0 extracted, (5 MB/? MB)
2024-10-20 20:21:52,053 INFO  [tc.tes.8.1] (docker-java-stream--247725539) Pulling image layers:  1 pending,  2 downloaded,  1 extracted, (6 MB/? MB)
2024-10-20 20:21:52,064 INFO  [tc.tes.8.1] (docker-java-stream--247725539) Pulling image layers:  0 pending,  3 downloaded,  1 extracted, (6 MB/6 MB)
2024-10-20 20:21:52,183 INFO  [tc.tes.8.1] (docker-java-stream--247725539) Pulling image layers:  0 pending,  3 downloaded,  2 extracted, (6 MB/6 MB)
2024-10-20 20:21:52,266 INFO  [tc.tes.8.1] (docker-java-stream--247725539) Pulling image layers:  0 pending,  3 downloaded,  3 extracted, (6 MB/6 MB)
2024-10-20 20:21:52,302 INFO  [tc.tes.8.1] (docker-java-stream--247725539) Pull complete. 3 layers, pulled in 1s (downloaded 6 MB at 6 MB/s)
2024-10-20 20:22:22,341 INFO  [io.qua.oid.dep.dev.key.KeycloakDevServicesProcessor] (build-42) Dev Services for Keycloak started.
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2024-10-20 20:22:23,868 INFO  [io.quarkus] (Quarkus Main Thread) quia 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.15.1) started in 59.868s. Listening on: http://localhost:8080
2024-10-20 20:22:23,870 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2024-10-20 20:22:23,870 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, oidc, rest, security, smallrye-context-propagation, vertx]

--
Tests paused
Press [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the terminal, [h] for more options>

```

</details>

In the other terminal we can assert that two Docker containers have started:
```bash
willem@willem-Latitude-5590:~/git/quia$ docker ps
CONTAINER ID   IMAGE                              COMMAND                  CREATED              STATUS              PORTS                                                               NAMES
5c05d71f7504   quay.io/keycloak/keycloak:25.0.6   "/opt/keycloak/bin/k…"   About a minute ago   Up About a minute   8443/tcp, 9000/tcp, 0.0.0.0:32769->8080/tcp, [::]:32769->8080/tcp   trusting_brahmagupta
0991bd4553c4   testcontainers/ryuk:0.8.1          "/bin/ryuk"              About a minute ago   Up About a minute   0.0.0.0:32768->8080/tcp, [::]:32768->8080/tcp                       testcontainers-ryuk-e8929262-cd2f-4861-bf34-d5b596e395c4
willem@willem-Latitude-5590:~/git/quia$ 

```

In the first terminal we can see the dev service details by typing `c`
```bash
Press [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the terminal, [h] for more options>c

== Dev Services

keycloak
  Container:        5c05d71f7504/trusting_brahmagupta  quay.io/keycloak/keycloak:25.0.6
  Network:          bridge - 0.0.0.0:32769->8080/tcp ,:::32769->8080/tcp
  Exec command:     docker exec -it 5c05d71f7504 /bin/bash
  Injected config:  - client.quarkus.oidc.auth-server-url=http://localhost:32769/realms/quarkus
                    - keycloak.realms=quarkus
                    - keycloak.url=http://localhost:32769
                    - oidc.users=alice=alice,bob=bob
                    - quarkus.oidc.application-type=service
                    - quarkus.oidc.auth-server-url=http://localhost:32769/realms/quarkus
                    - quarkus.oidc.client-id=quarkus-app
                    - quarkus.oidc.credentials.secret=secret




--
Tests paused
Press [e] to edit command line args (currently ''), [r] to resume testing, [o] Toggle test output, [:] for the terminal, [h] for more options>

```

### To test the [http://localhost:8080/hello/whoami](http://localhost:8080/hello/whoami) endpoint with provided dev default users
- step 1: In Extensions under OpenID Connect click on _Keycloak provider_

  ![](images/get-token-4-bob-01.png)
- step 2: In the next window click on _Log into single page application_

  ![](images/get-token-4-bob-02.png)
- step 3: In the next window fill in the user and password values of the default dev users bob or alice

  ![](images/get-token-4-bob-03.png)
- step 4: Copy the generated access token to the clipboard

  ![](images/get-token-4-bob-04.png)

- step 5: assert that `curl -H "Authorization: Bearer $AUTH_TOKEN" localhost:8080/hello/whoami` returns bob (or alice)
```bash
curl -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJrbGh2MkhJUGJ2Qm9JckxtZU8zYU92X0kwVVJRdUxHZGQzTWVkSHJFVkhJIn0.eyJleHAiOjE3Mjk0NTE1MzQsImlhdCI6MTcyOTQ1MDkzNiwianRpIjoiYzQ1NTBjODAtMmQzNy00ZDRhLTk1YzYtMzUzYjM2MmJiMzY5IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDozMjc3My9yZWFsbXMvcXVhcmt1cyIsInR5cCI6IkJlYXJlciIsImF6cCI6InF1YXJrdXMtYXBwIiwic2lkIjoiYzVlYmYwMzMtNzEzZC00OTMwLWE2OTAtNGFkNzI3NDI0ZTQwIiwic2NvcGUiOiJvcGVuaWQgbWljcm9wcm9maWxlLWp3dCIsInVwbiI6ImJvYiIsImdyb3VwcyI6WyJ1c2VyIl19.W43esRl5Yyir-dzHPK24PVfsmqv3u47rtLIDPOIbFUk2pK0XqcKGm_zXx4WEW_vSGmGxjXeqwBLNApCYqepRFgBJe6EC3o2y59bmLIliITo9GDbbO_V0p_dP6gZCGbYigbrFLmzU2i2SNhI56wo8wg5QJ5x8NN93gmHG4EdqQ-i1VAmUHfRkiTB8n62cbY1M8YzmsXxoUy6DDmmoiqCc97hTaWeWHgZKpgWBaTPe4XTIZnNB4XuQf5i21ZvkfaqA8-d9wVgoHz53WBAU_nLqoLwydfdqVxfEwf0z1adjts-WK0vXmTWriFaQRtS4zBKM49RNdIEzkx8Kei1fI5ZpGw" localhost:8080/hello/whoami
bobwillem@willem-Latitude-5590:~/git/quia$
```

