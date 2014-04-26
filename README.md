# BabbleHub #

Current version = 0.8

BabbleHub is a simple web chat client built on top of [Netty](http://netty.io) library, [Redis database](http://redis.io/) and WebSockets.
Connecting to Redis is done using the awesome [Jedis](https://github.com/xetorthio/jedis) library.
At this moment, everything is a work in progress, including this README.


## Installation instructions ##

 * Install [gradle](http://www.gradle.org/);
 * Install [Virtualbox](https://www.virtualbox.org/);
 * install [Vagrant](http://www.vagrantup.com/);
 * run ```vagrant up```;
 * Change the server.properties;
 * To run, simply type ```gradle run``` in your command line.
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

## Further work ##

 * ~~Add deployment by Vagrant~~
 * Add picture support;
 * Add file upload support;
 * Authentication;
 * Fix the UI;
 * Plugin system;
 * Mobile support;

## ROADMAP ##
 - (this space is reserved when first stable version is hit)
