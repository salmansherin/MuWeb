from base64 import b64encode, b64decode
import sqlite3

from Mutator.M_gen import to_b64


def encode_code(code):
    #return codecs.encode(bytes(code, 'ascii'), 'hex_codec')
    #return code.replace("'", "''")
    #return "'"+"'"+"'"+code+"'"+"'"+"'"
    code=to_b64(code)
    code=code.decode('utf-8')
    return code
def decode_code(code):
    code= code.encode('utf-8')
    code = b64decode(code)
    code = code.decode('utf-8')
    code = code.replace('/', '.,.,.,')
    code = code.replace('\\', '/')
    code = code.replace('/', '')
    code = code.replace('.,.,.,', '/')
    code = code.replace(r"&lt;",r"<")
    code = code.replace(r"&gt;",r">")
    return code
def main():
    conn = sqlite3.connect("E:\\Mutant_generator\\Mutator\\database\\mutant.db")
    cursor = conn.cursor()
    cursor.execute("SELECT Mutant, Original FROM mutant_table WHERE Type='BOOL'")
    for row in cursor:
        print("Original= {0}".format(decode_code(row[1])))
        print("Mutant= {0}".format(decode_code(row[0])))
        print("------------------------")
def main2():
    stri="if ($currentPageNumber - 1 <= $pageCount) {"
    print(decode_code("ICAgICAgICAkdGltZVRha2VuID0gdGltZSgpIC0gJHRoaXMtPl9zdGFydFRpbWU"))

main2()
