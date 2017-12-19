package model;

import java.util.ArrayList;

public class Holder {
    Report report;
    ArrayList<Substance> substances;
    ArrayList<Result> results;

    public Holder(Report report, ArrayList<Substance> substances, ArrayList<Result> results) {
        this.report = report;
        this.substances = substances;
        this.results = results;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public ArrayList<Substance> getSubstances() {
        return substances;
    }

    public void setSubstances(ArrayList<Substance> substances) {
        this.substances = substances;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
