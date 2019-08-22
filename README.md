# Jive Content Exporter

> This is for now a work-in-progress!

## Prerequisites

Set your credentials in `credentials.properties` as follow:

```properties
userId=your-user@domain.tld
password=your-password
```

## Run

The project is based on Java 11, Maven 3.6 and [NetBeans 11](https://netbeans.apache.org/download/nb110/).

The main class is `io.github.vitkin.jive.content.exporter.Exporter`.

Logs can be configured by editing `src/main/resources/log4j2.xml` or by following instructions in [Log4J 2 manual](https://logging.apache.org/log4j/2.x/manual/configuration.html).

The group name, export path (which default to "export") and proxy settings can be set in `config.properties` as follow:

```properties
group=my-group
export.path=export
proxy.host=my-proxy.domain.tld
proxy.port=3128
```

### Remarks

* [Unirest-Java](http://kong.github.io/unirest-java/) is used for handling the REST calls.

* [Jsoup](https://jsoup.org/) is used for HTML parsing.

* [Gson](https://github.com/google/gson/) is used for JSON parsing.

* [jsonschema2pojo](http://www.jsonschema2pojo.org/) is the tool used to quickly generate all the POJOs required for the Object mapping with Gson.

### Usage

#### Argument (overriding the one in properties file):

The name of the group to export.

##### Argument examples:

* `my-group`

#### Optional system properties (overriding the ones in the properties files):

* `-Djive-content-exporter.config.path` (Default: `etc/jive-content-exporter/config.properties`)
* `-Djive-content-exporter.credentials.path` (Default: `etc/jive-content-exporter/credentials.properties`)
* `-Djive-content-exporter.export.path` (Default: `var/jive-content-exporter`)
* `-Djive-content-exporter.userId`
* `-Djive-content-exporter.password`
* `-Djive-content-exporter.proxy.host`
* `-Djive-content-exporter.proxy.port`

### Examples

#### Development

```bash
mvn exec:java@run-main
```

Or:

```bash
mvn -e exec:exec \
  -Dexec.executable="java" \
  -Dexec.args="\
    -Dorg.apache.logging.log4j.simplelog.StatusLogger.level=TRACE \
    -Dlog4j.configurationFile=etc/jive-content-exporter/log4j2.xml \
    -Djive-content-exporter.config.path=etc/jive-content-exporter/config.properties \
    -Djive-content-exporter.credentials.path=etc/jive-content-exporter/credentials.properties \
    -cp %classpath \
    io.github.vitkin.jive.content.exporter.Main"
```

#### Distribution

```bash
bash bin/run.sh
```

## Reference

* [SAML 2.0](https://en.wikipedia.org/wiki/SAML_2.0)
* [cURL HTTP Cookies](https://curl.haxx.se/docs/http-cookies.html)
* [Is there a way to export Jive document HTML from the community?](https://community.jivesoftware.com/thread/299080)
* [Developer Introduction](https://developer.jivesoftware.com/intro/)
* [Jive SDK](https://developer.jivesoftware.com/sdk/)
* [Search Service](https://developers.jivesoftware.com/api/v3/cloud/rest/SearchService.html)
* [Place Service](https://developers.jivesoftware.com/api/v3/cloud/rest/PlaceService.html)
* [Content Service](https://developers.jivesoftware.com/api/v3/cloud/rest/ContentService.html)
* [Document](https://developers.jivesoftware.com/api/v3/cloud/rest/DocumentEntity.html)
* [Jive Developers](https://community.jivesoftware.com/community/developer)
* [Jive add-on server built and written in Java with SpringBoot + JPA + ThymeLeaf](https://github.com/jivesoftware/jive-sdk-java-springboot)
* [Implementation of the Jive SDK for JEE6 using Jersey, Spring Core and JSP](https://github.com/jivesoftware/jive-sdk-java-jersey)
* [Manual for `jq` 1.5](https://stedolan.github.io/jq/manual/v1.5)
* [Unirest-Java](http://kong.github.io/unirest-java/)
* [Jsoup](https://jsoup.org/)
* [Gson User Guide](https://github.com/google/gson/blob/master/UserGuide.md)
* [jsonschema2pojo](http://www.jsonschema2pojo.org/)
* [HTML character references](https://en.wikipedia.org/wiki/Character_encodings_in_HTML#HTML_character_references)
* [ASCII Table and Description](http://www.asciitable.com/)
