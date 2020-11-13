package pl.piotr.controller;

import pl.piotr.dao.DaoKategoria;
import pl.piotr.model.Kategoria;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class ServletMenu extends HttpServlet {

    private DaoKategoria dao;

    public ServletMenu() {
        super();
        dao = new DaoKategoria();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextUrl = "/menu.jsp";

        List<Kategoria> lista = dao.getAll();
        request.setAttribute("lista", lista);

        getServletContext()
                .getRequestDispatcher(nextUrl)
                .forward(request, response);
    }
}
