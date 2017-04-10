create table products(

sku_id int  not null ,
attr1  varchar(2) not null ,
attr2  varchar(2) not null ,
attr3  varchar(2) not null ,
cate  varchar(4) not null ,
brand varchar(8) not null)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--sku_id,attr1,attr2,attr3,cate,brand
--35541,1,-1,2,8,403
--73398,3,1,2,8,306

--×Ö¶ÎÃû£º
--user_id,age,sex,user_lv_cd,user_reg_dt
--insert into  user  values(44764,'-1',0,5,'2014/5/22');

LOAD DATA INFILE "g:/data/sql/product.csv"
 REPLACE INTO TABLE products
 CHARACTER SET utf8
 FIELDS TERMINATED BY "," ENCLOSED BY ""
 LINES TERMINATED BY "\r\n";