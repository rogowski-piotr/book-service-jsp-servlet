-- Table: ksiazki.kategoria

-- DROP TABLE ksiazki.kategoria;

CREATE SEQUENCE ksiazki.kategoria_idk_seq;
CREATE TABLE ksiazki.kategoria
(
    idk integer NOT NULL DEFAULT nextval('ksiazki.kategoria_idk_seq'::regclass),
    opis character varying(50) NOT NULL,
    CONSTRAINT kategoria_pkey PRIMARY KEY (idk)
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;

ALTER SEQUENCE ksiazki.kategoria_idk_seq OWNED BY ksiazki.kategoria.idk;