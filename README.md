## Synopsis

This application navigates a imaginary robotic hoover through a room and cleans patches of dirt. It accepts request in `json` format and returns success or failure reposne.

## Code Example

Application accepts input in the following form:

```javascript
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```

And produces the following output:

```javascript
{
  "coords" : [1, 3],
  "patches" : 1
}
```

## Installation

To install the app, run through the following steps:

* Clone the repository
* Make sure the machine has [Java](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html) and [Maven](https://maven.apache.org/download.cgi) installed.
* From the command line, type `mvn clean install`, this will start the installation process
* Once installation is complete, the `jar` file will be present inside `target` folder. This file is an executable file and can be run via `java -jar <file>.jar` command
* By default, the application starts on 8585 port and has `/hoover` configured as context path. However, these parameters can be changed via application.properties file

Below is the example curl request to test the app once it's up and running:

```
curl -X POST \
  http://localhost:8585/hoover/move \
  -H 'content-type: application/json' \
  -d '{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}'
```

## Tests

This application has built in tests. While building the app with `mvn clean install` command, these tests are exectuted and result is displayed in the console.
