import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class IpDestJsonObject {
    private String futObjet;
    private JSONObject oldObject;
    //We need an array of IP dest to add them into the array of children of the first line
    private JSONArray arrayOfIpDest;

    public IpDestJsonObject(){
        arrayOfIpDest = new JSONArray();
    }

    //Create a simple child
    public void createNewSimpleObj(String ipdst){
        JSONObject object = new JSONObject();
        object.put("ipdst", ipdst);
        object.put("description", "IP destination");
        object.put("size", 1);
        arrayOfIpDest.add(object);
    }

    //Create a parent object that have children
    public void createNewSurObj(String ipdst){
        JSONObject object = new JSONObject();
        object.put("ipdst", ipdst);
        object.put("description", "IP destination");
        arrayOfIpDest.add(object);
        oldObject = object;
    }

    //Add the children into the parent object
    public JSONObject createSurObj(JSONObject oldObject,JSONArray arrayForIpDst){
        oldObject.put("children", arrayForIpDst);

        return oldObject;
    }

    public String getFutObjet() {
        return futObjet;
    }

    public JSONObject getOldObject() {
        return oldObject;
    }

    public JSONArray getArrayOfIpDest() {
        return arrayOfIpDest;
    }
}
