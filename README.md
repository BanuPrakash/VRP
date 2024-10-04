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

req.getSession(); creates a session if not exist; if exists get the session

get.getSession(false); doesn't create a session; if preset get the existing session

invalidate(); // terminate the session

setMaxInactiveInterval(seconds); // timeout 

session.setAttribute(key , value)
session.removeAttribute(key);


Filter: are for interceptor pattern
They don't have main logic, but can be used along with main logic
* SecurityFilter
* Logging
* Profile
* Encrpt/decrypt
..

Spring Framework [ built on top this...]
mvn jetty:run -Djetty.http.port=8443

==================================================

Spring Framework
--> Lightweight container works of the concept of InversionOfControl
--> manage lifecycle of objects and wiring dependencies

Bean: any object managed by spring container


Metadata: XML or Annotation

```
    public interface EmployeeDao {
        void addEmployee(Employee e);
    }

    public class EmployeeDaoJdbcImpl implements EmployeeDao {
        public   void addEmployee(Employee e) {..}
    }

    public class EmployeeDaoMongoImpl implements EmployeeDao {
        public   void addEmployee(Employee e) {..}
    }

    public class AppService {
        EmployeeDao empDao; // loosely coupled
        UserDao userDao;

        public void setEmpDao(EmployeeDao edao) {
            this.empDao = edao;
        }

        public void setUdao(UserDao dao) {
            userDao = dao;
        }

        public void insert(Employee e) {
            this.empDao.addEmployee(e);
        }
    }


beans.xml
<beans>
    <bean id="jdbc" class="pkg.EmployeeDaoJdbcImpl" />
    <bean id="mongo" class="pkg.EmployeeDaoMongoImpl" />
    <bean id="service" class="pkg.AppService">
        <property name="empDao" ref="jdbc" />
        <property name="uDao" ref="null">
    </bean>
</beans>

service.setEmpDao(jdbc); // generated by Spring Container

ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

AppService ser = ctx.getBean("service", AppService.class);
ser.insert(e);
```

Annotation as metadata:

Spring Container scans for below annotations to create objects:
1) @Component
2) @Repository
3) @Service
4) @Configuration
5) @Controller
6) @RestController
7) @ControllerAdvice

```
 public interface EmployeeDao {
        void addEmployee(Employee e);
    }

    @Repository
    public class EmployeeDaoJdbcImpl implements EmployeeDao {
        public   void addEmployee(Employee e) {..}
    }

    @Service
    public class AppService {
        @Autowired
        EmployeeDao empDao; // loosely coupled
       

        public void insert(Employee e) {
            this.empDao.addEmployee(e);
        }
    }

ApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.scan("com.visa.prj");
ctx.refresh();

```

Spring Boot : Framework on top of Spring Framework.
Why Spring Boot?
* eliminates lot of boilerplate code
* highly opinated framework
1) if we are building web application
Spring boot configures Tomcat as default Servlet engine
creates a DispatcherServlet acts as FrontController [*]
Java <--> JSON ContentNegotiationHandler is provided by Jackson library
2) if we are building java <--> relational database 
Spring boot provides database Connection pool --> HikariCP


 SpringApplication.run(SpringdemoApplication.class, args); is same AS BELOW:
 ApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.scan("com.visa");
ctx.refresh();


@SpringBootApplication
* @ComponentScan --> com.visa.springdemo;
* @EnableAutoConfiguration --> highly opiniated config objects to be created
* @Configuration

Problem:
```
Field employeeDao in com.visa.springdemo.service.AppService required a single bean, but 2 were found:
	- employeeDaoJdbcImpl: 
	- employeeDaoMongoImpl:
```

Solution 1: using @Primary
```
@Repository
@Primary
public class EmployeeDaoMongoImpl implements EmployeeDao{

@Repository
public class EmployeeDaoJdbcImpl implements EmployeeDao{

```

Solution 2: using @Qualifier
```
@Repository
public class EmployeeDaoMongoImpl implements EmployeeDao{
@Repository
public class EmployeeDaoJdbcImpl implements EmployeeDao{

@Service
public class AppService {
    @Autowired
    @Qualifier("employeeDaoMongoImpl")
    private EmployeeDao employeeDao;
```

Solution 3: using @Profile

```
@Repository
@Profile("dev")
public class EmployeeDaoJdbcImpl implements EmployeeDao{
@Repository
@Profile("prod")
public class EmployeeDaoMongoImpl implements EmployeeDao{

@Service
public class AppService {
    @Autowired
    private EmployeeDao employeeDao;

resources
application.properties
spring.profiles.active=prod

or command line argument << more precidence>>
Edit Configuration
Active Profile: dev

```

