import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Member {
    Boolean wantsPresidency;
    int chancesOfResponse;
    int responseDelay;
    String name;
    int majority;
    int maxIDAccepted;
    Boolean acceptedPrevious;
    String acceptedValue;
    int acceptedID;
    ArrayList<Integer> idArr = new ArrayList<Integer>();
    ArrayList<Integer> countArr = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> valueArr = new ArrayList<ArrayList<String>>();
    ArrayList<Integer> acceptIdArr = new ArrayList<Integer>();
    ArrayList<Integer> acceptCountArr = new ArrayList<Integer>();
    ArrayList<Integer> proposalIdsSent = new ArrayList<Integer>();
    Boolean proposalAccepted;
    int acceptCount;
    Boolean instantRes;


    Member(Boolean wantsPresidency, int chancesOfResponse, int responseDelay, String name, int majority, Boolean instantRes) {
        this.wantsPresidency = wantsPresidency;
        this.chancesOfResponse = chancesOfResponse;
        this.responseDelay = responseDelay;
        this.name = name;
        this.majority = majority;
        maxIDAccepted = 0;
        acceptedPrevious = false;
        proposalAccepted = false;
        acceptCount = 0;
        this.instantRes = instantRes;
        System.out.println(name + " created");
    }

    public String getName() {
        return name;
    }

    public void Prepare() throws Exception {
       if (wantsPresidency){
            if (willRespond() || instantRes) {
                if (!instantRes) {
                    causeDelay();
                }
                Socket s2 = new Socket("localhost", 5432);
                DataOutputStream dout2=new DataOutputStream(s2.getOutputStream());  
                dout2.writeUTF(name);  
                s2.close();
            }
       }
    }

    public String Accept(String value, int ID) throws Exception {
            if (!instantRes) {
                causeDelay();
            }
            if (!willRespond()) {
                return "fail";
            }
            if (maxIDAccepted >= ID) {
                System.out.println(getName() + " seen an ID: " + maxIDAccepted + " larger than currentID: " + ID);

                return "fail";
            }
            if (acceptedPrevious) {
                this.maxIDAccepted = ID;
                System.out.println(getName() + " already accepted a previous option currentID: " + ID + " accepted ID: " + acceptedID);
                return "Accept " + ID + " accepted id = " + acceptedID + " accepted value: " + acceptedValue;
            }
            this.maxIDAccepted = ID;
            // this.acceptedID = ID;
            // this.initAcceptedValue = value; 
            // acceptedPrevious = true;
            System.out.println(getName() + "accepted ID: " + ID);

            return "Accept " + ID +", " + value;
    }

    public void AcceptedPrep(String acceptorRes) throws Exception {
        int id; 
        String value;

        if (!willRespond()) {
            return;
        }

        if (!instantRes) {
            causeDelay();
        }

        if (acceptorRes.contains("=")) {
            value = acceptorRes.split("accepted value: ")[1];
            id = Integer.parseInt(acceptorRes.replaceAll("[^\\d.]", ""));
        }
        else {
            value = acceptorRes.split("ccept")[1].split(",")[1];
            id = Integer.parseInt(acceptorRes.split("Accept")[1].split(",")[0].trim());
        } 

        int idFoundIndex = -1;
        for (int i = 0; i < idArr.size(); i++) {
            if (idArr.get(i) == id) {
                idFoundIndex = i;
                break;
            }
        }
        if (idFoundIndex >= 0) {
            Boolean match = false;
            ArrayList<String> previousValuesSaved = valueArr.get(idFoundIndex);
            for (String prevValue : previousValuesSaved) {
                if (prevValue.equals(value)) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                previousValuesSaved.add(value);
                valueArr.set(idFoundIndex, previousValuesSaved);
            }

            countArr.set(idFoundIndex, countArr.get(idFoundIndex)+1);
        }
        else {
            idArr.add(id);
            countArr.add(1);
            ArrayList<String> newArrList = new ArrayList<String>();
            newArrList.add(value);
            valueArr.add(newArrList);
            idFoundIndex = idArr.size()-1;
        }

        // for (int k = 0; k < countArr.size(); k++) {
        //     System.out.println(getName() +": countARR: " + k + " " + countArr.get(k));
        //     System.out.println(getName() +"idARR: " + k + " " + idArr.get(k));
            
        //     for (int j = 0; j < valueArr.get(k).size(); j++) {
        //         System.out.println(getName() +": value: " + k + "inside: " + j + " "  + valueArr.get(k).get(j));
        //     }

            
        // }

        if (countArr.get(idFoundIndex) >= majority) {
            String finalValue = null;
            ArrayList<String> valuesSaved = valueArr.get(idFoundIndex);

            if (valuesSaved.size() > 1) {
                for (String valueStored : valuesSaved) {
                    if (!valueStored.equals(name)) {
                        finalValue = valueStored;
                        break;
                    }
                }
            }
            else {
                finalValue = valuesSaved.get(0);
            }
            if (finalValue == null) {
                finalValue = name;
            }
            // propose id again to acceptors if I haven't already sent a proposal out
            Boolean sentBefore = false;
            for (int i = 0; i < proposalIdsSent.size(); i++) {
                if (proposalIdsSent.get(i) ==  idArr.get(idFoundIndex)) {
                    sentBefore = true;
                    break;
                }
            }
            if (!sentBefore) {
            proposalIdsSent.add(idArr.get(idFoundIndex));
            Socket s2 = new Socket("localhost", 5432);
            DataOutputStream dout2=new DataOutputStream(s2.getOutputStream());  
            dout2.writeUTF(finalValue + " id: " + idArr.get(idFoundIndex));  
            s2.close();
            }
        }
    }

    public String AcceptProposal(String value, int ID) throws Exception {
        if (!willRespond()) {
            return "fail";
        }
        if (!instantRes) {
            causeDelay();
        }
        if (maxIDAccepted == ID) {
            proposalAccepted = true;
            this.acceptedID = ID;
            acceptedValue = value;

            // send accepted to all proposers and learners
            return "accepted " + ID;
        }
        
        return "fail";
    }

    public Boolean majorityAccepted(int id) {
        int idFoundIndex = -1;
        for (int i = 0; i < acceptIdArr.size(); i++) {
            if  (acceptIdArr.get(i) == id) {
                idFoundIndex = i;
                break;
            }
        }
        if (idFoundIndex >= 0) {
            acceptCountArr.set(idFoundIndex, acceptCountArr.get(idFoundIndex) + 1);
        }
        else {
            acceptIdArr.add(id);
            acceptCountArr.add(1);
            idFoundIndex = acceptIdArr.size()-1;
        }

        for (int i = 0; i < acceptIdArr.size(); i++) {
            System.out.println(name + "accepted dets: id: " + acceptIdArr.get(i) + " count: " + acceptCountArr.get(i) + " I: " + i);
        }
        // cant just be accept count because it may be a new id
        if (acceptCountArr.get(idFoundIndex) >= majority) {
            return true;
        }
        else {
            return false;
        }
    }

    public String[] getStats() {
        String[] stats = new String[5];
        stats[2] = Integer.toString(acceptedID);
        stats[1] = acceptedValue;
        stats[0] = name;
        if (proposalAccepted) {
            stats[3] = "true";
        }
        else {
            stats[3] = "false";
        }

        return stats;
    }

    private void causeDelay() throws Exception {
        Thread.sleep(responseDelay);
    }

    private Boolean willRespond() {
        Random rand = new Random(); 
        int int_random = rand.nextInt(100); 
        
        if (int_random > chancesOfResponse) {
            return false;
        }
        return true;
    }
}