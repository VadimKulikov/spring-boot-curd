CREATE OR REPLACE PROCEDURE public.insert_faculty(IN faculty_name character varying)
LANGUAGE 'sql'
AS $BODY$
insert into faculty (name) values (faculty_name)
$BODY$;

CREATE OR REPLACE PROCEDURE public.update_faculty(IN faculty_name character varying, IN faculty_id integer)
LANGUAGE 'sql'
AS $BODY$
update faculty set name=faculty_name where id=faculty_id
$BODY$;

CREATE OR REPLACE PROCEDURE public.delete_faculty(IN faculty_id integer)
LANGUAGE 'sql'
AS $BODY$
delete from faculty where id=faculty_id
$BODY$;



CREATE OR REPLACE FUNCTION faculty_list() RETURNS TABLE(faculty_id int,faculty_name varchar, department_abbr varchar) AS '
    select faculty.id, faculty.name, department.abbr 
	from faculty
	left join department on faculty.id=department.faculty_id 
	order by faculty.id;
' LANGUAGE SQL;

CREATE OR REPLACE FUNCTION faculty_by_id(IN fac_id integer) RETURNS TABLE(faculty_id int,faculty_name varchar) AS '
	select * from faculty where id=fac_id
' LANGUAGE SQL;