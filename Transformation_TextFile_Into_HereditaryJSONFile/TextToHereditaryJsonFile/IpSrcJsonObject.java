import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class IpSrcJsonObject {
    private String futObjet;
    private JSONObject oldObject;
    //We need an array of IP dest to add them into the array of children of the first line
    private JSONArray arrayOfIpSrc;

    public IpSrcJsonObject(){
        arrayOfIpSrc = new JSONArray();
    }

    //Create a simple child
    public void createNewSimpleObj(String ipsrc){
        JSONObject object = new JSONObject();
        object.put("ipsrc", ipsrc);
        object.put("description", "IP source");
        object.put("size", 1);
        arrayOfIpSrc.add(object);
    }

    //Create a parent object that have children
    public void createNewSurObj(String ipsrc){
        JSONObject object = new JSONObject();
        object.put("ipsrc", ipsrc);
        object.put("description", "IP source");
        arrayOfIpSrc.add(object);
        oldObject = object;
    }

    //Add the children into the parent object
    public JSONObject createSurObj(JSONObject oldObject, JSONArray arrayForIpScr){
        oldObject.put("children", arrayForIpScr);

        return oldObject;
    }

    public JSONArray getArrayOfIpSrc() {
        return arrayOfIpSrc;
    }

    public String getFutObjet() { return futObjet; }

    public JSONObject getOldObject() {
        return oldObject;
    }
}
