show databases;
create database hexawareassignt1;
use hexawareassignt1;

create table user(userid int primary key, name varchar(255), email varchar(255) unique,password varchar(255), contactnumber varchar(20), address text);
create table courier(courierid int primary key, sendername varchar(255),senderaddress text,recievername varchar(255), receiveraddress text, weight decimal(5,2),status varchar(255), trackingnumber varchar(20) unique, delieverydate date);
create table courierservice(serviceid int primary key, servicename varchar(200),Cost decimal(8,2));
create table employee(employeeid int primary key, name varchar(255), emailaddress varchar(255) unique, contactnumber varchar(20),role varchar(25), salary decimal (10,2));
create table location(locationid int primary key, locationname varchar(100), address text);
create table payment(paymentid int primary key, courierid int, locationid int, amount decimal(10,2), paymentdate date, foreign key (courierid) references courier(courierid), foreign key (locationid) references location(locationid));
show tables;
use hexawareassignt1;
insert into user(userid, name, email, password, contactnumber, address) values (1,'saurabh','saurabh@mail.com','1234', 7720975819, 'kankavli'),(2,'mukund','mukund@mail.com','147',9856421764,'latur'),(3,'ayush','ayush@mail.com','258',7845698231,'ichalkaranji'),(4,'yash','yash@mail.com','986',8624802417,'chandgad');
select * from user;
insert into courier(courierid, sendername,senderaddress, recievername, receiveraddress,weight,status, trackingnumber, delieverydate) values (1,'saurabh','kankavli','om','kagal',5.1,'delivered','abc123','2025-03-02'),(2,'yash','chandgad','abhi','nipani',4.0,'shipped','abc456','2025-04-15'),(3,'mukund','latur','sangram','kudal',7.0,'delivered','abc789','2025-02-28');
insert into courierservice(serviceid,servicename,cost) values (1,'express service','150'),(2,'same-day','290'),(3,'international', '650');
insert into employee(employeeid, name,emailaddress, contactnumber,role,salary) values (1, 'chinmay','chinmay@mail.com', '9632547889','delivery man',20000.0),(2,'shreyash','shreyash@mail.com','6365787491','packer',27000.10),(3,'shubham','shubham@mail.com','7845963215','accountant',37000.65),(4,'shivam','shivam@mail.com','9874512365','Team Lead',77000.25);
insert into location(locationid, locationname, address) values (1,'kankavli','kalmath'),(2,'latur','Shivroad'),(3,'chandgad','mangaon');
insert into payment(paymentid, courierid,locationid,amount,paymentdate) values (1,1,1,150,'2025-02-22'),(2,3,2,290,'2025-02-28'),(3,2,3,150,'2025-04-09');
select * from courier;
select * from courierservice;

select * from employee;
select * from location;
select * from payment;

select * from user;
select * from courier where recievername='om';
select * from courier;
select * from courier where status='shipped';
select * from courier where status='delivered';
select * from courier join payment on courier.courierid= payment.courierid where amount>150;
insert into user (userid, name, email, password, contactnumber, address) values (5, 'John Doe', 'john@example.com', 'password123', '1234567890', 'New York'),
(6, 'Jane Doe', 'jane@example.com', 'password123', '9876543210', 'Los Angeles'),(7, 'Bob Smith', 'bob@example.com', 'password123', '5551234567', 'Chicago');
insert into courier (courierid, sendername, senderaddress, recievername, receiveraddress, weight, status, trackingnumber, delieverydate)
values(4, 'John Doe', 'New York', 'Jane Doe', 'Los Angeles', 5.0, 'delivered', 'BGT126', '2025-01-01'),
(5, 'Jane Doe', 'Los Angeles', 'Bob Smith', 'Chicago', 3.0, 'shipped', 'DEF456', '2025-01-05'),(6, 'Bob Smith', 'Chicago', 'John Doe', 'New York', 2.0, 'delivered', 'GHI789', '2025-01-10'),
(7, 'Alice Johnson', 'Houston', 'Mike Brown', 'Seattle', 4.0, 'shipped', 'JKL012', '2025-01-15'),(8, 'Mike Brown', 'Seattle', 'Alice Johnson', 'Houston', 6.0, 'delivered', 'MNO345', '2025-01-20'),
(9, 'Sarah Taylor', 'Denver', 'Tom Johnson', 'Dallas', 7.0, 'shipped', 'PQR678', '2025-01-25'),(10, 'Jackson Brown', 'San Francisco', 'Ava Lee', 'Boston', 8.0, 'delivered', 'STU901', '2025-02-01');
insert into employee (employeeid, name, emailaddress, contactnumber, role, salary)
values (5, 'David Lee', 'david@example.com', '1234567890', 'delivery', 50000.0),(6, 'Emily Chen', 'emily@example.com', '9876543210', 'packer', 40000.0),
(7, 'Kevin White', 'kevin@example.com', '5551234567', 'accountant', 60000.0),(8, 'Olivia Martin', 'olivia@example.com', '1234567890', 'delivery', 55000.0);
insert into location (locationid, locationname, address)values(4, 'New York', '123 Main St'),(5, 'Los Angeles', '456 Elm St'),(6, 'Chicago', '789 Oak St');
insert into payment (paymentid, courierid, locationid, amount, paymentdate)values(4, 1, 1, 100.0, '2025-01-01'),(5, 2, 2, 200.0, '2025-01-05'),
(6, 3, 3, 300.0, '2025-01-10'),(7, 4, 1, 400.0, '2025-01-15'),(8, 5, 2, 500.0, '2025-01-20'),(9, 6, 3, 600.0, '2025-01-25');
/*
--task2
*/
select * from user;
select courierid from courier where sendername='saurabh';
select * from courier;
select * from courier where sendername='yash';
select * from courier where courierid=5;
select * from courier where status='shipped';
select * from courier where delieverydate='2025-01-20';
select * from courier where status='delivered';
select sendername, count(courierid) as total_package from courier group by courierid;

