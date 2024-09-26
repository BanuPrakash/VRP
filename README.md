# Java


Banuprakash C

Full Stack Architect, Corporate Trainer

Co-founder & CTO: Lucida Technologies Pvt Ltd., 

Email: banuprakashc@yahoo.co.in

https://www.linkedin.com/in/banu-prakash-50416019/

https://github.com/BanuPrakash/VRP

===============================

Object: state and a behaviour

SOLID design Principle

S --> Single Responsibility
O --> Open Close Prinicple [ closed for Change, open for extension]
L --> Liskov substitution Principle
I --> Interface seggregation
D --> Dependency Injection

================

Compilation Environment
Development Kit:

Source Code --> compile --> bytecode [.class]
Java DK
Groovy DK
Kotlin DK

```
Account.java
    public class Account {
        private double balance;

        public void deposit(double amt) {
            this.balance += amt;
        }

        public double getBalance() {
            return this.balance;
        }
    }

javac Account.java ---> Account.class
```

AccountExample.java
```
    public class AccountExample {
        public static void main(String[] args) {
            Account rahulAcc = new Account();
            Account swethaAcc = new Account();
            swethaAcc.deposit(45000);
            System.out.println(swethaAcc.getBalance());
        }
    }

```

classloader:
findLoadedClass()
loadClass()
findSystemClass()
defineClass()

=======

Logically grouping of objects/classes:
1) entity / model / domain
represent a business data / no business logic
Uber
Customer, Driver, Vechicle, Payment, Trip,...

2) DAO --> Data Access Object ==> CRUD operations
3) Service
4) UI
5) Utility
6) Exception

==============

Why program to interface?

1) DESIGN
2) IMPLEMENTATION
3) TESTING
4) INTEGRATION
5) LOOSE COUPLING

Maven permission

```
a) docker pull mysql

b) 
For Windows:
docker run --name local-mysql –p 3306:3306 -e MYSQL_ROOT_PASSWORD=Welcome123 -d mysql

container name given here is "local-mysql"

For Mac:
docker run -p 3306:3306 -d --name local-mysql -e MYSQL_ROOT_PASSWORD=Welcome123 mysql

```

FunctionalInterface:
is one where only one method has to be implemented

we can use lambda expressions instead of anonymous class

HOF: High Order Function:
1) function accepting function as an argument
2) function return a function

Commonly used HOF:
1) filter
2) map
3) reduce
4) forEach
6) skip
7) limit

https://rxmarbles.com/

==================================================

Java Concurrency

Process: Program in Execution.
Every process needs to have at least one unit of work running --> Thread

Single Threaded application: notepad, onenote, calculator

Multi-threaded application: Word, Intellij, Browser, excel, ....

Word: typing, spell check, grammer check, auto save,....

Uses of multi-threaded application:
1) Avoid starvation
2) Optimization of resource usage [Threads are light weight process, threads shares resources allocted for process [ loaded classes, objects]]

Word:
Document class
document object --> HEAP area

Java provides:
1) Runnable interface
interface Runnable {
    void run(); // entry point for a thread, just like main() for main thread
}

2) Thread class ==> implements Runnable
contains life-cycle methods to start, sleep, suspend, resume, join, interrupt, stop..

stop, suspend and resume are deprecated --> Not supposed to be used.

======

Thread Safe:
A member is said to be thread safe if it doesn't get effected in multi-threaded environment

* local variables: reside on stack, each thread has a seperate stack --> safe
* static variables: reside in metaspace along with class data --> shared by all threads --> not safe
* instance variables: reside on heap area --> shared by threads --> not safe
* immutable objects: reside on heap --> shared --> safe

wait() and notify()/notifyAll() can be used for inter-thread communication


Thread calls this method:
```
// synchronized acquires a lock
// wait() release a lock
void doTask() {
    wait(); // what happens to this line ==> IllegalMonitorStateException
}
```

Banking Service:

```
class BankingService {

    public  void transferFunds(Account fromAcc, Account toAcc, double amt) {
        synchronized(fromAcc) { // acquire lock of fromAcc
            synchronized(toAcc) { // acquire lock of toAcc
                fromAcc.withdraw(amt);
                toAcc.deposit(amt);
            } // release lock of toAcc
        } // release lock of fromAcc
        
    }
}

class Uitlity {
    public void copyList(List<String> dest, List<String> source) {
        synchronized(dest) {
            syncronized(source) {
                copying from source to destination
            }
        }
    }
}

```

