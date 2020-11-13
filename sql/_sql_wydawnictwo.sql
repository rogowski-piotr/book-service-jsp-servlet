-- Table: ksiazki.wydawnictwo

-- DROP TABLE ksiazki.wydawnictwo;

CREATE SEQUENCE ksiazki.wydawnictwo_idw_seq;
CREATE TABLE ksiazki.wydawnictwo
(
    idw integer NOT NULL DEFAULT nextval('ksiazki.wydawnictwo_idw_seq'::regclass),
    nazwa character varying(50) NOT NULL,
    adres character varying,
    CONSTRAINT wydawnictwo_pkey PRIMARY KEY (idw)
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;

ALTER SEQUENCE ksiazki.wydawnictwo_idw_seq OWNED BY ksiazki.wydawnictwo.idw;

