# pet-houses-jooq
Sandbox example app with JOOQ 

<h3>Before start application</h3>
Need to create database on your postgres server:

`create database 'pet-jooq';`

or ... do nothing, if you are use H2 in memory DB.

<h3>Compile and run</h3>
<h4>For Postgres</h4>
`mvn clean package -P pgdb`

`cd ${buildDir}`

`java -jar -Dspring.profiles.active=pgdb *buildedArtifactName*.jar`

<h4>H2</h4>
`mvn clean package -P h2db`

`cd ${buildDir}`

`java -jar -Dspring.profiles.active=h2db *buildedArtifactName*.jar`

<h4>Check it ...</h4>
For example:
Get request: `http://localhost:8855/houses/projections/00000000-0000-0000-0000-000000000010`
or: 
`curl -X GET http://localhost:8855/houses/projections/00000000-0000-0000-0000-000000000010 | json_pp`