Java 5: fixed few issues with threads
1) only one lock per object [with synchronized]
```
class Account {
    UserData data; // avatar, profilePic, email
    double balance;
}

If transaction is happening, I can't update my avatar
We should have a sperate lock for UserData and another lock for balance

```
2) timeout is not there

3) Deadlock

```
Thread t1 --> SB102 to SB451 5000
Thread t2 --> SB451 to SB102 9000

 public  void transferFunds(Account fromAcc, Account toAcc, double amt) {
        synchronized(fromAcc) { // t1 gets SB102 lock, t2 gets SB451 lock
            synchronized(toAcc) { // t1 wants SB451, t2 wants SB102 lock
                fromAcc.withdraw(amt);
                toAcc.deposit(amt);
            } // release lock of toAcc
        } // release lock of fromAcc
        
    }
```

4) ThreadPool
5) Thread can't return a value

===============


Doug Lea: Concurrent Programming in Java
Lock --> Programmatically locking and unlocking

Deadlock Solution:
```
Thread t1 --> SB102 to SB451 5000
Thread t2 --> SB451 to SB102 9000

public  void transferFunds(Account fromAcc, Account toAcc, double amt) {
            // T1 try to get SB102, T2 gets SB451 lock
            if(fromAcc.balLock.tryLock(2, TimeUnit.SECONDS)) {
                 try {
                    // T1 try to get SB451
                    if(toAcc.balLock.tryLock(2, TimeUnit.SECONDS)) {
                          try {
                            fromAcc.withdraw(amt);
                            toAcc.deposit(amt);
                        }
                        finally {
                            toAcc.balLock.unlock();
                        }
                    }
                 } finally{
                    fromAcc.balLock.unlock(); // T1 releases SB102
                 }
       }
        
    }

```

The Callable interface is similar to Runnable, in that both are designed for classes whose instances are potentially executed by another thread. A Runnable, however, does not return a result and cannot throw a checked exception.

Always prefer ThreadPool instead of start()
latency involved in creating and destroying thread

=====================================================
Maven / gradle: build tool
* manages dependencies [your project depends on many 3rd party libraries]
can also manage transative dependency.

* goals: compile, test, packaging, deploy, ....

Maven projects are portable across IDEs

=============

Day 3:

Maven
Docker :Platform with OS virtualization; we can have applications running <<container>>
images are there for every applictaiont in Dokcer Hub

=============

```

docker exec -it local-mysql bash

bash-4.4# mysql -u root -p
Enter password: Welcome123
mysql> create database VRP;
mysql> use VRP;

mysql> create table products (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), price double, quantity int);

mysql> insert into products values (0, 'iPhone 16', 980000.00, 100);


mysql> insert into products values (0, 'Macbook Pro', 2350000.00, 100);


mysql> select * from products;
+----+-------------+---------+----------+
| id | name        | price   | quantity |
+----+-------------+---------+----------+
|  1 | iPhone 16   |  980000 |      100 |
|  2 | Macbook Pro | 2350000 |      100 |
+----+-------------+---------+----------+
2 rows in set (0.00 sec)

```

Java <---> database mysql

JDBC --> Java Database Connectivity --> Integration Library

JDBC is a set of interfaces; implementation classes are provided by database vendors

```
Steps:
1) Load 3rd party driver classes

Class.forName("com.mysql.cj.jdbc.Driver")

2) Establish a database Connection

Connection con = DriverManager.getConnection(URL, USER, PWD);

URL:
jdbc:thin:oracle://123.11.55.11:1521:empdb

jdbc:mysql://localhost:3306/empdb


3) Send SQL:
3.1) Statement
SQL is fixed and same for every execution
select * from products;

3.2) PreparedStatement
SQL needs IN parameters

select * from products where id = ?

insert into products values(0, ?, ?, ?)

3.3) CallableStatement

is used to invoke stored procedures of database

{call TRANSACTION('SB103', 'SB545', 56000.00)}

4) 
int executeUpdate(); // for INSERT, DELETE and UPDATE SQL
ResultSet executeQuery(); // SELECT

ResultSet is a cursor to fetched records

boolean next()

rs.getString("CustomerID")

rs.getInt("Age")

5) release resources in finally block
finally executes if exceptions occurs or not


```

