#! /usr/bin/bash
set -e
yum -y install wget
wget http://dev.mysql.com/get/mysql57-community-release-el7-7.noarch.rpm
yum -y localinstall mysql57-community-release-el7-7.noarch.rpm
yum -y install mysql-community-server
