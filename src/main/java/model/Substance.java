package model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "substance")
public class Substance {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long sub_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type" , nullable = false)
    private String type;

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "refId")
    private int refId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repo_id", nullable = false)
    private Report report;

    public Substance(String name, String type, double value, Report report, int refId) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.report = report;
        this.refId = refId;
    }

    public Substance() {
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "Substance{" +
                "sub_id=" + sub_id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", refId=" + refId +
                ", report=" + report +
                '}';
    }
}
