--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

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
-- TOC entry 220 (class 1259 OID 35863)
-- Name: age_restrictions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.age_restrictions (
    id integer NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.age_restrictions OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 35862)
-- Name: age_restrictions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.age_restrictions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.age_restrictions_id_seq OWNER TO postgres;

--
-- TOC entry 4937 (class 0 OID 0)
-- Dependencies: 219
-- Name: age_restrictions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.age_restrictions_id_seq OWNED BY public.age_restrictions.id;


--
-- TOC entry 230 (class 1259 OID 35924)
-- Name: bookings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bookings (
    id integer NOT NULL,
    session_id integer NOT NULL,
    user_id integer NOT NULL,
    row_number integer NOT NULL,
    seat_number integer NOT NULL,
    booking_time timestamp without time zone NOT NULL
);


ALTER TABLE public.bookings OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 35923)
-- Name: bookings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bookings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.bookings_id_seq OWNER TO postgres;

--
-- TOC entry 4938 (class 0 OID 0)
-- Dependencies: 229
-- Name: bookings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bookings_id_seq OWNED BY public.bookings.id;


--
-- TOC entry 231 (class 1259 OID 35940)
-- Name: film_genres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.film_genres (
    film_id integer NOT NULL,
    genre_id integer NOT NULL
);


ALTER TABLE public.film_genres OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 35877)
-- Name: films; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.films (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    age_restriction_id integer,
    poster_url character varying(255)
);


ALTER TABLE public.films OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 35876)
-- Name: films_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.films_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.films_id_seq OWNER TO postgres;

--
-- TOC entry 4939 (class 0 OID 0)
-- Dependencies: 223
-- Name: films_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.films_id_seq OWNED BY public.films.id;


--
-- TOC entry 216 (class 1259 OID 35849)
-- Name: genres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genres (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.genres OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 35848)
-- Name: genres_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.genres_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.genres_id_seq OWNER TO postgres;

--
-- TOC entry 4940 (class 0 OID 0)
-- Dependencies: 215
-- Name: genres_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.genres_id_seq OWNED BY public.genres.id;


--
-- TOC entry 218 (class 1259 OID 35856)
-- Name: languages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.languages (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.languages OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 35855)
-- Name: languages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.languages_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.languages_id_seq OWNER TO postgres;

--
-- TOC entry 4941 (class 0 OID 0)
-- Dependencies: 217
-- Name: languages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.languages_id_seq OWNED BY public.languages.id;


--
-- TOC entry 232 (class 1259 OID 35955)
-- Name: session_subtitles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.session_subtitles (
    session_id integer NOT NULL,
    language_id integer NOT NULL
);


ALTER TABLE public.session_subtitles OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 35891)
-- Name: sessions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sessions (
    id integer NOT NULL,
    film_id integer NOT NULL,
    start_time timestamp without time zone NOT NULL,
    theater_id integer NOT NULL,
    language_id integer
);


ALTER TABLE public.sessions OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 35890)
-- Name: sessions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sessions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sessions_id_seq OWNER TO postgres;

--
-- TOC entry 4942 (class 0 OID 0)
-- Dependencies: 225
-- Name: sessions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sessions_id_seq OWNED BY public.sessions.id;


--
-- TOC entry 222 (class 1259 OID 35870)
-- Name: theaters; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.theaters (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    capacity integer NOT NULL,
    number_of_rows integer NOT NULL,
    seats_per_row integer NOT NULL
);


ALTER TABLE public.theaters OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 35869)
-- Name: theaters_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.theaters_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.theaters_id_seq OWNER TO postgres;

--
-- TOC entry 4943 (class 0 OID 0)
-- Dependencies: 221
-- Name: theaters_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.theaters_id_seq OWNED BY public.theaters.id;


--
-- TOC entry 228 (class 1259 OID 35913)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 35912)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 4944 (class 0 OID 0)
-- Dependencies: 227
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 4733 (class 2604 OID 35866)
-- Name: age_restrictions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.age_restrictions ALTER COLUMN id SET DEFAULT nextval('public.age_restrictions_id_seq'::regclass);


--
-- TOC entry 4738 (class 2604 OID 35927)
-- Name: bookings id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings ALTER COLUMN id SET DEFAULT nextval('public.bookings_id_seq'::regclass);


--
-- TOC entry 4735 (class 2604 OID 35880)
-- Name: films id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.films ALTER COLUMN id SET DEFAULT nextval('public.films_id_seq'::regclass);


