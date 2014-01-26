#!/usr/bin/env bash

cd /tmp
wget http://download.redis.io/redis-stable.tar.gz
tar xvzf redis-stable.tar.gz
cd redis-stable
make
sudo make install


sudo mkdir /etc/redis
sudo mkdir -p /var/redis/6379


sudo useradd --system --home-dir /var/redis redis
sudo chown -R redis.redis /var/redis

sudo cp /vagrant/vagrant/redis.conf /etc/redis/6379.conf
sudo cp /vagrant/vagrant/redis.init.d /etc/init.d/redis_6379

sudo update-rc.d redis_6379 defaults
/etc/init.d/redis_6379 start

puppet module install puppetlabs/postgresql
