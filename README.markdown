    TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
    Copyright (C) 2007 Autentia Real Bussiness Solution S.L.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

# TNTConcept

With TNTConcept you can manage, in an integrated way, your customers, providers, contacts, business interactions, projects, week objectives, project time-tracking, bills, cash flow ... Built with the lastest J2EE techs: JSF, Spring, Hibernate, Maven ...

## TNTConcept Setup
In order to work in this project is necessary to setup
https://gitlab.com/autentia/internal/TNTGitHook to upload evidences automatically based
on the next setup file:

```json
{
"organization": "Autentia Real Business Solutions S.L.",
"project": "Desarrollo TNT Concept",
"role": "Programacion"
}
```

## Build requirements

- TNTConcept does not compile with JDK 8+
- Maven 2.2

## Configure docker OpenLDAP container for authentication

The [ldapTools/docker](https://github.com/autentia/TNTConcept/tree/master/ldapTools/docker) folder contains the Dockerfile for the image and a docker-compose.yml file already configured and ready to build the OpenLDAP container. This image is inspired by [dinkel/docker-openldap](https://github.com/dinkel/docker-openldap), so please check that README file in case you need to change any startup configuration.

The directory domain is autentia.com, so the current base/root of the directory tree would be `dc=autentia,dc=com`. The rootdn (LDAP's admin) is `cn=admin,dc=autentia,dc=com`and its password is `adminadmin`. As it uses OLC (Online Configuration), we have the `cn=config` entry, which is the root user able to make configuration changes. It also comes with an existing structure, having the `groups`and `people` organizational units (parent groups).

Additionally, the `ppolicy` module is already set up. This module provides enhanced password management capabilities. Since our application has a password reset feature, there is a `cn=reset,dc=autentia,dc=com` entry under the root structure (password `resetadmin`), which has access rights to reset the users' password.

If we want the password reset feature to work in our application and we have enabled LDAP authentication, we have to append the following lines in our `autentia.properties` file, under the LDAP configuration properties:

```
ldapAdminDn=cn=reset\\,dc=autentia\\,dc=com
ldapAdminPassword=resetadmin
```

Check [this documentation](http://www.zytrax.com/books/ldap/ch6/slapd-config.html) to get more information about OpenLDAP.

### How to manage/add users to the directory

LDAP has historically been configured statically through LDAP Data Interchange Files (LDIF). This means that when changes were made to `slapd.conf` (the LDAP configuration file), the server had to be rebooted for changes to take place. Recent versions use on-line configuration (OLC), which allows changes to be made through special commands while the server is running without having to restart.

Normal users in our application would be located under our `people` structure, so if we wanted to add a user called `example`, the entry DN would be `uid=example,ou=people,dc=autentia,dc=com`. LDAP entries include objectClasses, which are collections of attributes that must be present in the entry. A normal user in our application should be created with the `inetOrgPerson`and `simpleSecurityObject` objectClasses. This objectClasses will allow the entry to have some organizational info (name, surname) and a userPassword.

There are two ways to modify the LDAP directory tree to add/modify users or add/modify whatever you want (access rights, modules, schemas...): creating LDIF with the entries we want to add and executing the OLC commands or using LDAP browsers/editors.

#### Adding users through LDAP browsers

LDAP browsers allow us to make any changes we want to our LDAP directory tree without having to write any commands or LDIF files. There are many Open Source tools we can use: [LDAPAdmin](http://www.ldapadmin.org/index.html), [Apache Directory Studio](http://directory.apache.org/studio/), [phpLDAPadmin](http://phpldapadmin.sourceforge.net/wiki/index.php/Main_Page), or [JXplorer](http://pegacat.com/jxplorer/), along many others. Apache Directory Studio and phpLDAPadmin are the most powerful ones, but for this example we are going to use JXplorer, since it's the one we are more familiar with.

To make any changes to the directory, we must first bind to it with our admin user, so we press the Connect button (top-left corner). Here, the host would be the LDAP server address (tipically `localhost`), and the port is configured to be the default `389`. Our BaseDN is `dc=autentia,dc=com` and the bind we want to do is a 'User + Password' one, so we can bind with the admin user to make the changes. The UserDN is the full admin entry DN, `cn=amdin,dc=autentia,dc=com` and the password is the one we previously stated, `adminadmin`.

Once we have bound to the directory as admin, we can see all its entries. There should be the admin and reset entries and the groups and people structures. In order to create our `example` new entry, we have to right click the entry we want to be the parent of the new entry and press New or press the New button up on the toolbar (white paper icon). In our case we would select `people` and then make sure our Parent DN is `ou=people,dc=autentia,dc=com` and the relative DN is `uid=example`. In the same wizard we can choose which objectClasses we want the entry to have, in our case we must add `inetOrgPerson` and `simpleSecurityObject`. Before our entry is added to the directory, we are able to edit its attributes. There will be some default objectClasses added and the mandatory attributes will be highlighted. Once we fill all of them, we press Submit and the entry will be created.

#### Adding users through LDIF and command line

LDIF is the LDAP file format. It allows us to describe some directives in order to make changes to a database. As it is a bit complicated format, we will not go deep into it, but we will provide an example file to add a user. In case you want to gain any insight about LDIF, check [this](http://www.zytrax.com/books/ldap/ch2/).

Now, we should create a file called `addexample.ldif` and add the following lines with the properties we previously defined for our example entry:

```
dn: uid=example,ou=people,dc=autentia,dc=com
objectClass: inetOrgPerson
objectClass: simpleSecurityObject
cn: Example name
sn: Example surname
uid: example
userpassword: examplepass
```

Now, we should create a file called `addexample.ldif` and add the previous lines.

OpenLDAP provides several commands to allow us to modify the directory. The most important ones are ldapadd, ldapmodify and ldapdelete, but there are more. You can check all command line utilities [here](http://www.zytrax.com/books/ldap/ch14/#openldap).

So, if we want to push our LDIF entry into the LDAP database, we would execute the following command:
`ldapadd -H ldap://ldaphost -x -D "cn=admin,dc=autentia,dc=com" -f addexample.ldif -W adminadmin`
