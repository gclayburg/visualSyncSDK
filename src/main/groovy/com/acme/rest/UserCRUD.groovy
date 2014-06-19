package com.acme.rest

@Grab(group='ch.qos.logback',module ='logback-classic',version='1.1.2')

@Grab(group='org.slf4j',module = 'slf4j-api',version = '1.7.7')
import groovy.util.logging.Slf4j

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.GET

/**
 * Created by IntelliJ IDEA.
 * Date: 6/11/14
 * Time: 1:51 PM
 *
 * @author Gary Clayburg
 */
@Slf4j
class UserCRUD {
    private RESTClient restClient
    private HTTPBuilder http

    UserCRUD() {
        this('http://localhost:8080/')
    }

    UserCRUD(def hostPort) {
        restClient = new RESTClient(hostPort)

//tell RESTClient that it can parse 'application/hal+json' returned content-type as 'application/json'
//spring data REST uses 'application/hal+json' and RESTClient by default cannot parse it

        this.restClient.parser.'application/hal+json' = this.restClient.parser.'application/json'

        http = new HTTPBuilder(hostPort)
        this.http.parser.'application/hal+json' = this.http.parser.'application/json'

    }

    public void createUser(def first,def last,def idnum) {
        def resp = restClient.post(
                path: 'audited-users/auditedsave',
                body: [firstname: first,lastname: last,id: idnum],
                requestContentType: "application/json"
        )

        assert resp.status == 200 // user created on server
        log.info("created/modified userid [{}] {} {}",idnum, first,last)
    }

    public void createUser(def first,def idnum) {
        def resp = restClient.post(
                path: 'audited-users/auditedsave',
                body: [firstname: first,id: idnum],
                requestContentType: "application/json"
        )

        assert resp.status == 200 // user created on server
        log.info("created/modified userid [{}] {} {}",idnum, first)
    }

    def createRandomUsers(int numUsers) {
        def firstList = ["Bill","Bob","Jane","Peg","Frank","Don","Stacy","Cathy","Faye","Lars","Mike","Ethan","Hank","Sandra","Lori","Diane","Malia","Bridget","Cory","Shane","Shonda","Dave","Barb","Steve","Janna"]
        def lastList = ["Thomas","Miller","Sorensen","Fleming","Underwood","Wright","Anderson","Herman","Smith","Hanson","Pitrone","LeMond","Armstrong","Carter","Nickel"]

        for (i in 1..numUsers) {
            def first = firstList.get((int) (Math.random() * lastList.size()))
            def last = lastList.get((int) (Math.random() * lastList.size()))
            createUser(first,last,null)
        }
    }

    def printAllUsers() {

        log.info("retrieving all saved users...")
        http.request(GET,JSON) {
            uri.path = 'visualusers'

            response.success = { resp1,json ->
                log.info "status " + resp1.status
                log.info("size of doc: " + json.size()) //size of map generated from json doc
                log.info("page size: " + json.page.size)  //page.size attribute in json
                log.info("total users: " + json.page.totalElements)  // page.totalElements attribute in json
/* output ex:
  "_embedded" : {
    "vusers" : [ {
      "firstname" : "Johnboy",
      "lastname" : "Walton",
      "email" : null,
...
"page" : {"
    "size" : 20,
    "totalElements" : 43,
    "totalPages" : 3,
    "number" : 0
  }"
 */
                json._embedded.vusers.each{
                    log.info " ${it.firstname} ${it.lastname} : ${it._links.self}"
                }
            }
        }

    }

    def printUserAuditLog(int userId) {
        http.request(GET,JSON) {
            uri.path = "audited-users/findUserAuditByUserId/${userId}"
            response.success = {resp1,json ->
                log.info("user history:")
                json.each {  //array of users
                    log.info("[$userId] ${it.user.firstname} ${it.user.lastname} : modified by ${it.lastModifedBy} at ${it.lastModifiedDate.hourOfDay}:${it.lastModifiedDate.minuteOfHour}:${it.lastModifiedDate.secondOfMinute}.${it.lastModifiedDate.millisOfSecond}" )
                }
            }
        }
    }
}
