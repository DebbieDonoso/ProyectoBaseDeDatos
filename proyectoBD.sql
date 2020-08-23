create database proyectoBD1;

use proyectoBD1;
create table Recepcionista(
cedula char(10),
primary key(cedula)
); 

create table Cita(
id_Cita int,
fecha date,
hora varchar(5),
cedula_recepcionista char(10),
primary key (id_Cita),
foreign key (cedula_recepcionista) references Recepcionista(cedula)
);

create table Paciente(
cedula char(10),
cedula_paciente char(10) primary key,
nombre varchar(30),
apellido varchar(30),
fecha_nacimiento date,
direcciÃ³n varchar(40),
telefono varchar(10),
id_cita int,
foreign key(id_cita) references cita (id_cita),
foreign key(cedula) references recepcionista (cedula)
);

create table HistoriaClinica(
cedula_Paciente char(10) primary key,
foreign key(cedula_Paciente) references Paciente (cedula_Paciente)
);

create table Enfermera(
cedula char(10),
primary key(cedula),
foreign key(cedula) references paciente (cedula_paciente)
);

create table Enfermedad(
cÃ³digo int primary key,
nombre_enfermedad text
);

create table Padecimientos(
cedula_Paciente char(10),
codigo_enfermedad int,
fecha_padecimiento date,
foreign key(cedula_Paciente) references HistoriaClinica (cedula_Paciente),
foreign key(codigo_enfermedad) references Enfermedad (cÃ³digo)
);

create table CitasPrevias(
cedula_Paciente char(10),
fecha_Cita date,
tipo_Cita varchar(20),
diagnostico varchar(40),
primary key(fecha_Cita, tipo_Cita),
foreign key(cedula_Paciente) references HistoriaClinica (cedula_Paciente)
);

create table Registro(
cedula_Empleado char(10),
cedula_Paciente char(10),
primary key(cedula_Empleado, cedula_Paciente),
foreign key(cedula_Paciente) references HistoriaClinica (cedula_Paciente)
);

create table Empleado(
cedula char(10),
nombre varchar(30),
apellido varchar(30),
foreign key (cedula) references Registro (cedula_Empleado),
foreign key (cedula) references Enfermera (cedula),
foreign key (cedula) references recepcionista (cedula)
);

create table Medico(
cedula char(10),
primary key(cedula),
foreign key(cedula) references Empleado (cedula)
);

create table Laboratorista(
cedula char(10),
primary key(cedula),
foreign key(cedula) references Empleado (cedula)
);

create table Diagnostico(
cedula_Medico char(10),
conclusion varchar(50),
enfermedad_detectada varchar(30),
foreign key (cedula_Medico) references Medico (cedula)
);

create table Receta(
id_Receta int,
fecha date,
medicamento varchar(30),
cedula_Medico char(10),
primary key (id_Receta),
foreign key (cedula_Medico) references Medico (cedula)
);

create table Medicamento(
codigo_medicamento char(10),
id_Receta int,
nombre_Medicamento varchar(30),
posologÃ­a varchar(30),
primary key(codigo_medicamento),
foreign key (id_Receta) references Receta (id_Receta)
);

create table ExamenLaboratorio(
id_Cita int primary key,
id_Laboratorista char(10),
foreign key(id_Cita) references Cita (id_Cita),
foreign key(id_Laboratorista) references Laboratorista (cedula)
);

create table EcografÃ­a(
id_Cita int primary key,
id_Medico char(10),
foreign key(id_Cita) references Cita (id_Cita),
foreign key(id_Medico) references Medico (cedula)
);

create table Consulta(
id_Cita int primary key,
id_Medico char(10),
foreign key(id_Cita) references Cita (id_Cita),
foreign key(id_Medico) references Medico (cedula)
);

