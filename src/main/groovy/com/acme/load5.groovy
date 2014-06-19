package com.acme


import com.acme.rest.UserCRUD
/**
 * Created by IntelliJ IDEA.
 * Date: 6/11/14
 * Time: 1:52 PM
 *
 * @author Gary Clayburg
 */

def hostPort = 'http://localhost:8080/'

UserCRUD userCrud = new UserCRUD(hostPort)
// add a new user
userCrud.createUser("Robert","Baratheon",2014)
userCrud.createUser("Stannis","Baratheon",2015)
userCrud.createUser("Joffrey","Lannister",2017)
userCrud.createUser("Tommen","Lannister",2018)

userCrud.createRandomUsers(5)
