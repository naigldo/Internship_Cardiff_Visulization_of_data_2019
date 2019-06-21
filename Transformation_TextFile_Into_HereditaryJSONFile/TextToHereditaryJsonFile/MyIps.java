public class MyIps {
    private String line;
    private String ipSrc;
    private String ipDest;
    private String port;

    public MyIps(String l){
        line = l; //The line is the line of the file were we want to extract the informations
        setPositionSrc(line);
        setPositionDest(line);
        setPositionPort(line);
    }

    //Extract the position of the IP source
    public void setPositionSrc(String line){
        int positionSrc = line.indexOf("src=");
        int positionDst = line.indexOf("dst=");

        ipSrc = line.substring(positionSrc + 4, positionDst - 1);
    }

    //Extract the position of the IP destination
    public void setPositionDest(String line){
        int positionDst = line.indexOf("dst=");
        int positionSrcPort = line.indexOf("src_port=");

        ipDest = line.substring(positionDst + 4, positionSrcPort - 1);
    }

    //Extract the position of the port destination
    public void setPositionPort(String line){
        int positionDstPort = line.indexOf("dst_port=");
        int positionSrcXlated = line.indexOf("src-xlated");

        port = line.substring(positionDstPort + 9, positionSrcXlated - 1);
    }

    public String getLine() {
        return line;
    }

    public String getIpSrc() {
        return ipSrc;
    }

    public String getIpDest() {
        return ipDest;
    }

    public String getPort() {
        return port;
    }
}
