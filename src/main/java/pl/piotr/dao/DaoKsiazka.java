package pl.piotr.dao;

import pl.piotr.model.Kategoria;
import pl.piotr.model.Ksiazka;
import pl.piotr.model.Wydawnictwo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoKsiazka extends DaoAbstr<Integer, Ksiazka> {

    private DaoKategoria daoKategoria;
    private DaoWydawnictwo daoWydawnictwo;
    private String QUERY_ALL_BY_KATEGORIA = "SELECT idk, tytul, idwyd, idkat FROM ksiazki.ksiazka WHERE idkat=";

    public DaoKsiazka() {
        QUERY_ALL = "SELECT idk, tytul, idwyd, idkat FROM ksiazki.ksiazka";
        QUERY_BY_ID = "SELECT idk, tytul, idwyd, idkat FROM ksiazki.ksiazka WHERE idk=";
        QUERY_INSERT = "INSERT INTO ksiazki.ksiazka (idk, tytul, idwyd, idkat) VALUES(?,?,?,?)";
        daoKategoria = new DaoKategoria();
        daoWydawnictwo = new DaoWydawnictwo();
    }

    @Override
    Optional<Ksiazka> toModel(ResultSet response) {
        try {
            Kategoria kategoria = daoKategoria.getById(response.getInt("idkat"))
                    .orElseThrow(() -> new IllegalArgumentException("Can not found kategoria for ksiazka"));
            Wydawnictwo wydawnictwo = daoWydawnictwo.getById(response.getInt("idwyd"))
                    .orElseThrow(() -> new IllegalArgumentException("Can not found wydawnictwo for ksiazka"));
            return Optional.of(
                    Ksiazka.builder()
                            .idk(response.getInt("idk"))
                            .tytul(response.getString("tytul"))
                            .kategoria(kategoria)
                            .wydawnictwo(wydawnictwo)
                            .build()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    PreparedStatement prepateToInsert(Connection dbConnection, Ksiazka ksiazka) throws SQLException {
        ksiazka.setIdk(getAll().stream()
                .mapToInt(Ksiazka::getIdk)
                .max().orElse(0) + 1);
        PreparedStatement pstmt = dbConnection.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, ksiazka.getIdk());
        pstmt.setString(2, ksiazka.getTytul());
        pstmt.setInt(3, ksiazka.getWydawnictwo().getIdw());
        pstmt.setInt(4, ksiazka.getKategoria().getIdk());
        return pstmt;
    }

    public List<Ksiazka> getByKategoria(Kategoria kategoria) {
        int idKategoria = kategoria.getIdk();
        List<Ksiazka> list = new ArrayList<>();
        try {
            connect();
            ResultSet result = dbStatement.executeQuery(QUERY_ALL_BY_KATEGORIA + idKategoria);
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

    public static void main(String[] args) {
        DaoKsiazka dao = new DaoKsiazka();
        DaoKategoria daoKategoria = new DaoKategoria();
        DaoWydawnictwo daoWydawnictwo = new DaoWydawnictwo();

        Kategoria kategoria = daoKategoria.getById(1).get();
        Wydawnictwo wydawnictwo = daoWydawnictwo.getById(1).get();
        Ksiazka ksiazka = Ksiazka.builder()
                .tytul("Test")
                .wydawnictwo(wydawnictwo)
                .kategoria(kategoria)
                .build();

        dao.insert(ksiazka);

        /*DaoKategoria daoKategoria = new DaoKategoria();

        dao.getByKategoria(daoKategoria.getById(3).get())
                .stream().forEach(System.out::println);

        dao.getAll().stream()
                .forEach(System.out::println);

        dao.getById(3)
                .ifPresent(System.out::println);*/
    }
}