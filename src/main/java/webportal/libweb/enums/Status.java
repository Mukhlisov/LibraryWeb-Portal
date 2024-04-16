package webportal.libweb.enums;

public enum Status {

    FREE("free"),
    ORDERED("ordered"),
    RENTED("rented");

    private String representation;

    Status(String representation){
        this.representation = representation;
    }

    public String getRepresentation(){
        return  this.representation;
    }
}
