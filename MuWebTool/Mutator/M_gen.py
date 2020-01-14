
import sys

from base64 import b64encode


import bs4 as bs
import re
import os, fnmatch, ntpath, random
import sqlite3,  hashlib
import time

global overall_start
counter=0

overall_start=int(time.time())
global values
values=[]

class db_conn:
    def __init__(self):
        conn =""
        try:
            self.conn = sqlite3.connect(os.getcwd()+"\\database"+"\\mutant.db")
            ##print((os.getcwd()))
        except sqlite3.Error as e:
            #print("e")
            ggwp=1
    def query(self,  sql):
        cursor=self.conn.cursor()
        try:
            cursor.execute(sql)
            self.conn.commit()
        except sqlite3.Error as e:

            print("Cursor failed to execute query: '" + sql + "'" + " " + "Because of the following error:\n" + str(e))


        return cursor

class html_mutation:
    def __init__(self,  f_path, DB, files, app):
        self.DB=DB
        self.file=f_path
        self.files=files
        self.app=app
class php_mutation:
    def __init__(self,  f_path, DB, files, app):
        self.DB=DB
        self.file=f_path
        self.files=files
        self.app=app
class link_mutation(html_mutation):
    def find_links_single_file(self, file):
        links = []
        file_data=""
        f = open(file,  'r+')
        try:
            file_data = f.read()
        except:
            with open(file) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            file_data=file_data+ch
                except:
                    ggwp=1
                    #print("Ignoring special characters (Usually found in 'lingual' documents). File: ", file)
           # #print("Cant read file: ",  get_file_name(file))
       # #print(file_data)
        for r in re.findall(r'<a.*/a>',  file_data):
            soup1=bs.BeautifulSoup(str(r),'lxml')
            lis= soup1.find_all('a')
            if(len(lis)>1):
                for l in lis:
                    links.append(str(l))
                    print(l)
            else:
                links.append(r)
        soup=bs.BeautifulSoup(file_data, 'lxml')
        for l in soup.find_all('a'):
            if str(l) not in links:
                links.append(str(l))
        return links

class WLR(link_mutation):

    def link_mutate(self, links,  link_to_replace):
        b = True
        if (len(links) > 2):
            while (b == True):
                index = random.randint(0,  len(links) - 1)
                if (links[index] != link_to_replace):
                    b = False

            return links[index]
        elif(len(links)>1):
            for lin in links:
                if lin!=link_to_replace:

                    return lin
        elif (len(links)==1):
            linko=str(links[0])
            linko=str(linko)
            soup=bs.BeautifulSoup(str(linko), 'lxml')
            val = soup.find('a').get('href')
            try:
                linko.replace(val, "http://www.google.com")
            except:
                print(linko)
                #os.system("PAUSE")

            return str(linko)
        else:

            return 7

    def runner(self):
        links = self.find_links_single_file(self.file)
        for link in links:
            check = self.DB.query("SELECT COUNT(*) FROM mutant_table where Name='" +encode_MD5(link)+"'"+" AND Type='WLR'")
            try:
                ct=check.fetchone()[0]
            except:
                ct=0
            if (ct > 0):
                #print("Already mutated WLR")
                ggwp=1
            else:
                rep_link = self.link_mutate(links,  link)
                if (rep_link != 7):
                    #print("Replace link: " + link + "  With: " + rep_link)
                    link_md = encode_MD5(link)
                    data = self.DB.query("SELECT Name From mutant_table where Name='" + link_md + "'")
                    curs = self.DB.conn.cursor()
                    curs.execute("SELECT * FROM mutant_table ORDER BY id DESC LIMIT 1")
                    id = curs.fetchone()
                    try:
                         ID = id[0] + 1
                    except:
                        ID = 0
                    try:
                        add_to_db(ID,  link_md,  'WLR',  'HTML',  self.file,  0,  self.DB, encode_code(rep_link), encode_code(link), self.app)
                    except:
                        h=1
                else:
                    #print("No links in file!")
                    ggwp=1

