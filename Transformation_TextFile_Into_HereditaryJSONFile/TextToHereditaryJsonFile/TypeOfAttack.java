import java.io.*;
import java.util.ArrayList;

public class TypeOfAttack {
    private String type;
    private String line;

    public TypeOfAttack(File f) throws FileNotFoundException {

        try{
            BufferedReader buff = new BufferedReader(new FileReader(f));

            ArrayList<MyIps> arrayOfIps = new ArrayList<MyIps>();

            //Put every IP in an array to compare easily each element
            while ((line = buff.readLine()) != null) {
                MyIps ipClas = new MyIps(line);
                arrayOfIps.add(ipClas);
            }

            for(int i = 1; i<arrayOfIps.size(); i++){
                if(arrayOfIps.get(i).getIpDest().equals(arrayOfIps.get(i-1).getIpDest())){
                    if(arrayOfIps.get(i).getPort().equals(arrayOfIps.get(i-1).getPort())){
                        type = "dos";
                    }else if(arrayOfIps.get(i).getIpSrc().equals(arrayOfIps.get(i-1).getIpSrc())){
                        type = "scanning";
                    }
                }else{
                    type="other";
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getType() {
        return type;
    }
}
