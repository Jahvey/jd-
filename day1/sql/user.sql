create table users(

user_id int  not null ,
age  nvarchar(10) not null ,
sex  int not null,
user_lv_cd varchar(4) not null,
user_reg_dt date not null)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--字段名：
--user_id,age,sex,user_lv_cd,user_reg_dt
--insert into  user  values(44764,'-1',0,5,'2014/5/22');

LOAD DATA INFILE "g:/data/sql/user.csv"
 REPLACE INTO TABLE users
 CHARACTER SET utf8
 FIELDS TERMINATED BY "," ENCLOSED BY ""
 LINES TERMINATED BY "\r\n";










 

