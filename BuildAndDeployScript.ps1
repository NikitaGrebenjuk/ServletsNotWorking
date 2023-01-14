# get all java files in the src directory and its subfolders
$java_files = Get-ChildItem -Path src -Filter *.java -Recurse

# create the target/classes directory if it doesn't exist
if (!(Test-Path target/classes)) {New-Item -ItemType Directory -Path target/classes}

#compile the java files with config.properties on classpath 
javac -cp config.properties -d target/classes $java_files.FullName

# build a war file
jar -cvf export/myapp.war -C target/classes/ .

# stopp the docker container if it exists
docker kill tc1

# delete docker contaienr if it exists
docker rm tc1

#build a docker container using the docker file in the same directory
docker build -t tc .

#Start tomcat docker container on the defined port
docker run --name tc1 -p 8888:8080 -d tc sh -c "catalina.sh run"