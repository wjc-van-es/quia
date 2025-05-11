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

# Upgrade from 3.17.6 to 3.18.3 leads to test failures in reservation-service
When trying a `mvn clean package -e -X` we found 

<details>

```bash
[DEBUG] [io.quarkus.bootstrap.classloading.QuarkusClassLoader.lifecycle] (main) Creating class loader QuarkusClassLoader:Augmentation Class Loader: PROD for reservation-service-1.0.0@7200702
java.lang.RuntimeException: Created to log a stacktrace
    at io.quarkus.bootstrap.classloading.QuarkusClassLoader.<init> (QuarkusClassLoader.java:200)
    at io.quarkus.bootstrap.classloading.QuarkusClassLoader$Builder.build (QuarkusClassLoader.java:957)
    at io.quarkus.bootstrap.app.CuratedApplication.getOrCreateAugmentClassLoader (CuratedApplication.java:226)
    at io.quarkus.bootstrap.app.CuratedApplication.createDeploymentClassLoader (CuratedApplication.java:338)
    at io.quarkus.maven.GenerateCodeMojo.generateCode (GenerateCodeMojo.java:84)
    at io.quarkus.maven.GenerateCodeMojo.doExecute (GenerateCodeMojo.java:59)
    at io.quarkus.maven.QuarkusBootstrapMojo.execute (QuarkusBootstrapMojo.java:171)
    at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:126)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute2 (MojoExecutor.java:328)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute (MojoExecutor.java:316)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:212)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:174)
    at org.apache.maven.lifecycle.internal.MojoExecutor.access$000 (MojoExecutor.java:75)
    at org.apache.maven.lifecycle.internal.MojoExecutor$1.run (MojoExecutor.java:162)
    at org.apache.maven.plugin.DefaultMojosExecutionStrategy.execute (DefaultMojosExecutionStrategy.java:39)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:159)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:105)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:73)
    at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:53)
    at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:118)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:261)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:173)
    at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:101)
    at org.apache.maven.cli.MavenCli.execute (MavenCli.java:906)
    at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:283)
    at org.apache.maven.cli.MavenCli.main (MavenCli.java:206)
    at jdk.internal.reflect.DirectMethodHandleAccessor.invoke (DirectMethodHandleAccessor.java:103)
    at java.lang.reflect.Method.invoke (Method.java:580)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:255)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:201)
    at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:361)
    at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:314)
    
    [DEBUG] [io.quarkus.bootstrap.classloading.QuarkusClassLoader.lifecycle] (main) Creating class loader QuarkusClassLoader:Augmentation Class Loader: PROD for reservation-service-1.0.0@2d607072
java.lang.RuntimeException: Created to log a stacktrace
    at io.quarkus.bootstrap.classloading.QuarkusClassLoader.<init> (QuarkusClassLoader.java:200)
    at io.quarkus.bootstrap.classloading.QuarkusClassLoader$Builder.build (QuarkusClassLoader.java:957)
    at io.quarkus.bootstrap.app.CuratedApplication.getOrCreateAugmentClassLoader (CuratedApplication.java:226)
    at io.quarkus.bootstrap.app.CuratedApplication.createDeploymentClassLoader (CuratedApplication.java:338)
    at io.quarkus.maven.GenerateCodeMojo.generateCode (GenerateCodeMojo.java:84)
    at io.quarkus.maven.GenerateCodeTestsMojo.doExecute (GenerateCodeTestsMojo.java:41)
    at io.quarkus.maven.QuarkusBootstrapMojo.execute (QuarkusBootstrapMojo.java:171)
    at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:126)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute2 (MojoExecutor.java:328)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute (MojoExecutor.java:316)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:212)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:174)
    at org.apache.maven.lifecycle.internal.MojoExecutor.access$000 (MojoExecutor.java:75)
    at org.apache.maven.lifecycle.internal.MojoExecutor$1.run (MojoExecutor.java:162)
    at org.apache.maven.plugin.DefaultMojosExecutionStrategy.execute (DefaultMojosExecutionStrategy.java:39)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:159)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:105)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:73)
    at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:53)
    at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:118)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:261)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:173)
    at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:101)
    at org.apache.maven.cli.MavenCli.execute (MavenCli.java:906)
    at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:283)
    at org.apache.maven.cli.MavenCli.main (MavenCli.java:206)
    at jdk.internal.reflect.DirectMethodHandleAccessor.invoke (DirectMethodHandleAccessor.java:103)
    at java.lang.reflect.Method.invoke (Method.java:580)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:255)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:201)
    at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:361)
    at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:314)
[DEBUG] [io.quarkus.bootstrap.classloading.QuarkusClassLoader] (main) Adding normal priority element io.quarkus.bootstrap.classloading.PathTreeClassPathElement[/home/willem/git/quia/services/reservation-service/target/classes runtime=true resources=null] to QuarkusClassLoader Deployment Class Loader: PROD for reservation-service-1.0.0
[DEBUG] [io.quarkus.bootstrap.classloading.QuarkusClassLoader.lifecycle] (main) Creating class loader QuarkusClassLoader:Deployment Class Loader: PROD for reservation-service-1.0.0@6a907765
java.lang.RuntimeException: Created to log a stacktrace
    at io.quarkus.bootstrap.classloading.QuarkusClassLoader.<init> (QuarkusClassLoader.java:200)
    at io.quarkus.bootstrap.classloading.QuarkusClassLoader$Builder.build (QuarkusClassLoader.java:957)
    at io.quarkus.bootstrap.app.CuratedApplication.createDeploymentClassLoader (CuratedApplication.java:366)
    at io.quarkus.maven.GenerateCodeMojo.generateCode (GenerateCodeMojo.java:84)
    at io.quarkus.maven.GenerateCodeTestsMojo.doExecute (GenerateCodeTestsMojo.java:41)
    at io.quarkus.maven.QuarkusBootstrapMojo.execute (QuarkusBootstrapMojo.java:171)
    at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:126)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute2 (MojoExecutor.java:328)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute (MojoExecutor.java:316)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:212)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:174)
    at org.apache.maven.lifecycle.internal.MojoExecutor.access$000 (MojoExecutor.java:75)
    at org.apache.maven.lifecycle.internal.MojoExecutor$1.run (MojoExecutor.java:162)
    at org.apache.maven.plugin.DefaultMojosExecutionStrategy.execute (DefaultMojosExecutionStrategy.java:39)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:159)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:105)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:73)
    at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:53)
    at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:118)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:261)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:173)
    at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:101)
    at org.apache.maven.cli.MavenCli.execute (MavenCli.java:906)
    at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:283)
    at org.apache.maven.cli.MavenCli.main (MavenCli.java:206)
    at jdk.internal.reflect.DirectMethodHandleAccessor.invoke (DirectMethodHandleAccessor.java:103)
    at java.lang.reflect.Method.invoke (Method.java:580)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:255)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:201)
    at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:361)
    at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:314)
    
    
...
[DEBUG] [io.quarkus.bootstrap.classloading.QuarkusClassLoader.lifecycle] (main) Closing class loader QuarkusClassLoader:Deployment Class Loader: PROD for reservation-service-1.0.0@6a907765
java.lang.RuntimeException: Created to log a stacktrace
    at io.quarkus.bootstrap.classloading.QuarkusClassLoader.close (QuarkusClassLoader.java:735)
    at io.quarkus.maven.GenerateCodeMojo.generateCode (GenerateCodeMojo.java:102)
    at io.quarkus.maven.GenerateCodeTestsMojo.doExecute (GenerateCodeTestsMojo.java:41)
    at io.quarkus.maven.QuarkusBootstrapMojo.execute (QuarkusBootstrapMojo.java:171)
    at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:126)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute2 (MojoExecutor.java:328)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute (MojoExecutor.java:316)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:212)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:174)
    at org.apache.maven.lifecycle.internal.MojoExecutor.access$000 (MojoExecutor.java:75)
    at org.apache.maven.lifecycle.internal.MojoExecutor$1.run (MojoExecutor.java:162)
    at org.apache.maven.plugin.DefaultMojosExecutionStrategy.execute (DefaultMojosExecutionStrategy.java:39)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:159)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:105)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:73)
    at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:53)
    at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:118)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:261)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:173)
    at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:101)
    at org.apache.maven.cli.MavenCli.execute (MavenCli.java:906)
    at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:283)
    at org.apache.maven.cli.MavenCli.main (MavenCli.java:206)
    at jdk.internal.reflect.DirectMethodHandleAccessor.invoke (DirectMethodHandleAccessor.java:103)
    at java.lang.reflect.Method.invoke (Method.java:580)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:255)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:201)
    at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:361)
    at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:314)
[DEBUG] [io.quarkus.bootstrap.classloading.QuarkusClassLoader.lifecycle] (main) Closing class loader QuarkusClassLoader:Augmentation Class Loader: PROD for reservation-service-1.0.0@2d607072
java.lang.RuntimeException: Created to log a stacktrace
    at io.quarkus.bootstrap.classloading.QuarkusClassLoader.close (QuarkusClassLoader.java:735)
    at io.quarkus.bootstrap.app.CuratedApplication.close (CuratedApplication.java:433)
    at io.quarkus.maven.QuarkusBootstrapProvider$QuarkusMavenAppBootstrap.closeApplication (QuarkusBootstrapProvider.java:409)
    at io.quarkus.maven.QuarkusBootstrapProvider.closeApplication (QuarkusBootstrapProvider.java:143)
    at io.quarkus.maven.QuarkusBootstrapMojo.closeApplication (QuarkusBootstrapMojo.java:300)
    at io.quarkus.maven.GenerateCodeMojo.generateCode (GenerateCodeMojo.java:109)
    at io.quarkus.maven.GenerateCodeTestsMojo.doExecute (GenerateCodeTestsMojo.java:41)
    at io.quarkus.maven.QuarkusBootstrapMojo.execute (QuarkusBootstrapMojo.java:171)
    at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:126)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute2 (MojoExecutor.java:328)
    at org.apache.maven.lifecycle.internal.MojoExecutor.doExecute (MojoExecutor.java:316)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:212)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:174)
    at org.apache.maven.lifecycle.internal.MojoExecutor.access$000 (MojoExecutor.java:75)
    at org.apache.maven.lifecycle.internal.MojoExecutor$1.run (MojoExecutor.java:162)
    at org.apache.maven.plugin.DefaultMojosExecutionStrategy.execute (DefaultMojosExecutionStrategy.java:39)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:159)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:105)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:73)
    at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:53)
    at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:118)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:261)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:173)
    at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:101)
    at org.apache.maven.cli.MavenCli.execute (MavenCli.java:906)
    at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:283)
    at org.apache.maven.cli.MavenCli.main (MavenCli.java:206)
    at jdk.internal.reflect.DirectMethodHandleAccessor.invoke (DirectMethodHandleAccessor.java:103)
    at java.lang.reflect.Method.invoke (Method.java:580)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:255)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:201)
    at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:361)
    at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:314)
...
2025-05-03 14:19:03,552 INFO  [io.qua.sma.rea.rab.dep.RabbitMQDevServicesProcessor] (build-16) Other Quarkus applications in dev mode will find the broker automatically. For Quarkus applications in production mode, you can connect to this by starting your application with -Drabbitmq-host=localhost -Drabbitmq-port=32821 -Drabbitmq-username=guest -Drabbitmq-password=guest
2025-05-03 14:19:14,881 INFO  [tc.qua.io/.0.7] (build-2) Container quay.io/keycloak/keycloak:26.0.7 started in PT23.9246271S
2025-05-03 14:19:17,060 INFO  [io.qua.dev.key.KeycloakDevServicesProcessor] (build-2) Dev Services for Keycloak started.
2025-05-03 14:19:19,167 WARN  [io.qua.hib.orm.run.ser.QuarkusRuntimeInitDialectFactory] (JPA Startup Thread) Persistence unit default-reactive: Could not retrieve the database version to check it is at least 12.0.0
2025-05-03 14:19:19,850 WARNING [io.qua.ope.run.exp.otl.sen.VertxGrpcSender] (vert.x-eventloop-thread-4) Failed to export TraceRequestMarshalers. The request could not be executed. Full error message: Connection refused: localhost/127.0.0.1:4317
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 30.83 s <<< FAILURE! -- in nl.vea.reservation.reservation.ReservationPersistenceTest
[ERROR] nl.vea.reservation.reservation.ReservationPersistenceTest.testCreateReservation(TransactionalUniAsserter) -- Time elapsed: 0.004 s <<< ERROR!
java.lang.RuntimeException: java.lang.RuntimeException: Failed to start quarkus
        at io.quarkus.test.junit.QuarkusTestExtension.throwBootFailureException(QuarkusTestExtension.java:611)
        at io.quarkus.test.junit.QuarkusTestExtension.interceptTestClassConstructor(QuarkusTestExtension.java:695)
        at java.base/java.util.Optional.orElseGet(Optional.java:364)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
Caused by: java.lang.RuntimeException: Failed to start quarkus
        at io.quarkus.runner.ApplicationImpl.doStart(Unknown Source)
        at io.quarkus.runtime.Application.start(Application.java:101)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at io.quarkus.runner.bootstrap.StartupActionImpl.run(StartupActionImpl.java:305)
        at io.quarkus.test.junit.QuarkusTestExtension.doJavaStart(QuarkusTestExtension.java:224)
        at io.quarkus.test.junit.QuarkusTestExtension.ensureStarted(QuarkusTestExtension.java:578)
        at io.quarkus.test.junit.QuarkusTestExtension.beforeAll(QuarkusTestExtension.java:628)
        ... 1 more
Caused by: jakarta.enterprise.inject.spi.DeploymentException: java.lang.IllegalArgumentException: SRMSG00071: Invalid channel configuration -  the `connector` attribute must be set for channel `invoices-rabbitmq`
        at io.quarkus.smallrye.reactivemessaging.runtime.SmallRyeReactiveMessagingLifecycle.onApplicationStart(SmallRyeReactiveMessagingLifecycle.java:58)
        at io.quarkus.smallrye.reactivemessaging.runtime.SmallRyeReactiveMessagingLifecycle_Observer_onApplicationStart_qTrMuLFyQ1IvGfeSxRVitl6CCBQ.notify(Unknown Source)
        at io.quarkus.arc.impl.EventImpl$Notifier.notifyObservers(EventImpl.java:351)
        at io.quarkus.arc.impl.EventImpl$Notifier.notify(EventImpl.java:333)
        at io.quarkus.arc.impl.EventImpl.fire(EventImpl.java:80)
        at io.quarkus.arc.runtime.ArcRecorder.fireLifecycleEvent(ArcRecorder.java:163)
        at io.quarkus.arc.runtime.ArcRecorder.handleLifecycleEvents(ArcRecorder.java:114)
        at io.quarkus.runner.recorded.LifecycleEventsBuildStep$startupEvent1144526294.deploy_0(Unknown Source)
        at io.quarkus.runner.recorded.LifecycleEventsBuildStep$startupEvent1144526294.deploy(Unknown Source)
        ... 8 more
Caused by: java.lang.IllegalArgumentException: SRMSG00071: Invalid channel configuration -  the `connector` attribute must be set for channel `invoices-rabbitmq`
        at io.smallrye.reactive.messaging.providers.impl.ConnectorConfig.lambda$getConnectorAttribute$0(ConnectorConfig.java:65)
        at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        at io.smallrye.reactive.messaging.providers.impl.ConnectorConfig.lambda$getConnectorAttribute$1(ConnectorConfig.java:65)
        at java.base/java.util.Optional.orElseGet(Optional.java:364)
        at io.smallrye.reactive.messaging.providers.impl.ConnectorConfig.getConnectorAttribute(ConnectorConfig.java:64)
        at io.smallrye.reactive.messaging.providers.impl.ConnectorConfig.<init>(ConnectorConfig.java:59)
        at io.smallrye.reactive.messaging.providers.impl.ConfiguredChannelFactory.lambda$extractConfigurationFor$0(ConfiguredChannelFactory.java:94)
        at java.base/java.lang.Iterable.forEach(Iterable.java:75)
        at io.smallrye.reactive.messaging.providers.impl.ConfiguredChannelFactory.extractConfigurationFor(ConfiguredChannelFactory.java:81)
        at io.smallrye.reactive.messaging.providers.impl.ConfiguredChannelFactory.initialize(ConfiguredChannelFactory.java:110)
        at io.smallrye.reactive.messaging.providers.impl.ConfiguredChannelFactory_ClientProxy.initialize(Unknown Source)
        at java.base/java.util.Iterator.forEachRemaining(Iterator.java:133)
        at java.base/java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1939)
        at java.base/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:762)
        at io.smallrye.reactive.messaging.providers.extension.MediatorManager.start(MediatorManager.java:250)
        at io.smallrye.reactive.messaging.providers.extension.MediatorManager_ClientProxy.start(Unknown Source)
        at io.quarkus.smallrye.reactivemessaging.runtime.SmallRyeReactiveMessagingLifecycle.onApplicationStart(SmallRyeReactiveMessagingLifecycle.java:53)
        ... 16 more

```

