CREATE OR REPLACE FUNCTION department_list_by_page(IN page_size integer, IN page_offset integer) RETURNS 
TABLE(abbr varchar,name varchar, faculty_id int, faculty_name varchar) AS '
	select department.abbr, department.name, faculty.id, faculty."name" as faculty_name
 	from department 
	left join faculty on department.faculty_id = faculty.id 
	order by faculty."name" limit page_size offset page_offset
' LANGUAGE SQL;

CREATE OR REPLACE FUNCTION department_list() RETURNS 
TABLE(abbr varchar,name varchar, faculty_id int, faculty_name varchar) AS '
	select department.abbr, department.name, faculty.id, faculty."name" as faculty_name
 	from department 
	left join faculty on department.faculty_id = faculty.id 
	order by faculty."name"
' LANGUAGE SQL;

CREATE OR REPLACE PROCEDURE public.delete_department(IN department_id varchar)
LANGUAGE 'sql'
AS $BODY$
delete from department where abbr=department_id
$BODY$;

CREATE OR REPLACE PROCEDURE public.insert_department(IN dep_abbr character varying, IN dep_faculty_id integer
	, IN dep_name character varying)
LANGUAGE 'sql'
AS $BODY$
insert into department (abbr, faculty_id, name) values (dep_abbr, dep_faculty_id, dep_name) 
on conflict (abbr) do update set faculty_id=dep_faculty_id, name=dep_name
$BODY$;

CREATE OR REPLACE FUNCTION department_find_by_id(IN dep_id varchar) RETURNS 
TABLE(abbr varchar,name varchar, faculty_id int, faculty_name varchar) AS '
	select department.abbr, department.name,faculty.id, faculty."name" as faculty_name 
	from department 
	left join faculty on department.faculty_id = faculty.id 
	where department.abbr=dep_id
' LANGUAGE SQL;