select * from courier where weight between 7 and 15;
select * from employee where name like 'John%';
select * from courier join payment on courier.courierid=payment.courierid where amount>50;

/*
Task 3
*/
select e.name as employee_name, count(p.courierid) as total_couriers_handled from employee e left join  payment p ON e.employeeid = p.paymentid
group by e.employeeid, e.name;
select l.locationname, sum(p.amount) as total_revenue from location l left join payment p on l.locationid=p.locationid group by l.locationname,l.locationid;
select l.locationname, count(c.courierid) as total_package from location l left join courier c on l.locationid=c.receiveraddress group by l.locationid, l.locationname;
select c.courierid, c.sendername, c.recievername, 
       avg(DATEDIFF(c.delieverydate, p.paymentdate)) as avg_delivery_time_days
from courier c join payment p ON c.courierid = p.courierid
group by c.courierid, c.sendername, c.recievername
order by avg_delivery_time_days DESC LIMIT 1;
select l.locationname, SUM(p.amount) as total_payments
from location l join payment p on l.locationid = p.locationid
group by l.locationid, l.locationname having SUM(p.amount) < 500;

select l.locationname, sum(p.amount) from location l left join payment p on l.locationid=p.locationid group by l.locationid, l.locationname;

select c.courierid, c.sendername, c.recievername, SUM(p.amount) AS total_payments
from courier c join payment p ON c.courierid = p.courierid
where p.locationid = 1  group by c.courierid, c.sendername, c.recievername
having SUM(p.amount) > 300;

select c.courierid, c.sendername, c.recievername, SUM(p.amount) AS total_payments
from courier c join payment p ON c.courierid = p.courierid
where p.paymentdate > '2025-01-15'
group by c.courierid, c.sendername, c.recievername having SUM(p.amount) > 300;

select l.locationname, SUM(p.amount) AS total_amount_received
from location l join payment p ON l.locationid = p.locationid
where p.paymentdate < '2025-02-01'  group by l.locationid, l.locationname
having SUM(p.amount) > 300;

select* from courier c left join payment p on c.courierid=p.courierid;
select * from payment p left join location l on p.locationid=l.locationid;
select * from payment p left join courier c on p.courierid=c.courierid
 left join location l on l.locationid=p.locationid;
 
select * from payment p right join courier c on p.courierid=c.courierid;
select c.courierid,c.sendername,c.recievername,SUM(p.amount) AS total_payments_received
from courier c left join payment p on c.courierid = p.courierid
group by c.courierid, c.sendername, c.recievername
order by c.courierid;

select p.paymentid,p.courierid,c.sendername,c.recievername,l.locationname,p.amount,p.paymentdate
from payment p join courier c ON p.courierid = c.courierid
join location l ON p.locationid = l.locationid
where p.paymentdate = '2025-02-22'
order by p.paymentid;

select * from courier c inner join payment p on c.courierid=p.courierid ;
select * from payment p left join location l on p.locationid=l.locationid where p.locationid=2;
select c.receiveraddress, count(p.paymentid) from payment p join courier c on p.courierid=c.courierid group by p.courierid;
select p.paymentid,p.amount from payment p left join courier c on p.courierid=p.courierid where p.paymentdate between '2025-01-20' and '2025-01-22' ;

alter table user rename as users;
select * from users inner join courier c on users.name=c.sendername;
select c.*, s.servicename 
from courier c 
left join courierservice s on c.courierid = s.serviceid; 
select e.employeeid, e.name, p.paymentid, p.amount from employee e 
left join payment p on e.employeeid = p.paymentid 
union select e.employeeid, e.name, p.paymentid, p.amount from employee e 
right join payment p on e.employeeid = p.paymentid;
select u.name, s.servicename from users u 
cross join courierservice s;
select e.name as employee, l.locationname from employee e 
cross join location l;
select c.courierid, c.sendername, c.senderaddress 
from courier c;
select c.courierid, c.recievername, c.receiveraddress 
from courier c;
select c.*, s.servicename, s.cost from courier c 
left join courierservice s on c.courierid = s.serviceid; 
select employeeid, count(*) as courier_count from courier 
group by employeeid;
select l.locationname, sum(p.amount) as total_amount from payment p 
join location l on p.locationid = l.locationid group by l.locationname;
select * from courier where sendername = 'yash';
select role, count(*) as count from employee 
group by role having count(*) > 1;
select p.* from payment p 
join courier c on p.courierid = c.courierid 
where c.senderaddress = 'kankavli';
select * from courier 
where senderaddress = 'kankavli';

select employeeid, count(*) from courier group by employeeid;
select c.courierid from courier c 
join payment p on c.courierid = p.courierid 
join courierservice s on c.courierid = s.serviceid where p.amount > s.cost;

select * from courier where weight > (select avg(weight) from courier);
select name from employee where salary > (select avg(salary) from employee);
select sum(cost) as total_cost from courierservice 
where cost < (select max(cost) from courierservice);
select * from courier where courierid in (select courierid from payment);
select locationid from payment where amount = (select max(amount) from payment);
select * from courier where weight > all (select weight from courier where sendername = 'saurabh');
alter table users rename as user;
show tables;
alter table courier rename as couriers;



