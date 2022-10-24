from subprocess import run, Popen
import time
from pathlib import Path
import os
import glob

def testInstant():
    paxos = Popen("java" + " Paxos", shell=True)
    time.sleep(1)
    paxos.communicate("default")
    # time.sleep(1)
    # paxos.terminate()
    pass

def testA3():
    pass

def testNMembers():
    pass


testInstant()
testA3()
testNMembers()