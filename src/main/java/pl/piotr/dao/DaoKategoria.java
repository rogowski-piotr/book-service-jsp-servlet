package pl.piotr.dao;

import pl.piotr.model.Kategoria;
import pl.piotr.model.Ksiazka;

import java.sql.*;
import java.util.Optional;

public class DaoKategoria extends DaoAbstr<Integer, Kategoria> {

    public DaoKategoria() {
        QUERY_ALL = "SELECT idk, opis FROM ksiazki.kategoria";
        QUERY_BY_ID = "SELECT idk, opis FROM ksiazki.kategoria WHERE idk=";
        QUERY_INSERT = "INSERT INTO ksiazki.kategoria (idk, opis) VALUES(?,?)";
    }

    @Override
    Optional<Kategoria> toModel(ResultSet response) {
        try {
            return Optional.of(
                    Kategoria.builder()
                            .idk(response.getInt("idk"))
                            .opis(response.getString("opis"))
                            .build()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    PreparedStatement prepateToInsert(Connection dbConnection, Kategoria kategoria) throws SQLException {
        kategoria.setIdk(getAll().stream()
                .mapToInt(Kategoria::getIdk)
                .max().orElse(0) + 1);
        PreparedStatement pstmt = dbConnection.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, kategoria.getIdk());
        pstmt.setString(2, kategoria.getOpis());
        return pstmt;
    }

    public static void main(String[] args) {
        DaoKategoria dao = new DaoKategoria();

        dao.insert(
                Kategoria.builder()
                        .opis("opis test")
                        .build());

        dao.getAll().stream()
                .forEach(System.out::println);

        dao.getById(3)
                .ifPresent(System.out::println);
    }

}