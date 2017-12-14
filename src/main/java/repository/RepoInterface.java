package repository;

import model.Result;
import model.Substance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RepoInterface <T> {
    boolean addObject(T emp) throws SQLException;
    Object getObject(Class clazz, Long id) throws SQLException;
    List getAllObjects(Class clazz) throws SQLException;
    ArrayList<Substance> getSubByReport(long id) throws SQLException;
    ArrayList<Result> getResByReport(long id) throws SQLException;
}
