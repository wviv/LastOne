# # a = 10
# #
# # print(a)
#
# text = '所有的数是：'
# num = '222'
#
# # print(id(10))
# #
# # print(1 and 2)
# # print(1 or 2)
# # print(0 or 1)
# #
# # if True:
# #     print(1111)
# #     print(2222)
# #
# # print(789)
#
#
# text = f'{text}{num},'
#
# num = 333
# text = f'{text}{num},'
# num = 444
# text = f'{text}{num},'
# print(text)
num = 153

bai = num // 100
bai_mo = num % 100
shi = bai_mo // 10
ge = bai_mo % 10

print(bai, shi, ge)

print(bai ** 3 + shi ** 3 + ge ** 3)
