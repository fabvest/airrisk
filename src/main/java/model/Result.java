package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long res_id;

    @Column(name = "res_name")
    private String res_name;

    @Column(name = "value")
    private double value;

    @Column(name = "type")
    private boolean type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repo_id", nullable = false)
    private Report report;

    public Result(String res_name, double value, boolean type, Report report) {
        this.res_name = res_name;
        this.value = value;
        this.type = type;
        this.report = report;
    }

    public Result() {
    }

    public Long getRes_id() {
        return res_id;
    }

    public void setRes_id(Long res_id) {
        this.res_id = res_id;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
