#!/bin/sh

if ! type "ant" > /dev/null; then
  echo 'You need Ant configured on your path'
fi

if ! type "java" > /dev/null; then
  echo 'You need Java configured on your path'
fi

if ! type "diff" > /dev/null; then
  echo 'You need diff configured on your path'
fi

echo 'Compiling..\n'

ant compile

echo 'Testing..\n'

ant test

echo 'Packaging..\n'

ant package

echo 'Running..\n'

java -jar rich-marscher-java-codetest.jar

echo 'Verifying..\n'

diff -s outputFiles/model_output.txt outputFiles/output.txt

echo '\nDone\n'
