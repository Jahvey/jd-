create table comments(

dt date  not null ,
sku_id  int not null ,
comment_num  int not null ,
has_bad_comment int  not null ,
bad_comment_rate DECIMAL(7,4) not null)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--dt,sku_id,comment_num,has_bad_comment,bad_comment_rate
--2016-03-14,118886,3,0,0.0
--2016-04-11,85403,2,0,0.0action_201602-1
--2016-04-15,122963,3,1,0.0606
--2016-02-15,23448,3,1,0.04
--2016-03-14,103480,4,1,0.0132

--×Ö¶ÎÃû£º
--user_id,age,sex,user_lv_cd,user_reg_dt
--insert into  user  values(44764,'-1',0,5,'2014/5/22');

LOAD DATA INFILE "g:/data/sql/comment.csv"
 REPLACE INTO TABLE comments
 CHARACTER SET utf8
 FIELDS TERMINATED BY "," ENCLOSED BY ""
 LINES TERMINATED BY "\r\n";