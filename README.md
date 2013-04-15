# BabbleHub #
============

Current version = 0.5.0

BabbleHub is a simple web chat client built on top of [Netty](http://netty.io) library, [Redis database](http://redis.io/) and WebSockets.
Connecting to Redis is done using the awesome [Jedis](https://github.com/xetorthio/jedis) library.
At this moment, everything is a work in progress, including this README.


## Installation instructions ##

 * Install simple build tool;
 * Obtain and install Redis following the Redis installation instructions;
 * Change the server.properties redis.location property to point to your Redis server;
 * To run, simply type ```sbt run``` in your command line.
 * Point your browser to [localhost:8080](http://localhost:8080/)

### A few remarks ###
If you run your Redis instance anywhere else but the default port, you will have to make a 
few changes to the source code.
So anywhere where you find code looking like this:
``` java
    Jedis jedis = new Jedis (ServerConfig.getConfig().getRedisLocation());
```
Change it to:
``` java
    Jedis jedis = new Jedis (ServerConfig.getConfig().getRedisLocation(), PORT_NUM);
```
where ```PORT_NUM``` is an integer indicating port.

More on using jedis can be seen in the [jedis wiki](https://github.com/xetorthio/jedis/wiki)
## If you want to you could...##

...help me with javascript

...look for bugs in java soruce

...or just thorw ideas at me. Including silly ones.
## Further work ##

 * Implement authentication;
 * Fix the UI (make it pretty);
 * Plugin system;
 * Mobile support;

## ROADMAP ##
 -
