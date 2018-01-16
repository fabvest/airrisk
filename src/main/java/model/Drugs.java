package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "drugs")
public class Drugs {
    @javax.persistence.Id
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "IUPAC")
    private String IUPAC;

    @Column(name = "CAS")
    private String CAS;

    @Column(name = "M")
    private double M;

    @Column(name = "RfC")
    private double RfC;

    @Column(name = "RfD")
    private double RfD;

    @Column(name = "GIABS")
    private double GIABS;

    @Column(name = "H")
    private double H;

    @Column(name = "Dw")
    private double Dw;

    @Column(name = "Da")
    private double Da;

    @Column(name = "Organs")
    private String Organs;

    @Column(name = "Carcinogenic")
    private String Carcinogenic;

    @Column(name = "Vp")
    private double Vp;

    @Column(name = "t")
    private int t;

    @Column(name = "S")
    private double S;

    @Column(name = "Kow")
    private double Kow;

    @Column(name = "Kp")
    private double Kp;

    @Column(name = "Koc")
    private double Koc;

    public Drugs(int id, String IUPAC, String CAS, double m, double rfC, double rfD, double GIABS, double h, double dw, double da, String organs, String carcinogenic, double vp, int t, double s, double kow, double kp, double koc) {
        this.id = id;
        this.IUPAC = IUPAC;
        this.CAS = CAS;
        M = m;
        RfC = rfC;
        RfD = rfD;
        this.GIABS = GIABS;
        H = h;
        Dw = dw;
        Da = da;
        Organs = organs;
        Carcinogenic = carcinogenic;
        Vp = vp;
        this.t = t;
        S = s;
        Kow = kow;
        Kp = kp;
        Koc = koc;
    }

    public Drugs() {
    }

    public String getIUPAC() {
        return IUPAC;
    }

    public void setIUPAC(String IUPAC) {
        this.IUPAC = IUPAC;
    }

    public String getCAS() {
        return CAS;
    }

    public void setCAS(String CAS) {
        this.CAS = CAS;
    }

    public double getM() {
        return M;
    }

    public void setM(double m) {
        M = m;
    }

    public double getRfC() {
        return RfC;
    }

    public void setRfC(double rfC) {
        RfC = rfC;
    }

    public double getRfD() {
        return RfD;
    }

    public void setRfD(double rfD) {
        RfD = rfD;
    }

    public double getGIABS() {
        return GIABS;
    }

    public void setGIABS(double GIABS) {
        this.GIABS = GIABS;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }

    public double getDw() {
        return Dw;
    }

    public void setDw(double dw) {
        Dw = dw;
    }

    public double getDa() {
        return Da;
    }

    public void setDa(double da) {
        Da = da;
    }

    public String getOrgans() {
        return Organs;
    }

    public void setOrgans(String organs) {
        Organs = organs;
    }

    public String getCarcinogenic() {
        return Carcinogenic;
    }

    public void setCarcinogenic(String carcinogenic) {
        Carcinogenic = carcinogenic;
    }

    public double getVp() {
        return Vp;
    }

    public void setVp(double vp) {
        Vp = vp;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public double getS() {
        return S;
    }

    public void setS(double s) {
        S = s;
    }

    public double getKow() {
        return Kow;
    }

    public void setKow(double kow) {
        Kow = kow;
    }

    public double getKp() {
        return Kp;
    }

    public void setKp(double kp) {
        Kp = kp;
    }

    public double getKoc() {
        return Koc;
    }

    public void setKoc(double koc) {
        Koc = koc;
    }
}
