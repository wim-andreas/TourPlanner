--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1 (Debian 14.1-1.pgdg110+1)
-- Dumped by pg_dump version 14.1 (Debian 14.1-1.pgdg110+1)

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
-- Name: logs; Type: TABLE; Schema: public; Owner: swe2user
--

CREATE TABLE public.logs (
    log_id integer NOT NULL,
    tour_id integer NOT NULL,
    date date,
    "time" timestamp without time zone,
    comment character varying(256),
    diffculty integer,
    total_time time without time zone,
    rating integer
);


ALTER TABLE public.logs OWNER TO swe2user;

--
-- Name: logs_log_id_seq; Type: SEQUENCE; Schema: public; Owner: swe2user
--

CREATE SEQUENCE public.logs_log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.logs_log_id_seq OWNER TO swe2user;

--
-- Name: logs_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: swe2user
--

ALTER SEQUENCE public.logs_log_id_seq OWNED BY public.logs.log_id;


--
-- Name: tours; Type: TABLE; Schema: public; Owner: swe2user
--

CREATE TABLE public.tours (
    tour_id integer NOT NULL,
    name character varying(64),
    description character varying(256),
    from_where character varying(64),
    to_where character varying(64),
    transportation character varying(128),
    distance double precision,
    "time" character varying(64),
    route_info character varying(256)
);


ALTER TABLE public.tours OWNER TO swe2user;

--
-- Name: tours_tour_id_seq; Type: SEQUENCE; Schema: public; Owner: swe2user
--

CREATE SEQUENCE public.tours_tour_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tours_tour_id_seq OWNER TO swe2user;

--
-- Name: tours_tour_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: swe2user
--

ALTER SEQUENCE public.tours_tour_id_seq OWNED BY public.tours.tour_id;


--
-- Name: logs log_id; Type: DEFAULT; Schema: public; Owner: swe2user
--

ALTER TABLE ONLY public.logs ALTER COLUMN log_id SET DEFAULT nextval('public.logs_log_id_seq'::regclass);


--
-- Name: tours tour_id; Type: DEFAULT; Schema: public; Owner: swe2user
--

ALTER TABLE ONLY public.tours ALTER COLUMN tour_id SET DEFAULT nextval('public.tours_tour_id_seq'::regclass);


--
-- Data for Name: logs; Type: TABLE DATA; Schema: public; Owner: swe2user
--

COPY public.logs (log_id, tour_id, date, "time", comment, diffculty, total_time, rating) FROM stdin;
\.


--
-- Data for Name: tours; Type: TABLE DATA; Schema: public; Owner: swe2user
--

COPY public.tours (tour_id, name, description, from_where, to_where, transportation, distance, "time", route_info) FROM stdin;
\.


--
-- Name: logs_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: swe2user
--

SELECT pg_catalog.setval('public.logs_log_id_seq', 1, false);


--
-- Name: tours_tour_id_seq; Type: SEQUENCE SET; Schema: public; Owner: swe2user
--

SELECT pg_catalog.setval('public.tours_tour_id_seq', 1, false);


--
-- Name: logs logs_pkey; Type: CONSTRAINT; Schema: public; Owner: swe2user
--

ALTER TABLE ONLY public.logs
    ADD CONSTRAINT logs_pkey PRIMARY KEY (log_id);


--
-- Name: tours tours_pkey; Type: CONSTRAINT; Schema: public; Owner: swe2user
--

ALTER TABLE ONLY public.tours
    ADD CONSTRAINT tours_pkey PRIMARY KEY (tour_id);


--
-- Name: logs logs_tours_tour_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: swe2user
--

ALTER TABLE ONLY public.logs
    ADD CONSTRAINT logs_tours_tour_id_fk FOREIGN KEY (log_id) REFERENCES public.tours(tour_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

