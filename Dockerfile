FROM tomcat:latest
ADD target/encurtador-1.0.1.war /usr/local/tomcat/webapps/
EXPOSE 8099
CMD ["catalina.sh", "run"]