class C1:
    a = 10
    b = 20

    def __init__(self):
        print('init')

    def aaa(self):
        print('shashasha %s ' % self.a)


a1 = C1()

# print(C1)
# print(type(C1))
# print(id(a1))
# print(a1.a)
print(a1.aaa())