public class ProductDaoJdbcImpl implements ProductDao {
     public void addProduct(Product product) throws DaoException {
        try {
             // SQL 
        } catch(SQLException ex) {
            // based on error code
            throw new DaoException("unable to add product", ex);
        }
      
       
     }
   // List<Product> getProducts();
}




try {
    Product p = new Product(...);
    ProductDao productDao = new ProductDaoJdbcImpl();
    productDao.addProduct(p);
} catch(DaoException ex) {
    ex.printStackTrace();
}


=============

Annotation: Metadata

Who uses it?
* COMPILER ==> Annotation data is only present in source ==> bytecode won;t have it
* CLASSLOADER ==> .class contains metadata, gets removed from METASPACE
* RUNTIME ==> metadata is available in JRE [ metaspace]

Where can i use it?
* TYPE
* METHOD
* FIELDS
* PARAMETERS


=============================

Database application ===> Web application
JSE --> Java Std Edition
JEE --> Java Enterprise Edition
--> build heterogenoius applications like web, enteripse applications

Web application development:
* Web Server
* Servlet Container / Web Container / Servlet engine

Servlet engine: Tomcat / Jetty / Netty ....

WebSphere [ Tomcat engine]
JBOSS [ Tomcat engine]
Apache [ Netty / Jetty / Tomcat]
GlassFish [Jetty / Tomcat...]

jar --> Java Archive
war --> web archive

```
database.war
    |
    WEB-INF
     |
        classes
            |
            pkg
                ....class
                ...class
                ...class
        web.xml
    index.html
    style.css
```

web.xml ---> Deployment descriptor

Servlet --> java server side code

```
public class RegisterServlet extends HttpServlet {
    // HTTP GET method
    void doGet(HttpServletRequest req, HttpServletResponse res) {

    }
    // HTTP POST method
    void doPut(HttpServletRequest req, HttpServletResponse res) {

    }
}

public class LoginServlet extends HttpServlet {
    // HTTP GET method
    void doGet(HttpServletRequest req, HttpServletResponse res) {

    }
    // HTTP POST method
    void doPut(HttpServletRequest req, HttpServletResponse res) {

    }
}


web.xml
<servlet>
    <servlet-name>Reg</servlet-name>
    <servlet-class>pkg.RegisterServlet</servlet-class>
</servlet>
<servlet>
    <servlet-name>login</servlet-name>
    <servlet-class>pkg.LoginServlet</servlet-class>
</servlet>

<servlet-mappin>
      <servlet-name>Reg</servlet-name>
      <url-pattern>/register</url-pattern>
</serlvet-mapping>
<servlet-mappin>
      <servlet-name>login</servlet-name>
      <url-pattern>/login</url-pattern>
</serlvet-mapping>
```

HttpServletRequest --> encapsulates all data from client : Form data / browser / os
HttpServletResponse --> used to write response back to client


New Apis: we can use annotations instead of web.xml for deployment descriptor

mvn compile

mvn package
mvn jetty:run
http://localhost:8080/


Address bar and hyperlink are GET request
default FORM method is also GET

mvn jetty:run
    * mvn compile
    * starting the jetty server
    * war file is built and deployed on jetty

mvn clean compile test package jetty:run


MVC architectural pattern:
M --> model : business data and logic
V -> View is for presentation: HTML, CSS, JS and JSP pages
C --> Controller: application logic --> Servlet

SSR or CSR
Server Side Rendering: data to presentation is done on server; presentation page [HTML] is sent to client
JSP / thymeleaf as templates for SSR

Client side rendering: data is converted into various formats like XML / JSON and sent to client.
Client consumes this and creates presentation
Good part: Android / Swift / Flutter/ TV / Web-React / Angular/ Svelte

---
HTTP protocol is a stateless protocol: doesn't keep track of conversational state of client.
Session Tracking: ability to web application to track conversational state of client.

HttpSession API: Servlet api for Session Tracking

cookie

Servlet/JSP












