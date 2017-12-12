
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

## Final touches

Please open the project inside the folder :  /020Assignment/ .

Inside this folder there is also a .jar file. You need this file and please include that into the project as an library 

in Netbeans this is done like: 
* right click Libraries in your project
* Add Library
* Create...
* Name the library : PAHO
* Now add the jar to this library and press OK.
* Don't forget to actually add this library to the project.

## Assignment

The assignment is to implement a MQTT ChatBoxClient. there are TODO's throughout the project where functionality is missing. Please RTFM (a.k.a. documentation) for more instructions. Good luck and have fun.

# Acceptence criteria
The client should be able to... :
* connect to a broker
* disconnect from a broker
* subscribe to a topic
* publish to a topic
* transmit messages like described in the documentation.
