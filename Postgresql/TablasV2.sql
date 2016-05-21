CREATE TABLE public.usuario
(
  idUsuario integer not null,
  nombreUsuario character varying(15) NOT NULL,
  contrasenia character varying(15),
  nombre character varying(25),
  apellidos character varying(25),
  correo character varying(25),
  CONSTRAINT "pk_Usuario" PRIMARY KEY (idUsuario)
);
CREATE TABLE public.contacto 
(
  idUsuario integer not null,
  tipo character varying(15),
  informacion character varying(30),
  CONSTRAINT "pk_Contacto" PRIMARY KEY (idUsuario, tipo),
  CONSTRAINT "fk_UsuarioContacto" FOREIGN KEY (idUsuario)
      REFERENCES public.usuario (idUsuario)
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE public.objeto
(
  idLibro integer not null,
  nombreLibro character varying(20) NOT NULL,
  autor character varying(30),
  edicion integer,
  anio integer,
  genero character varying(15),
  sinopsis character varying(500),  
  numPaginas integer,
  idUsuario integer NOT NULL,
  CONSTRAINT "pk_Objeto" PRIMARY KEY (idLibro),
  CONSTRAINT "fk_UsuarioObjeto" FOREIGN KEY (idUsuario)
      REFERENCES public.usuario (idUsuario)
      ON UPDATE CASCADE ON DELETE CASCADE
);
create table prestamo(
 idPrestamo integer not null,
 idPrestador integer NOT NULL,
 idConsumidor integer NOT NULL,
 idLibro integer NOT NULL,
 fechaPrestamo date,
 calificacionPrestador integer,
 calificaConsumidor integer,
 opinionSobrePrestador character varying(150),
 opinionSobreConsumidor character varying(150),
 tiempoSolicitado integer,
 medida character varying(10),
 CONSTRAINT "pk_Prestamo" PRIMARY KEY (idPrestamo),
 CONSTRAINT "fk_PrestadorPrestamo" FOREIGN KEY (idPrestador)
      REFERENCES public.usuario (idUsuario) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT "fk_ConsumidorPrestamo" FOREIGN KEY (idConsumidor)
      REFERENCES public.usuario (idUsuario) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT "fk_LibroPrestamo" FOREIGN KEY (idLibro)
      REFERENCES public.objeto (idLibro) 
      ON UPDATE CASCADE ON DELETE CASCADE
);
create table public.solicitar
(
 idSolicitud integer not null,
 idPrestador integer NOT NULL,
 idConsumidor integer NOT NULL,
 idLibro integer NOT NULL,
 fechaSolicitud date,
 TiempoSolicitado integer not null,
 medida character varying(10),
 CONSTRAINT "pk_Solicitar" PRIMARY KEY (idSolicitud),
 CONSTRAINT "fk_PrestadorSolicitar" FOREIGN KEY (idPrestador)
      REFERENCES public.usuario (idUsuario) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT "fk_ConsumidorSolicitar" FOREIGN KEY (idConsumidor)
      REFERENCES public.usuario (idUsuario) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT "fk_LibroSolicitar" FOREIGN KEY (idLibro)
      REFERENCES public.objeto (idLibro) ON UPDATE CASCADE ON DELETE CASCADE

);