--
-- TOC entry 4731 (class 2604 OID 35852)
-- Name: genres id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genres ALTER COLUMN id SET DEFAULT nextval('public.genres_id_seq'::regclass);


--
-- TOC entry 4732 (class 2604 OID 35859)
-- Name: languages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.languages ALTER COLUMN id SET DEFAULT nextval('public.languages_id_seq'::regclass);


--
-- TOC entry 4736 (class 2604 OID 35894)
-- Name: sessions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions ALTER COLUMN id SET DEFAULT nextval('public.sessions_id_seq'::regclass);


--
-- TOC entry 4734 (class 2604 OID 35873)
-- Name: theaters id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.theaters ALTER COLUMN id SET DEFAULT nextval('public.theaters_id_seq'::regclass);


--
-- TOC entry 4737 (class 2604 OID 35916)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 4919 (class 0 OID 35863)
-- Dependencies: 220
-- Data for Name: age_restrictions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.age_restrictions (id, description) FROM stdin;
1	PERE
2	MS-6
3	MS-12
4	K-14
5	K-12
\.


--
-- TOC entry 4929 (class 0 OID 35924)
-- Dependencies: 230
-- Data for Name: bookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bookings (id, session_id, user_id, row_number, seat_number, booking_time) FROM stdin;
\.


--
-- TOC entry 4930 (class 0 OID 35940)
-- Dependencies: 231
-- Data for Name: film_genres; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.film_genres (film_id, genre_id) FROM stdin;
1	4
1	3
2	6
3	5
3	3
4	4
4	3
4	1
5	3
5	7
6	2
6	3
6	1
7	6
7	8
8	14
9	4
9	6
10	2
10	10
10	12
\.


--
-- TOC entry 4923 (class 0 OID 35877)
-- Dependencies: 224
-- Data for Name: films; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.films (id, title, age_restriction_id, poster_url) FROM stdin;
1	Kass ja koer: põgenemine	2	assets/images/cat.jpg
2	Elu ja armastus	3	assets/images/Elu_ja_armastus.jpg
3	Düün: teine osa	3	assets/images/Dune.jpg
4	Pardid!	1	assets/images/Migration.jpg
5	Emma ja must jaaguar	2	assets/images/Autumn&BlackJaguar.jpg
6	Kung Fu Panda 4	1	assets/images/KungFuPanda.jpg
7	Priscilla	5	assets/images/Priscilla.jpg
8	Kottpea	5	assets/images/Baghead.jpg
9	Mahajäänud	5	assets/images/Holdovers.jpg
10	Demon Slayer	5	assets/images/DemonSlayer.jpg
\.


--
-- TOC entry 4915 (class 0 OID 35849)
-- Dependencies: 216
-- Data for Name: genres; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.genres (id, name) FROM stdin;
1	Animatsioon
2	Märul
3	Seiklus
4	Komöödia
5	Ulmefilm
6	Draama
7	Kogupere
8	Biograafia
9	Muusika
10	Fantaasia
11	Triller
12	Anime
13	Sport
14	Õudus
\.


--
-- TOC entry 4917 (class 0 OID 35856)
-- Dependencies: 218
-- Data for Name: languages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.languages (id, name) FROM stdin;
1	Eesti
2	Inglise
3	Vene
\.


--
-- TOC entry 4931 (class 0 OID 35955)
-- Dependencies: 232
-- Data for Name: session_subtitles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.session_subtitles (session_id, language_id) FROM stdin;
\.


--
-- TOC entry 4925 (class 0 OID 35891)
-- Dependencies: 226
-- Data for Name: sessions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sessions (id, film_id, start_time, theater_id, language_id) FROM stdin;
\.


--
-- TOC entry 4921 (class 0 OID 35870)
-- Dependencies: 222
-- Data for Name: theaters; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.theaters (id, name, capacity, number_of_rows, seats_per_row) FROM stdin;
1	Suur Saal	250	10	25
2	Väike Saal	100	5	20
3	VIP Saal	50	5	10
\.


--
-- TOC entry 4927 (class 0 OID 35913)
-- Dependencies: 228
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password) FROM stdin;
1	admin	admin
2	test	test
\.


--
-- TOC entry 4945 (class 0 OID 0)
-- Dependencies: 219
-- Name: age_restrictions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.age_restrictions_id_seq', 5, true);


--
-- TOC entry 4946 (class 0 OID 0)
-- Dependencies: 229
-- Name: bookings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bookings_id_seq', 1, false);


