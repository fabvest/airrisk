package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "organization")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_id", nullable = false)
    private Substance substance;


}
