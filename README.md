# Room ID parser

Room-ID-Parser is sample project which parses `Strings` to determine if a room is valid.

## Run Room-ID-parser

Package and run the jar with maven (clean to remove all old data in target-dir):

`mvn clean package exec:java`

This will boot up the spring boot-app and will make the rest interface accessible

## Use Room-ID-Parser

You can access [http://localhost:8080/](http://localhost:8080/) and upload the file *roomids.txt*.
 
The file is located in `src/test/resources/`.

## Routes in in the Room-ID-Parser

* `/` index page
  * Shows index.html from resources directory (`src/main/resources/templates`)
* `/parser/rooms` 
  * parses the *roomids.txt* file with a POST
* `/parser/result` 
  * Shows results.html from resources directory (`src/main/resources/templates`)








