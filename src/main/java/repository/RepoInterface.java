package repository;

import model.Drugs;
import model.Result;
import model.Substance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RepoInterface<T> {
    boolean addObject(T emp) throws SQLException;
    Object getObject(Class<T> clazz, Long id) throws SQLException;
    List getAllObjects(Class<T> clazz) throws SQLException;
    ArrayList<Substance> getSubByReport(long id) throws SQLException;
    ArrayList<Result> getResByReport(long id) throws SQLException;
    ArrayList<Drugs> getDrugById(long id) throws SQLException;
    void updateObject(Object o) throws  SQLException;
}
