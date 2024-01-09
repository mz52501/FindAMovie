CREATE TABLE public.actor
(
    personid integer NOT NULL,
    CONSTRAINT actor_pkey PRIMARY KEY (personid),
    CONSTRAINT actor_personid_fkey FOREIGN KEY (personid)
        REFERENCES public.person (personid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.actor
    OWNER to postgres;

CREATE TABLE public.acts
(
    movieid integer NOT NULL,
    personid integer NOT NULL,
    CONSTRAINT acts_pkey PRIMARY KEY (movieid, personid),
    CONSTRAINT acts_movieid_fkey FOREIGN KEY (movieid)
        REFERENCES public.movie (movieid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT acts_personid_fkey FOREIGN KEY (personid)
        REFERENCES public.actor (personid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.acts
    OWNER to postgres;

CREATE TABLE public.direct
(
    movieid integer NOT NULL,
    personid integer NOT NULL,
    CONSTRAINT direct_pkey PRIMARY KEY (movieid, personid),
    CONSTRAINT direct_movieid_fkey FOREIGN KEY (movieid)
        REFERENCES public.movie (movieid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT direct_personid_fkey FOREIGN KEY (personid)
        REFERENCES public.director (personid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.direct
    OWNER to postgres;

CREATE TABLE public.director
(
    personid integer NOT NULL,
    CONSTRAINT director_pkey PRIMARY KEY (personid),
    CONSTRAINT director_personid_fkey FOREIGN KEY (personid)
        REFERENCES public.person (personid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.director
    OWNER to postgres;

CREATE TABLE public.genre
(
    genrename character varying(255) COLLATE pg_catalog."default" NOT NULL,
    genreid integer NOT NULL DEFAULT nextval('genre_genreid_seq'::regclass),
    CONSTRAINT genre_pkey PRIMARY KEY (genreid)
)

    TABLESPACE pg_default;

ALTER TABLE public.genre
    OWNER to postgres;

CREATE TABLE public.movie
(
    duration time without time zone NOT NULL,
    movieid integer NOT NULL DEFAULT nextval('movie_movieid_seq'::regclass),
    description character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    image character varying(255) NOT NULL,
    releasedate date NOT NULL,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT movie_pkey PRIMARY KEY (movieid)
)

    TABLESPACE pg_default;

ALTER TABLE public.movie
    OWNER to postgres;

CREATE TABLE public.person
(
    personid integer NOT NULL DEFAULT nextval('person_personid_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    image character varying(255) NOT NULL,
    CONSTRAINT person_pkey PRIMARY KEY (personid)
)

    TABLESPACE pg_default;

ALTER TABLE public.person
    OWNER to postgres;

CREATE TABLE public.review
(
    rating integer NOT NULL,
    comment character varying(3000) COLLATE pg_catalog."default" NOT NULL,
    movieid integer NOT NULL,
    userid integer NOT NULL,
    CONSTRAINT review_pkey PRIMARY KEY (movieid, userid),
    CONSTRAINT review_movieid_fkey FOREIGN KEY (movieid)
        REFERENCES public.movie (movieid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT review_userid_fkey FOREIGN KEY (userid)
        REFERENCES public.users (userid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.review
    OWNER to postgres;

CREATE TABLE public.type
(
    movieid integer NOT NULL,
    genreid integer NOT NULL,
    CONSTRAINT type_pkey PRIMARY KEY (movieid, genreid),
    CONSTRAINT type_genreid_fkey FOREIGN KEY (genreid)
        REFERENCES public.genre (genreid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT type_movieid_fkey FOREIGN KEY (movieid)
        REFERENCES public.movie (movieid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE public.type
    OWNER to postgres;

CREATE TABLE public.users
(
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    userid integer NOT NULL DEFAULT nextval('users_userid_seq'::regclass),
    isadmin boolean NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (userid)
)

    TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;