import ntpath
from base64 import b64encode, b64decode
from distutils.dir_util import copy_tree
import sqlite3
import os, _thread,sys
import bs4 as bs
import shutil
from shutil import copyfile
class db_conn:
    def __init__(self):
        conn =""
        try:
            self.conn = sqlite3.connect(os.getcwd() + "\\database" + "\\mutant.db")

        except sqlite3.Error as e:
            ggwp = 1

    def query(self, sql):
        cursor = self.conn.cursor()
        try:
            cursor.execute(sql)
            self.conn.commit()
            print("done")
        except sqlite3.Error as e:
            file=open("logger.txt","a+")
            file.write("Cursor failed to execute query: '"+sql+"'"+" "+"Because of the following error:\n"+str(e))
            print("Cursor failed to execute query: '"+sql+"'"+" "+"Because of the following error:\n"+str(e))

            file.close()
            ggwp = 1
        return cursor

def generate_file_copy(f_path):
    destination=f_path+"1"
    copyfile(f_path,destination)

def replace_back_file(f_path):
    os.remove(f_path)
    copyfile(f_path+'1',f_path)
    os.remove(f_path+'1')


def reset(DB,f_path):
    DB.query("UPDATE mutant_table SET executed = '0' WHERE  executed = '1';")
    DB.query("UPDATE mutant_table SET Killed = '0' WHERE  Killed = '1';")
    DB.query("UPDATE mutant_table SET killed_by = ' ' WHERE  Killed = '1';")
    replace_back(f_path)
def make_temp(f_path):
    copy_tree(f_path, f_path + "1")

def replace_back(f_path):
    shutil.rmtree(f_path)
    copy_tree(f_path+"1", f_path)

def fetch_non_executed(DB,W_app):
    check = DB.query("SELECT COUNT(*) FROM mutant_table WHERE executed=0")
    check=check.fetchone()
    if(check[0]<=0):
        return 0
    result = DB.query("SELECT * FROM mutant_table WHERE executed=0 AND Web_app='{0}'".format(W_app))
    return result.fetchone()

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
def check_fail(result_file):
    infile = open(result_file,'r+')
    content = infile.read()
    soup = bs.BeautifulSoup(content, "xml")
    var = soup.find('testng-results')
    pas = var.get('failed')
    return int(pas)

def get_killed_by(result_file):
	killed_by=[]
	output=""
	infile = open(result_file, 'r+')
	content = infile.read()
	soup = bs.BeautifulSoup(content, "xml")
	var = soup.findAll('test-method')
	for i in var:
		if(i.get('status')=='FAIL'):
			killed_by.append(i.get('name'))
	if(len(killed_by)>1):
		output+=killed_by[0]
		for i in range(1,len(killed_by) -1 ):
			output+=", "+killed_by[i]
	else:
		output=killed_by[0]
	return output
def get_file_name(path):
    return ntpath.basename(path)

def copy_file(f_path,index):
    copy_tree(f_path,f_path+str(index))

def free_ram():
    os.system("taskkill /IM chromedriver.exe /F")
    #os.system("taskkill /IM Chrome.exe /F")
def main_thread(DB,f_path):
    mutant = fetch_non_executed(DB,"SN")
    while (mutant != 0):
        mutant = fetch_non_executed(DB,"SN")
        print(mutant)
        data = ""
        try:
            file = open(mutant[4], 'r+')
            data = file.read()
            file.close()
        except:
            try:
                with open(mutant[4]) as fileobj:
                    try:
                        for line in fileobj:
                            for ch in line:
                                data = data + ch
                    except:
                        ggwp = 1
            except:
                "no big deal"

        link = decode_code(mutant[7])
        print(link)
        co=data
        data=data.replace(link,decode_code(mutant[6]))



        #print(data)
        try:
            generate_file_copy(mutant[4])

            file = open(mutant[4], 'w')
            file.write(data)
            file.truncate()
            file.close()
            print(link , "File= ",mutant[4])
            DB.query("UPDATE mutant_table SET executed = '1' WHERE  Name = '{0}';".format(mutant[1]))
            print("Working on mutant: ",decode_code(mutant[6]))
            print("Original: ",link)
            run_java_component(f_path)
            check=check_fail(r"C:\Users\pc\eclipse-workspace\wolf_TestNG_pilot\test-outputtestng-results.xml")
            print(check)
            if(check>0):
                DB.query("UPDATE mutant_table SET Killed = '1'  WHERE  Name = '{0}';".format(mutant[1]))
                DB.query("UPDATE mutant_table SET killed_by = '{1}'  WHERE  Name = '{0}';".format(mutant[1],get_killed_by(r"C:\Users\pc\eclipse-workspace\wolf_TestNG_pilot\test-outputtestng-results.xml")))
            replace_back_file(mutant[4])
            print(mutant[4])
            free_ram()
        except:
            "Blank exception"
            file.close()
            replace_back_file(mutant[4])

def run_java_component(case_study_path):
	#to be made dynamic
        os.chdir(r"C:\Users\pc\eclipse-workspace\BH_test")
        os.system(case_study_path)
def main():
	#to be made dynamic
    DB = db_conn()
    # web_app=argv[0]

    # done=False
    sttt=r"Java -cp "+'"'+r"C:\Users\pc\eclipse-workspace\wolf_TestNG_pilot\lib\*;C:\Users\pc\eclipse-workspace\wolf_TestNG_pilot\bin"+'"'+" org.testng.TestNG testng.xml"
    sttt2 = r"Java -cp " + '"' + r"C:\Users\pc\eclipse-workspace\BH_test\lib\*;C:\Users\pc\eclipse-workspace\BH_test\bin" + '"' + " org.testng.TestNG testng.xml"
    # print(sttt)
    # os.chdir(r"C:\Users\pc\eclipse-workspace\wolf_TestNG_pilot")
    # os.system(sttt+" > logger2.txt")
    # soup=bs.BeautifulSoup(os.getcwd()+"test-output/index.html",'lxml')
    # soup.find_all('h')
    #replace_back(r"C:\xampp\htdocs\wolf")
    #reset(DB,r"C:\xampp\htdocs\SN")
    main_thread(DB,sttt)
if __name__ == '__main__':
    main()
    #main()
