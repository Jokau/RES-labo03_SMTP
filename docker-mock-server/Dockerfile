#Docker server Java

FROM java:8

ADD src /opt/src

WORKDIR /opt/src

ENTRYPOINT ["java","-jar","MockMock.jar", "-p", "2525"]