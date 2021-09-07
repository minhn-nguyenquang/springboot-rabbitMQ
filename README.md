# Spring MessageQ Architecture to import the large data of transactions

<p>Based on requirement of Bank ABC, we need to build an backend webservice that handle an function to allow back office users import transactions to their system (The file can have more 1 million of records).</p>
<p>The Backend must have some characteristics:</p>
<ul>
  <li>High Available</li>
  <li>Scalability and reusability, as well as efficiency</li>
  <li>Work very well with containers, such as Docker</li>
  <li>Better fault isolation</li>
</ul>

<p>That why I choose <b>Spring Boot </b>, <b>Keycloak</b> as authentication server, <b>RabbitMQ</b> as messaging application. The System Architecture same as image below:</p>

<h3>Postgres Database(transaction_db)</h3>
 <ul>
  <li><b>transaction_imported_file</b> table: Store all information of imported transaction files </li>
  <li><b>transaction</b> table: Store the transactions that are imported from file</li>
</ul>

<h3>--------------------------Work Flow--------------------------</h3>
<h4>1. Authentication</h4>
 <p>- User/Client have to be authenticated before import transactions. In this system we use Keycloak as authentication service. Client will be authenticated as image below:</p>
 
![](/imgForReadme/keycloak_wf.png)

<h3>1.1. Keycloak configuration</h3>
<p>In this system we use Docker for Container. To start Keycloak, from a terminal start Keycloak with the following command:</p>

<b>docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:15.0.2</b>
<p>This will start Keycloak exposed on the local port 8080. It will also create an initial admin user with username admin and password admin. Now let's open a browser and visit http://localhost:8080. We'll be redirected to http://localhost:8080/auth to create an administrative login:</p>

![](/imgForReadme/keycloak_home.png)

<h2>Create a realm</h2>
<p>A realm in Keycloak is the equivalent of a tenant. It allows creating isolated groups of applications and users. By default there is a single realm in Keycloak called master. This is dedicated to manage Keycloak and should not be used for your own applications.</p>

<p>Let’s create our first realm. Open the Keycloak Admin Console</p>

<p>Hover the mouse over the dropdown in the top-left corner where it says Master, then click on Add realm</p>

<p>Fill in the form with the following values:</p>

<p>Name: SpringBootRealm</p>

<p>Click Create</p>

![](/imgForReadme/keycloak_realm.png)

<h2>Create a user</h2>

<p>Initially there are no users in a new realm, so let’s create one:</p>
<ul>
	<li>Click Users (left-hand menu). Click Add user (top-right corner of table)</li>
	<li>Fill in the form with the following values:</li>
	<li>Username: nqminh</li>
	<li>First Name: Your first name</li>
	<li>Last Name: Your last name</li>
	<li>Click Save</li>
</ul>

![](/imgForReadme/keycloak_adduser.png)

<p>The user will need an initial password set to be able to login. To do this:</p>

<p>Click Credentials (top of the page). Fill in the Set Password form with a password</p>

<p>Click ON to OFF next to Temporary to prevent having to update password on first login

![](/imgForReadme/keycloak_changepassword.png)
