import ntpath
import os
import sqlite3

import bs4 as bs
import re
import sys
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

        except sqlite3.Error as e:
            file=open("logger.txt","a+")
            file.write("Cursor failed to execute query: '"+sql+"'"+" "+"Because of the following error:\n"+str(e))
            print("Cursor failed to execute query: '"+sql+"'"+" "+"Because of the following error:\n"+str(e))

            file.close()
            ggwp = 1
        return cursor
def get_file_name(path):
    tail=ntpath.split(path)
    return  ntpath.basename(path)


def main(argv):
    name = str(get_file_name(argv[0]))
    print(name)
    DB= db_conn();
    WLD_Kills=0
    WLD_count=0
    WLR_Kills=0
    WLR_count=0
    WFR_Kills=0
    WFR_count=0
    WTR_count=0
    WTR_Kills=0
    ARTH_count=0
    ARTH_Kills=0
    BOOL_count=0
    BOOL_Kills=0
    extract=DB.query("SELECT * FROM mutant_table Where Killed='1' and Type='WLD'")
    for row in extract:
        WLD_Kills+=1
    extract = DB.query("SELECT * FROM mutant_table Where Killed='1' and Type='WLR'")
    for row in extract:
        WLR_Kills += 1
    extract = DB.query("SELECT * FROM mutant_table Where Killed='1' and Type='WFR'")
    for row in extract:
        WFR_Kills += 1
    extract = DB.query("SELECT * FROM mutant_table Where Killed='1' and Type='WTR'")
    for row in extract:
        WTR_Kills += 1
    extract = DB.query("SELECT * FROM mutant_table Where Killed='1' and Type='ARTH'")
    for row in extract:
        ARTH_Kills += 1
    extract = DB.query("SELECT * FROM mutant_table Where Killed='1' and Type='BOOL'")
    for row in extract:
        BOOL_Kills += 1
    extract = DB.query("SELECT COUNT(*) FROM mutant_table Where Type='BOOL'")
    for row in extract:
        BOOL_count=row[0]
    extract = DB.query("SELECT COUNT(*) FROM mutant_table Where Type='WTR'")
    for row in extract:
        WTR_count = row[0]
    extract = DB.query("SELECT COUNT(*) FROM mutant_table Where Type='WFR'")
    for row in extract:
        WFR_count = row[0]
    extract = DB.query("SELECT COUNT(*) FROM mutant_table Where Type='WLD'")
    for row in extract:
        WLD_count = row[0]
    extract = DB.query("SELECT COUNT(*) FROM mutant_table Where Type='WLR'")
    for row in extract:
        WLR_count = row[0]
    extract = DB.query("SELECT COUNT(*) FROM mutant_table Where Type='ARTH'")
    for row in extract:
        ARTH_count = row[0]

    file=open(r"E:\Mutant_generator\Mutator\Report\Test report.html",'r+')
    data=file.read()
    file.close()
    soup=bs.BeautifulSoup(str(data),'lxml')

    file = open(r"E:\Mutant_generator\Mutator\Report\Test report.html", 'w')
    title=soup.find('h1').text
    title2="MuGET report of: "+name
    data=data.replace(title,title2)
    WFR=soup.find('tr',{"id": "ARTH"})
    count=0
    print(data.find(str(WFR)))
    WFRD=data.replace(str(WFR),"<tr id='WFR'>\n<td>WFR<\\td>\n<td>HTML<\\td>\n<td>{0}<\\td>\n<td>{1}<\\td>\n<td>{2}<\\td>\n<td>{3}<\\td>\n<td>{4}<\\td>".format(WFR_count,str(0),WFR_Kills,int(WFR_Kills)/int(WFR_count),str(0),data))
    print(WFRD)

    file.write(data)
    file.close()
if __name__ == '__main__':
    main(sys.argv[1:])