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
public class GeneratedAttributes {

    @TargetAttribute(target = "internalLDAP",attributeName = "cn")
    static String cn(User user){
        return user.firstname +" "+ user.lastname
    }

    @TargetAttribute(target = "Active Directory BCT Domain", attributeName = "cn")
    static adcn(User user){
        return cn(user)
    }

    //default attributeName is the name of the method - "objectclass" in this case
    @TargetAttribute(target = "internalLDAP")
    static String objectclass(User user){
        return "top person organizationalperson inetorgperson"
    }

    @TargetAttribute(target = "internalLDAP")
    static String sn(User user){
        if (user.lastname == null || user.lastname.equals("")) {
            return user.firstname
        } else {
            return user.lastname
        }
    }

    @TargetAttribute(target = 'Active Directory BCT Domain',attributeName = "displayName")
    static String buildDisplayName(User user){
        def var
        if (user.lastname == null || user.lastname.equals("")){
            var = user.firstname
        } else{
            var = user.lastname + ", "+ user.firstname
        }
        return var;
    }

    @TargetAttribute
    static String cityState(User user){
        return "Kansas City, Kansas "
    }

    @TargetAttribute
    static String streetAddress(User user){
        return "114 Lincoln Way"
    }
}
