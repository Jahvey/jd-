create table action_201603_extra(

user_id int  not null ,
sku_id  int not null ,
times  datetime not null ,
model_id smallint  not null ,
type_id varchar(2) not null,
cate varchar(4) not null,
brand varchar(8)not null)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--字段名：
--user_id,sku_id,time,model_id,type,cate,brand
--70442,36040,2016-02-08 21:43:03,,1,6,78
--79348,167719,2016-02-20 12:13:04,211,6,8,214
--34053,62080,2016-02-17 21:21:29,,1,6,78
--16131,54209,2016-02-26 17:33:44,28,6,6,306
--94176,45576,2016-02-06 15:11:30,,1,5,200
--1470,88184,2016-02-27 21:10:53,216,6,5,306
--11953,58475,2016-02-17 17:20:13,211,6,8,214
--76218,6530,2016-02-14 20:14:13,0,6,6,200




LOAD DATA INFILE "g:/data/action_201603_extraNOQCH.csv"
 REPLACE INTO TABLE action_201603_extra
 CHARACTER SET utf8
 FIELDS TERMINATED BY "," ENCLOSED BY ""
 LINES TERMINATED BY "\r\n";