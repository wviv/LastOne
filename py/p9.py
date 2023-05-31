def dd():
    print(1111)


def su_m(a, b):
    a += b
    print(a)


def su_m2(a, b, ddd=200):
    a += b
    print(a)
    print(ddd)


def fn3(a):
    a[0] = 0
    print(a)

c = 10
d = 20
su_m(c, d)
su_m2(c, d)
# 位置参数要在关键字参数之前
# 如果形参是一个变量，会同步修改
su_m2(a=c, b=d)
print(c)
print(d)
a1 = [1, 2, 3]
fn3(a1)
print(a1)
a1 = [1, 2, 3]
fn3(a1.copy())
print(a1)
fn3(a1[:])
print(a1)

# 参数可以传递任意类型

# * 默认是元组，且只能有一个，带*后的参数必须是关键字传参
# 如果是*开头，则要求所有参数必须是关键字传参


def fn2(*a2):
    print(type(a2))
    re = 0
    for s in a2:
        re += s
    print(re)


fn2(1, 2, 3, 4)


# **可以接收其他的关键字参数，且保存到字典中,只能有一个，且只能在最后
def fn5(**a2):
    print(type(a2))
    re = 0
    for s in a2.keys():
        re += a2[s]
    print(re)


# adddd = dict(aaa = 1, bbb = 2, ccc = 3)
fn5(aaa=1, bbb=2, ccc=3)


def fn6(*a2):
    print(type(a2))
    re = 0
    for s in a2:
        re += s
    print(re)


# 元素个数和参数必须一致
t = (1, 2, 3, 4, 5)
fn6(*t)


def fn7() -> int:
    """
        *可以解包
        ** 可以对字典进行解包
    :return: 固定值
    """
    return 100


# print(fn7())
help(fn7)