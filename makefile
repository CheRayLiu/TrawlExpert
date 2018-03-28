server:
	mkdir -p tomcat/webapps/Trawl/WEB-INF/classes
	chmod u+x tomcat/bin/startup.sh
	chmod u+x tomcat/bin/shutdown.sh
	chmod u+x tomcat/bin/catalina.sh
	cp -rf bin/* tomcat/webapps/Trawl/WEB-INF/classes

start: server
	tomcat/bin/startup.sh

stop:
	tomcat/bin/shutdown.sh