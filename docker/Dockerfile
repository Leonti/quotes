FROM leonti/tomcat
MAINTAINER Leonti Bielski "leonti.bielski@gmail.com"

EXPOSE 8080
# also expose debugging port
EXPOSE 8000

ADD configuration.properties /usr/share/tomcat7/.quotes/configuration.properties
ADD quotes.war /var/lib/tomcat7/webapps/ROOT.war
RUN rm -rf /var/lib/tomcat7/webapps/ROOT

CMD service tomcat7 start && tail -f /var/lib/tomcat7/logs/catalina.out
