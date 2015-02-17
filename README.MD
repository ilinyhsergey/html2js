mvn jar:jar
mvn clean install -Dmaven.test.skip=true

java -jar target/html2js-1.0.jar -t target/test-classes/template.js -o result.js target/test-classes/src1.html target/test-classes/src2.html
