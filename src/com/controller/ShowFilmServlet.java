package com.controller;

import com.entities.Film;
import com.services.FilmFacadeImpl;
import com.utilities.Comparators;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ShowFilmServlet", urlPatterns = "/ShowFilmServlet")
public class ShowFilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cookieIfPresent = getCookieIfPresent(request);
        String order = request.getParameter("order");
        String parameter;

        if (cookieIfPresent != null && order == null) {
            parameter = cookieIfPresent;

        } else {
            String rememberOrder = request.getParameter("rememberOrder");
            parameter = (order == null) ? "1" : order;
            boolean isRememberChecked = rememberOrder != null;
            if (isRememberChecked) {
                response.addCookie(new Cookie("order", parameter));
            }
        }
        List<Film> films = new FilmFacadeImpl(Film.class).findAll();
        films = getOrderedList(parameter, films);
        request.setAttribute("list", films);
        request.setAttribute("sort", parameter);
        request.getRequestDispatcher("ShowFilmPage.jsp").forward(request, response);
    }

    private List<Film> getOrderedList(String parameter, List<Film> list) {
        switch (parameter) {
            case "1": // Order by Year
                return list; // TODO OrderByList
            default:
                return orderByTitle(list);
        }
    }

    private List<Film> orderByYear(List<Film> list) {
        return list.stream().sorted((Film, t1) -> new Comparators.CompareYear().compare(Film, t1)).collect(Collectors.toList());
    }

    private List<Film> orderByTitle(List<Film> list) {
        return list.stream().sorted((Film, t1) -> new Comparators.CompareTitle().compare(Film, t1)).collect(Collectors.toList());
    }

    private String getCookieIfPresent(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String preferredOrder = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("order")) {
                preferredOrder = cookie.getValue();
            }
        }
        return preferredOrder;
    }

}
