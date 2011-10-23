What I will need to put in my future pom.xml (when it will work :p ).
- request factory dependencies.
	requestfactory-server.jar
    javax/validation/validator-api-1.0.0.GA.jar
    A JSR 303 Validator of your choice, such as hibernate-validator 
    (see http://code.google.com/intl/fr/webtoolkit/doc/latest/DevGuideRequestFactory.html)
    
- guice (for server side)
- gin (for client side) (Gwt INjection) (see http://code.google.com/p/google-gin/wiki/GinTutorial)




For now, all sessions are handled manually. Maybe on time it should be done by jetty (or tomcat, depending on what we use).










An error I get as I was trying to use GIN :
18:30:38.120 [ERROR] [s2le] Failed to create an instance of 'fr.piroxxi.s2le.client.S2le' via deferred binding 

java.lang.RuntimeException: Deferred binding failed for 'fr.piroxxi.s2le.client.injector.ClientGinjector' (did you forget to inherit a required module?)
    at com.google.gwt.dev.shell.GWTBridgeImpl.create(GWTBridgeImpl.java:53)
    at com.google.gwt.core.client.GWT.create(GWT.java:97)
    at fr.piroxxi.s2le.client.S2le.&lt;init&gt;(S2le.java:15)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
    at java.lang.reflect.Constructor.newInstance(Constructor.java:532)
    at com.google.gwt.dev.shell.ModuleSpace.rebindAndCreate(ModuleSpace.java:465)
    at com.google.gwt.dev.shell.ModuleSpace.onLoad(ModuleSpace.java:375)
    at com.google.gwt.dev.shell.OophmSessionHandler.loadModule(OophmSessionHandler.java:200)
    at com.google.gwt.dev.shell.BrowserChannelServer.processConnection(BrowserChannelServer.java:525)
    at com.google.gwt.dev.shell.BrowserChannelServer.run(BrowserChannelServer.java:363)
    at java.lang.Thread.run(Thread.java:679)
Caused by: java.lang.NoClassDefFoundError: javax/inject/Provider
    at com.google.inject.internal.MoreTypes.canonicalizeForKey(MoreTypes.java:81)
    at com.google.inject.Key.&lt;init&gt;(Key.java:119)
    at com.google.inject.Key.get(Key.java:212)
    at com.google.inject.spi.Elements$RecordingBinder.bind(Elements.java:262)
    at com.google.inject.internal.InjectorShell$RootModule.configure(InjectorShell.java:276)
    at com.google.inject.spi.Elements$RecordingBinder.install(Elements.java:223)
    at com.google.inject.spi.Elements.getElements(Elements.java:101)
    at com.google.inject.internal.InjectorShell$Builder.build(InjectorShell.java:133)
    at com.google.inject.internal.InternalInjectorCreator.build(InternalInjectorCreator.java:103)
    at com.google.inject.Guice.createInjector(Guice.java:95)
    at com.google.inject.Guice.createInjector(Guice.java:83)
    at com.google.gwt.inject.rebind.GinjectorGenerator.generate(GinjectorGenerator.java:47)
    at com.google.gwt.core.ext.GeneratorExtWrapper.generate(GeneratorExtWrapper.java:48)
    at com.google.gwt.core.ext.GeneratorExtWrapper.generateIncrementally(GeneratorExtWrapper.java:60)
    at com.google.gwt.dev.javac.StandardGeneratorContext.runGeneratorIncrementally(StandardGeneratorContext.java:647)
    at com.google.gwt.dev.cfg.RuleGenerateWith.realize(RuleGenerateWith.java:41)
    at com.google.gwt.dev.shell.StandardRebindOracle$Rebinder.rebind(StandardRebindOracle.java:78)
    at com.google.gwt.dev.shell.StandardRebindOracle.rebind(StandardRebindOracle.java:268)
    at com.google.gwt.dev.shell.ShellModuleSpaceHost.rebind(ShellModuleSpaceHost.java:141)
    at com.google.gwt.dev.shell.ModuleSpace.rebind(ModuleSpace.java:585)
    at com.google.gwt.dev.shell.ModuleSpace.rebindAndCreate(ModuleSpace.java:455)
    at com.google.gwt.dev.shell.GWTBridgeImpl.create(GWTBridgeImpl.java:49)
    at com.google.gwt.core.client.GWT.create(GWT.java:97)
    at fr.piroxxi.s2le.client.S2le.&lt;init&gt;(S2le.java:15)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
    at java.lang.reflect.Constructor.newInstance(Constructor.java:532)
    at com.google.gwt.dev.shell.ModuleSpace.rebindAndCreate(ModuleSpace.java:465)
    at com.google.gwt.dev.shell.ModuleSpace.onLoad(ModuleSpace.java:375)
    at com.google.gwt.dev.shell.OophmSessionHandler.loadModule(OophmSessionHandler.java:200)
    at com.google.gwt.dev.shell.BrowserChannelServer.processConnection(BrowserChannelServer.java:525)
    at com.google.gwt.dev.shell.BrowserChannelServer.run(BrowserChannelServer.java:363)
    at java.lang.Thread.run(Thread.java:679)






















[ERROR] [s2le] - Failed to create an instance of 'fr.piroxxi.s2le.client.S2le' via deferred binding 
19:10:08.735 [ERROR] [s2le] Failed to create an instance of 'fr.piroxxi.s2le.client.S2le' via deferred binding 

java.lang.RuntimeException: Deferred binding failed for 'fr.piroxxi.s2le.client.injector.ClientGinjector' (did you forget to inherit a required module?)
    at com.google.gwt.dev.shell.GWTBridgeImpl.create(GWTBridgeImpl.java:53)
    at com.google.gwt.core.client.GWT.create(GWT.java:97)
    at fr.piroxxi.s2le.client.S2le.&lt;init&gt;(S2le.java:15)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
    at java.lang.reflect.Constructor.newInstance(Constructor.java:532)
    at com.google.gwt.dev.shell.ModuleSpace.rebindAndCreate(ModuleSpace.java:465)
    at com.google.gwt.dev.shell.ModuleSpace.onLoad(ModuleSpace.java:375)
    at com.google.gwt.dev.shell.OophmSessionHandler.loadModule(OophmSessionHandler.java:200)
    at com.google.gwt.dev.shell.BrowserChannelServer.processConnection(BrowserChannelServer.java:525)
    at com.google.gwt.dev.shell.BrowserChannelServer.run(BrowserChannelServer.java:363)
    at java.lang.Thread.run(Thread.java:679)
Caused by: java.lang.NoClassDefFoundError: [Lorg/aopalliance/intercept/MethodInterceptor;
    at java.lang.Class.getDeclaredMethods0(Native Method)
    at java.lang.Class.privateGetDeclaredMethods(Class.java:2444)
    at java.lang.Class.getDeclaredMethods(Class.java:1808)
    at com.google.inject.internal.ProviderMethodsModule.getProviderMethods(ProviderMethodsModule.java:81)
    at com.google.inject.internal.ProviderMethodsModule.configure(ProviderMethodsModule.java:73)
    at com.google.inject.spi.Elements$RecordingBinder.install(Elements.java:223)
    at com.google.inject.spi.Elements$RecordingBinder.install(Elements.java:232)
    at com.google.inject.spi.Elements.getElements(Elements.java:101)
    at com.google.inject.internal.InjectorShell$Builder.build(InjectorShell.java:133)
    at com.google.inject.internal.InternalInjectorCreator.build(InternalInjectorCreator.java:103)
    at com.google.inject.Guice.createInjector(Guice.java:95)
    at com.google.inject.Guice.createInjector(Guice.java:83)
    at com.google.gwt.inject.rebind.GinjectorGenerator.generate(GinjectorGenerator.java:47)
    at com.google.gwt.core.ext.GeneratorExtWrapper.generate(GeneratorExtWrapper.java:48)
    at com.google.gwt.core.ext.GeneratorExtWrapper.generateIncrementally(GeneratorExtWrapper.java:60)
    at com.google.gwt.dev.javac.StandardGeneratorContext.runGeneratorIncrementally(StandardGeneratorContext.java:647)
    at com.google.gwt.dev.cfg.RuleGenerateWith.realize(RuleGenerateWith.java:41)
    at com.google.gwt.dev.shell.StandardRebindOracle$Rebinder.rebind(StandardRebindOracle.java:78)
    at com.google.gwt.dev.shell.StandardRebindOracle.rebind(StandardRebindOracle.java:268)
    at com.google.gwt.dev.shell.ShellModuleSpaceHost.rebind(ShellModuleSpaceHost.java:141)
    at com.google.gwt.dev.shell.ModuleSpace.rebind(ModuleSpace.java:585)
    at com.google.gwt.dev.shell.ModuleSpace.rebindAndCreate(ModuleSpace.java:455)
    at com.google.gwt.dev.shell.GWTBridgeImpl.create(GWTBridgeImpl.java:49)
    at com.google.gwt.core.client.GWT.create(GWT.java:97)
    at fr.piroxxi.s2le.client.S2le.&lt;init&gt;(S2le.java:15)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
    at java.lang.reflect.Constructor.newInstance(Constructor.java:532)
    at com.google.gwt.dev.shell.ModuleSpace.rebindAndCreate(ModuleSpace.java:465)
    at com.google.gwt.dev.shell.ModuleSpace.onLoad(ModuleSpace.java:375)
    at com.google.gwt.dev.shell.OophmSessionHandler.loadModule(OophmSessionHandler.java:200)
    at com.google.gwt.dev.shell.BrowserChannelServer.processConnection(BrowserChannelServer.java:525)
    at com.google.gwt.dev.shell.BrowserChannelServer.run(BrowserChannelServer.java:363)
    at java.lang.Thread.run(Thread.java:679)
Caused by: java.lang.ClassNotFoundException: org.aopalliance.intercept.MethodInterceptor
    at java.net.URLClassLoader$1.run(URLClassLoader.java:217)
    at java.security.AccessController.doPrivileged(Native Method)
    at java.net.URLClassLoader.findClass(URLClassLoader.java:205)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:321)
    at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:294)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:266)
    at java.lang.Class.getDeclaredMethods0(Native Method)
    at java.lang.Class.privateGetDeclaredMethods(Class.java:2444)
    at java.lang.Class.getDeclaredMethods(Class.java:1808)
    at com.google.inject.internal.ProviderMethodsModule.getProviderMethods(ProviderMethodsModule.java:81)
    at com.google.inject.internal.ProviderMethodsModule.configure(ProviderMethodsModule.java:73)
    at com.google.inject.spi.Elements$RecordingBinder.install(Elements.java:223)
    at com.google.inject.spi.Elements$RecordingBinder.install(Elements.java:232)
    at com.google.inject.spi.Elements.getElements(Elements.java:101)
    at com.google.inject.internal.InjectorShell$Builder.build(InjectorShell.java:133)
    at com.google.inject.internal.InternalInjectorCreator.build(InternalInjectorCreator.java:103)
    at com.google.inject.Guice.createInjector(Guice.java:95)
    at com.google.inject.Guice.createInjector(Guice.java:83)
    at com.google.gwt.inject.rebind.GinjectorGenerator.generate(GinjectorGenerator.java:47)
    at com.google.gwt.core.ext.GeneratorExtWrapper.generate(GeneratorExtWrapper.java:48)
    at com.google.gwt.core.ext.GeneratorExtWrapper.generateIncrementally(GeneratorExtWrapper.java:60)
    at com.google.gwt.dev.javac.StandardGeneratorContext.runGeneratorIncrementally(StandardGeneratorContext.java:647)
    at com.google.gwt.dev.cfg.RuleGenerateWith.realize(RuleGenerateWith.java:41)
    at com.google.gwt.dev.shell.StandardRebindOracle$Rebinder.rebind(StandardRebindOracle.java:78)
    at com.google.gwt.dev.shell.StandardRebindOracle.rebind(StandardRebindOracle.java:268)
    at com.google.gwt.dev.shell.ShellModuleSpaceHost.rebind(ShellModuleSpaceHost.java:141)
    at com.google.gwt.dev.shell.ModuleSpace.rebind(ModuleSpace.java:585)
    at com.google.gwt.dev.shell.ModuleSpace.rebindAndCreate(ModuleSpace.java:455)
    at com.google.gwt.dev.shell.GWTBridgeImpl.create(GWTBridgeImpl.java:49)
    at com.google.gwt.core.client.GWT.create(GWT.java:97)
    at fr.piroxxi.s2le.client.S2le.&lt;init&gt;(S2le.java:15)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57)
    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
    at java.lang.reflect.Constructor.newInstance(Constructor.java:532)
    at com.google.gwt.dev.shell.ModuleSpace.rebindAndCreate(ModuleSpace.java:465)
    at com.google.gwt.dev.shell.ModuleSpace.onLoad(ModuleSpace.java:375)
    at com.google.gwt.dev.shell.OophmSessionHandler.loadModule(OophmSessionHandler.java:200)
    at com.google.gwt.dev.shell.BrowserChannelServer.processConnection(BrowserChannelServer.java:525)
    at com.google.gwt.dev.shell.BrowserChannelServer.run(BrowserChannelServer.java:363)
    at java.lang.Thread.run(Thread.java:679)


[ERROR] [s2le] - Unable to load module entry point class fr.piroxxi.s2le.client.S2le (see associated exception for details)
19:10:10.382 [ERROR] [s2le] Unable to load module entry point class fr.piroxxi.s2le.client.S2le (see associated exception for details)




[ERROR] [s2le] - Failed to load module 's2le' from user agent 'Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.202 Safari/535.1' at localhost:49784
19:10:12.276 [ERROR] [s2le] Failed to load module 's2le' from user agent 'Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.202 Safari/535.1' at localhost:49784





