# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "centos/7"

  config.vm.provider "virtualbox" do |vb|
  	  vb.customize ["modifyvm", :id, "--memory", "2048"]
  	  vb.customize ["modifyvm", :id, "--cpus", "1"]
  end

  config.vm.provision "shell", path: "provision.sh"
  config.vm.network "forwarded_port", guest: 1433, host: 1233
end

