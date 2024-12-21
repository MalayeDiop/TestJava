package test.java.core.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Database {
    Connection connexion() throws SQLException;
    void closeConnexion(Connection connection);
    ResultSet executeQuery(String query, PreparedStatement ps) throws SQLException;
    void preparedStatement (String sql) throws SQLException;
    int executeUpdate(String query, PreparedStatement ps) throws SQLException;
    String generateSQL(String baseQuery, Map<String, Object> conditions);
    void init(String sql) throws SQLException ;
    void setFields(PreparedStatement ps, List<Object> params) throws SQLException;
}