class WLD(link_mutation):
    def delete_link(self, link):
        curs = self.DB.conn.cursor()
        curs.execute("SELECT * FROM mutant_table ORDER BY id DESC LIMIT 1")
        id = curs.fetchone()
        link_md = encode_MD5(link)
        try:
            href_php=re.findall("<a href="+'"'+".*>"+'"', link)
        except:
            print("ignore")
        temp_check_list=[]
        try:
            ID = id[0]
        except:
            ID = 0

        for hr in href_php:
            singleout_href=re.findall("href=\".*\"", hr)
            for h in singleout_href:
                linka=link
                hre=linka.replace(h, "href=\"#\"")
            ID+=1
            dele=hre
            add_to_db(ID,  link_md,  'WLD',  'HTML',  self.file,  0,  self.DB,  encode_code(dele), encode_code(link), self.app)

        soup=bs.BeautifulSoup(link, 'lxml')
        check = self.DB.query("SELECT COUNT(*) FROM mutant_table where Name='" + link_md + "'" + " AND Type='WLD'")
        ss = check.fetchone()
        if (ss[0] <= 0):
            for a in soup.find_all('a',  href=True,  text=True):

                key_word=a['href']
                ID+=1
                a=str(a)
                a=a.replace('href="'+key_word+'"', 'href="#"')
                dele=a
                dele=encode_code(dele)
                link=encode_code(link)
                check2 = self.DB.query("SELECT COUNT(*) FROM mutant_table where Mutant='" + dele + "'" + " AND Original='{0}'".format(link))
                ss2 = check.fetchone()
                if (ss[0] <= 0):
                    add_to_db(ID,  link_md,  'WLD',  'HTML',  self.file,  0,  self.DB,  dele,  link, self.app)


    def runner(self):
        links=self.find_links_single_file(self.file)
        for link in links:
            check = self.DB.query("SELECT COUNT(*) FROM mutant_table where Name='" + encode_MD5(link) + "'" + " AND Type='WLD'")
            ss=check.fetchone()
            if (ss[0]>0):
                #print("Already mutated WLD")
                ggwp=1
            else:
                self.delete_link(link)

class form_mutation(html_mutation):
    def find_form_single_file(self, file):
            f = open(file,  'r')
            file_data = ""
            try:
                file_data = f.read()
                soup = bs.BeautifulSoup(file_data,  'lxml')
                forms1 = soup.find_all("form")
            except:
                with open(file) as fileobj:
                    try:
                        for line in fileobj:
                            for ch in line:
                                file_data = file_data + ch
                    except:
                        ###print("Ignoring special characters (Usually found in 'lingual' documents). File: ",  file)
                        e = 3
            soup = bs.BeautifulSoup(file_data,  'lxml')
            forms1 = soup.find_all("form")
            return forms1

    def find_all_forms(self):
        forms = []
        for file in self.files:
            forms1=self.find_form_single_file(file)
            forms = forms + forms1
        return forms

    def replace_form(self, form_to_replace):
        forms=self.find_all_forms()
        i=0
        while(i!=len(forms) - 1):
            index = random.randint(0,  len(forms) - 1)
            if (encode_MD5(forms[index]) != encode_MD5(form_to_replace)):

                return forms[index]
            i+=1
        else:
            print("The document has only one or no forms")

class WFR(form_mutation):
    def find_original_action(self, ori):
        rep = ori.replace(r"&lt;",  r"<")
        rep = ori.replace(r"&gt;",  r">")
        soup = bs.BeautifulSoup(rep,  'lxml')
        a = soup.find('form').get("action")
        return a
    def replace_Flink(self, rep):
        rep = rep.replace(r"&lt;",  r"<")
        rep = rep.replace(r"&gt;",  r">")
        soup = bs.BeautifulSoup(rep,  'lxml')
        a = soup.find('form').get("action")
        return a
    def runner(self):
        forms=[]
        f= open(self.file, 'r')
        file_data=""
        try:
            file_data = f.read()
        except:
            with open(self.file) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            file_data = file_data + ch
                except:
                    ###print("Ignoring special characters (Usually found in 'lingual' documents). File: ",  file)
                    e=3

        forms=self.find_form_single_file(self.file)
        for form in forms:
            rep_form = str(self.replace_form(form))
            rep=self.replace_Flink(rep_form)
            check = self.DB.query("SELECT COUNT(*) FROM mutant_table where Name='" + encode_MD5(str(form)) + "'" + " AND Type='WFR'")
            ss = check.fetchone()
            if (ss[0] > 0):
                #print("Already mutated WFR")
                ggwp=1
            else:
                form=self.find_original_action(str(form))
                add_to_db(99,  encode_MD5(form),  'WFR',  'HTML',  str(self.file),  0,  self.DB,  encode_code(str(rep)), encode_code(str(form)), self.app)


       # #print("Form found in :", file)
