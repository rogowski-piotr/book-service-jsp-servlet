package pl.piotr.dao;

import pl.piotr.model.Wydawnictwo;

import java.sql.*;
import java.util.Optional;

public class DaoWydawnictwo extends DaoAbstr<Integer, Wydawnictwo> {

    public DaoWydawnictwo() {
        QUERY_ALL = "SELECT idw, nazwa, adres FROM ksiazki.wydawnictwo";
        QUERY_BY_ID = "SELECT idw, nazwa, adres FROM ksiazki.wydawnictwo WHERE idw=";
        QUERY_INSERT = "INSERT INTO ksiazki.wydawnictwo (idw, nazwa, adres) VALUES(?,?,?)";
    }

    @Override
    Optional<Wydawnictwo> toModel(ResultSet response) {
        try {
            return Optional.of(
                    Wydawnictwo.builder()
                            .idw(response.getInt("idw"))
                            .nazwa(response.getString("nazwa"))
                            .adres(response.getString("adres"))
                            .build()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    PreparedStatement prepateToInsert(Connection dbConnection, Wydawnictwo wydawnictwo) throws SQLException {
        wydawnictwo.setIdw(getAll().stream()
                .mapToInt(Wydawnictwo::getIdw)
                .max().orElse(0) + 1);
        PreparedStatement pstmt = dbConnection.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, wydawnictwo.getIdw());
        pstmt.setString(2, wydawnictwo.getNazwa());
        pstmt.setString(3, wydawnictwo.getAdres());
        return pstmt;
    }

    public static void main(String[] args) {
        DaoWydawnictwo dao = new DaoWydawnictwo();

        dao.insert(
                Wydawnictwo.builder()
                        .adres("adres test")
                        .nazwa("nazwa test")
                        .build()
        );

        dao.getAll().stream()
                .forEach(System.out::println);

        dao.getById(3)
                .ifPresent(System.out::println);
    }
}