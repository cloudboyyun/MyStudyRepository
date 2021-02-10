# -*- coding: utf-8 -*-
"""
Created on Tue Feb  9 14:55:35 2021

@author: xiayun
"""

# 这是单行注释
'''
    这里是
    多行注释
'''

a = 1
print('a=', a, 'a的类型是：', type(a))

a = "hello"
print('a=', a, 'a的类型是：', type(a))

# f = open("d:\\temp\\test.txt", "w")
# print('床前明月光', file = f)
# print('疑是地上霜', file = f)
# f.close()

# 导入keyword 模块
import keyword
# 显示所有关键字
print(keyword.kwlist)

a = "G:\publish\codes\02\2.4"
print(a)
a = r"G:\publish\codes\02\2.4"
print(a)

# 创建一个空的bytes
bl =bytes()
# 创建一个空的bytes值
b2 = b''
# 通过b前缀指定hello是bytes类型的值
b3 = b'hello'
print(b3)
print (b3[0])
print(b3[2 : 4])
# 调用bytes 方法将字符串转换成bytes对象
b4 = bytes('我爱Python 编程', encoding='utf-8')
print(b4)
# 利用字符串的encode（）方法编码成bytes ，默认使用UTF-8 字符集
b5 = "学习Python很有趣".encode('utf-8')
print (b5)

dir(str)
help(str.title)

table = str.maketrans('abt', 'αβτ')
s = 'crazyit.org is a good site'
s1 = s.translate(table)
print(s1)