class  WTR(form_mutation):
    def replacer(self, form, f_path):
        soup=bs.BeautifulSoup(str(form), 'lxml')
        print()
        try:
            if(re.search("post", str(soup.find("form").get("method")))!="None"):
                rep_form = "method=\"get\""

                add_to_db(99,  encode_MD5(form),  'WTR',  'HTML',  f_path,  0,  self.DB, encode_code(str(rep_form)),  encode_code("method=\"{0}\"".format(str(soup.find("form").get("method")))), self.app)
            elif (re.search("get",  str(soup.find("form").get("method"))) != "None"):
                rep_form = "method=\"post\""

                add_to_db(99,  encode_MD5(form),  'WTR',  'HTML',  f_path,  0,  self.DB, encode_code(str(rep_form)),  encode_code("method=\"{0}\"".format(str(soup.find("form").get("method")))), self.app)
            elif (re.search("GET",  str(soup.find("form").get("method"))) != "None"):
                rep_form = "method=\"post\""

                add_to_db(99,  encode_MD5(form),  'WTR',  'HTML',  f_path,  0,  self.DB, encode_code(str(rep_form)),  encode_code("method=\"{0}\"".format(str(soup.find("form").get("method")))), self.app)
            elif (re.search("POST",  str(soup.find("form").get("method"))) != "None"):
                rep_form = "method=\"get\""

                add_to_db(99,  encode_MD5(form),  'WTR',  'HTML',  f_path,  0,  self.DB, encode_code(str(rep_form)),  encode_code("method=\"{0}\"".format(str(soup.find("form").get("method")))), self.app)
                return rep_form
            else:
                return form
        except:
            print("?")
        #temp_form=re.sub(str(form)), method
    def runner(self):
        forms=self.find_form_single_file(self.file)
        for form in forms:
            check = self.DB.query("SELECT COUNT(*) FROM mutant_table where Name='" + encode_MD5(form) + "'" + " AND Type='WTR'")
            ss = check.fetchone()
            if (ss[0] > 0):
                ggwp = 1
            else:
                self.replacer(form, self.file)

class WHR(html_mutation):
    def find_hidden_inputs(self):
        file_data=""
        inputs=[]
        f= open(self.file, "r+")
        try:
            file_data=self.file.read()
        except:
            with open(self.file) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            file_data = file_data + ch
                except:
                    ###print("Ignoring special characters (Usually found in 'lingual' documents). File: ",  file)
                    e = 3
        inputs=re.findall("<input.*>", str(file_data))
        h_inputs=[]
        for i in range(0, len(inputs) - 1):
            soup=bs.BeautifulSoup(str(inputs[i]), 'lxml')
            type=soup.find('input').get("type")
            print("Type=", type)
            if(type=='hidden' or type=="\\\"hidden\\\""):
                h_inputs.append(inputs[i])
        return h_inputs
    def change_values(self):
        inputs=[]
        inputs=self.find_hidden_inputs()
        for Input in inputs:
            rep_inp=" "
            add_to_db(99, encode_MD5(Input), "WHR", "HTML", self.file, 0, self.DB, encode_code(str(rep_inp)), encode_code(str(Input)), self.app)

class WHD(WHR):
    def change_values(self):
        inputs = []
        inputs = self.find_hidden_inputs()
        for Input in inputs:
            soup = bs.BeautifulSoup(str(Input),  'lxml')
            inps = soup.find('input')
            rep_inp = str(Input).replace(inps.get('value'), " ")
            add_to_db(99,  encode_MD5(Input),  "WHR",  "HTML",  self.file,  0,  self.DB, encode_code(str(rep_inp)),  encode_code(Input), self.app)
class WIR(html_mutation):
    def div_includes(self, file_data):
        soup = bs.BeautifulSoup(str(file_data),  'lxml')
        divs= soup.find_all('div')
        for div in divs:
            p=p
    def find_includes(self):
        file_data = ""
        includes = []
        f = open(self.file,  "r+")
        try:
            file_data = self.file.read()
        except:
            with open(self.file) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            file_data = file_data + ch
                except:
                    ###print("Ignoring special characters (Usually found in 'lingual' documents). File: ",  file)
                    e = 3
        #includes = self.div_includes(file_data)
        includes= file_data.find('include')
        return includes
class sql_mutation:
    def __init__(self, f_path, DB, files):
        self.DB=DB
        self.file=f_path
        self.files=files
        self.file_data=""
        f = open(self.file,  "r+")
        try:
            self.file_data = self.file.read()
        except:
            with open(self.file) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            self.file_data = self.file_data + ch
                except:
                    ###print("Ignoring special characters (Usually found in 'lingual' documents). File: ",  file)
                    e = 3
