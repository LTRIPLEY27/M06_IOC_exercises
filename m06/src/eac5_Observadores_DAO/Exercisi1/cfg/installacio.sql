-- Table: public.districte

-- DROP TABLE public.districte;

CREATE TABLE public.districte
(
    numero integer NOT NULL,
    nom character varying(50) COLLATE pg_catalog."default" NOT NULL,
    habitants integer,
    CONSTRAINT "Districte_pkey" PRIMARY KEY (numero)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.districte
    OWNER to ioc;

-- Table: public.barri

-- DROP TABLE public.barri;

CREATE TABLE public.barri
(
    nom character varying(60) COLLATE pg_catalog."default" NOT NULL,
    densitat numeric(5, 2),
    num_districte integer,
    CONSTRAINT "Barri_pkey" PRIMARY KEY (nom),
    CONSTRAINT "FK_Districte" FOREIGN KEY (num_districte)
        REFERENCES public.districte (numero) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE SET NULL
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.barri
    OWNER to ioc;
