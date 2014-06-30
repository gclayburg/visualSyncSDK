package com.acme

import com.garyclayburg.attributes.AttributesClass
import com.garyclayburg.attributes.TargetAttribute
import com.garyclayburg.persistence.domain.User

/**
 * Created by IntelliJ IDEA.
 * Date: 6/24/14
 * Time: 10:59 AM
 *
 * @author Gary Clayburg
 */
@AttributesClass
class PasswdDB {

    @TargetAttribute(target = Targets.PASSWDDB,attributeName = "name")
    static String cn(User user){
        return user.firstname +" "+ user.lastname
    }

    @TargetAttribute(target = Targets.PASSWDDB,attributeName = "lastname")
    static String lastname(User user){
        return "" + user.lastname
    }

}

