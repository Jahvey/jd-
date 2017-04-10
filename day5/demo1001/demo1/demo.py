#-*- coding: UTF-8 -*-

'''
Created on 2017��3��27��

@author: Administrator
'''
from __builtin__ import str
from __future__ import print_function  

i =10
'''
while i<=0: 
    print 'hello'+str(i)
    i=i-1

for j in range(1,10):
    print 'hello'+str(j)
    
    '''
for i in range(1, 10):  
    for j in range(1, i+1):  
        print('{}x{}={}\t'.format(j, i, i*j), end='')  
    print()  
    

print(sum([x for x in range(1000) if x%3==0 or x%5==0]))  



# 打印斐波拉契数列   
  
iterations = int(input("Number of iterations: "))  
cont = 1  
result = ""  
  
if iterations > 0:  
    fibonacci1 = 0  
    fibonacci2 = 1  
  
    result = result + "" + format(fibonacci1)  
    result = result + ", " + format(fibonacci2)  
  
    while cont < iterations:  
        temp = fibonacci2  
        fibonacci2 = fibonacci1 + fibonacci2  
        fibonacci1 = temp  
        result = result + ", " + format(fibonacci2)  
        cont = cont + 1  
  
print("Fibonacci: " + result)  
    