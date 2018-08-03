
<!-- DROP TABLE IF EXITS -- >
DROP TABLE IF EXISTS engineer_entity;

<!--CREATE TABLE Engineer_entity-- >
CREATE TABLE engineer_entity (
id BIGINT(6) AUTO_INCREMENT PRIMARY KEY,
engineer_name VARCHAR(30) NOT NULL,
shift_number INT(11) NOT NULL
);

<!-- Insert Values -->
insert into engineer_entity values(1, "engineer1",0);
insert into engineer_entity values(2, "engineer2",0);
insert into engineer_entity values(3, "engineer3",0);
insert into engineer_entity values(4, "engineer4",0);
insert into engineer_entity values(5, "engineer5",0);
insert into engineer_entity values(6, "engineer6",0);
insert into engineer_entity values(7, "engineer7",0);
insert into engineer_entity values(8, "engineer8",0);
insert into engineer_entity values(9, "engineer9",0);
insert into engineer_entity values(10, "engineer10",0);