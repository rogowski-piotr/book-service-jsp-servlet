package pl.piotr.controller;

import pl.piotr.dao.DaoKategoria;
import pl.piotr.model.Kategoria;
import pl.piotr.model.Ksiazka;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/kategorie")
public class ServletKategorii extends HttpServlet {

    private DaoKategoria dao;

    public ServletKategorii() {
        super();
        dao = new DaoKategoria();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao.insert(
                Kategoria.builder()
                        .opis(request.getParameter("opis"))
                        .build()
        );

        List<Kategoria> lista = dao.getAll();
        request.setAttribute("listaKategorii", lista);

        getServletContext()
                .getRequestDispatcher("/listaKategorii.jsp")
                .forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Kategoria> lista = dao.getAll();
        request.setAttribute("listaKategorii", lista);

        getServletContext()
                .getRequestDispatcher("/listaKategorii.jsp")
                .forward(request, response);
    }
}
