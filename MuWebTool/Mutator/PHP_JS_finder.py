import hashlib
import os
import fnmatch
import re



def get_files(pattern,path):
    result = []
    for root, dirs, files in os.walk(path):
        for name in files:
            if fnmatch.fnmatch(name,pattern):
                result.append(os.path.join(root, name))
    return result

def PHP_finder(ffpath):
        num_lines=0
        file_data=""
        files=get_files("*.php",ffpath)
        ID=0
        for file in files:
            try:
                file_data = open(file, 'r').read()
            except:
                with open(file) as fileobj:
                    try:
                        for line in fileobj:
                            for ch in line:
                                file_data = file_data + ch
                    except:
                        "ignore"
            cleaned = re.findall(r".*",file_data,re.MULTILINE)
            j=0
            for i in cleaned:
                if(i.find("//")>=0):
                   cleaned[j]='...,,,...,,,...,,,...,,,'
                elif(i.find("/*")>=0):

                    for k in range(j,len(cleaned)-1):

                        if(cleaned[k].find("*/")>=0):

                            for l in range(j,k+1):
                                cleaned[l]='>!>!>!>!>!'
                            break
                j+=1

            #print(cleaned)
            cleaned=list(filter(("").__ne__, cleaned))
            cleaned = list(filter(("...,,,...,,,...,,,...,,,").__ne__, cleaned))
            cleaned = list(filter(("#").__ne__, cleaned))
            cleaned = list(filter((">!>!>!>!>!").__ne__, cleaned))
            cleaned = list(filter((" *").__ne__, cleaned))

            try:
                #print(ID," ",file)
                #print(ID,"  ",cleaned)
                #print(file_data)
                #print("|||||||||||||| ",file)
                num_lines += sum(1 for line in cleaned)

            except:
                "ignore"
            ID+=1
        print("PHP LINES: ",num_lines," IN {0} FILES".format(len(files)))
def JS_logic(file_data):
    cleaned = re.findall(r".*", file_data, re.MULTILINE)
    #print("ORIGINAL: ",cleaned)
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
    return cleaned
def JS_from_HTML(file_data):
    num_lines=0
    cleaned = re.findall(r".*", file_data, re.MULTILINE)
    j=0
    script=[]
    for i in cleaned:
        if(i.find("<script")<0 or i.find("script>")<0):
            script.append(i)
            cleaned[j]=".,.,.,.,.,"
        j+=1
    cleaned = list(filter((".,.,.,.,.,").__ne__, cleaned))
    num_lines += sum(1 for line in cleaned)
    k=0
    count=0
    for i in script:
        if(i.find("<script")>=0):
            l=k
            while(script[l].find("script>")<=0):
                count+=1
                l+=1
            #print(i)
    return num_lines+count

def JS_finder(path):
    num_lines = 0
    file_data = ""

    files = get_files("*.js", path)

    html= get_files("*.html", path)
    php=get_files("*.php",path)
    html=html+php
    count_returned=0
    for f in html:
        try:
            fd = open(f, 'r').read()
        except:
            with open(f) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            fd = fd + ch
                except:
                    "ignore"
        count_returned+=JS_from_HTML(fd)
    ID = 0
    for file in files:
        try:
            file_data = open(file, 'r').read()
        except:
            with open(file) as fileobj:
                try:
                    for line in fileobj:
                        for ch in line:
                            file_data = file_data + ch
                except:
                    "ignore"
        #print(file)
        cleaned=JS_logic(file_data)
        try:
            #print(ID, " ", file)
            #print(ID, "  ", cleaned)
            # print(file_data)
            # print("|||||||||||||| ",file)
            num_lines += sum(1 for line in cleaned)

        except:
            "ignore"
        ID += 1
    #print(".js files",len(files))
    num=len(files)+len(html)
    print("JavaScript LINES: ", num_lines+count_returned, " IN {0} FILES".format(num))
def main():
    path="C:\\xampp\\htdocs\\timeclock"
    PHP_finder(path)
    JS_finder(path)
main()


