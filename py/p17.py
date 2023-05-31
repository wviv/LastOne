import pprint

file_name = r"z1.txt"

# text_io = open(file_name)
#
# print(text_io.read())
#
# text_io.close()

# print(text_io.read())

with open(file_name, encoding='utf-8') as text_io:
    # print(text_io.read())
    # print(text_io.readline())
    # read1 = text_io.read(20)
    # print(read1)
    # print(len(read1))
    readlines = text_io.readline()
    pprint.pprint(readlines)
    text_io.seek(50, 1)
    print(text_io.tell())