Solution 4: @ConditionalOnMissingBean

```
@Repository
@ConditionalOnMissingBean(name="employeeDaoJdbcImpl")
public class EmployeeDaoMongoImpl implements EmployeeDao{
```

Factory Method --> @Bean
3rd party classes

ORM: Object Relational Mapping

Object in Python / C# / Java ...  <---> Relatational database table
fields <--> columns of table

Once mapping is done : DDL and DML operations are generated by ORM frameworks

ORM Frameworks:
1) Hibernate --> JBoss --> RedHat
2) Toplink --> Oracle
3) KODO --> BEA --> Oracle
4) JDO --> Sun MS --> Oracle
5) OpenJPA --> Apache
6) EclipseLink --> Eclipse

JPA : Specification for ORM Java Persistence API

```

@Configuration
public class AppConfig {

    // factory method
    @Bean
    DataSource getDataSource() throws  Exception{
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/VRP" );
        cpds.setUser("root");
        cpds.setPassword("Welcome123");
        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(3);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);

        return cpds;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean emf(DataSource ds) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(ds);
        emf.setJpaVendor(new HibernateJpaVendor());
        emf.setPackagesToScan("com.visa.prj.entity");
        .. // hbm2ddl
        return emf;
    }
}

@Repository
public class EmployeeDaoJdbcImpl implements EmployeeDao{
    @PersistenceContext
    EntityManager em;
    @Override
    public void addEmployee(Employee e) {
        em.persist(e);
    }
}
```


Spring Data JPA
New Project orderapp
Java , Maven
depdendency: mysql, lombok, spring data jpa

spring.jpa.hibernate.ddl-auto=update
* Map class to existing table
* if required alter the table
* if table is not present - create

spring.jpa.hibernate.ddl-auto=create
* application starts --> tables are created
* application stops --> tables are destroyed

spring.jpa.hibernate.ddl-auto=verify
* map class to existing table; don't alow create / alter


```

Without cascade:
1 order has 4 items;
orderRepo.save(order);
itemRepo.save(i1);
itemRepo.save(i2);
itemRepo.save(i3);
itemRepo.save(i4);

To Delete
orderRepo.delete(order);
itemRepo.delete(i1);
itemRepo.delete(i2);
itemRepo.delete(i3);
itemRepo.delete(i4);

WithCascade:
 @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_fk")
    List<LineItem> items = new ArrayList<>();

1 order has 4 items;
orderRepo.save(order); --> saves items also

orderRepo.delete(order); --> deletes items also

Default is LAZY fetcing:
List<Order> orders = orderRepo.findAll(); // select * from orders;
for(Order order : orders) {
    List<LineItems> = order.getLineItem(); // select * from line_items where order_fk = ?
}

===
   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_fk")
    List<LineItem> items = new ArrayList<>();

List<Order> orders = orderRepo.findAll(); // select * from orders; --> fetchs line_items of each order
```
===========================

Order data looks like:

```
    {
        "customer": {
            "email": "Uma@visa.com"
        },
        "items": [
            {"product": {"id": 2}, "qty": 1},
            {"product": {"id": 1}, "qty" : 2}
        ]
    }

```

Spring uses ByteBuddy / Java Assist / CGLib libraries

SCALAR Values;

select c.fname, c.email, o.order_date, o.total  from orders o inner join customers c on c.email = o.customer_fk;

================

one-to-one relationship

Employee
    email
    firstName
    lastname

Laptop
    serialNo
    make
    size

employees
email           | fname     | lname
a@visa.com          Asha        Rao
b@visa.com          Bharthi     Sharma

laptop
serial_no | make | size | employee_fk
3434        Mac     13      a@visa.com  
6343443     Intel   15

================

students
sid | name      |start_date
1       Asha

course
cid | name      |start_date
1       Spring      
2       React

students_course
cid | sid | start_date | end_date | grade
1     1
2     1

====================

Aspect Oriented Programming (AOP)

* lot of Cross-cutting concerns which leads to code tangling and code scattering
```
public void transferFunds(Account fromAcc, Account toAcc, double amt) {
    // start time
    if(securityContext.getPriniciple().getAuthorities() === WRITE_ROLE) {
        log.info("started transaction");
        Connection con = ds.getConnection();
        con.setAutoCommit(false); // begin Transaction
        log.info("transaction started");
        updatefromAcc(amt); //actual logic
        log.info("amount debitted ...");
        updatetoAcc(amt); // actual logic
        ...

    // end_time
    }
}
```

