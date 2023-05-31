print(locals())


def easy(n):
    if n == 1:
        return n
    return n * easy(n - 1)


print(easy(10))
