import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PortJsonObject {
    private String futObjet;
    private JSONObject oldObject;
    //We need an array of IP dest to add them into the array of children of the first line
    private JSONArray arrayOfPort;

    public PortJsonObject(){
        arrayOfPort = new JSONArray();
    }

    //Create a simple child
    public void createNewSimpleObj(String port){
        JSONObject object = new JSONObject();
        object.put("port", port);
        object.put("description", "Port destination");
        object.put("size", 1);
        arrayOfPort.add(object);
    }

    //Create a parent object that have children
    public void createNewSurObj(String port){
        JSONObject object = new JSONObject();
        object.put("port", port);
        object.put("description", "Port destination");
        arrayOfPort.add(object);
        oldObject = object;
    }

    //Add the children into the parent object
    public JSONObject createSurObj(JSONObject oldObject, JSONArray arrayForPort){
        oldObject.put("children", arrayForPort);

        return oldObject;
    }

    public String getFutObjet() {
        return futObjet;
    }

    public JSONObject getOldObject() {
        return oldObject;
    }

    public JSONArray getArrayOfPort() {
        return arrayOfPort;
    }
}
