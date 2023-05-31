import pprint

file_name = r"z3.png"
with open(file_name, mode="rb") as text_io:
    print(text_io.readline(2))
    text_io.seek(10, 1)
    print(text_io.readline(2))
    text_io.seek(-10, 2)
    print(text_io.readline(2))
    print(text_io.tell())
