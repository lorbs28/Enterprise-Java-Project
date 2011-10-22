Enterprise Java
---------------
Project specs are subject to change.  The noted project specs are the old written specs.  Project may not be according to the project specs per request of the instructor.


-----

Module 1:

Testing and Security

1. Concepts

This project will focus on demonstrating competency in several parts of the Java programming language. These will include, but are not limited to:
Incorporating unit testing within a development project
Incorporating software development security concepts within a development project 
2. Project Overview

This project will include a package of classes written by the author. The project consists of a command line application which will process a series of orders based on file input.  The output will be written to a file. The deliverables will be accompanied with unit tests and employ security concepts (including a policy)
3. Specifications

3.1 Input Files

Input files: orderin.txt can be downloaded from here.
3.2 Output File

The orderout.txt file will contain the generated order information.  An example of one order is below:
Customer: Doe
Item Ordered: SomethingOrOther
Unit Price: $13.05
Quantity Ordered: 1
Total: $13.05

 
3.3 Exception Handling

All places in this application which could encounter problems should use exception handling. All exceptions that happen during the running of this application must be caught and the stack trace displayed to the command window. One exception that should be tested is if the input files are not found on the disk. This can be easily tested by entering a file name that is not on your computer.
3.4 Project Requirements

3.4.1 Core Functionality

Create a class within a package to contain order information.
Create an ini file that will be loaded by the application. It should be loaded into a properties instance and stored in the System properties instance. Currently, it should have only two entries: filenames for the input and output files
The class should have instance variables for:  
customer name.
order number.
extended price.
item name 
quantity of items ordered 
unit price of the item.

The class will have a method that will compute the extended price for this order. This is calculated by multiplying the quantity by the unit price and then setting the extended price.
The class'  toString() method will be formatted as following:

Customer: Jones
Item Ordered: Widget
Unit Price: $12.99
Quantity Ordered: 7
Total: $90.93
Create another class that will provide for the processing of orders.
The class will eventually read and store the orderin.txt file
The class will contain a table-like instance variable that will store individual order instances; it should be keyed by order number; we will henceforth refer to this as the Customer Ledger
The class will read the orderin.txt file (see step 7) and create individial order instances which, in turn, are stored in the tabular instance variable (see step 8)
This class will process each order and write them out, in a formatted fashion (see step 4), to a file.
You will need to launch the class that will process the orders. You can satisfy this by using a dedicated driver class or by incorporating the main() into the order processing class.
3.4.2 Testing requirements

Each class in this project will have corresponding test classes.
Each method (excluding constructors) should have corresponding tests; getter/setter method pairs can be represented by a single test method which tests both getter and setter at the same time 
You should create a Test Suite for your tests
You will need to invoke the Suite, error-free, to satisfy your testing requirements.

3.4.3 Security requirements

Security concepts must be embedded within the core functionality of the deliverables.
Specifically, input/output checks and exception handling should be in place.
The application must be secured with a policy file enforced using the SecurityManager.
You will need to execute your application within the context of your enforced policy file to satisfy your security requirements.
 
4. Final housekeeping

Ensure that your project adheres to the global requirements as specified in the Project Rubrics. Remember, the Rubrics apply to all compilable code (to include core functionality and testing functionality)

------

Module 2:

Parallel Programming

1. Concepts

The concepts we will be exploring with this project include:
Refactoring code
parallel programming
Creating tests for parallel apps 
Logging

2. Project Overview

This project will be continue the introduction of parallel programming concepts. You will be refactoring Project 1 to enable file additions to be performed in parallel.
3. Specifications

3.1 Property file

Edit the property file to contain an additional entry to specify whether the application will be mutithreaded or not
This entry will need to be checked in the application from here on when the app needs to determine which type of code to invoke: sequential or concurrent code
Add another entry to the property file  to specify a log file for the app

3.2 Output file threading

Create a new Runnable class that will write information to a file in parallel. Remember, when building your class, only place method calls in the run() so as to support unit testing of your method functionality. The ONLY thing that should be in this class is a run() and a method to write to a file. The class should be completely generic. This class shall NOT include dependencies to any other classes in this project.
Refactor Project 1 to allow for additions to the output file to be written in parallel. You should use your new Runnable class for this task. You should not remove the old code. Rather, you should add code that can only be invoked by evaluating the properties entry specifying sequential or concurrent invocations. For instance, if your properties file states that your app is concurrent, then your app will instantiate your Runnable class, pass it appropriate data, and then run it. However, if your properties file is set for sequential operation, then you should use Project 1's method for writing the output file.
 
3.3 Customer Ledger scanning

Create a new Runnable class that will continually check for changes to the Customer Ledger. Remember, when building your class, only place method calls in the run() so as to support unit testing of your method functionality. The ONLY thing that should be in this class is a run() and a method to check the size of your ledger. The class should be completely generic. This class shall NOT include dependencies to any other classes in this project.

Refactor Project 1 to start a new thread for our runnable class when the app starts up
When if changes to the ledger are identified, then a log entry should be written. For instance, if you detect that the Customer Ledger now has an additional entry, you should create a string that includes the time of day (new Date().toString())  and something stating that "an entry was added to the ledger" or something to that effect.  
 

3.4.2 Testing requirements

Each class in this project will have corresponding test classes. 
Each method (excluding constructors) should have corresponding tests; getter/setter method pairs can be represented by a single test method which tests both getter and setter at the same time  
You should create a Test Suite for your tests. 
You will need to invoke the Suite, error-free, to satisfy your testing requirements.

3.4.3 Security requirements

Security concepts must be embedded within the core functionality of the deliverables. 
Specifically, logging of CRUD operations, input/output checks and exception handling should be in place. 
The application must be secured with a policy file enforced using the SecurityManager. 
You will need to execute your application within the context of your enforced policy file to satisfy your security requirements.