# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  # All Vagrant configuration is done here. The most common configuration
  # options are documented and commented below. For a complete reference,
  # please see the online documentation at vagrantup.com.

  # Every Vagrant virtual environment requires a box to build off of.
  config.vm.box = "puppetlabs-precise64"

  # The url from where the 'config.vm.box' box will be fetched if it
  # doesn't already exist on the user's system.
  config.vm.box_url = "http://puppet-vagrant-boxes.puppetlabs.com/ubuntu-server-12042-x64-vbox4210.box"
  config.vm.hostname = "devel.base.vm"
  config.vm.network :private_network, ip: "192.168.0.5"
  config.vm.network :forwarded_port, guest: 80, host:8080

  config.vm.provider :virtualbox do |vb|
  end

  config.vm.provision :shell, :path => "vagrant/bootstrap.sh"

  config.vm.provision :puppet do |puppet|
    puppet.manifests_path  = "vagrant/manifests"
    puppet.manifest_file = "site.pp"
    puppet.module_path = "vagrant/modules"
    puppet.options = "--verbose --debug"
  end

  # vb.costumize["modifyvm", :id, "--memory", "1536"]
  

end
