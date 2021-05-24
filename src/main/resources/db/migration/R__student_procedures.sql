CREATE OR REPLACE FUNCTION students_group(IN student_group_name varchar) RETURNS 
TABLE(student_id int, student_name varchar, student_surname varchar, student_groupname varchar,
		student_diploma_num int, school_diploma_year int) AS '
	select student.id, student.name, student.surname, student.group_name, 
	student.school_diploma_num, school_diploma."year" 
	from student 
	left join school_diploma on student.school_diploma_num = school_diploma.number
	where student.group_name=student_group_name
' LANGUAGE SQL;

CREATE OR REPLACE FUNCTION student_find_by_id(IN student_id int) RETURNS 
TABLE(student_id int, student_name varchar, student_surname varchar, student_groupname varchar,
		student_diploma_num int, school_diploma_year int) AS '
	select student.id, student.name, student.surname, student.group_name, 
	student.school_diploma_num, school_diploma."year" from student 
	left join school_diploma on student.school_diploma_num = school_diploma.number
	where student.id=student_id
' LANGUAGE SQL;

CREATE OR REPLACE PROCEDURE public.delete_student(IN student_diploma_id int)
LANGUAGE 'sql'
AS $BODY$
delete from school_diploma where number= student_diploma_id
$BODY$;

CREATE OR REPLACE PROCEDURE public.insert_diploma(IN diploma_num int, IN diploma_year int)
LANGUAGE 'sql'
AS $BODY$
insert into school_diploma values( diploma_num , diploma_year )
on conflict(number) do update set year=diploma_year, number=diploma_num
$BODY$;

CREATE OR REPLACE PROCEDURE public.insert_student(IN student_name varchar, IN student_surname varchar,
	IN student_group varchar, IN student_diploma int)
LANGUAGE 'sql'
AS $BODY$
insert into student (name, surname, group_name, school_diploma_num) 
values (student_name, student_surname, student_group, student_diploma )
$BODY$;

CREATE OR REPLACE PROCEDURE public.update_student(IN student_name varchar, IN student_surname varchar,
	IN student_group varchar, IN student_diploma int, IN student_id int)
LANGUAGE 'sql'
AS $BODY$
update student set name=student_name, surname=student_surname, group_name=student_group, school_diploma_num=student_diploma 
where id=student_id
$BODY$;