</details>

It appears that dev mode and production mode classloaders are combined
When running the tests in the service from the IDE:
```bash

```
This probably leads to later inconsistencies:
```bash
java.lang.RuntimeException: java.lang.ClassCastException: class io.quarkus.builder.BuildChainBuilder cannot be cast to class io.quarkus.builder.BuildChainBuilder (io.quarkus.builder.BuildChainBuilder is in unnamed module of loader io.quarkus.bootstrap.classloading.QuarkusClassLoader @41831611; io.quarkus.builder.BuildChainBuilder is in unnamed module of loader 'app')

	at io.quarkus.test.junit.QuarkusTestExtension.throwBootFailureException(QuarkusTestExtension.java:628)
	at io.quarkus.test.junit.QuarkusTestExtension.interceptTestClassConstructor(QuarkusTestExtension.java:712)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
Caused by: java.lang.ClassCastException: class io.quarkus.builder.BuildChainBuilder cannot be cast to class io.quarkus.builder.BuildChainBuilder (io.quarkus.builder.BuildChainBuilder is in unnamed module of loader io.quarkus.bootstrap.classloading.QuarkusClassLoader @41831611; io.quarkus.builder.BuildChainBuilder is in unnamed module of loader 'app')
	at io.quarkus.test.junit.QuarkusTestExtension$TestBuildChainFunction$1.accept(QuarkusTestExtension.java:1182)
	at io.quarkus.deployment.QuarkusAugmentor.run(QuarkusAugmentor.java:131)
	at io.quarkus.runner.bootstrap.AugmentActionImpl.runAugment(AugmentActionImpl.java:350)
	at io.quarkus.runner.bootstrap.AugmentActionImpl.createInitialRuntimeApplication(AugmentActionImpl.java:271)
	at io.quarkus.runner.bootstrap.AugmentActionImpl.createInitialRuntimeApplication(AugmentActionImpl.java:61)
	at io.quarkus.test.junit.QuarkusTestExtension.doJavaStart(QuarkusTestExtension.java:212)
	at io.quarkus.test.junit.QuarkusTestExtension.ensureStarted(QuarkusTestExtension.java:595)
	at io.quarkus.test.junit.QuarkusTestExtension.beforeAll(QuarkusTestExtension.java:645)
	... 1 more


```

## Failed tests
* nl.vea.reservation.billing.ReservationInvoiceProducerTest
* nl.vea.reservation.reservation.ReservationPersistenceTest

## Suggestion & current status
- [https://www.perplexity.ai/search/quarkus-3-18-3-upgrade-leads-t-dUwbdvpnThWB4IKWL574gA](https://www.perplexity.ai/search/quarkus-3-18-3-upgrade-leads-t-dUwbdvpnThWB4IKWL574gA)
- Upgrade to 3.22 still gave problems with `mvn clean package -e -X`
- Selecting 3.17.8 as highest 3.17.x version gave good result running tests and maven builds, so we hold out on this 
  version currently (03-05-2025)
- [https://mvnrepository.com/artifact/io.quarkus.platform/quarkus-bom](https://mvnrepository.com/artifact/io.quarkus.platform/quarkus-bom)
