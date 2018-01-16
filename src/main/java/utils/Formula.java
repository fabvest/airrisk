package utils;

import model.Drugs;
import model.Report;
import model.Result;
import model.Substance;
import repository.RepoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public static double riskNoncarcinogenic(int idDgug, double AC){
        double result;
        Drugs drug = new Drugs();
        RepoImpl repo = new RepoImpl();
        try {
            drug = (Drugs) repo.getObject(Drugs.class, Long.valueOf(idDgug));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //result = AC / drug.getRfC();
        result = AC / 0.302221;

        return result;
    }

    public static void calculate(Long id){
        Report report = new Report();
        RepoImpl repo = new RepoImpl();
        ArrayList<Substance> substance = new ArrayList<Substance>();
        try {
            report = (Report) repo.getObject(Report.class, id);
            substance = repo.getSubByReport(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < substance.size(); i++){
            Substance sub = substance.get(i);
            double resC = riskCarcinogens(report.getCategory(), sub.getValue(), sub.getValue());
            Result result = new Result(sub.getName(), resC, true, report);
            try {
                repo.addObject(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < substance.size(); i++){
            Substance sub = substance.get(i);
            double resNC = riskNoncarcinogenic(sub.getRefId(), sub.getValue());
            Result result = new Result(sub.getName(), resNC, false, report);
            try {
                repo.addObject(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
