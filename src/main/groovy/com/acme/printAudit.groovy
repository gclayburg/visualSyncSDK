package com.acme

//@groovy.lang.Grab

import com.acme.rest.UserCRUD

/**
 * Created by IntelliJ IDEA.
 * Date: 6/16/14
 * Time: 11:05 AM
 *
 * @author Gary Clayburg
 */
UserCRUD crud = new UserCRUD("http://localhost:8080/")

crud.createUser("Joffrey","Baratheon",2017)
crud.createUser("Joffrey","Lannister",2017)  //modify last name

crud.printUserAuditLog(2017)
