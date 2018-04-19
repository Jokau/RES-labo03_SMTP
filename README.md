# SMTPrankster :: Forged emails prank campaign

SMTPrankster is a client application built on Java, developped during the 2018 RES course in HEIG-VD. It allows you to run prank campaign that sends forged emails to a list of victim.

## Requirement
SMTPrankster is built using Java and to function you need a Java 8 runtime environment. It was developped and tested on Windows 10.

## Installation / setup
set up

### Setting up a mock SMTP server (with Docker)

use mockmock

## configuring and running a prank campaign

SMTPrankster's configuration is done with configuration files. Modify these files to configure your own prank campaign.

#### config.properties

The file `config.properties` contains the properties of the application. Modify the value of the following keys to adapt it to your needs.
* **smtpServerAddress**: the address of the SMTP server to send emails to.
* **smtpPortNumber**: the port number of the SMTP server.
* **numberOfGroups**: the number of groups victims to form in the campaign.

Here is an example of configuration working with MockMocK on Docker:
```
smtpServerAddress=192.168.99.100
smtpPortNumber=2525
numberOfGroups=3
```

#### victims.utf8

The file `victims.utf8` defines a list of victims. It contains a list e-mail addresses (one address per line).

Here is an example of victims list:
```
first@victim.com
second@victim.com
third@victim.com
fourth@victim.com
```

#### messages.utf8
