# SMTPrankster :: Forged e-mails prank campaign

SMTPrankster is a client application built with Java, developed during the 2018 RES course in HEIG-VD. It allows the user to run prank campaigns that sends forged e-mails to a list of victims.

Your are not allowed to use/copy/modify this project for malicious purpose, such as spam or social engineering.

## Requirement
SMTPrankster is built using Java (JDK 10) and to run it you need a Java runtime environment (tested on JRE 8 & 10). It was developped and tested on Windows 10.

## Installation / setup
You can either clone the repo and build it or use the .jar file.
In both cases, make sure to have a 'config' folder with the required files, more details below.

### Setting up a mock SMTP server (with Docker)
The project, as is, is configured to work with a [MockMock server by Tweakers](https://github.com/tweakers-dev/MockMock) using [Docker](https://www.docker.com/what-docker).

The MockMock jar and Dockerfile are stored in 'SMTPrankster-build/docker-mock-server' folder, to run it go to the folder and run in Docker terminal :
```
docker build -t mockmock-serv .
docker run -p 2525:2525 -p 8282:8282 mockmock-serv
```
Now that the server is up, you can run the Java project.
MockMock provides an http interface so that you can see the result. 
You can access it by simpy browsing to 192.168.99.100:8282, where 192.168.99.100 is the docker ip address.

## configuring and running a prank campaign

There are currently no GUI to customize the SMTPrankster's configuration. It is done with three configuration files located at 'SMTPrankster-build/config/'
Modify these files to configure your own prank campaign.

#### config.properties

The file `config.properties` contains the properties of the application. Modify the value of the following keys to adapt it to your needs.
* **smtpServerAddress**: the address of the SMTP server to send e-mails to.
* **smtpPortNumber**: the port number of the SMTP server.
* **numberOfGroups**: the number of groups victims to form in the campaign.

Here is an example of configuration working with MockMocK on Docker:
```
smtpServerAddress=192.168.99.100
smtpPortNumber=2525
numberOfGroups=3
```

#### victims.utf8

The file `victims.utf8` defines a list of victims. It contains a list of e-mail addresses (one address per line).

Here is an example of victims list:
```
first@victim.com
second@victim.com
third@victim.com
fourth@victim.com
```

#### messages.utf8

The file `messages.utf8` contains a list of e-mail messages. When a prank is played on a group of victims, then one of these messages is selected.  

The messages  
* are separated with "==="
* contain a subject
* contain the text to send to a victim

The syntax of a message is the following:
```
Subject: This is the subject

This is the message I want to send.
===
Subject: Another subject

This is another message!
```

Once you configured these files, you are good to go!

## Implementation
