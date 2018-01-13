DROP TABLE TestEntity;

CREATE TABLE TestEntity (
	ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT Test_PK PRIMARY KEY,
	NAME VARCHAR(30) NOT NULL UNIQUE
	);
	
insert into TestEntity (name) values ('brown');
insert into TestEntity (name) values ('sky blue');
insert into TestEntity (name) values ('red');
insert into TestEntity (name) values ('purple');
insert into TestEntity (name) values ('orange');
insert into TestEntity (name) values ('grey');
insert into TestEntity (name) values ('yellow');
insert into TestEntity (name) values ('green');
insert into TestEntity (name) values ('blue');

select * from TestEntity;