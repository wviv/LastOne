class C1:
    a = 10
    b = 20

    def __init__(self, name):
        self.__name = name

    def get_name(self):
        print('name : %s ' % self.__name)


a1 = C1('张三')

a1.get_name()

print(a1.get_name())