class SODS(sql_mutation):
    def __init__(self):
        self.select=[]
    def find_select(self):
        self.select=re.findall("^(SELECT)(.*)")

class ARTH(php_mutation):
    def find_all_arithmatic(self):
        file_data = ""
        includes = []
        f = open(self.file, "r+")
        try:
            file_data = self.file.read()
        except:
            with open(self.file) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            file_data = file_data + ch
                except:
                    ###print("Ignoring special characters (Usually found in 'lingual' documents). File: ",  file)
                    e = 3
        cleaned = re.findall(r".*", file_data, re.MULTILINE)
        j = 0
        for i in cleaned:
            if (i.find("//") >= 0):
                cleaned[j] = '...,,,...,,,...,,,...,,,'
            elif (i.find("/*") >= 0):

                for k in range(j, len(cleaned) - 1):

                    if (cleaned[k].find("*/") >= 0):

                        for l in range(j, k + 1):
                            cleaned[l] = '>!>!>!>!>!'
                        break
            j += 1

        # print(cleaned)
        cleaned = list(filter(("").__ne__, cleaned))
        cleaned = list(filter(("...,,,...,,,...,,,...,,,").__ne__, cleaned))
        cleaned = list(filter(("#").__ne__, cleaned))
        cleaned = list(filter((">!>!>!>!>!").__ne__, cleaned))
        cleaned = list(filter((" *").__ne__, cleaned))
        cleaned_data=""

        for i in cleaned:

            if(len(re.findall("[A-za-z0-9() ]\+[()$ ]",i))>0):
                temp=i
                temp=temp.replace('+','-')

                add_to_db(99, encode_MD5(i), "ARTH", "PHP", self.file, 0, self.DB, encode_code(temp), encode_code(i), self.app)
            if (len(re.findall("[A-za-z0-9()$ ]\-[()$ ]",i))>0):
                temp=i
                temp=temp.replace('-', '+')
                add_to_db(99, encode_MD5(i), "ARTH", "PHP", self.file, 0, self.DB, encode_code(temp),encode_code(i), self.app)
            if (len(re.findall("[A-za-z0-9()$ ]\*[()$ ]",i))>0):
                temp=i
                temp=temp.replace('*', '/')
                add_to_db(99, encode_MD5(i), "ARTH", "PHP", self.file, 0, self.DB, encode_code(temp),encode_code(i), self.app)
            if (len(re.findall("[A-za-z0-9()$ ]\/[$ ]",i))>0):
                temp=i
                temp=temp.replace('/', '*')
                add_to_db(99, encode_MD5(i), "ARTH", "PHP", self.file, 0, self.DB, encode_code(temp),encode_code(i), self.app)
