import os
import time



def timer(seconds):
    rem=seconds%60
    min=int(seconds/60)
    return min,  rem
def main():
    overall_start = int(time.time())
    os.chdir(r"C:\Users\pc\Desktop")
    os.system("run_test.bat")
    os.system("cls")
    MINUTES, SECONDS = timer(int(time.time() - overall_start))
    print("Test took {0} minutes and {1} seconds".format(str(MINUTES),str(SECONDS)))
    os.system("PAUSE")

main()