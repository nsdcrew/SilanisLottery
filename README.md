# SilanisLottery

Online app address : 
silanislottery-nsdcrew.rhcloud.com

SECURITY : 
HOW TO LOGIN to the app:
Login name: admin
Login password : silanis

JAVA VERSION : 1.7

APPLICATION SERVER :
Tomcat 7

DATABASE INFOS:
Database Serveur MySQL 5.5
Database name : silanislottery
database Root User: adminI3x9mL6
database Root Password: pqMJdWnH8URc
Database schema : open file Silanis Lottery.mwb with MySQL Workbench . 
You can also see the schema as image opening "Silanis Lottery model.png" file

HOW TO INSTALL silanislottery DATABASE to a local database:
import silanislottery.sql to a mysql database throw phpmyadmin
silanislottery.sql contain a "CREATE DATABASE" instruction, so you don't need to create the database first

HOW TO EXECUTE APP WITH ECLIPSE:
file --> import --> existing project into workspace --> select "SilanisLottery-master" --> finish
To make it working you have to install the silanislottery database on a mysql server local first.
you can also change database configuration throw the persistance.xml file
Once you made it, you should be able to make the app working well on a tomcat server.

HOW TO EXECUTE JUNIT TEST WITH ECLIPSE:
select /src/test/java package --> right click --> run as --> JUNIT test
those test are not strong test , it just show you that i know how it works and how to write it.
