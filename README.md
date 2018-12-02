# MyBusiness
A Grails 3 plugin for online  e-business including product e-commerce.

## Introduction
The goal of MyBusiness is to create a foundation for a product content management and sales web application in a Grails 3 plugin.

## How-To
More information can be found on the MyBusiness Plugin project [documentation page](http://cbmarcum.github.io/mybusiness/).

## Building
The MyBusiness plugin has a dependency on our updated version of the grails-paypal plugin.  
When working with a MyBusiness Client application the paypal plugin can be part of a gradle multi-project build. However when working on the MyBusiness plugin this dependency needs to be resolved and currently is not in an online repository and needs to be installed in the local maven cache.

Build the plugin and install locally (check version!):
```Shell Console
$ pwd
/home/carl/dev-git/grails-paypal
$ ./gradlew assemble
$ cd build/libs
$ mvn install:install-file \
  -DgroupId=org.grails.plugin \
  -DartifactId=grails-paypal \
  -Dpackaging=jar \
  -Dversion=1.1.1 \
  -Dfile=grails-paypal-1.1.1.jar \
  -DgeneratePom=true
```

## License
This software is licensed under the Apache License 2.0

See the LICENSE, and NOTICE files for more information.

## Acknowledgements
We use stable released Grails 3 plugins when available.  
If a Grails 2 plugin project has not upgraded and released a Grails 3 version 
we incorporate it until that time if they are Apache v2.0 licensed.

This plugin contains original code and the ideas from the following projects:

[MyBusiness 2.3](http://codebuilders.net/project/mybusiness) for Grails `2.0` by Carl Marcum
 
[Taggable Plugin 1.1.0](https://github.com/gpc/taggable) for Grails `1.1` by Graeme Rocher.
 
[Commentable Plugin 0.8.1](https://github.com/gpc/grails-commentable) for Grails `1.1` by Graeme Rocher.

[Feeds Plugin 1.6](https://github.com/gpc/feeds) for Grails `1.3` by Burt Beckwith and Marc Palmer.

[CKeditor Plugin 4.5.9.0](https://github.com/stefanogualdi/grails-ckeditor) for Grails `3.0` by Stefano Gualdi.

## History
v3.1.2.0 - changed version scheme from 1.1.0  
v3.1.3.0 - added Taggable, Commentable, and Feeds  
v3.2.10.0 - updated blog feature   
v3.2.11.0 - updated notice feature with a page field  
v3.2.12.0 - added facebook integration for product detail and blog entries
