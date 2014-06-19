package com.acme
import com.acme.rest.UserCRUD

/**
 * Created by IntelliJ IDEA.
 * Date: 6/16/14
 * Time: 11:05 AM
 *
 * @author Gary Clayburg
 */
UserCRUD crud = new UserCRUD("http://localhost:8080/")

crud.printAllUsers()
