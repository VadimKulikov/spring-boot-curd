CREATE OR REPLACE FUNCTION discipline_list_by_page(IN page_size integer, IN page_offset integer) RETURNS 
TABLE(disc_name varchar, disc_dur int, disc_sem int) AS '
	select * from discipline 
	order by name 
	limit page_size offset page_offset;
' LANGUAGE SQL;

CREATE OR REPLACE FUNCTION discipline_find_by_id(IN disc_id varchar) RETURNS 
TABLE(disc_name varchar, disc_dur int, disc_sem int) AS '
	select * from discipline where name = disc_id
' LANGUAGE SQL;

CREATE OR REPLACE PROCEDURE public.delete_discipline(IN disc_id varchar)
LANGUAGE 'sql'
AS $BODY$
delete from discipline where name =disc_id
$BODY$;

CREATE OR REPLACE PROCEDURE public.insert_discipline(IN disc_name varchar, IN disc_dur int, IN disc_sem int)
LANGUAGE 'sql'
AS $BODY$
insert into discipline values (disc_name, disc_dur, disc_sem) on conflict (name) do update set
duration=disc_dur, semester=disc_sem
$BODY$;