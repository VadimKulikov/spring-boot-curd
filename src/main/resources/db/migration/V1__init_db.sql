--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-04-20 23:10:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 24793)
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

DROP TABLE IF EXISTS public.department CASCADE;

CREATE TABLE public.department (
    name character varying(256) NOT NULL,
    faculty_id integer
);


ALTER TABLE public.department OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24829)
-- Name: discipline; Type: TABLE; Schema: public; Owner: postgres
--

DROP TABLE IF EXISTS public.discipline CASCADE;

CREATE TABLE public.discipline (
    name character varying(256) NOT NULL,
    duration integer NOT NULL,
    semester integer NOT NULL
);


ALTER TABLE public.discipline OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 24787)
-- Name: faculty; Type: TABLE; Schema: public; Owner: postgres
--
DROP TABLE IF EXISTS public.faculty CASCADE;

CREATE TABLE public.faculty (
    id integer NOT NULL,
    name character varying(256)
);


ALTER TABLE public.faculty OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 24785)
-- Name: faculty_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.faculty_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.faculty_id_seq OWNER TO postgres;

--
-- TOC entry 3083 (class 0 OID 0)
-- Dependencies: 200
-- Name: faculty_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.faculty_id_seq OWNED BY public.faculty.id;


--
-- TOC entry 207 (class 1259 OID 24839)
-- Name: grade; Type: TABLE; Schema: public; Owner: postgres
--
DROP TABLE IF EXISTS public.grade CASCADE;

CREATE TABLE public.grade (
    code character varying(8) NOT NULL,
    description text
);


ALTER TABLE public.grade OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24803)
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--
DROP TABLE IF EXISTS public.groups CASCADE;

CREATE TABLE public.groups (
    name character varying(16) NOT NULL,
    year integer NOT NULL,
    department_name character varying(256) NOT NULL
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 24880)
-- Name: performance; Type: TABLE; Schema: public; Owner: postgres
--
DROP TABLE IF EXISTS public.performance CASCADE;

CREATE TABLE public.performance (
    student_id integer NOT NULL,
    discipline character varying(256) NOT NULL,
    grade_code character varying(8) NOT NULL
);


ALTER TABLE public.performance OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24855)
-- Name: school_diploma; Type: TABLE; Schema: public; Owner: postgres
--
DROP TABLE IF EXISTS public.school_diploma CASCADE;

CREATE TABLE public.school_diploma (
    number bigint NOT NULL,
    year integer
);


ALTER TABLE public.school_diploma OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 24862)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--
DROP TABLE IF EXISTS public.student CASCADE;

CREATE TABLE public.student (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    surname character varying(64) NOT NULL,
    patrynomic character varying(128),
    group_name character varying(16),
    school_diploma_num bigint
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 24860)
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.student_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.student_id_seq OWNER TO postgres;

--
-- TOC entry 3084 (class 0 OID 0)
-- Dependencies: 210
-- Name: student_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.student_id_seq OWNED BY public.student.id;


--
-- TOC entry 205 (class 1259 OID 24815)
-- Name: teacher; Type: TABLE; Schema: public; Owner: postgres
--
DROP TABLE IF EXISTS public.teacher CASCADE;

CREATE TABLE public.teacher (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    surname character varying(64) NOT NULL,
    patronymic character varying(64),
    department_name character varying(256),
    academic_title character varying(256)
);


ALTER TABLE public.teacher OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 24929)
-- Name: teacher_discipline; Type: TABLE; Schema: public; Owner: postgres
--
DROP TABLE IF EXISTS public.teacher_discipline CASCADE;

CREATE TABLE public.teacher_discipline (
    teacher_id integer NOT NULL,
    discipline_name character varying(256) NOT NULL
);


ALTER TABLE public.teacher_discipline OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 24813)
-- Name: teacher_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.teacher_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.teacher_id_seq OWNER TO postgres;

--
-- TOC entry 3085 (class 0 OID 0)
-- Dependencies: 204
-- Name: teacher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.teacher_id_seq OWNED BY public.teacher.id;


--
-- TOC entry 208 (class 1259 OID 24847)
-- Name: type_of_control; Type: TABLE; Schema: public; Owner: postgres
--

DROP TABLE IF EXISTS public.type_of_control CASCADE;

CREATE TABLE public.type_of_control (
    name character varying(128) NOT NULL,
    description text
);


ALTER TABLE public.type_of_control OWNER TO postgres;

--
-- TOC entry 2897 (class 2604 OID 24790)
-- Name: faculty id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.faculty ALTER COLUMN id SET DEFAULT nextval('public.faculty_id_seq'::regclass);


--
-- TOC entry 2899 (class 2604 OID 24865)
-- Name: student id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student ALTER COLUMN id SET DEFAULT nextval('public.student_id_seq'::regclass);


--
-- TOC entry 2898 (class 2604 OID 24818)
-- Name: teacher id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher ALTER COLUMN id SET DEFAULT nextval('public.teacher_id_seq'::regclass);


