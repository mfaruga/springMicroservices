# Spring Boot OAuth 2 Examples

Examples how to set up an OAuth2 identity server and resource provider within a few minutes using [Spring Boot] and Maven.

Please feel free to take a look at [my blog] for the full tutorial.

## Running the Identity Server

Using Maven

```
cd identity-server && mvn spring-boot:run
```

## Running the Resource Provider

Using Maven

```
cd resource-provider && mvn spring-boot:run
```

## Requesting a Token

Using Curl

```
   curl -XPOST -k -vi foo:foosecret@localhost:9000/hascode/oauth/token \
   -d grant_type=password -d client_id=foo -d client_secret=abc123 \
   -d redirect_uri=http://www.hascode.com -d username=bar -d password=barsecret
```

## Accessing the secured Resource

```
   TOKEN = 'xxxxxxx'
   curl -vi -H "Authorization: Bearer $TOKEN" http://localhost:9001/resource/
```

---

**2016 Micha Kops / hasCode.com**

   [my blog]:http://www.hascode.com/
   [Spring Boot]:http://projects.spring.io/spring-boot/
