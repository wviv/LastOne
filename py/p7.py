a = {'name': 'wwc', 'age': 18, 'name': 'cy'}
print(type(a))
print(a)
print(a['age'])
d = dict(bbb=11, age=18)
print(d)
print(len(d))
print('ccc' in d)
print('bbb' in d)
print(d['bbb'])
print(d.get('hhhhh'))
print(d.get('bbb'))
d['bbb'] = 'wwc'
# 添加
d['wwww'] = 'www'
print(d)
d.update(a)
# print(d)
# del d['bbb']
# print(d)
# d.pop('name')
# print(d)
# c = d.copy()
# c['age'] = 16
# print(d)
# print(c)

for s in d.keys():
    print(s)
    print(d[s])

for k, v in d.items():
    print(k, '=', v)