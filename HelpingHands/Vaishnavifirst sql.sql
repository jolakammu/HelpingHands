create table vaishnavi.students
(
	student_id	int not null, 
	student_lastname varchar(50) not null,
	student_firstname varchar(50) not null,
	student_middlename varchar(50),
	student_birthdate date not null,
	student_race varchar(10) not null
)
	
	
	

create schema vaishnavi;

select * from vaishnavi.students 
where student_firstname = 'Gayathri'