import pprint

file_name = r"z2.txt"
with open(file_name, mode="r+t", encoding='utf-8') as text_io:
    text_io.write("滴答滴答")
    readlines = text_io.read(10)
    pprint.pprint(readlines)
