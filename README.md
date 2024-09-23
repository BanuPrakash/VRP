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


