CREATE OR REPLACE FUNCTION group_list_by_page(IN page_size integer, IN page_offset integer) RETURNS 
TABLE(group_name varchar, group_year int, dept_name varchar, dept_fullname varchar) AS '
	select groups.name , groups."year" , groups.department_name, department.name as depname 
	from groups 
	left join department on department_name = department.abbr 
	order by department_name limit page_size offset page_offset
' LANGUAGE SQL;

CREATE OR REPLACE FUNCTION group_find_by_id(IN group_id varchar) RETURNS 
TABLE(group_name varchar, group_year int, dept_name varchar, dept_fullname varchar) AS '
	select groups.name , groups."year" , groups.department_name, department.name as depname 
	from groups 
	left join department on department_name = department.abbr 
	where groups.name=group_id
' LANGUAGE SQL;

CREATE OR REPLACE PROCEDURE public.insert_group(IN group_name character varying, IN group_year integer
	, IN dep_name character varying)
LANGUAGE 'sql'
AS $BODY$
insert into groups (name, year, department_name) values (group_name, group_year , dep_name)
on conflict (name) do update set year= group_year, department_name= dep_name
$BODY$;

CREATE OR REPLACE PROCEDURE public.delete_group(IN group_name character varying)
LANGUAGE 'sql'
AS $BODY$
delete from groups where name=group_name
$BODY$;

