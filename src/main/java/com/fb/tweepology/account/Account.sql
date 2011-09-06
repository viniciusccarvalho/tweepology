create table if not exists Account (id integer not null auto_increment,
						username varchar(16) unique,
						password varchar(16) not null,
						firstName varchar(32) not null, 
						lastName varchar(64) not null,
						primary key (id));
