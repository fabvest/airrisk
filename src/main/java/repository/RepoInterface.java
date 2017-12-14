package repository;

import java.sql.SQLException;
import java.util.List;

public interface RepoInterface <T> {
    boolean addObject(T emp) throws SQLException;
    Object getObject(Class clazz, Long id) throws SQLException;
    List getAllObjects(Class clazz) throws SQLException;
}
