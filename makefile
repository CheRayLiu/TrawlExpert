server:
	cp -rf bin/* tomcat/webapps/Trawl/WEB-INF/classes

start: server
	tomcat/bin/startup.sh

stop:
	tomcat/bin/shutdown.sh