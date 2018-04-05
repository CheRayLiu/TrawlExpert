import subprocess

pathToTomcat = "\"C:\\Users\\Ray Liu\\Desktop\\Uni\\Second year\\2xb3\\2XB3" # REPLACE ME - CUSTOM

pathToSrc = "\\bin\""
pathToFin = "\\tomcat\\webapps\\Trawl\\WEB-INF\\classes\""
#pathClasspath = pathToTomcat + "\\lib\\servlet-api.jar;" + pathToTomcat + "\\webapps\\Trawl\\bin"

# compileCMD1 = "javac -cp " + pathClasspath + " " + pathToTomcat + pathToSrc + "web\\Director.java"
# compileCMD2 = "javac -cp " + pathClasspath + " " + pathToTomcat + pathToSrc + "model\\TrawlExpert.java"

# print(compileCMD1)
# print(compileCMD2)

copyCMD1 = "xcopy " + pathToTomcat + pathToSrc  + " " + pathToTomcat + pathToFin + " " + "/E" 


#print(copyCMD1)


# Command Processes

subprocess.call(copyCMD1, shell=True)

