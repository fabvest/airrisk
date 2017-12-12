package repository;

import java.sql.SQLException;

public interface RepoInterface <T> {
    boolean addObject(T emp) throws SQLException;
}
