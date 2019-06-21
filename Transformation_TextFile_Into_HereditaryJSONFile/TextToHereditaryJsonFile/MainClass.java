import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) throws Exception {
        File input = new File("C:\\Users\\c1972519\\Desktop\\dos.txt");
        File output = new File("C:\\Users\\c1972519\\Desktop\\output.json");

        try {
            BufferedReader buff = new BufferedReader(new FileReader(input));

            TypeOfAttack typeAttack = new TypeOfAttack(input);

            try {
                String line;
                JSONObject finalObject = new JSONObject();

                ArrayList<MyIps> arrayOfIps = new ArrayList<MyIps>();

                //Add every element to the array
                while ((line = buff.readLine()) != null) {
                    MyIps ipClas = new MyIps(line);
                    arrayOfIps.add(ipClas);

                }

                //Call either the DOS method or the Scanning one depedning on the type of attack
                if(typeAttack.getType().equals("dos")){
                    finalObject = dosAttack(arrayOfIps);
                }else if(typeAttack.getType().equals("scanning")){
                    finalObject = scanningAttack(arrayOfIps);
                }else{
                    System.out.println("There is neither DOS attack nor Port Scanning attack in this file. Please try with another file.");
                }

                //Write the first line that contains all the other JSON object into the JSON file
                try (FileWriter writer = new FileWriter(output)) {
                    writer.write(finalObject.toJSONString());
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }finally{
                buff.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //The first line is essential
    public static JSONObject createFirstLine(JSONArray arrayOfIpDst){
        JSONObject firstLine = new JSONObject();
        firstLine.put("IPDEST", "null");
        firstLine.put("children", arrayOfIpDst);

        return firstLine;
    }

    public static JSONObject dosAttack(ArrayList<MyIps> array){
        //create classes
        IpDestJsonObject newIpDest = new IpDestJsonObject();
        PortJsonObject newPort = new PortJsonObject();
        IpSrcJsonObject newIpSrc = new IpSrcJsonObject();

        //create simple json object
        newIpDest.createNewSurObj(array.get(1).getIpDest());
        newPort.createNewSurObj(array.get(1).getPort());

        for(int i = 1; i<array.size(); i++) {
            newIpSrc.createNewSimpleObj(array.get(i).getIpSrc());
        }

        //create surobject
        newPort.createSurObj(newPort.getOldObject(), newIpSrc.getArrayOfIpSrc());
        newIpDest.createSurObj(newIpDest.getOldObject(), newPort.getArrayOfPort());

        return createFirstLine(newIpDest.getArrayOfIpDest());
    }

    public static JSONObject scanningAttack(ArrayList<MyIps> array){
        //create the class
        IpDestJsonObject newIpDest = new IpDestJsonObject();
        PortJsonObject newPort = new PortJsonObject();
        IpSrcJsonObject newIpSrc = new IpSrcJsonObject();

        //create simple json object
        newIpDest.createNewSurObj(array.get(1).getIpDest());
        newIpSrc.createNewSurObj(array.get(1).getIpSrc());

        for(int i = 1; i<array.size(); i++) {
            newPort.createNewSimpleObj(array.get(i).getPort());
        }

        //create surobject
        newIpSrc.createSurObj(newIpSrc.getOldObject(), newPort.getArrayOfPort());
        newIpDest.createSurObj(newIpDest.getOldObject(), newIpSrc.getArrayOfIpSrc());

        return createFirstLine(newIpDest.getArrayOfIpDest());
    }
}