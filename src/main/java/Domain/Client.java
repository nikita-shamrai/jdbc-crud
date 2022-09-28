package Domain;

import java.util.Date;

public class Client {
    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", Country='" + Country + '\'' +
                ", City='" + City + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

    private int id;
    private String name;
    private String Country;
    private String City;
    private Date registrationDate;

    public Client(String name, String country, String city, Date registrationDate) {
        this.name = name;
        Country = country;
        City = city;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
