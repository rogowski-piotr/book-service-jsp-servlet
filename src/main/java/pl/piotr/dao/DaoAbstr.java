package pl.piotr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DaoAbstr <Key, Entity> {

    static final String LOGIN = "postgres";

    static final String PASSWORD = "";

    static final String URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=\"ksiazki\"";

    abstract Optional<Entity> toModel(ResultSet response);

    abstract PreparedStatement prepateToInsert(Connection dbConnection, Entity entity) throws SQLException;

    protected Connection dbConnection;

    protected Statement dbStatement;

    String QUERY_ALL;

    String QUERY_BY_ID;

    String QUERY_INSERT;

    protected void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        dbStatement = dbConnection.createStatement();
    }

    protected void disconnect() {
        if (dbConnection == null) return;
        try {
            dbConnection.close();
        } catch(SQLException ex) {
            System.err.println("Problem z zamknięciem bazy: " +
                    ex.getSQLState());
        }
    }

    public List<Entity> getAll() {
        List<Entity> list = new ArrayList<>();
        try {
            connect();
            ResultSet result = dbStatement.executeQuery(QUERY_ALL);
            while(result.next()) {
                toModel(result)
                        .ifPresent(list::add);
            }
        } catch (NullPointerException e) {
            System.out.println("Brak wyników zapytania do bazy");
        } catch (ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException z init: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("SQLException z init: " + ex.getMessage());
        } finally {
            disconnect();
        }
        return list;
    }

    public Optional<Entity> getById(Key id) {
        try {
            connect();
            ResultSet result = dbStatement.executeQuery(QUERY_BY_ID + id);
            if (result.next()) {
                return toModel(result);
            }
        } catch (NullPointerException e) {
            System.out.println("Brak wyników zapytania do bazy");
        } catch (ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException z init: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("SQLException z init: " + ex.getMessage());
        } finally {
            disconnect();
        }
        return Optional.empty();
    }

    public void insert(Entity entity) {
        try {
            connect();
            PreparedStatement pstmt = prepateToInsert(dbConnection, entity);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
