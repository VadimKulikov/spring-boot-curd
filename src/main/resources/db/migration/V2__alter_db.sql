ALTER TABLE public.department
    RENAME name TO abbr;

ALTER TABLE public.department
    ADD COLUMN name character varying(255);
    
ALTER TABLE public.student DROP COLUMN patrynomic;

ALTER TABLE public.teacher
    ALTER COLUMN academic_title SET NOT NULL;
    
UPDATE public.department SET
name = 'Философия и право'::character varying WHERE
abbr = 'ФП';

UPDATE public.department SET
name = 'Экономика и финансы'::character varying WHERE
abbr = 'ЭФ';

UPDATE public.department SET
name = 'Строительное производство и геотехника'::character varying WHERE
abbr = 'СПГ';

UPDATE public.department SET
name = 'Социология и политология'::character varying WHERE
abbr = 'СИП';

UPDATE public.department SET
name = 'Металловедение, термическая и лазерная обработка металлов'::character varying WHERE
abbr = 'МТО';

UPDATE public.department SET
name = 'Менеджмент и маркетинг'::character varying WHERE
abbr = 'МиМ';

UPDATE public.department SET
name = 'Информационные технологии и автоматизированные системы'::character varying WHERE
abbr = 'ИТАС';

UPDATE public.department SET
name = 'Архитектура и урбанистика'::character varying WHERE
abbr = 'АУР';

UPDATE public.department SET
name = 'Автомобили и технологические машины'::character varying WHERE
abbr = 'АТМ';

UPDATE public.department SET
name = 'Инновационные технологии машиностроения'::character varying WHERE
abbr = 'ИТМ';

UPDATE public.department SET
name = 'Автоматика и телемеханика'::character varying WHERE
abbr = 'АТ';

UPDATE public.department SET
name = 'Государственное управление и история'::character varying WHERE
abbr = 'ГУИ';