package com.acme

import groovyx.net.http.Method

/**
 * Created by IntelliJ IDEA.
 * Date: 4/2/14
 * Time: 8:13 AM
 *
 * @author Gary Clayburg
 */

//uncomment @Grab to execute as standalone script
//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
//@Grab(group='org.slf4j',module = 'slf4j-api',version = '1.7.7')
//@Grab(group='org.slf4j',module = 'slf4j-simple',version = '1.7.7')

import groovyx.net.http.RESTClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.GET
import groovyx.net.http.HTTPBuilder;

final Logger log = LoggerFactory.getLogger("baratheon.groovy");

def hostPort = 'http://localhost:8080/'
//def hostPort = 'http://bagley.garyclayburg.com:8081/userconsole-1.0-SNAPSHOT/'
//def hostPort = 'http://visualsync2.garyclayburg.cloudbees.net/'
def restClient = new RESTClient(hostPort)

//tell RESTClient that it can parse 'application/hal+json' returned content-type as 'application/json'
//spring data REST uses 'application/hal+json' and RESTClient by default cannot parse it
restClient.parser.'application/hal+json' = restClient.parser.'application/json'


// add a new user
createUser(restClient,"Robert","Baratheon",2014)
createUser(restClient,"Stannis","Baratheon",2015)
createUser(restClient,"Joffrey","Lannister",2017)
createUser(restClient,"Tommen","Lannister",2018)
createUser(restClient,"Tywin","Lannister",2019)
createUser(restClient,"Tyrion","Lannister",2020)
createUser(restClient,"Cersei","Lannister",2021)
createUser(restClient,"Jamie","Lannister",2022)
createUser(restClient,"Bran","Stark",2023)
createUser(restClient,"Arya","Stark",2024)
createUser(restClient,"John","Snow",2025)
createUser(restClient,"Daenerys","Targaryen",2026)
createUser(restClient,"Ygritte","",2027)
createUser(restClient,"Sansa",null,2028)

private void createUser(RESTClient restClient,def first,def last,def idnum) {
    def resp = restClient.post(
            path: 'audited-users/auditedsave',
            body: [firstname: first,lastname: last,id: idnum],
            requestContentType: "application/json"
    )
    assert resp.status == 200 // user created on server
}

def http = new HTTPBuilder(hostPort)
http.parser.'application/hal+json' = http.parser.'application/json'

log.info("retrieving all saved users...")
http = new HTTPBuilder(hostPort)
http.request(GET,JSON) {
    uri.path = 'visualusers'

    response.success = { resp1,json ->
        log.info "status " + resp1.status
        log.info("size of doc: " + json.size()) //size of map generated from json doc
        log.info("users found on page: " + json.page.size)  //page.size attribute in json
        log.info("total users: " + json.page.totalElements)  // page.totalElements attribute in json
        log.info("first user "+ json._embedded.vusers.get(1))
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
            log.info "created ${it.firstname} ${it.lastname}"
        }
    }
}