class BOOL(php_mutation):
    def runner(self):
        file_data = ""
        includes = []
        f = open(self.file, "r+")
        try:
            file_data = self.file.read()
        except:
            with open(self.file) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            file_data = file_data + ch
                except:
                    ###print("Ignoring special characters (Usually found in 'lingual' documents). File: ",  file)
                    e = 3
        cleaned = re.findall(r".*", file_data, re.MULTILINE)
        j = 0
        for i in cleaned:
            if (i.find("//") >= 0):
                cleaned[j] = '...,,,...,,,...,,,...,,,'
            elif (i.find("/*") >= 0):

                for k in range(j, len(cleaned) - 1):

                    if (cleaned[k].find("*/") >= 0):

                        for l in range(j, k + 1):
                            cleaned[l] = '>!>!>!>!>!'
                        break
            j += 1

        # print(cleaned)
        cleaned = list(filter(("").__ne__, cleaned))
        cleaned = list(filter(("...,,,...,,,...,,,...,,,").__ne__, cleaned))
        cleaned = list(filter(("#").__ne__, cleaned))
        cleaned = list(filter((">!>!>!>!>!").__ne__, cleaned))
        cleaned = list(filter((" *").__ne__, cleaned))
        cleaned_data=""
        for i in cleaned:

            if (len(re.findall("[$][\S][A-za-z0-9() ]and[ ][$()]", i)) > 0):
                    if(len(re.findall("[A-za-z0-9() ]and[()$ ][$()]", i))>0):
                        temp = i
                        temp = re.sub("[ ]and[()$ ][A-za-z$()]", " or ", temp)
                        print(i)
                        print(temp)
                        if i!=temp:
                            add_to_db(99, encode_MD5(i), "BOOL", "PHP", self.file, 0, self.DB, encode_code(temp), encode_code(i),self.app)
            elif(len(re.findall("[A-za-z0-9() ]and[()$ ][$()]", i))>0):
                temp = i
                temp = re.sub("[ ]and[()$ ][A-za-z$()]", " or ", temp)
                print(i)
                print(temp)

                if i != temp:
                    add_to_db(99, encode_MD5(i), "BOOL", "PHP", self.file, 0, self.DB, encode_code(temp),
                              encode_code(i), self.app)
            if (len(re.findall("[A-za-z0-9() ]or[ ][$()]", i)) > 0):
                    if(len(re.findall("[A-za-z0-9() ]or[()$ ][$()]", i))>0):
                        temp = i
                        temp = re.sub("[ ]or[()$ ][A-za-z$()]", " and ", temp)
                        print(i)
                        print(temp)

                        if i != temp:
                            add_to_db(99, encode_MD5(i), "BOOL", "PHP", self.file, 0, self.DB, encode_code(temp),
                                      encode_code(i), self.app)
            elif(len(re.findall("[A-za-z0-9() ]or[()$ ][$()]", i))>0):
                temp = i
                temp = re.sub("[ ]or[()$ ][A-za-z$()]", " and ", temp)
                print(i)
                print(temp)
                if i != temp:
                    add_to_db(99, encode_MD5(i), "BOOL", "PHP", self.file, 0, self.DB, encode_code(temp),encode_code(i), self.app)
            if (len(re.findall("[A-za-z0-9() ]\|\|[ ][$()]", i)) > 0):
                    if(len(re.findall("[A-za-z0-9() ]\|\|[()$ ][$()]", i))>0):
                        temp = i
                        temp = re.sub("[ ]\|\|[()$ ][A-za-z$()]", " && ", temp)
                        print(i)
                        print(temp)

                        if i != temp:
                            add_to_db(99, encode_MD5(i), "BOOL", "PHP", self.file, 0, self.DB, encode_code(temp),
                                      encode_code(i), self.app)
            elif(len(re.findall("[A-za-z0-9() ]\|\|[()$ ][$()]", i))>0):
                temp = i
                temp = re.sub("[ ]\|\|[()$ ][A-za-z$()]", " && ", temp)
                print(i)
                print(temp)
                if i != temp:
                    add_to_db(99, encode_MD5(i), "BOOL", "PHP", self.file, 0, self.DB, encode_code(temp),encode_code(i), self.app)
            if (len(re.findall("[A-za-z0-9() ]&&[ ][$()]", i)) > 0):
                    if(len(re.findall("[A-za-z0-9() ]&&[()$ ][$()]", i))>0):
                        temp = i
                        temp = re.sub("[ ]or[()$ ][A-za-z$()]", " || ", temp)
                        print(i)
                        print(temp)

                        if i != temp:
                            add_to_db(99, encode_MD5(i), "BOOL", "PHP", self.file, 0, self.DB, encode_code(temp),
                                      encode_code(i), self.app)
            elif(len(re.findall("[A-za-z0-9() ]&&[()$ ][$()]", i))>0):
                temp = i
                temp = re.sub("[ ]&&[()$ ][A-za-z$()]", " || ", temp)
                print(i)
                print(temp)
                if i != temp:
                    add_to_db(99, encode_MD5(i), "BOOL", "PHP", self.file, 0, self.DB, encode_code(temp),encode_code(i), self.app)

def get_file_name(path):
    tail=ntpath.split(path)
    return tail or ntpath.basename(path)

def get_files(pattern, path):
    result = []
    for root,  dirs,  files in os.walk(path):
        for name in files:
            if fnmatch.fnmatch(name, pattern):
                result.append(os.path.join(root,  name))
    return result


def string2RawString(string):
    co=0
    try:
        string=string.replace("\\", '/')
    except:
        file=open("lists", "w")
        for line in string:
            file.write(str(line))

    return string
def to_b64(st):
	return b64encode(st.encode("utf-8"))


def encode_code(code):
    #return codecs.encode(bytes(code, 'ascii'), 'hex_codec')
    #return code.replace("'", "''")
    #return "'"+"'"+"'"+code+"'"+"'"+"'"
    code=to_b64(code)
    code=code.decode('utf-8')
    return code

def encode_MD5(string):
    try:
        result = hashlib.md5(string.encode()).hexdigest()
        return result
    except:
        #print("Cant convert list to MD5 HASH")
        ggwp=1

