package sample;

public class Employee extends Person {
    public Employee(String firstName, String lastName,String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        company = null;
    }

    private String firstName;
    private String lastName;
    private Company company;
    private String job;

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
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }




    public void save(){
        Main.getApp().getEmployeeFile().save(this);
    }

    public void remove(){
        Main.getApp().getEmployeeFile().remove(this);
    }

    public void edit(){
        Main.getApp().getEmployeeFile().edit(this);

    }

}
