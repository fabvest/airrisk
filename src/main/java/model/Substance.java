package model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "substance")
public class Substance {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id", insertable = false, updatable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long sub_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type" , nullable = false)
    private String type;

    @Column(name = "value", nullable = false)
    private double value;

    public Substance(String name, String type, double value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public Substance() {
    }

    public Long getId() {
        return sub_id;
    }

    public void setId(Long id) {
        this.sub_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}