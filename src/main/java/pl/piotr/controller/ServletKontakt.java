package pl.piotr.controller;

import pl.piotr.dao.DaoWydawnictwo;
import pl.piotr.model.Wydawnictwo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/kontakt")
public class ServletKontakt extends HttpServlet {

    private DaoWydawnictwo dao;

    public ServletKontakt() {
        super();
        dao = new DaoWydawnictwo();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextUrl = "/kontakt.jsp";

        List<Wydawnictwo> lista = dao.getAll();
        request.setAttribute("listaWydawnictw", lista);

        getServletContext()
                .getRequestDispatcher(nextUrl)
                .forward(request, response);
    }
}
