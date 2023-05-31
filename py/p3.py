num = 0
total = 0

while num <= 100:
    # 奇数
    if num % 2 != 0:
        total += num
    num += 1
else:
    print(total)