Aspect: code which is not a part of main logic,but can be used along with main logic. generally a cross cutting concern
Examples: Transaction, Security, Profile, Log, ExceptionHandler

JoinPoint: place in your code where aspect can be weaved
method and exception

PointCut: selected joinpoint

Advice: before, after, around, afterReturning, afterThrowing

@Around( "@annotation(Transactional)")

```
  @Around("execution(* com.visa.prj.orderapp.service.*.*(..))")
    public Object doTransaction(ProceedingJoinPoint pjp) throws Throwable {
        Transaction tx = ctx.getTransaction();

        try {
            pjp.proceed();
            tx.commit();
        } catch(Exception ex) {
            tx.rollback();
        }

    }

```
New Spring boot project:
lombok, mysql, jpa, web

spring.datasource.url=jdbc:mysql://localhost:3306/TICKET_TRACKER?createDatabaseIfNotExist=true

```
Ticket tracker application:

Employee
email

Project 
pid <<AUTO INCREMENT>> | name | client

Ticket
ticket_id | raised_by <<FK>> | project_fk | issue | raised_date | resolved_by <<FK to emp>> | resolved_date | resolve_text

1) populate projects and employees
2) raise a ticket

ticket_id | raised_by <<FK>> | project_fk | issue | raised_date  | 
24          george@visa.com     534         MAven   12-SEP-2024 4:50:20
other fields will be null

3) resolve a ticket ==> try DIRTY CHECKING
pick a ticket based on ticket_id
set resolved_by, resolved_date, resolve_text

```
SQL:
```
SELECT * FROM `reservations` WHERE `vehicle_id`=1 AND (`start_date`>".$start_date." AND `end_date`<".$end_date.") OR (`start_date`<".$start_date." AND `end_date`>".$end_date.") OR (`start_date<".$end_date." AND `end_date`>".$end_date.") OR (`start_date`<".$start_date." AND `end_date`>".$start_date.")
```
=========

Building RESTful WS --> Client side Rendering
Spring Web MVC module

```
   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
```
* Provides embedded Tomcat Servlet Container
* provided DispatcherServlet with URL-pattern as *
* HandlerMapping: map urls to @Controller or @RestController classes
* ContentNegotiationHandler for Java<--->JSON in the form of Jackson library
Java<--->JSON can be done using :Jackson, jettison, GSON, Moxy,..

RESTful WS: REpresentational State Transfer
* Resource: anything present on server like files/ database / printer
* representation: state of the resource at given point of time
* ContentNegotiationHandler:
    representation to various format based on client request
    HTTP header
    Accept: application/json
    Accept: text/xml

-----
Resources are identified by URLs and actions by HTTP methods

```
* GET http://server.com/api/products
get all products 

* GET http://server.com/api/products/4
* Path parameter [/] is generally used to get by PK
get product whose id is 4

* GET http://server.com/api/customers/banu@gmail.com/orders

* GET http://server.com/api/products?page=1&limit=20
* GET http://server.com/api/products?low=1000&high=20000
use Query parameter [?] --> sub set [ filter]
```
-----

```
* POST http://server.com/api/products
Content-type:application/json

payload:
{
    "name":"MS Mouse",
    "price": 2323,
    "quantity": 3434
}
```
Avoid this end-point
* DELETE http://server.com/api/products/3
delete a product whose id is "4"

```
Update 
* PUT http://server.com/api/products/3
Content-type:application/json

payload:
{
    "price": 2323
}

Optionally we can use PATCH instead of PUT for Update
```

@Controller ---> sends rendered pages
@RestController --> sends json/xml

```
@RestController
@RequestMapping("api/products")
public class ProductController {
    @GetMapping()
    public List<Product> getProducts() {
        service.getProducts();
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product p) {
        service.addProduct(p);
    }
}


```

PostMAN is a REST client.
https://jsonpatch.com/

JSON-PATCH:
```
PATCH http://localhost:8080/api/employees/123
Content-Type: application/json-patch+json
Accept: application/json

[
  {"op": "replace", "path":"/title", "value":  "Team lead"},
  {"op": "remove", "path" :  "/personal/phone"},
  {"op":  "add", "path" : "/personal/email", "value": "smitha@visa.com"},
  {"op": "add", "path":  "/programmingSkills/1", "value":  "Spring Boot"}
]


{"id":123,
"title":"Team lead",
"personal":{"firstName":"Smitha","lastName":"Patil","email":"smitha@visa.com"},
"programmingSkills":["Java","Spring Boot","Python"]}

