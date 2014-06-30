package com.acme

import com.garyclayburg.attributes.AttributesClass
import com.garyclayburg.attributes.TargetAttribute
import com.garyclayburg.persistence.domain.User

/**
 * Created by IntelliJ IDEA.
 * Date: 3/27/14
 * Time: 12:42 PM
 *
 * @author Gary Clayburg
 */

class TargetIn {
    public static final BCT="Active Directory BCT Domain"
}

@AttributesClass
class o2cAttributes {

    @TargetAttribute(target = TargetIn.BCT,attributeName = "dept")
    static String department(User user){
        return "dept 71" + Targets.BCT
    }

    @TargetAttribute(target = TargetIn.BCT)
    static String address1(User user){
        return GeneratedAttributes.streetAddress(user)
    }

    @TargetAttribute(target = Targets.BCT)
    static String userid(User user){
        return user.id
    }

}

