/********************************/

Java Code Test
Richard Marscher
1/21/2012

/*********************************/

Included in this ZIP file is the solution to the Java Code Test
as written by Richard Marscher.

The task is to write code that can handle a set of unsorted text files
each conforming to a different format and to aggregate the data and 
output it in various sorted orders. The outputFiles/model_output.txt
file was given as the expected output. The program tries to generate
a file outputFiles/output.txt that is identical to the model output.

The code was written using Java 1.6.

The build has been configured using Ant as well as Maven 2, the build.xml and pom.xml files are included to build and test the project.

Instructions to build, test and run:

ANT

1. Run the codeTestAnt.sh script 

or

#Execute the JUnit test classes
1. ant test

#Package the test into a jar
2. ant package

3. java -jar rich-marscher-java-codetest.jar

4. Output should be created in a new file in ./outputFiles/output.txt

5. diff -s outputFiles/model_output.txt outputFiles/output.txt

MAVEN

1. Build the project (implicitly runs test)
mvn package

2. Execute the jar file from the root folder
java -jar target/CodeTest-1.0.jar
 
3. Assuming no errors, output should be created in a new file
./outputFiles/output.txt

5. diff -s outputFiles/model_output.txt outputFiles/output.txt
