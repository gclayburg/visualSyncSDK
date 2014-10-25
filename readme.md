

# Quickstart

1. Clone this repository to a directory on your local machine

    ```
    $ mkdir development
    $ cd development
    $ git clone git@github.com:gclayburg/visualSyncSDK.git
    ```
2. Verify Oracle Java 7 (or higher) is installed

    ```
    $ java -version
    java version "1.8.0_25"
    Java(TM) SE Runtime Environment (build 1.8.0_25-b17)
    Java HotSpot(TM) 64-Bit Server VM (build 25.25-b02, mixed mode)

    $  echo $JAVA_HOME
    /usr/lib/jvm/java-8-oracle/
    ```
3. Use the embedded gradle to run the application

    ```
    $ cd visualSyncSDK
    $ gradlew execrun
    ```

There are a lot of things happening behind the scenes here, especially the first time you run this command.  If you look close you will see several things being downloaded here, namely

* [gradlew][1] will download and install gradle to build the project
* [MongoDB](http://www.mongodb.org/) will be downloaded and installed

* **Note: An embedded Tomcat server is bundled inside this project.  Installing Tomcat is not necessary to run the server**



The server is ready once you see this in the console output

```
Server is ready for e-business
```

## Loading users via REST
At this point, the Tomcat server is running with an empty MongoDB database.  

With the server running in one command window, start another command window and execute this gradle command:

```
$ gradlew load_baratheon
```
This command uses gradle to run this groovy script: **src/main/groovy/com/acme/load_baratheon.groovy**

The groovy script uses the visualSync REST API to load in a few users.  For this example we load only a few simply user attributes such as first name, last name and a unique ID number:

```
...
createUser(restClient,"Robert","Baratheon",2014)
createUser(restClient,"Stannis","Baratheon",2015)
createUser(restClient,"Joffrey","Lannister",2017)
...
```


## Use the REST API to display all users
**src/main/groovy/com/acme/printUsers.groovy**
```
$ gradlew -q printUsers
2014-06-19 18:33:53,604 INFO  com.acme.rest.UserCRUD               - retrieving all saved users...
2014-06-19 18:33:54,515 INFO  com.acme.rest.UserCRUD               - status 200
2014-06-19 18:33:54,520 INFO  com.acme.rest.UserCRUD               - size of doc: 3
2014-06-19 18:33:54,525 INFO  com.acme.rest.UserCRUD               - page size: 20
2014-06-19 18:33:54,526 INFO  com.acme.rest.UserCRUD               - total users: 14
2014-06-19 18:33:54,596 INFO  com.acme.rest.UserCRUD               -  Robert Baratheon : [href:http://localhost:8080/visualusers/2014]
2014-06-19 18:33:54,598 INFO  com.acme.rest.UserCRUD               -  Stannis Baratheon : [href:http://localhost:8080/visualusers/2015]
2014-06-19 18:33:54,600 INFO  com.acme.rest.UserCRUD               -  Joffrey Lannister : [href:http://localhost:8080/visualusers/2017]
2014-06-19 18:33:54,601 INFO  com.acme.rest.UserCRUD               -  Tommen Lannister : [href:http://localhost:8080/visualusers/2018]
2014-06-19 18:33:54,601 INFO  com.acme.rest.UserCRUD               -  Tywin Lannister : [href:http://localhost:8080/visualusers/2019]
2014-06-19 18:33:54,602 INFO  com.acme.rest.UserCRUD               -  Tyrion Lannister : [href:http://localhost:8080/visualusers/2020]
2014-06-19 18:33:54,603 INFO  com.acme.rest.UserCRUD               -  Cersei Lannister : [href:http://localhost:8080/visualusers/2021]
2014-06-19 18:33:54,604 INFO  com.acme.rest.UserCRUD               -  Jamie Lannister : [href:http://localhost:8080/visualusers/2022]
2014-06-19 18:33:54,604 INFO  com.acme.rest.UserCRUD               -  Bran Stark : [href:http://localhost:8080/visualusers/2023]
2014-06-19 18:33:54,605 INFO  com.acme.rest.UserCRUD               -  Arya Stark : [href:http://localhost:8080/visualusers/2024]
2014-06-19 18:33:54,606 INFO  com.acme.rest.UserCRUD               -  John Snow : [href:http://localhost:8080/visualusers/2025]
2014-06-19 18:33:54,612 INFO  com.acme.rest.UserCRUD               -  Daenerys Targaryen : [href:http://localhost:8080/visualusers/2026]
2014-06-19 18:33:54,613 INFO  com.acme.rest.UserCRUD               -  Ygritte  : [href:http://localhost:8080/visualusers/2027]
2014-06-19 18:33:54,618 INFO  com.acme.rest.UserCRUD               -  Sansa null : [href:http://localhost:8080/visualusers/2028]
```

The URL displayed for each user can be used to retrieve the complete user in JSON form in a browser or any other http client.

## Use the REST API to change a user and show the audit log
**src/main/groovy/com/acme/printAudit.groovy**
```
$ gradlew -q printAudit
2014-06-19 18:31:53,216 INFO  com.acme.rest.UserCRUD               - created/modified userid [2017] Joffrey Baratheon
2014-06-19 18:31:53,281 INFO  com.acme.rest.UserCRUD               - created/modified userid [2017] Joffrey Lannister
2014-06-19 18:31:53,675 INFO  com.acme.rest.UserCRUD               - user history:
2014-06-19 18:31:53,749 INFO  com.acme.rest.UserCRUD               - [2017] Joffrey Lannister : modified by system user at 18:7:9.415
2014-06-19 18:31:53,750 INFO  com.acme.rest.UserCRUD               - [2017] Joffrey Baratheon : modified by system user at 18:17:32.859
2014-06-19 18:31:53,750 INFO  com.acme.rest.UserCRUD               - [2017] Joffrey Lannister : modified by system user at 18:17:32.978
2014-06-19 18:31:53,750 INFO  com.acme.rest.UserCRUD               - [2017] Joffrey Baratheon : modified by system user at 18:17:54.417
2014-06-19 18:31:53,750 INFO  com.acme.rest.UserCRUD               - [2017] Joffrey Lannister : modified by system user at 18:17:54.532
2014-06-19 18:31:53,750 INFO  com.acme.rest.UserCRUD               - [2017] Joffrey Baratheon : modified by system user at 18:31:53.53
2014-06-19 18:31:53,751 INFO  com.acme.rest.UserCRUD               - [2017] Joffrey Lannister : modified by system user at 18:31:53.249
```

# Using the policy console
Now that we have some users loaded, we can view them with the policy console.  Open this URL in your browser:
http://localhost:8080/console

You should see something like this

![Initial Policy Console][2]

The search box can search for users by first or last name.  For now enter the wildcard character, * to show all users:

![14 searched users][3]

You now see the users that we loaded on the left side of the screen.  On the right side are generated attributes for the selected user.  If you recall, our load_baratheon.groovy script created these users with only a firstname, lastname and id number like this:

src/main/groovy/com/acme/load_baratheon.groovy
```groovy
...
createUser(restClient,"Robert","Baratheon",2014)
createUser(restClient,"Stannis","Baratheon",2015)
createUser(restClient,"Joffrey","Lannister",2017)
...
```
Where did these generated attributes come from?

To answer this question, look at this file in the SDK:

**identitypolicy/src/main/groovy/com/acme/o2cAttributes.groovy**

```
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
```

This is a groovy class that generates the attributes values for `dept`, `address1` and `userid` seen in the policy console. 
## Dynamic Policy Console
The policy console is dynamic.  To see what this means, open the `o2cAttributes.groovy` class in a text editor and change the definition for the department method to something this:

**identitypolicy/src/main/groovy/com/acme/o2cAttributes.groovy**
```
...
    @TargetAttribute(target = "Active Directory BCT Domain",attributeName = "dept")
    static String department(User user){
        return "dept 86"
    }
...
```
Save this file and examine the policy console screen.  You should see something like this:

![Policy Change][4]

## Troubleshooting attribute policy script errors
This policy console can also help troubleshoot script errors.  For example, introduce an intentional error into the o2cAttributes class like this:

**identitypolicy/src/main/groovy/com/acme/o2cAttributes.groovy**
```
    @TargetAttribute(target = "Active Directory BCT Domain",attributeName = "dept")
    static String department(User user){
        return "dept string should be terminated
    }
```
You should see an error in the policy console immediately after saving the file.  Click on the bell for the details:

![enter image description here][5]

To clear the error condition, return to your text editor and fix the syntax error.  Once the file is saved, the errors in the policy console will disappear.  The Bell icon will light up if there is at least one error detected with either script compilation or execution.

You might notice that there is no "execute" or "simulate" button in the policy console.  This is because the policy console is intended to be a near real-time simulator of policy.  Create policy in an editor.  Save the file.  View the impact instantly.  Simple.

## Create a new attribute
Right click on a user and bring up the "internalLDAP" window.  You should see a new window with attributes that are tagged for the LDAP target.  Re-arrange this window so that it is visible:

![enter image description here][6]

We are going to add a new `sn` LDAP attribute for this user.  We want the `sn` attribute for the LDAP target to be generated from the stored surname or last name of the user in MongoDB.  In our case, the `User` object passed in to our attribute policy script contains a `lastname` attribute.  The new method looks like this:

**identitypolicy/src/main/groovy/com/acme/o2cAttributes.groovy**
```
    @TargetAttribute(target = "internalLDAP")
    static String sn(User user){
        return user.lastname
    }
```
Robert now has an `sn` attribute:
![enter image description here][7]

Now, what happens if the user does not have a `lastname`?  Click on the user named "Ygritte" in the policy console.  This user only has a `firstname`.

![enter image description here][8]

We need to change our policy to deal with this situation.  Lets replace our `sn` attribute definition with this one:

**identitypolicy/src/main/groovy/com/acme/o2cAttributes.groovy**
```
...
    @TargetAttribute(target = "internalLDAP")
    static String sn(User user){
        if (user.lastname == null){
            return user.firstname
        } else{
            return user.lastname
        }
    }
...
```

As you can see, we just decided to use the `firstname` of the person for the value of the `sn` attribute if they do not have a `lastname`.  Save this file and examine the policy console.  

![enter image description here][9]

The value of the `sn` attribute did not change.  Why?  To find out, lets try this version of the `sn` policy:

**identitypolicy/src/main/groovy/com/acme/o2cAttributes.groovy**
```
...
    @TargetAttribute(target = "internalLDAP")
    static String sn(User user){
        if (user.lastname == null || user.lastname.equals("")){
            return user.firstname
        } else{
            return user.lastname
        }
    }
...
```

and the result:

![enter image description here][10]

Aha!  Even though this user didn't have a `lastname`, it was stored in the database as a empty `String` value.  This is an artifact of how the user was loaded.  The load_baratheon.groovy script that loaded values into the database supplied an empty `String` for `lastname` instead of just leaving it out.  Our `sn` attribute policy now accounts for both situations.  We can verify this with a representative sample of users in the policy console.

The `User` object represents the user that is stored in the MongoDB database. The values for these users are expected to be authoritative.  Any of these authoritative values can be used to generate attributes for target systems.  These attributes are dynamic.  None of these generated attributes are stored in the database.

## Stopping the server
If you started the server with this gradle command

    $ gradlew execrun

you can stop it with a simple interrupt (ctrl-c in the command window)  The embedded Tomcat server will stop and the embedded database will stop immediately.  Once the server has stopped, all the user data is gone.  This embedded server is essentially a lightweight distribution to demo the system as quickly as possible.

# Running against a permanent user database
In order for our user changes to be persistent across server restarts, we need to install a separate database.  VisualSync uses the MongoDB NoSQL database as the user store.  Instructions for installing MongoDB can be found on the MongoDB website:
http://www.mongodb.org/

### Configuring VisualSync to use the standalone database

Open this file with a text editor **identitypolicy/config/application.properties** and add these lines:

**identitypolicy/config/application.properties**
```
spring.profiles.active=mongolocal
mongoHost=localhost
mongoPort=27017
```
These settings assume that you have installed your MongoDB instance using the default settings.  Start the server as normal:
```
$ gradlew execrun
```

---
> Written with [StackEdit](https://stackedit.io/).


  [1]: http://www.gradle.org/docs/current/userguide/gradle_wrapper.html
  [2]: https://raw.githubusercontent.com/gclayburg/visualSyncSDK/master/screenshots/Screenshot-initial.png
  [3]: https://raw.githubusercontent.com/gclayburg/visualSyncSDK/master/screenshots/Screenshot-14users.png
  [4]: https://raw.githubusercontent.com/gclayburg/visualSyncSDK/master/screenshots/Screenshot-policychange.png
  [5]: https://raw.githubusercontent.com/gclayburg/visualSyncSDK/master/screenshots/Screenshot-policyerror.png
  [6]: https://raw.githubusercontent.com/gclayburg/visualSyncSDK/master/screenshots/Screenshot-internalLDAP.png
  [7]: https://raw.githubusercontent.com/gclayburg/visualSyncSDK/master/screenshots/Screenshot-sn.png
  [8]: https://raw.githubusercontent.com/gclayburg/visualSyncSDK/master/screenshots/Screenshot-blanksn.png
  [9]: https://raw.githubusercontent.com/gclayburg/visualSyncSDK/master/screenshots/Screenshot-blanksn.png
  [10]: https://raw.githubusercontent.com/gclayburg/visualSyncSDK/master/screenshots/Screenshot-ygritte.png