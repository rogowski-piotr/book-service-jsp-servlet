package pl.piotr.controller;

import pl.piotr.dao.DaoKategoria;
import pl.piotr.dao.DaoKsiazka;
import pl.piotr.model.Kategoria;
import pl.piotr.model.Ksiazka;
import pl.piotr.model.Wydawnictwo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/kategoriaKsiazki")
public class ServletKategorieKsiazek extends HttpServlet {

    private DaoKategoria daoKategoria;
    private DaoKsiazka daoKsiazka;

    public ServletKategorieKsiazek() {
        super();
        daoKategoria = new DaoKategoria();
        daoKsiazka = new DaoKsiazka();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextUrl = "/kategoriaKsiazka.jsp";

        int idKategorii = Integer.valueOf(request.getParameter("id"));
        Kategoria kategoria = daoKategoria.getById(idKategorii).get();

        List<Ksiazka> lista = daoKsiazka.getByKategoria(kategoria);

        request.setAttribute("kategoria", kategoria);
        request.setAttribute("listaKsiazek", lista);

        getServletContext()
                .getRequestDispatcher(nextUrl)
                .forward(request, response);
    }
}
