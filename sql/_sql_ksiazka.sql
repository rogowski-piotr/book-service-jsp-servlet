-- Table: ksiazki.ksiazka

-- DROP TABLE ksiazki.ksiazka;

CREATE SEQUENCE ksiazki.ksiazka_idk_seq;
CREATE TABLE ksiazki.ksiazka
(
    idk integer NOT NULL DEFAULT nextval('ksiazki.ksiazka_idk_seq'),
    tytul character varying(60) NOT NULL,
    idwyd integer,
    idkat integer,
    CONSTRAINT ksiazka_pkey PRIMARY KEY (idk)
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;

ALTER SEQUENCE ksiazki.ksiazka_idk_seq OWNED BY ksiazki.ksiazka.idk; 