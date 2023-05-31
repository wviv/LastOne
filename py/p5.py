num = 100
text = "所有的数是："
while num < 1000:
    bai = num // 100
    bai_mo = num % 100
    shi = bai_mo // 10
    ge = num % 10
    if bai ** 3 + shi ** 3 + ge ** 3 == num:
        text = f'{text}{num},'
        print(num)
    num += 1
else:
    print(text)
