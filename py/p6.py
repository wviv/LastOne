laaaa = [1, 2, 3, 4, 5, 6]
lbbb = [7, 8, 9, 10, 11]
print(type(laaaa))
print(laaaa[1])
print(len(laaaa))
laaaa[1] = 10
print(laaaa[1])
print(laaaa[0:2])
print(laaaa[0:])
print(laaaa[:3])
print(laaaa[:5:2])
print(laaaa[::3])
# 反转列表
# print(laaaa[::-1])

# print(laaaa + lbbb)
# print(laaaa * 2)
# print(5 in laaaa)
# print(89 in laaaa)
# print(5 not in laaaa)
# print(89 not in laaaa)
# print(min(laaaa))
# print(max(laaaa))
# print(lbbb.index(11))
# print(lbbb.index(11, 2))
# print(lbbb.count(233))

sdasjda = "hello"
print(sdasjda[2])
print("o" in sdasjda)
print("o" not in sdasjda)


print(laaaa)
del laaaa[0]
print(laaaa)
laaaa[0:2] = ['a', 'b']
print(laaaa)


# 注意匹配
laaaa[::2] = ['cc', 'bb', 'aa']
print(laaaa)
laaaa.append('ddd')
print(laaaa)
laaaa.insert(0, 'ccc')
print(laaaa)
laaaa.extend(['xx', 'xxx'])
print(laaaa)


print(laaaa.pop(1))
print(laaaa)
laaaa.extend(['xx', 'xxx'])
# 相同只会删除第一个
print(laaaa.remove('xx'))
print(laaaa)

laaaa.reverse()
print(laaaa)
lbbb.sort()
print(lbbb)
lbbb.reverse()
print(lbbb)

for s in laaaa:
    print(s)

print(list(range(100, 200, 20)))


for s in range(20):
    print(s)

lccc = ('a', 'b', 'c', 'd')
print("cccccc", type(lccc))
d, c, b, a = lccc
print(a)
print(b)
print(c)
print(d)

a, b, c, d = d, c, b, a
print(a)
print(b)
print(c)
print(d)

# dd, *ddd, dddd = lccc
dd, *ddd, dddd = 'hello'
print(ddd)


# ==  判断值是否相等   is  判断id是否相等，是不是同一个对象
