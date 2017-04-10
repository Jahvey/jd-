#! /bin/bash

sed -i '1d' action_201602.csv
sed -i '1d' action_201603.csv
sed -i '1d' action_201603_extra.csv
sed -i '1d' action_201604.csv

awk '!a[$0]++' action_201602.csv >201602.csv

   cat action_201602.csv |sort|uniq >02.csv
 cat action_201603.csv |sort|uniq >03.csv
 free -m

 sudo echo 1>/proc/sys/vm/drop_caches
  su
 free -m
 cat action_201604.csv |sort|uniq >04.csv

 cat comment1.csv |sort|uniq >comments.csv
 tail -n 3 02.csv 
 tail -n 3 03.csv 
 tail -n 3 04.csv 

  sed -i '$d' 02.csv 
 tail -n 3 02.csv 
 tail -n 3 03.csv 
 sed -i '$d' 03.csv 
  tail -n 3 03.csv 
     tail -n 3 04.csv 
     sed -i '$d' 04.csv 
     tail -n 3 04.csv 
     tail -n 3 02.csv 

