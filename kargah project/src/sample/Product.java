package sample;

import java.time.LocalDate;

public class Product {
    public Product(String name, String code, LocalDate expireDate, LocalDate produceDate) {
        this.name = name;
        this.code = code;
        this.expireDate = expireDate;
        this.produceDate = produceDate;
        company = null;
    }

    private String name;
    private String code;
    private Company company;
    private LocalDate expireDate;
    private LocalDate produceDate;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public LocalDate getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }
    public LocalDate getProduceDate() {
        return produceDate;
    }
    public void setProduceDate(LocalDate produceDate) {
        this.produceDate = produceDate;
    }

    public void save(){
        Main.getApp().getProductFile().save(this);
    }


    public void remove(){
        Main.getApp().getProductFile().remove(this);
    }

    public void edit(){
        Main.getApp().getProductFile().edit(this);
    }

}
