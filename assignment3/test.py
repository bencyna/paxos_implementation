from subprocess import run, Popen
import time
from pathlib import Path
import os
import glob

def testInstant():
    print("running instant response time test: outcome typically m1 as it is created first, can be both m2 and m3 too")
    f = open("consensusValue.txt", "w")
    f.write(" ")
    f.close()
    time.sleep(1)
    paxos = Popen(["java", "Paxos", "default", "true"])
    time.sleep(3)

    with open("./consensusValue.txt") as output:
        outcome = output.readline()
        print(f"outcome: {outcome}")
        if (outcome == "Member M1" or outcome == "Member M2" or outcome == "Member M3"):
            print("test case passed outcome: " + outcome)

    paxos.terminate()

def testA3():
    pass

def testNMembers():
    pass


testInstant()
testA3()
testNMembers()