--
-- TOC entry 4947 (class 0 OID 0)
-- Dependencies: 223
-- Name: films_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.films_id_seq', 10, true);


--
-- TOC entry 4948 (class 0 OID 0)
-- Dependencies: 215
-- Name: genres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.genres_id_seq', 14, true);


--
-- TOC entry 4949 (class 0 OID 0)
-- Dependencies: 217
-- Name: languages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.languages_id_seq', 3, true);


--
-- TOC entry 4950 (class 0 OID 0)
-- Dependencies: 225
-- Name: sessions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sessions_id_seq', 1, false);


--
-- TOC entry 4951 (class 0 OID 0)
-- Dependencies: 221
-- Name: theaters_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.theaters_id_seq', 3, true);


--
-- TOC entry 4952 (class 0 OID 0)
-- Dependencies: 227
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- TOC entry 4744 (class 2606 OID 35868)
-- Name: age_restrictions age_restrictions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.age_restrictions
    ADD CONSTRAINT age_restrictions_pkey PRIMARY KEY (id);


--
-- TOC entry 4756 (class 2606 OID 35929)
-- Name: bookings bookings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (id);


--
-- TOC entry 4758 (class 2606 OID 35944)
-- Name: film_genres film_genres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film_genres
    ADD CONSTRAINT film_genres_pkey PRIMARY KEY (film_id, genre_id);


--
-- TOC entry 4748 (class 2606 OID 35884)
-- Name: films films_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.films
    ADD CONSTRAINT films_pkey PRIMARY KEY (id);


--
-- TOC entry 4740 (class 2606 OID 35854)
-- Name: genres genres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genres_pkey PRIMARY KEY (id);


--
-- TOC entry 4742 (class 2606 OID 35861)
-- Name: languages languages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.languages
    ADD CONSTRAINT languages_pkey PRIMARY KEY (id);


--
-- TOC entry 4760 (class 2606 OID 35959)
-- Name: session_subtitles session_subtitles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_subtitles
    ADD CONSTRAINT session_subtitles_pkey PRIMARY KEY (session_id, language_id);


--
-- TOC entry 4750 (class 2606 OID 35896)
-- Name: sessions sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);


--
-- TOC entry 4746 (class 2606 OID 35875)
-- Name: theaters theaters_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.theaters
    ADD CONSTRAINT theaters_pkey PRIMARY KEY (id);


--
-- TOC entry 4752 (class 2606 OID 35922)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4754 (class 2606 OID 35920)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4765 (class 2606 OID 35930)
-- Name: bookings bookings_session_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_session_id_fkey FOREIGN KEY (session_id) REFERENCES public.sessions(id);


--
-- TOC entry 4766 (class 2606 OID 35935)
-- Name: bookings bookings_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4767 (class 2606 OID 35945)
-- Name: film_genres film_genres_film_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film_genres
    ADD CONSTRAINT film_genres_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.films(id);


--
-- TOC entry 4768 (class 2606 OID 35950)
-- Name: film_genres film_genres_genre_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film_genres
    ADD CONSTRAINT film_genres_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genres(id);


--
-- TOC entry 4761 (class 2606 OID 35885)
-- Name: films films_age_restriction_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.films
    ADD CONSTRAINT films_age_restriction_id_fkey FOREIGN KEY (age_restriction_id) REFERENCES public.age_restrictions(id);


--
-- TOC entry 4769 (class 2606 OID 35965)
-- Name: session_subtitles session_subtitles_language_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_subtitles
    ADD CONSTRAINT session_subtitles_language_id_fkey FOREIGN KEY (language_id) REFERENCES public.languages(id);


--
-- TOC entry 4770 (class 2606 OID 35960)
-- Name: session_subtitles session_subtitles_session_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.session_subtitles
    ADD CONSTRAINT session_subtitles_session_id_fkey FOREIGN KEY (session_id) REFERENCES public.sessions(id);


--
-- TOC entry 4762 (class 2606 OID 35897)
-- Name: sessions sessions_film_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.films(id);


--
-- TOC entry 4763 (class 2606 OID 35907)
-- Name: sessions sessions_language_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_language_id_fkey FOREIGN KEY (language_id) REFERENCES public.languages(id);


--
-- TOC entry 4764 (class 2606 OID 35902)
-- Name: sessions sessions_theater_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT sessions_theater_id_fkey FOREIGN KEY (theater_id) REFERENCES public.theaters(id);

--
-- PostgreSQL database dump complete
--

