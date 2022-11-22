package sample;

public class Manager extends Person {
    public Manager(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        company = null;
    }

    private String firstName;
    private String lastName;
    private Company company;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }




    public void save(){
        Main.getApp().getManagerFile().save(this);
    }

    public void remove(){
        Main.getApp().getManagerFile().remove(this);
    }

    public void edit(){
        Main.getApp().getManagerFile().edit(this);

    }

}