--
-- TOC entry 3066 (class 0 OID 24793)
-- Dependencies: 202
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.department (name, faculty_id) VALUES ('СИП', 12);
INSERT INTO public.department (name, faculty_id) VALUES ('МиМ', 12);
INSERT INTO public.department (name, faculty_id) VALUES ('ЭФ', 12);
INSERT INTO public.department (name, faculty_id) VALUES ('АТМ', 17);
INSERT INTO public.department (name, faculty_id) VALUES ('ИТМ', 17);
INSERT INTO public.department (name, faculty_id) VALUES ('МТО', 17);
INSERT INTO public.department (name, faculty_id) VALUES ('АУР', 13);
INSERT INTO public.department (name, faculty_id) VALUES ('СПГ', 13);
INSERT INTO public.department (name, faculty_id) VALUES ('ИТАС', 19);
INSERT INTO public.department (name, faculty_id) VALUES ('АТ', 19);
INSERT INTO public.department (name, faculty_id) VALUES ('ГУИ', 12);
INSERT INTO public.department (name, faculty_id) VALUES ('ФП', 12);


--
-- TOC entry 3070 (class 0 OID 24829)
-- Dependencies: 206
-- Data for Name: discipline; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.discipline (name, duration, semester) VALUES ('Правоведение', 1, 6);
INSERT INTO public.discipline (name, duration, semester) VALUES ('ООП', 1, 3);
INSERT INTO public.discipline (name, duration, semester) VALUES ('Архитектура ПО', 1, 4);
INSERT INTO public.discipline (name, duration, semester) VALUES ('Философия', 1, 1);


--
-- TOC entry 3065 (class 0 OID 24787)
-- Dependencies: 201
-- Data for Name: faculty; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.faculty (id, name) VALUES (13, 'Строительный');
INSERT INTO public.faculty (id, name) VALUES (12, 'Гуманитарный');
INSERT INTO public.faculty (id, name) VALUES (17, 'Механико-технологический');
INSERT INTO public.faculty (id, name) VALUES (19, 'Электротехнический');


--
-- TOC entry 3071 (class 0 OID 24839)
-- Dependencies: 207
-- Data for Name: grade; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3067 (class 0 OID 24803)
-- Dependencies: 203
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.groups (name, year, department_name) VALUES ('РИС-18', 2018, 'ИТАС');
INSERT INTO public.groups (name, year, department_name) VALUES ('АаА-15', 2015, 'ГУИ');


--
-- TOC entry 3076 (class 0 OID 24880)
-- Dependencies: 212
-- Data for Name: performance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3073 (class 0 OID 24855)
-- Dependencies: 209
-- Data for Name: school_diploma; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.school_diploma (number, year) VALUES (123, 2013);
INSERT INTO public.school_diploma (number, year) VALUES (125, 2014);
INSERT INTO public.school_diploma (number, year) VALUES (0, 0);
INSERT INTO public.school_diploma (number, year) VALUES (185, 2013);


--
-- TOC entry 3075 (class 0 OID 24862)
-- Dependencies: 211
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.student (id, name, surname, patrynomic, group_name, school_diploma_num) VALUES (1, 'Олег', 'Олегов', 'Олегович', 'РИС-18', 123);
INSERT INTO public.student (id, name, surname, patrynomic, group_name, school_diploma_num) VALUES (2, 'Кирилл', 'Жмых', 'Александрович', 'АаА-15', 125);
INSERT INTO public.student (id, name, surname, patrynomic, group_name, school_diploma_num) VALUES (16, 'Игорь', 'Косополов', 'Михайлович', 'АаА-15', 185);


--
-- TOC entry 3069 (class 0 OID 24815)
-- Dependencies: 205
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.teacher (id, name, surname, patronymic, department_name, academic_title) VALUES (1, 'Олег', 'Гагуля', 'Александрович', 'АТ', 'Доцент');
INSERT INTO public.teacher (id, name, surname, patronymic, department_name, academic_title) VALUES (3, 'Александр', 'Опалов', 'Алексеевич', 'ИТАС', 'Доцент');
INSERT INTO public.teacher (id, name, surname, patronymic, department_name, academic_title) VALUES (11, 'Аркадий', 'Леев', 'Владиславович', 'ФП', 'Доцент');
INSERT INTO public.teacher (id, name, surname, patronymic, department_name, academic_title) VALUES (7, 'Владимир', 'Харьков', 'Станиславович', 'ИТАС', 'Доцент');


--
-- TOC entry 3077 (class 0 OID 24929)
-- Dependencies: 213
-- Data for Name: teacher_discipline; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.teacher_discipline (teacher_id, discipline_name) VALUES (3, 'ООП');
INSERT INTO public.teacher_discipline (teacher_id, discipline_name) VALUES (3, 'Архитектура ПО');
INSERT INTO public.teacher_discipline (teacher_id, discipline_name) VALUES (7, 'Архитектура ПО');
INSERT INTO public.teacher_discipline (teacher_id, discipline_name) VALUES (7, 'ООП');
INSERT INTO public.teacher_discipline (teacher_id, discipline_name) VALUES (11, 'Философия');


