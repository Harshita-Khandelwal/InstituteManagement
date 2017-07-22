# InstituteManagement
Institute Management System is developed to help the users to perform all the activities related to the institute like students entry, creating course and batch, and many more. It is developed using java Swing, JDBC, jsp, servlet, html and CSS. MySQL database is used.

There are two versions of this system which can be used simultaneously:
1. One is the desktop version developed using java Swing.
2. Another is a web application developed using jsp, servlet, html and css. 

Working:
1. User authentication with username and password is done to access the system.
2. There are 3 types of users defined for this system. And each user has their specific role to perform and thus personalized interfaces are displayed on successful login.

2.1 Admin: 
Various roles performed by admin are
a) Create new student entry. View student details by searching all entries or according to id, name, course or batch. Student details are displayed in a tabular format.On selecting a row of student detail and clicking on edit button, a dialog box for editing student information is opened.
b) Similarly creating new query, view/ edit query details. View can be opened either by searching all entries or by id, name or course. Queries are basically those student information who have enrolled for a course but till now have not joined a batch in the institute.
c) Export query feature enables the query to be converted into a student entry.
d) Insert/ view/ edit course details like, name, fees and duration.
e) Insert/ view/ edit batch details for a course type. Batch details include batch timings, starting date and faculty name.
f) Create new user - admin/ manager or a accounts manager
g) Deposit fee according to student id. Student id tells the course in which the student has enrolled and the fees amount that is due for the same. All the mathematical validation required for depositing fees are applied. View the fee details by searching all entries or according to id, name, course or batch. Edit option is provided to select the entry from the tabular view and depositing the fees from this page.
h) Create new faculty and assign a course type to him/her. View/edit the faculty details by searching all entries or according to id, name, course or batch.

2.2 Manager: 
Roles performed are
a) Create new student entry. View/ edit student details by searching all entries or according to id, name, course or batch. 
b) Similarly creating new query, view/ edit/ export query details. View can be opened either by searching all entries or by id, name or course.
c) View all Course details
d) View batch details. Either all entries or course specific
e) View Fee details by searching all entries or according to id, name, course or batch. 
f) View faculty details by searching all entries or according to id, name, course or batch.

2.3 Accounts Manager:
Roles performed are
a) Deposit fee according to student id. Student id tells the course in which the student has enrolled and the fees amount that is due for the same. All the mathematical validation required for depositing fees are applied. 
b) View the fee details by searching all entries or according to id, name, course or batch. 
c) Edit option is provided to select the entry from the tabular view and depositing the fees from this page.

3) All the required validations like no null, max length or numeric validations are applied on each of the above form for creaing or editing entries


Procedure to run:
1. Create the database using instituteDatabase.sql file.
2. Make sure the sql connector file is placed in the classpath for desktop application. While for the web application place it in WEB-INF/lib folder.
3. Run TheInstituteLoginGUI class to access the desktop application
4. Run tomcat and access the web application successfully.

