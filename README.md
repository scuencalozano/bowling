 To execute:

 What you’ll need:
  - Maven Apache Maven 3 
  - JDK 6 or later
 
 Run de JAR:
  - mvn compile
  - java -jar target/bowling-0.1.0.jar ~/players.txt

 Run tests:
  - mvn test

 Validations:
  - lines: The accepted pattern: "name<space>score" including "<whitespace>name<whitespace>score<whitespace>"
  - information: the app will not calculate the scores if the file does not contain the correct information to fill all frames
 
 Example of correct file:
 Jeff 10
 Jeff 7
 Jeff 3
 Jeff 9
 Jeff 0
 Jeff 10
 Jeff 0
 Jeff 8
 Jeff 8
 Jeff 2
 Jeff F
 Jeff 6
 Jeff 10
 Jeff 10
 Jeff 10
 Jeff 8
 Jeff 1