--
-- TOC entry 3072 (class 0 OID 24847)
-- Dependencies: 208
-- Data for Name: type_of_control; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3086 (class 0 OID 0)
-- Dependencies: 200
-- Name: faculty_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.faculty_id_seq', 19, true);


--
-- TOC entry 3087 (class 0 OID 0)
-- Dependencies: 210
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.student_id_seq', 17, true);


--
-- TOC entry 3088 (class 0 OID 0)
-- Dependencies: 204
-- Name: teacher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.teacher_id_seq', 11, true);


--
-- TOC entry 2903 (class 2606 OID 24797)
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (name);


--
-- TOC entry 2909 (class 2606 OID 24833)
-- Name: discipline discipline_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discipline
    ADD CONSTRAINT discipline_pkey PRIMARY KEY (name);


--
-- TOC entry 2901 (class 2606 OID 24792)
-- Name: faculty faculty_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.faculty
    ADD CONSTRAINT faculty_pkey PRIMARY KEY (id);


--
-- TOC entry 2911 (class 2606 OID 24846)
-- Name: grade grade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grade
    ADD CONSTRAINT grade_pkey PRIMARY KEY (code);


--
-- TOC entry 2905 (class 2606 OID 24807)
-- Name: groups group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT group_pkey PRIMARY KEY (name);


--
-- TOC entry 2921 (class 2606 OID 24884)
-- Name: performance performance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance
    ADD CONSTRAINT performance_pkey PRIMARY KEY (student_id, discipline);


--
-- TOC entry 2915 (class 2606 OID 24859)
-- Name: school_diploma school_diploma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.school_diploma
    ADD CONSTRAINT school_diploma_pkey PRIMARY KEY (number);


--
-- TOC entry 2917 (class 2606 OID 24867)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- TOC entry 2923 (class 2606 OID 24933)
-- Name: teacher_discipline teacher_discipline_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher_discipline
    ADD CONSTRAINT teacher_discipline_pkey PRIMARY KEY (teacher_id, discipline_name);


--
-- TOC entry 2907 (class 2606 OID 24823)
-- Name: teacher teacher_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (id);


--
-- TOC entry 2913 (class 2606 OID 24854)
-- Name: type_of_control type_of_control_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_of_control
    ADD CONSTRAINT type_of_control_pkey PRIMARY KEY (name);


--
-- TOC entry 2919 (class 2606 OID 24869)
-- Name: student unique_school_diploma_num; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT unique_school_diploma_num UNIQUE (school_diploma_num);


--
-- TOC entry 2925 (class 2606 OID 24808)
-- Name: groups fk_department_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fk_department_name FOREIGN KEY (department_name) REFERENCES public.department(name);


--
-- TOC entry 2926 (class 2606 OID 24824)
-- Name: teacher fk_department_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher
    ADD CONSTRAINT fk_department_name FOREIGN KEY (department_name) REFERENCES public.department(name);


--
-- TOC entry 2930 (class 2606 OID 24890)
-- Name: performance fk_discipline_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance
    ADD CONSTRAINT fk_discipline_name FOREIGN KEY (discipline) REFERENCES public.discipline(name);


--
-- TOC entry 2933 (class 2606 OID 24939)
-- Name: teacher_discipline fk_discipline_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher_discipline
    ADD CONSTRAINT fk_discipline_name FOREIGN KEY (discipline_name) REFERENCES public.discipline(name) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 2924 (class 2606 OID 24924)
-- Name: department fk_faculty_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT fk_faculty_id FOREIGN KEY (faculty_id) REFERENCES public.faculty(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- TOC entry 2931 (class 2606 OID 24895)
-- Name: performance fk_grade_code; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance
    ADD CONSTRAINT fk_grade_code FOREIGN KEY (grade_code) REFERENCES public.grade(code);


--
-- TOC entry 2927 (class 2606 OID 24870)
-- Name: student fk_group_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk_group_name FOREIGN KEY (group_name) REFERENCES public.groups(name);


--
-- TOC entry 2928 (class 2606 OID 24875)
-- Name: student fk_school_diploma_num; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk_school_diploma_num FOREIGN KEY (school_diploma_num) REFERENCES public.school_diploma(number);


--
-- TOC entry 2929 (class 2606 OID 24885)
-- Name: performance fk_student_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.performance
    ADD CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- TOC entry 2932 (class 2606 OID 24934)
-- Name: teacher_discipline fk_teacher_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher_discipline
    ADD CONSTRAINT fk_teacher_id FOREIGN KEY (teacher_id) REFERENCES public.teacher(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


-- Completed on 2021-04-20 23:10:42

--
-- PostgreSQL database dump complete
--

