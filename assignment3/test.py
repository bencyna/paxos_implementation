from subprocess import run, Popen
import time
from pathlib import Path
import os
import glob

def testInstant():
    print("\nrunning instant response time test: outcome typically m1 as it is created first, can be both m2 and m3 too\n")
    f = open("consensusValue.txt", "w")
    f.write(" ")
    f.close()
    time.sleep(1)
    paxos = Popen(["java", "Paxos", "default", "true"])
    time.sleep(3)

    with open("./consensusValue.txt") as output:
        outcome = output.readline()
        print(f"\noutcome: {outcome}")
        if (outcome == "Member M1" or outcome == "Member M2" or outcome == "Member M3"):
            print("test case passed outcome: " + outcome)

    paxos.terminate()

def testA3():
    print("\nrunning A3 outline test: outcome expected 1 of M1, M2 or M3 still\n")
    f = open("consensusValue.txt", "w")
    f.write(" ")
    f.close()
    time.sleep(1)
    paxos = Popen(["java", "Paxos", "default", "false"])
    time.sleep(10)

    sleepCount = 0
    outcome = " "
    while (True):
        if (sleepCount >= 60):
            print("\nbeen about a minute, timing out...\n")
            break
        
        with open("./consensusValue.txt") as output:
            outcome = output.readline()
            time.sleep(1)
            sleepCount += 1
            if (outcome == " "):
                continue

            print(f"\noutcome: {outcome}")
            if (outcome == "Member M1" or outcome == "Member M2" or outcome == "Member M3"):
                print("test case passed outcome: " + outcome)
            else:
                print("test case failed!")
            break

    paxos.terminate()


def testM3Failure():
    print("\nrunning A3 outline test: outcome expected 1 of M1, M2, and M3 - M2 and M3 will go offline after proposing so they won't be able to get to phase 2, however, they can be restarted and respond after intitial offline\n")
    f = open("consensusValue.txt", "w")
    f.write(" ")
    f.close()
    time.sleep(1)
    paxos = Popen(["java", "Paxos", "failure"])
    time.sleep(10)

    sleepCount = 0
    outcome = " "
    while (True):
        if (sleepCount >= 60):
            print("been about a minute, timing out...")
            break
        
        with open("./consensusValue.txt") as output:
            outcome = output.readline()
            time.sleep(1)
            sleepCount += 1
            if (outcome == " "):
                continue

            print(f"\noutcome: {outcome}")
            if (outcome == "Member M1" or outcome == "Member M2" or outcome == "Member M3"):
                print("test case passed outcome: " + outcome)
            else:
                print("test case failed!")
            break

    paxos.terminate()

    
testInstant()
testA3()
testM3Failure()