employee = mapper.treeToValue(target, Employee.class);
employeeService.updateEmployee(employee);

updateEmployee(Employee e) {
    employeeRepo.save(e);
}

```
//@WebMvcTest(ProductController.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = ShopappApplication.class)

==============
Bi-Directional mapping:
```
public class Owner {

    @OneToMany(mappedBy="owner, fetch=FetchType.EAGER)
    private Set<Pet> pets = new LinkedHashSet<>();
}


public class Pet {

    @ManyToOne
    @JoinColumn(name="owner_fk")
    private Owner owner;
}


Owner owner = ownerRepo.findById(1);
```

@ControllerAdvice : AOP
this acts like a GlobalExceptionHandler which gets called whenever an exception is propagated from @Controller and @RestController

```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
 @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Valid  Product p) {
        return  service.addProduct(p);
    }

```

===

MethodArgumentNotValidException: 
 3 errors: 
 default message [Name is required]] 
 default message [Quantity 0 must be greater than 1]] 
 default message [Price -100.0 must be greater than 10]] 

BindingResult is an interface that represents the result of binding data to a Java object. It's commonly used in conjunction with the @Valid annotation to validate user input and handle validation errors in your controller methods.

AAA ==> Assemble Action Assert

Testing module for web by default adds:
1) JUnit as Unit testing framework / TestNG alternate
2) Mockito as Mocking library to test in isolation / EqasyMock, JMock, ...
3) Hamcrest --> matchers
4) jsonPath https://jsonpath.com/


@WebMvcTest(ProductController.class)
creates a TestDispatcherServlet instead of DispatcherServlet
creates web context where @Controller and @RestController can execute
No Spring Container intialzed with beans 
only ProductController is created as bean


Static imports:
```
public class Sample {
    public static double PI = 3.14159;
    public static void doTask() {

    }
}

import static pkg.Sample.doTask;
import static pkg.Sample.PI;
```

Employee resolved_by;  // wrong
Employee resolvedBy; // correct

private final static double MAX_AGE = 100;  // correct

===========================================

Documentation : RESTApi
* RAML --> write YAML REST APi Modeling Language
* OpenAPI -> Swagger

http://localhost:8080/v3/api-docs

http://localhost:8080/swagger-ui/index.html

https://springdoc.org

=====================

Caching:
* Client Side Caching
* Middleware Caching --> Web application level
* Backend caching --> JPA second level cache [Swarm Cache / EHCache]

Client Side Caching:
Cache-control header
Cache-Control: max-age=604800 <<Sec>>

ETag

```
 @Version
 @Column(name = "ver")
 int version;

 for every JPA update --> it increments ver column value
 +----+----------------+--------+----------+
| id | name           | price  | quantity | ver
+----+----------------+--------+----------+ 
|  1 | iPhone 16      | 980000 |       94 |  0
|  2 | Macbook Pro    | 259990 |       99 |  0

User 1:
    gets product 
    2 | Macbook Pro    | 259990 |       99 |  0

    buys 2 laptops
    without version:
    update products set qty = qty - 2 where id = 2

    With version
      update products set qty = qty - 2, ver = ver + 1 where id = 2 and ver = 0
User 2:
     gets product 
    2 | Macbook Pro    | 259990 |       99 |  0

    buys 9 laptops
    without version:
    update products set qty = qty - 2 where id = 2

     With version
      update products set qty = qty - 9, ver = ver + 1 where id = 2 and ver = 0
```


@Cacheable --> Cache
@CachePut --> update the cache
@CacheEvict --> remove from cache

https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions

docker run -d --name=some-redis -p 6379:6379 redis

redis-commander:
NodeJs has to be installed

npx redis-commander

============

Get permission and install  NodeJS and VS Code.
Node version is 20.x.x

npm config set proxy http://username:password@userproxy.visa.com:80
npm config set https-proxy http://username:password@userproxy.visa.com:80

======================================
https://martinfowler.com/articles/richardsonMaturityModel.html

Level 3 RESTful WS.
HATEOAS: Hypermedia As The Engine Of Application State

WebMvcLinkBuilder:

RepresentationModel
* EntityModel
* CollectionModel

Spring Data Rest
Spring Data REST builds on top of Spring Data repositories, analyzes your application's domain model and exposes hypermedia-driven HTTP resources
* No need for Services and Controller and RestController classes

====

dependecies:
lombok, mysql, jpa, web, spring-data-rest <<Rest Repositories>>