insert into recepcionista values(0952294841);
insert into cita values(1,'2018-02-18','14:09',0952294841);
insert into paciente values(0952294841, 0952294556, 'Daniella', 'Donoso', '1998-02-19', 'acuarelas',0984189264,1);
insert into historiaclinica values(0952294556);
insert into citasprevias values (0952294556, '2018-01-19','ecografÃ­a','sano');

insert into recepcionista values(0952294846);
insert into cita values(2,'2017-04-17','13:07',0952294846);
insert into paciente values(0952294846, 0952294565, 'Daniel', 'Mendoza', '1993-01-19', 'sauces',0984189289,2);
insert into historiaclinica values(0952294565);
insert into citasprevias values (0952294565, '2017-02-24','consulta','gripe');

insert into recepcionista values(0952294848);
insert into cita values(3,'2019-07-19','13:07',0952294848);
insert into paciente values(0952294848, 0952297865, 'Jose', 'Aviles', '1990-07-25', 'alborada',0984189279,3);
insert into historiaclinica values(0952297865);
insert into citasprevias values (0952297865, '2019-05-20','consulta','hipertensiÃ³n');

insert into recepcionista values(0952294884);
insert into cita values(4,'2018-07-26','13:50',0952294884);
insert into paciente values(0952294884, 0952299265, 'Maria', 'Perez', '1997-09-19', 'alborada',0984107273,4);
insert into historiaclinica values(0952299265);
insert into citasprevias values (0952299265, '2018-02-05','examenlaboratorio','sano');

insert into recepcionista values(0952294870);
insert into cita values(5,'2019-07-06','13:50',0952294870);
insert into paciente values(0952294870, 0952299232, 'Susana', 'Rodriguez', '1995-09-05', 'alborada',0984107279,5);
insert into historiaclinica values(0952299232);
insert into citasprevias values (0952299232, '2018-02-10','examenlaboratorio','grave');

insert into recepcionista values(0952294876);
insert into cita values(6,'2017-10-06','16:50',0952294876);
insert into paciente values(0952294876, 0952299258, 'Romina', 'Salvatierra', '1997-12-18', 'sur',0984107809,6);
insert into historiaclinica values(0952299258);
insert into citasprevias values (0952299258, '2017-09-17','ecografia','quistes');

insert into recepcionista values(0952294809);
insert into cita values(7,'2019-12-12','11:00',0952294809);
insert into paciente values(0952294809, 0952299260, 'Cesar', 'CedeÃ±o', '2000-06-18', 'guayacanes',0984107809,7);
insert into historiaclinica values(0952299260);
insert into citasprevias values (0952299260, '2019-12-01','consulta','alergia');

insert into recepcionista values(0952294879);
insert into cita values(8,'2018-03-22','19:15',0952294879);
insert into paciente values(0952294879, 0952299277, 'Ana', 'De La Torre', '1998-01-10', 'sauces3',0984107879,8);
insert into historiaclinica values(0952299277);
insert into citasprevias values (0952299277, '2018-02-20','examenlaboratorio','anemia');

insert into recepcionista values(0952294866);
insert into cita values(9,'2019-08-20','19:15',0952294866);
insert into paciente values(0952294866, 0952299200, 'Margarita', 'Velez', '1999-08-20', 'centro',0984107880,9);
insert into historiaclinica values(0952299200);
insert into citasprevias values (0952299200, '2019-08-20','ecografia','piedrasenelriÃ±on');


insert into recepcionista values(0952294888);
insert into cita values(10,'2019-12-04','17:15',0952294888);
insert into paciente values(0952294888, 0952299299, 'Juan', 'Velasco', '1990-08-20', 'samanes',0984109080,10);
insert into historiaclinica values(0952299299);
insert into citasprevias values (0952299299, '2019-11-23','consulta','higado graso');

insert into recepcionista values(0952294888);
insert into cita values(11,'2019-12-04','17:15',0952294888);
insert into paciente values(0952294888, 0952299299, 'Juan', 'Velasco', '1990-08-20', 'samanes',0984109080,10);
insert into historiaclinica values(0952299299);
