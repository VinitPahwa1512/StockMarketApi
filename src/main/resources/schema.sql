DROP TABLE IF EXISTS Stock;


create table Stock(
    id INT AUTO_INCREMENT  PRIMARY KEY,
	name varchar_ignorecase(50) not null ,
	currentprice decimal(10,4) not null ,
	lastupdate DATETIME not null 
);


