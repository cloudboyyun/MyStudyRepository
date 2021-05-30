# -*- coding: utf-8 -*-
"""
Created on Wed Feb 10 09:29:41 2021

@author: xiayun
"""

# 使用方括号定义列表
my_list = ['crazyit', 20, 'Python']
print(my_list)
# 使用圆括号定义元组
my_tuple = ('crazyit', 20 ,'Python')
print(my_tuple)

print(my_list[0])
print(my_list[-1])

tuple1 = (1, 2, 3, 4)
print(tuple1)
print(tuple1 * 3)

tuple2 = 1, 2,3,4
print(tuple2)
a, b,c,d=tuple2
print(a, b, c, d)

x, y, z = 1, 2, 3
print(x,y,z)
x, y, z = z, x, y
print(x,y,z)

tuple = (1, 2, 3, 4, 5,6)
a,*b, c = tuple
print(a, b, c)

a_range = range(1, 5)
print(a_range, type(a_range))
a_list = list(a_range)
print(a_list, type(a_list))

list1 = [1,2,3,4,5]
print(list1)
del list1[2:3]
print(list1)

list1 = [1,2,3,4,5]
list1[2:2] = ["x","y"]
print(list1)

dict1 = {'语文':98, '数学':87, '化学':73}
dict2 = dict(key1 = 98, key2=87, key3=73)
print(dict1)
print(dict2)
print(dict1['数学'])
dict1['物理'] = 60
print(dict1)
del dict1['物理']
print(dict1)
print('数学' in dict1)

# 在字符串模板中使用key
temp = '书名是： %(name)s ，价格是： %(price)010.2f ，出版社是：%(publish)s'
book = {'name': '疯狂Python讲义', 'price': 88.9, 'publish': '电子社'}
# 使用字典为字符串模板中的key 传入值
print(temp % book)