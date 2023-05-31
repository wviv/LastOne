import os
import sys
import pprint

print(sys.argv)
# print(sys.modules)
# pprint.pprint(sys.modules)
pprint.pprint(sys.path)
pprint.pprint(sys.platform)
pprint.pprint(os.environ)
os.system("pwd")
