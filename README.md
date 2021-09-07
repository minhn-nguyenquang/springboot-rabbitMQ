# Spring MessageQ Architecture to import the large data of transactions

<p>Based on requirement of Bank ABC, we need to build an backend webservice that handle an function to allow back office users import transactions to their system (The file can have more 1 million of records).</p>
<p>The Backend must have some characteristics:</p>
<ul>
  <li>High Available</li>
  <li>Scalability and reusability, as well as efficiency</li>
  <li>Work very well with containers, such as Docker</li>
  <li>Better fault isolation</li>
</ul>

<p>That why I choose <b>Spring Boot </b>, <b>Keycloak</b> as authentication server, <b>RabbitMQ<b> as messaging application. The System Architecture same as image below:</p>

<h3>Postgres Database(transaction_db)</h3>
 <ul>
  <li><b>transaction_imported_file</b> table: Store all information of imported transaction files </li>
  <li><b>transaction</b> table: Store the transactions that are imported from file</li>
</ul>

<h3>--------------------------Work Flow--------------------------</h3>
<h4>1. Authentication w</h4>
 <p>- User/Client have to be authenticated before import transactions. In this system we use Keycloak as authentication service. Client will be authenticated as image below:</p>
 
![](/imgForReadme/keycloak_wf.png)