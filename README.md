# BabbleHub #
============

Current version = 0.2.0

BabbleHub is a simple web chat client built on top of Netty libary, Redis database and WebSockets.
At this moment, everything is a work in progress, including this README.


## Installation instructions ##

 * Install simple build tool;
 * Obtain and install Redis following the Redis installation instructions;
 * Change the server.properties redis.location property to point to your Redis server;
 * To run, simply type sbt run in your command line.

### A few remarks ###
If you run your instance anywhere else but the default port, you will have to make a 
few changes to the source code.
So anywhere where you find code looking like this:
``` java
    Jedis jedis = new Jedis (ServerConfig.getConfig().getRedisLocation());
```
Change it to:
``` java
    Jedis jedis = new Jedis (ServerConfig.getConfig().getRedisLocation(), PORT_NUM);
```
## If you want to you could...##

...help me with javascript

...look for bugs in java soruce

...or just thorw ideas at me. Including silly ones.
## ROADMAP ##

 1. Better logging
   [X] remove sysout calls, except the first one
   [ ] better logger
 2. Show a part of chat history;
 3. Implement authentication;
 4. Stop depending on browser WebSocket implementation and start using Socket.io libary;
 5. Fix the UI (make it pretty);
 6. Plugin system;
 7. Mobile support;

