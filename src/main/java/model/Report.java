package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "report")
public class Report  {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "org")
    private String organization;

    @Column(name = "city")
    private String city;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String houseNumber;

    @Column(name = "category")
    private int category;

    @Column(name = "date")
    private String date;

    @Column(name = "start_per")
    private String start_per;

    @Column(name = "start_ref")
    private String start_ref;

    @Column(name = "stop_per")
    private String stop_per;

    @Column(name = "stop_ref")
    private String stop_ref;

    @Column(name = "danger")
    private short danger;



    public Report(String organization, String city, String neighborhood, String street, String houseNumber,
                  int category, String date, String start_per, String start_ref, String stop_per, String stop_ref, short danger) {
        this.organization = organization;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.houseNumber = houseNumber;
        this.category = category;
        this.date = date;
        this.start_per = start_per;
        this.start_ref = start_ref;
        this.stop_per = stop_per;
        this.stop_ref = stop_ref;
        this.danger = danger;
    }

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getDanger() {
        return danger;
    }

    public void setDanger(short danger) {
        this.danger = danger;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_per() {
        return start_per;
    }

    public void setStart_per(String start_per) {
        this.start_per = start_per;
    }

    public String getStart_ref() {
        return start_ref;
    }

    public void setStart_ref(String start_ref) {
        this.start_ref = start_ref;
    }

    public String getStop_per() {
        return stop_per;
    }

    public void setStop_per(String stop_per) {
        this.stop_per = stop_per;
    }

    public String getStop_ref() {
        return stop_ref;
    }

    public void setStop_ref(String stop_ref) {
        this.stop_ref = stop_ref;
    }
}
