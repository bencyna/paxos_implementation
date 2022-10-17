import java.io.DataOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

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


    Member(Boolean wantsPresidency, int chancesOfResponse, String name, int majority) {
        this.wantsPresidency = wantsPresidency;
        this.chancesOfResponse = chancesOfResponse;
        responseDelay = 0;
        this.name = name;
        this.majority = majority;
        maxIDAccepted = 0;
        acceptedPrevious = false;
    }

    public String getName() {
        return name;
    }

    public void Propose() throws Exception {
        if (!name.contains("M1")) {
            System.out.println("not m1: " + name);
            return;
        }
        Socket s2 = new Socket("localhost", 5432);
        DataOutputStream dout2=new DataOutputStream(s2.getOutputStream());  
        dout2.writeUTF(name);  
        s2.close();
    }

    public String Accept(String value, int ID) {
        if (maxIDAccepted > ID) {
            return "fail";
        }
        if (acceptedPrevious) {
            this.maxIDAccepted = ID;
            return "Accept " + ID + " accepted id = " + acceptedID;
        }
        System.out.println("accept: " + value + " ID: " + ID);
        this.maxIDAccepted = ID;
        acceptedPrevious = true;
        return "Accept " + ID +", " + value;
    }

    public void AcceptedProposal(String acceptorRes) throws Exception {
        int id = Integer.parseInt(acceptorRes.split("ccept")[1].split(",")[0].trim());
        String value = acceptorRes.split("ccept")[1].split(",")[1];

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
        if (countArr.get(idFoundIndex) >= majority) {
            String finalValue;
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

        // propose id again to acceptors
            Socket s2 = new Socket("localhost", 5432);
            DataOutputStream dout2=new DataOutputStream(s2.getOutputStream());  
            dout2.writeUTF(name + " id: " + idArr.get(idFoundIndex));  
            s2.close();
            System.out.println("socket connection request sent 2");
        }
    }


}