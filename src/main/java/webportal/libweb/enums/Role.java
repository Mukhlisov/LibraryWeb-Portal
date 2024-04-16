package webportal.libweb.enums;

public enum Role {
    LIBRARIAN("librarian"),
    OWNER("owner");

    private String representation;

    Role (String representation){
        this.representation = representation;
    }

    public String getRepresentation(){
        return this.representation;
    }
}
