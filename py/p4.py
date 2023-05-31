num = 0
total = 0
count = 0

while num <= 100:
    # 奇数
    if num % 7 == 0:
        total += num
        count += 1
    num += 1
else:
    print(total)
    print(count)
