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

@AttributesClass
class o2cAttributes {

    @TargetAttribute(target = "Active Directory BCT Domain",attributeName = "dept")
    static String department(User user){
        return "dept 77"
    }

    @TargetAttribute(target = "Active Directory BCT Domain")
    static String address1(User user){
        return GeneratedAttributes.streetAddress(user)
    }

    @TargetAttribute(target = "Active Directory BCT Domain")
    static String userid(User user){
        return user.id
    }

}
