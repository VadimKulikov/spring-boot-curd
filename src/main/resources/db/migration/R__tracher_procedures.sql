CREATE OR REPLACE FUNCTION teacher_list_by_page(IN page_size integer, IN page_offset integer) RETURNS 
TABLE(teacher_id int,teacher_name varchar, teacher_surname varchar, teacher_patronymic varchar,
	teacher_department varchar, teacher_title varchar, teacher_disc_name varchar) AS '
	select teacher.id, teacher.name, teacher.surname, teacher.patronymic, teacher.department_name,
	teacher.academic_title , teacher_discipline.discipline_name 
	from teacher 
	left join teacher_discipline on teacher_discipline.teacher_id = teacher.id 
	order by teacher.id limit page_size offset page_offset
' LANGUAGE SQL;

CREATE OR REPLACE FUNCTION teacher_find_by_id(IN teach_id int) RETURNS 
TABLE(teacher_id int,teacher_name varchar, teacher_surname varchar, teacher_patronymic varchar,
	teacher_department varchar, teacher_title varchar, teacher_disc_name varchar) AS '
	select teacher.id, teacher.name, teacher.surname, teacher.patronymic, teacher.department_name,
	teacher.academic_title , teacher_discipline.discipline_name 
	from teacher 
	left join teacher_discipline on teacher_discipline.teacher_id = teacher.id 
	where teacher.id = teach_id
' LANGUAGE SQL;

CREATE OR REPLACE PROCEDURE public.insert_teacher(IN teacher_name varchar, IN teacher_surname varchar,
	IN teacher_patronymic varchar, IN teacher_department_name varchar, IN teacher_academic_title varchar)
LANGUAGE 'sql'
AS $BODY$
insert into teacher (name,surname,patronymic,department_name, academic_title) values
(teacher_name, teacher_surname,teacher_patronymic, teacher_department_name , teacher_academic_title)
$BODY$;

CREATE OR REPLACE PROCEDURE public.update_teacher(IN teacher_name varchar, IN teacher_surname varchar,
	IN teacher_patronymic varchar, IN teacher_department_name varchar, IN teacher_academic_title varchar, IN teacher_id int)
LANGUAGE 'sql'
AS $BODY$
update teacher set name=teacher_name, surname=teacher_surname, patronymic=teacher_patronymic,
department_name=teacher_department_name, academic_title=teacher_academic_title where id=teacher_id
$BODY$;

CREATE OR REPLACE PROCEDURE public.delete_teacher(IN teacher_id int)
LANGUAGE 'sql'
AS $BODY$
delete from teacher where id=teacher_id
$BODY$;

CREATE OR REPLACE PROCEDURE public.delete_teacher_records(IN teach_id int)
LANGUAGE 'sql'
AS $BODY$
delete from teacher_discipline where teacher_id=teach_id
$BODY$;