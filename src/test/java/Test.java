import model.Drugs;
import model.Substance;
import repository.RepoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {
    @org.junit.jupiter.api.Test
    void drugTest(){
        RepoImpl repo = new RepoImpl();

        try {
            ArrayList<Drugs> drugs = repo.getDrugById(3L);
            //Drugs drugs1 = drugs.get(0);
            //drugs1.toString();
            Substance substance = (Substance) repo.getObject(Substance.class, 1L);
            Drugs drugs1 = (Drugs) repo.getObject(Drugs.class, 1L);
            System.out.println(substance.toString());
            System.out.println(drugs1.toString());
//            drugs.toString();

            //ArrayList<Drugs> drugs1 = (ArrayList<Drugs>) repo.getAllObjects(Drugs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @org.junit.jupiter.api.Test
    void textTest(){

    }

}