def add_to_db(ID, f_name, type, family, f_path, killed, DB, mutant, original, app):
    DB.query("INSERT INTO `mutant_table`(ID, Name, Type, Family, File_path, Killed, Mutant, Original, Web_app) Values("+str(ID)+", '{0}', '{1}', '{2}', '{3}', {4}, '{5}', '{6}', '{7}')".format(f_name, type, family, f_path, killed, mutant, original, app))

def find_execute_in_file(f_path, DB, check, files, app):
    if check=='WLR':
        Html_rep_link = WLR(f_path, DB, files, app)
        Html_rep_link.runner()
    elif check=='WLD':
        OO=WLD(f_path, DB, files, app)
        OO.runner()
    elif check=='WFR':
        WFRs = WFR(f_path, DB, files, app)
        WFRs.runner()
    elif check=='WTR':
        WTRs=WTR(f_path, DB, files, app)
        WTRs.runner()
    elif check=='WHR':
        WHRs=WHR(f_path, DB, files, app)
        WHRs.change_values()
    elif check=='WIR':
        WIRs=WIR(f_path, DB, files, app)
        WIRs.find_includes()
    elif check=='ARTH':
        ARTHs=ARTH(f_path, DB, files, app)
        ARTHs.find_all_arithmatic()
    elif check=='BOOL':
        BOOLS=BOOL(f_path, DB, files, app)
        BOOLS.runner()
def check_done(operator_type, file):
    DB = db_conn()
    print(file)
    sql = "SELECT COUNT(*) FROM done_files WHERE F_path='{0}' AND Operator='{1}'".format(string2RawString(file), operator_type)
    data = DB.query(sql)
    try:
        ct = data.fetchone()[0]
    except:
        ct=0
    if(ct<=0):
        return False
    else:
        return True

def run_OPTRS(files, operator, app,left):
    DB = db_conn()
    curs = DB.conn.cursor()
    count=0
    start = int(time.time())
    tick=0
    total_files=len(files)*2

    for file in files:
        if check_done(operator,  file) == False:
            find_execute_in_file(file,  DB,  operator, files, app)
            #print("Done ", count)
            count=count+1
            DB.query("INSERT INTO 'done_files'(F_path, Operator, Web_app) VALUES('{0}', '{1}', '{2}')".format(string2RawString(file),  operator, app))
        else:
            ggwp=1
            #print("File already done!")
        os.system('cls')
        MINUTES, SECONDS=timer(int(time.time() - overall_start))
        if (tick > 15):
            tick = 1
        print("Time elapsed: ", MINUTES, " minutes and ", SECONDS, " seconds", "||||"*tick)
        tick += 1

        count+=1
        print("Operators left: {0}, Files completed: {1} out of {2}".format(left,count,total_files))

    # except:
    # #print("Ignore garbage vals")
    end = int(time.time()) - start
    fil= open("logger.txt", "a")
    das="Operator "+operator+" took {0} minutes and {1} seconds\n".format(str(MINUTES), str(SECONDS))
    fil.write(das)
    fil.close()
    return end
    DB.conn.close()
def timer(seconds):
    rem=seconds%60
    min=int(seconds/60)
    return min,  rem

def main_thread(path):
    """Main thread create childs of it"""
    files = []
    #print("?")
    time_dict={}
    HTML_operators = ['WLD','WLR','WFR', 'WTR','ARTH','BOOL']
    #HTML_operators  = ['BOOL']
    SQL_operators = ['WPA']
    files = get_files("*.php",  path)
    html_files = get_files('*.html',  path)
    files = files + html_files
    sqlfiles=files+get_files("*.sql", path)
    app=get_file_name(path)[1]

    print("Running...Please wait,  If an error or interrupt occurs it will be displayed ")
    opt_count=0
    for operator in HTML_operators:
        left=len(HTML_operators)-opt_count
        exec_time=run_OPTRS(files,  operator, app,left)
        time_dict[operator]=exec_time
        opt_count+=1
    print("DONE!: ")
    for operator in HTML_operators:
        minutes, seconds=timer(time_dict[operator])
        print("Operator '"+operator+"' took ", minutes, " minutes and ", seconds, " seconds")

def main(argv):
    #sou=sys.argv[0]
    sou=str(argv[0])
    #_thread.start_new_thread(main_thread, (sou, ))

    main_thread(sou)

if __name__ == '__main__':
     main(sys.argv[1:])
     #print("DONE EXECUTION/ NO ERR\nPress 'ENTER' key to continue....")
