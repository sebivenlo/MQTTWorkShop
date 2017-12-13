
# MQTT ChatBox - Getting started.

This workshop will teach you how to use MQTT in Java.

## Setting up

First clone the GitHub repository.

```
git clone https://github.com/sebivenlo/MQTTWorkShop.git
```
Or with ssh...

```
git clone git@github.com:sebivenlo/MQTTWorkShop.git
```

And open the project in your favourite IDE ( Netbeans project file is included )

## Assignment

The assignment is to implement a MQTT ChatBoxClient. there are TODO's throughout the project where functionality is missing. Please RTFM (a.k.a. documentation) for more instructions. Good luck and have fun.

The client has to be connected to an IP adress, we will provide this during the workshop.

# Acceptence criteria
The client should be able to... :
* connect to a broker
* disconnect from a broker
* subscribe to a topic
* publish to a topic
* transmit messages like described in the documentation.

# Broker

The mqtt broker that is used here is mosquitto, which you can download here ; http://mosquitto.org/download/ . One of the big benefits is that mosquitto is available for a lot of platforms so if you want to setup a broker for yourself, please try to do so. 
