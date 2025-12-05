# oaelelevator

To execute, build project with maven and execute jar passing in arguments from command line. There is a sample shell script in the /bin folder.

I can't think of any assumptions that I made beyond selection of technology stack. I used Maven with Java 11 and wrote unit tests using Junit 5. I didn't add any dependencies beyond unit testing tools to keep it simple. I leveraged the Stream API's as much as possible to keep code succinct and easier to read. 

Instructions asked for unimplemented features. I implemented the instructions as written.

For example:

java -jar {project root}/target/oaelevator-1.0-SNAPSHOT.jar start=12 floor=2,9,1,32
