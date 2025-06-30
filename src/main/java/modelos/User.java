package modelos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Company;

public class User {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("address")
    private Adress address;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("website")
    private String website;
    @JsonProperty("company")
    private Company company;

    public int getId() {
        return id;
    }

    public Adress getAddress() {
        return address;
    }

    public Company getCompany() {
        return company;
    }
}