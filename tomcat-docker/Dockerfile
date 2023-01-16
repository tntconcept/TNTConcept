FROM tomcat:9-jdk17-corretto

COPY ../tntconcept-web/target/tntconcept-web-tomcat.war /usr/local/tomcat/webapps/
COPY ../tntconcept-web/src/main/webapp/META-INF/context.xml /usr/local/tomcat/conf/
COPY ../src/main/install/config /etc/tntconcept

RUN sed -i 's/localhost/mysql-TNT/g' /usr/local/tomcat/conf/context.xml
RUN mkdir -p /usr/local/tomcat/logs
RUN chmod 777 /usr/local/tomcat/logs
RUN mkdir -p /var/autentia/tntconcept/reports
RUN chmod 777 /var/autentia/tntconcept
