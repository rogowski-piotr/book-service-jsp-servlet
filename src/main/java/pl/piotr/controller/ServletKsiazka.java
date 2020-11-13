package pl.piotr.controller;

import pl.piotr.dao.DaoKategoria;
import pl.piotr.dao.DaoKsiazka;
import pl.piotr.dao.DaoWydawnictwo;
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

@WebServlet("/listaKsiazek")
public class ServletKsiazka extends HttpServlet {

    private DaoKsiazka daoKsiazka;
    private DaoWydawnictwo daoWydawnictwo;
    private DaoKategoria daoKategoria;

    public ServletKsiazka() {
        super();
        daoKsiazka = new DaoKsiazka();
        daoWydawnictwo = new DaoWydawnictwo();
        daoKategoria = new DaoKategoria();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (    request.getParameterMap().containsKey("tytul") &&
                request.getParameterMap().containsKey("wydawnictwo") &&
                request.getParameterMap().containsKey("kategoria")) {
            daoKsiazka.insert(
                    Ksiazka.builder()
                            .tytul(request.getParameter("tytul"))
                            .wydawnictwo(daoWydawnictwo.getById(
                                    Integer.valueOf(request.getParameter("wydawnictwo")))
                                    .orElseThrow())
                            .kategoria(daoKategoria.getById(
                                    Integer.valueOf(request.getParameter("kategoria")))
                                    .orElseThrow())
                            .build()
            );
        }

        List<Ksiazka> lista = daoKsiazka.getAll();
        request.setAttribute("listaKsiazek", lista);

        getServletContext()
                .getRequestDispatcher("/listaKsiazek.jsp")
                .forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean add = request.getParameterMap().containsKey("add") ? Boolean.valueOf(request.getParameter("add")) : false;

        if (add) {
            List<Wydawnictwo> listaWydawnictw = daoWydawnictwo.getAll();
            List<Kategoria> listaKategorii = daoKategoria.getAll();

            request.setAttribute("listaWydawnictw", listaWydawnictw);
            request.setAttribute("listaKategorii", listaKategorii);

            getServletContext()
                    .getRequestDispatcher("/addKsiazka.jsp")
                    .forward(request, response);
        }

        List<Ksiazka> lista = daoKsiazka.getAll();
        request.setAttribute("listaKsiazek", lista);

        getServletContext()
                .getRequestDispatcher("/listaKsiazek.jsp")
                .forward(request, response);
    }
}
