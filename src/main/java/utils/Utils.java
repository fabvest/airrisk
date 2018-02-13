package utils;

import model.*;
import repository.RepoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class Utils {
    public Long convertAndSave(UserInfo userInfo){
        Report report = new Report();
        Substance substance = new Substance();
        RepoImpl repo = new RepoImpl();

        report.setOrganization(userInfo.getOrgControl());
        String[] cat = userInfo.getCatPeopleControl().split("-");
        report.setCategory(Integer.parseInt(cat[1]));
        report.setCity(userInfo.getCityControl());
        report.setDate(userInfo.dateControl);
        report.setHouseNumber(String.valueOf(userInfo.getHouseControl()));
        report.setNeighborhood(userInfo.getDistrictControl());
        report.setStreet(userInfo.streetControl);
        report.setStart_per(String.valueOf(userInfo.startPerYearControl));
        report.setStart_ref(String.valueOf(userInfo.startPerMonthControl));
        report.setStop_per(String.valueOf(userInfo.endPerYearControl));
        report.setStop_ref(String.valueOf(userInfo.endPerMonthControl));
        report.setDanger((short) 0);

        try {
            repo.addObject(report);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayDrag> arrayDrag = userInfo.getArrayDrag();
        Drugs drugs = new Drugs();
        for (ArrayDrag f : arrayDrag) {
            try {
                drugs = (Drugs) repo.getObject(Drugs.class, (long) f.drug);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            substance.setName(drugs.getIUPAC());
            //TODO переделать
            substance.setType(String.valueOf(f.unit));
            substance.setValue(f.concentration);
            substance.setRefId(f.drug);
            substance.setReport(report);
            try {
                repo.addObject(substance);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return report.getId();
    }
}
