import codecs

import bs4 as bs
import urllib.request
import re
import time
import os ,fnmatch,ntpath,random
import sqlite3,hashlib
from html.parser import HTMLParser
import sys
#6a8b34a9e786ff641b9b122cd1891722
class db_conn:
    conn = sqlite3.connect(os.getcwd()+"\\database"+"\\mutant.db")
    def __init__(self):
        
        try:
            
            print((os.getcwd()))
        except sqlite3.Error as e:
            print("e")
    def query(self,sql):
        cursor=self.conn.cursor()
        try:
            cursor.execute(sql)
            self.conn.commit()
        except sqlite3.Error as e:
            print("Cursor failed to execute query: '",sql,"'")
            print("Because of the following error:\n"+str(e))
        return cursor

class html_mutation:
    def __init__(self,f_path,DB):
        self.DB=DB
        self.file=f_path

class link_mutation(html_mutation):
    def find_links_single_file(self,file):
        links = []
        file_data=""
        f = open(file, 'r+')
        try:
            file_data = f.read()
        except:
            with open(file) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            file_data=file_data+ch
                except:
                    print("Ignoring special characters (Usually found in 'lingual' documents). File: ",file)
           # print("Cant read file: ", get_file_name(file))
        print(file_data)
        for r in re.findall(r'<a.*/a>', file_data):
            links.append(r)
        return links

class SLR(link_mutation):

    def link_mutate(self,links, link_to_replace):
        b = True
        if (len(links) > 1):
            while (b == True):
                index = random.randint(0, len(links) - 1)
                if (links[index] != link_to_replace):
                    b = False
            return links[index]
        else:
            return 7

    def runner(self):
        links = self.find_links_single_file(self.file)
        for link in links:
            check = self.DB.query("SELECT COUNT(*) FROM mutant_table where Name='" +encode_MD5(link)+"'"+" AND Type='SLR'")
            ct=check.fetchone()[0]
            if (ct > 0):
                print("Already mutated SLR")
            else:
                rep_link = self.link_mutate(links, link)
                if (rep_link != 7):
                    print("Replace link: " + link + "  With: " + rep_link)
                    link_md = encode_MD5(link)
                    data = self.DB.query("SELECT Name From mutant_table where Name='" + link_md + "'")
                    curs = self.DB.conn.cursor()
                    curs.execute("SELECT * FROM mutant_table ORDER BY id DESC LIMIT 1")
                    id = curs.fetchone()
                    try:
                         ID = id[0] + 1
                    except:
                        ID = 0
                    add_to_db(ID, link_md, 'SLR', 'HTML', self.file, 0, self.DB,encode_code(rep_link),encode_code(link))
                else:
                    print("No links in file!")

class SLD(link_mutation):
    def delete_link(self,link):
        curs = self.DB.conn.cursor()
        curs.execute("SELECT * FROM mutant_table ORDER BY id DESC LIMIT 1")
        id = curs.fetchone()
        link_md = encode_MD5(link)
        href_php=re.findall("href="+'"'+".*>"+'"',link)
        temp_check_list=[]
        for hr in href_php:
            print(hr)
            linka=link
            hre=re.sub(linka,'href="#"',hr)
            temp_check_list.append(hr)
            try:
                ID = id[0] + 1
            except:
                ID = 0
            dele=hre
            add_to_db(ID, link_md, 'SLD', 'HTML', self.file, 0, self.DB, encode_code(dele),encode_code(link))
        soup=bs.BeautifulSoup(link,'lxml')
        for a in soup.find_all('a', href=True, text=True):
            key_word=a['href']
            if(a in temp_check_list):
                continue
            else:
                print(a)
                try:
                    ID = id[0] + 1
                except:
                    ID = 0
                print(key_word)
                a=str(a)
                a=a.replace('href="'+key_word+'"','href="#"')
                dele=a
                add_to_db(ID, link_md, 'SLD', 'HTML', self.file, 0, self.DB, encode_code(dele),encode_code(link))


    def runner(self):
        links=self.find_links_single_file(self.file)
        for link in links:
            check = self.DB.query("SELECT COUNT(*) FROM mutant_table where Name='" + encode_MD5(link) + "'" + " AND Type='SLD'")
            ss=check.fetchone()
            if (ss[0]>0):
                print("Already mutated SLD")
            else:
                self.delete_link(link)


