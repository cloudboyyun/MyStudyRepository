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

f = open("d:\\temp\\test.txt", "w")
print('床前明月光', file = f)
print('疑是地上霜', file = f)
f.close()
