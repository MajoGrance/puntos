/************ Add: Sequences ***************/

CREATE SEQUENCE public.bolsa_puntos_sec INCREMENT BY 1 START 1;

CREATE SEQUENCE public.cliente_id_seq INCREMENT BY 1 START 1;

CREATE SEQUENCE public.cliente_sec INCREMENT BY 1 START 1;

CREATE SEQUENCE public.concepto_puntos_sec INCREMENT BY 1 START 1;

CREATE SEQUENCE public.regla_asignacion_puntos_sec INCREMENT BY 1 START 1;

CREATE SEQUENCE public.uso_puntos_cabecera_sec INCREMENT BY 1 START 1;

CREATE SEQUENCE public.uso_puntos_detalle_sec INCREMENT BY 1 START 1;

CREATE SEQUENCE public.vencimiento_puntos_sec INCREMENT BY 1 START 1;



/************ Update: Tables ***************/

/******************** Add Table: public.bolsa_puntos ************************/

/* Build Table Structure */
CREATE TABLE public.bolsa_puntos
(
	id INTEGER NOT NULL,
	id_cliente INTEGER NOT NULL,
	fecha_asignacion DATE NOT NULL,
	fecha_caducidad DATE NOT NULL,
	puntos_asignados INTEGER NOT NULL,
	puntos_utilizados INTEGER NOT NULL,
	puntos_saldo INTEGER NOT NULL,
	monto_operacion DOUBLE PRECISION NOT NULL,
	estado VARCHAR(50) NOT NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.bolsa_puntos ADD CONSTRAINT pkbolsa_puntos
	PRIMARY KEY (id);


/******************** Add Table: public.cliente ************************/

/* Build Table Structure */
CREATE TABLE public.cliente
(
	id INTEGER DEFAULT nextval('cliente_id_seq'::regclass) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	numero_documento INTEGER NOT NULL,
	tipo_documento VARCHAR(50) NOT NULL,
	nacionalidad VARCHAR(50) NULL,
	email VARCHAR(50) NULL,
	telefono VARCHAR(50) NULL,
	fecha_nacimiento DATE NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.cliente ADD CONSTRAINT pkcliente
	PRIMARY KEY (id);


/******************** Add Table: public.concepto_puntos ************************/

/* Build Table Structure */
CREATE TABLE public.concepto_puntos
(
	id INTEGER NOT NULL,
	descripcion VARCHAR(200) NOT NULL,
	puntos_requeridos INTEGER NOT NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.concepto_puntos ADD CONSTRAINT pkconcepto_puntos
	PRIMARY KEY (id);


/******************** Add Table: public.regla_asignacion_puntos ************************/

/* Build Table Structure */
CREATE TABLE public.regla_asignacion_puntos
(
	id INTEGER NOT NULL,
	limite_inferior DOUBLE PRECISION NOT NULL,
	limite_superior DOUBLE PRECISION NOT NULL,
	monto_equivalencia DOUBLE PRECISION NOT NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.regla_asignacion_puntos ADD CONSTRAINT pkregla_asignacion_puntos
	PRIMARY KEY (id);


/******************** Add Table: public.uso_puntos_cabecera ************************/

/* Build Table Structure */
CREATE TABLE public.uso_puntos_cabecera
(
	id INTEGER NOT NULL,
	id_cliente INTEGER NOT NULL,
	puntos_utilizados INTEGER NOT NULL,
	fecha DATE NOT NULL,
	id_concepto INTEGER NOT NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.uso_puntos_cabecera ADD CONSTRAINT pkuso_puntos_cabecera
	PRIMARY KEY (id);


/******************** Add Table: public.uso_puntos_detalle ************************/

/* Build Table Structure */
CREATE TABLE public.uso_puntos_detalle
(
	id INTEGER NOT NULL,
	id_cabecera INTEGER NOT NULL,
	id_bolsa_puntos INTEGER NOT NULL,
	puntos_utilizados INTEGER NOT NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.uso_puntos_detalle ADD CONSTRAINT pkuso_puntos_detalle
	PRIMARY KEY (id);


/******************** Add Table: public.vencimiento_puntos ************************/

/* Build Table Structure */
CREATE TABLE public.vencimiento_puntos
(
	id INTEGER NOT NULL,
	fecha_inicio_validez DATE NOT NULL,
	fecha_fin_validez DATE NOT NULL,
	dias_duracion INTEGER NOT NULL
) WITHOUT OIDS;

/* Add Primary Key */
ALTER TABLE public.vencimiento_puntos ADD CONSTRAINT pkvencimiento_puntos
	PRIMARY KEY (id);





/************ Add Foreign Keys ***************/

/* Add Foreign Key: fk_bolsa_puntos_cliente */
ALTER TABLE public.bolsa_puntos ADD CONSTRAINT fk_bolsa_puntos_cliente
	FOREIGN KEY (id_cliente) REFERENCES public.cliente (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_uso_puntos_cabecera_cliente */
ALTER TABLE public.uso_puntos_cabecera ADD CONSTRAINT fk_uso_puntos_cabecera_cliente
	FOREIGN KEY (id_cliente) REFERENCES public.cliente (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_uso_puntos_cabecera_concepto_puntos */
ALTER TABLE public.uso_puntos_cabecera ADD CONSTRAINT fk_uso_puntos_cabecera_concepto_puntos
	FOREIGN KEY (id_concepto) REFERENCES public.concepto_puntos (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_uso_puntos_detalle_bolsa_puntos */
ALTER TABLE public.uso_puntos_detalle ADD CONSTRAINT fk_uso_puntos_detalle_bolsa_puntos
	FOREIGN KEY (id_bolsa_puntos) REFERENCES public.bolsa_puntos (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;

/* Add Foreign Key: fk_uso_puntos_detalle_uso_puntos_cabecera */
ALTER TABLE public.uso_puntos_detalle ADD CONSTRAINT fk_uso_puntos_detalle_uso_puntos_cabecera
	FOREIGN KEY (id_cabecera) REFERENCES public.uso_puntos_cabecera (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;