def get_file_name(path):
    tail=ntpath.split(path)
    return tail or ntpath.basename(path)

def get_files(pattern,path):
    result = []
    for root, dirs, files in os.walk(path):
        for name in files:
            if fnmatch.fnmatch(name,pattern):
                result.append(os.path.join(root, name))
    return result


def string2RawString(string):
    co=0
    try:
        string=string.replace("\\",'/')
    except:
        file=open("lists","w")
        for line in string:
            file.write(str(line))

    return string
def encode_code(code):
    #return codecs.encode(bytes(code,'ascii'),'hex_codec')
    return code.replace("'","''")

def encode_MD5(string):
    try:
        result = hashlib.md5(string.encode()).hexdigest()
        return result
    except:
        print("Cant convert list to MD5 HASH")

def add_to_db(ID,f_name,type,family,f_path,killed,DB,mutant,original):
    DB.query("INSERT INTO `mutant_table`(ID,Name,Type,Family,File_path,Killed,Mutant,Original) Values("+str(ID)+",'{0}','{1}','{2}','{3}',{4},'{5}','{6}')".format(f_name,type,family,f_path,killed,mutant,original))

def find_execute_in_file(f_path,DB,check):
    if check==1:
        Html_rep_link = SLR(f_path,DB)
        Html_rep_link.runner()
    if check==2:
        OO=SLD(f_path,DB)
        OO.runner()
def all_file_path_includer(paths):
    DB = db_conn()
    curs = DB.conn.cursor()
    count=0
    for path in paths:
        DB.query("INSERT INTO `done_files`(F_path) Values("+"'"+path+"'"+")")
        count=count+1
        print("Included {0} out of {1} files.".format(count,len(paths)))
    curs.close()
#def check_done(operator_type,paths)
def run_SLR(path,files):
    DB = db_conn()
    curs = DB.conn.cursor()

    for file in files:
        #try:
            curs.execute("SELECT COUNT(*) from done_files")
            cou = curs.fetchone()
            if (cou[0] == 0):
                print("first exe")
                find_execute_in_file(file, DB,1)
                DB.query("INSERT INTO 'done_files'(F_path) VALUES('{0}')".format(string2RawString(file)))
            else:
                sql = "SELECT COUNT(*) FROM done_files WHERE F_path='{0}'".format(string2RawString(file))

                data = DB.query(sql)
                ct = data.fetchone()[0]
                if ct <= 0:
                    find_execute_in_file(file, DB,1)
                    DB.query("INSERT INTO 'done_files'(F_path) VALUES('{0}')".format(string2RawString(file)))
                else:
                    print("File already done!")
        #except:
                #print("Ignore garbage vals")

    DB.conn.close()
def run_SLD(path,files):
    DB = db_conn()
    curs = DB.conn.cursor()

    for file in files:
        try:
            curs.execute("SELECT COUNT(*) from done_files")
            cou = curs.fetchone()
            if (cou[0] == 0):
                print("first exe")
                find_execute_in_file(file, DB,2)
                DB.query("INSERT INTO 'done_files'(F_path_SLD) VALUES('{0}')".format(string2RawString(file)))
            else:
                sql = "SELECT COUNT(*) FROM done_files WHERE F_path_SLD='{0}'".format(string2RawString(file))

                data = DB.query(sql)
                ct = data.fetchone()[0]
                if ct <= 0:
                    find_execute_in_file(file, DB,2)
                    DB.query("INSERT INTO 'done_files'(F_path_SLD) VALUES('{0}')".format(string2RawString(file)))
                else:
                    print("File already done!")

                #
        except:
            print("Ignore garbage vals")
    DB.conn.close()

def main():
    #sou=sys.argv[0]
    sou=str(r"C:\xampp\htdocs\wolfcms")
    files=[]
    files = get_files("*.php", sou)
    html_files=get_files('*.html',sou)
    files=files+html_files
    print(len(files))
    '''run_SLR(sou,files)
    run_SLD(sou,files)
    form_link_replacement(sou,files)
'''
        #find_links()
    all_file_path_includer(files)

if __name__ == "__main__":
     main()
     input()
