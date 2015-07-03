
CREATE DATABASE gen; 
use gen;

create table class 
(
   classmodule          char(50)                       not null,
   classname            char(50)                       not null,
   classrmk             varchar(50)                    null,
   constraint PK_CLASS primary key clustered (classmodule, classname)
);

create table fields 
(
   classmodule          char(50)                       not null,
   classname            char(50)                       not null,
   coltitle             char(80)                       not null,
   colorder             int                            null,
   colname              char(80)                       null,
   coltype              char(50)                       null,
   classrmk             varchar(50)                    null,
   ctime                char(23)                       null,
   constraint PK_FIELDS primary key clustered (classmodule, classname, coltitle)
);

create index Index_1 on fields (
classmodule ASC,
classname ASC,
colorder ASC
);