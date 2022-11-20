# PAXOS Assignment 3 Distributed Systems 

## Major design decisions
To ensure failures still manage to reach conensus, as long as one proposer remains online, a new proposal can be sent after a timeout. If a proposer sends a prepare and then dies, it cannot enter phase 2, so cannot be elected. 




## Compile and run instructions for linux 
compile:
```
javac Member.java Paxos.java PaxosImplementation.java M1.java M2.java M3.java M4.java ProposalThread.java
```
start server  

to run the default m1-m9 with immediate response times use this command

```
java Paxos default true
```

to run the default m1-m9 according to their member profiles outlined in assignment 3 run this command below
```
java Paxos default false
```

and to run the paxos implementation with failures use this command: 
```
java Paxos failure
```



## Testing instructions
the testing in this covers cases where m1, m2 and m3 send proposals at the same time and all respond instantly  

it covers the memeber outlines typical random case  

and it covers the case where m1 and m2 propose and go offline. 

to execute the tests first ensure you have compiled the program, then run this command 
```
python3 test.py 
```

