package utils;

import com.sun.org.apache.xpath.internal.operations.Equals;
import model.Drugs;
import model.Report;
import model.Result;
import model.Substance;
import repository.RepoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Formula {

        public static Double riskCarcinogens(int type, double cA, double cH){
        double result;

        switch (type){
            case 1:
                result = ((cA * Ref.tOutKid * Ref.vOutKid + cH * Ref.tInKid * Ref.vInKid)
                        * Ref.EF * Ref.EDKid) / (Ref.BWKid * Ref.AT * 365);
                break;
            case 2:
                result = ((cA * Ref.tOutTeen * Ref.vOutTeen + cH * Ref.tInTeen * Ref.vInTeen)
                        * Ref.EF * Ref.EDTeen) / (Ref.BWTeen * Ref.AT * 365);
                break;
            case 3:
                result = ((cA * Ref.tOutAdults * Ref.vOutAdults + cH * Ref.tInAdults * Ref.vInAdult)
                        * Ref.EF * Ref.EDAdult) / (Ref.BWAdult * Ref.AT * 365);
                break;
            default:
                return null;
        }

        return result;
    }

    public static double riskNoncarcinogenic(Drugs drug, double AC){
        double result;
        result = AC / drug.getRfC();
        return result;
    }

    public static void calculate(Long id){
        Report report = new Report();
        RepoImpl repo = new RepoImpl();
        Drugs drug = new Drugs();
        ArrayList<Substance> substance = new ArrayList<>();
        try {
            report = (Report) repo.getObject(Report.class, id);
            substance = repo.getSubByReport(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < substance.size(); i++){
            Substance sub = substance.get(i);
            try {
                drug = (Drugs) repo.getObject(Drugs.class, (long) sub.getRefId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String carc = drug.getCarcinogenic();
            if(carc.length() == 6) {
                double resC = riskCarcinogens(report.getCategory(), sub.getValue(), sub.getValue());
                Result result = new Result(sub.getName(), resC, true, report);
                try {
                    repo.addObject(result);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i = 0; i < substance.size(); i++){
            Substance sub = substance.get(i);
            try {
                drug = (Drugs) repo.getObject(Drugs.class, (long) sub.getRefId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String carc = drug.getCarcinogenic();
            if(carc.length() == 4) {
                double resNC = riskNoncarcinogenic(drug, sub.getValue());
                Result result = new Result(sub.getName(), resNC, false, report);
                try {
                    repo.addObject(result);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
