heroku-mongo-springmvc-angularjs
========================

Quickstart for a Spring MVC RESTful Angular web app backed by MongoHQ, deployable to Heroku.


## Build

    mvn package
    

## Run

Note: Running requires the `MONGOHQ_URL` environment variable to be set.

    
### Run Via Jetty

    mvn clean jetty:run
    

### Run Via Tomcat

    mvn clean tomcat7:run
    

### Run Via Command Line (Java+Jetty)

    java $JAVA_OPTS -jar ./target/dependency/jetty-runner.jar --port $PORT ./target/*.war


### Run Via Eclipse WTP

1. Project::Right-Click::Properties::Project Facts tab
2. Check: Dynamic Web Module (3.0+), Java  (1.6+), JavaScript 
3. Add to web container (Tomcat, VMWare TCServer, etc.) 

## Verify

Use a browser to access:

* http://localhost:8080/index.jsp
* http://localhost:8080/hello
* http://localhost:8080/foo

## Deploy to Heroku

    $ heroku create -s cedar
    Creating quiet-waterfall-6274... done, stack is cedar
    http://quiet-waterfall-6274.herokuapp.com/ | git@heroku.com:quiet-waterfall-6274.git
    Git remote heroku added
    
    $ heroku addons:add mongohq:sandbox
    -----> Adding mongohq to quiet-waterfall-6274... done, v1 (free)
    
    $ heroku addons:add papertrail:choklad
    -----> Adding papertrail to quiet-waterfall-6274... done, v1 (free)
    
    $ git push heroku master
