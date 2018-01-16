package com.controller;

import com.entities.Film;
import com.entities.Genre;
import com.services.FilmFacadeImpl;
import com.services.GenreFacadeImpl;
import com.utilities.FilmValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FilmOperationServlet", urlPatterns = "/FilmOperationServlet")
public class FilmOperationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO add element
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String year = request.getParameter("year");
        String director = request.getParameter("director");
        String cast = request.getParameter("cast");
        String duration = request.getParameter("duration");
        String description = request.getParameter("description");

        // add element
        if (title != null && genre != null && year != null && director != null && cast != null && duration != null) {
            Film film = new Film();
            film.setTitle(title);
            Genre genre1 = new Genre();
            genre1.setId(Integer.parseInt(genre));
            film.setFilmGenre(genre1);
            film.setYear(Integer.parseInt(year));
            film.setDirector(director);
            film.setCast(cast);
            film.setDuration(Integer.parseInt(duration));
            film.setDirector(description);

            List<String> errors = new FilmValidator().validate(film); // check if errors present
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("FilmOperationPage.jsp").forward(request, response);
            } else {
                new FilmFacadeImpl(Film.class).create(film);
                request.getRequestDispatcher("ShowFilmServlet").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Genre> genres = new GenreFacadeImpl(Genre.class).findAll();
        System.out.println(genres);
        request.setAttribute("genreList", genres);
        request.getRequestDispatcher("FilmOperationPage.jsp").forward(request, response);
    }

    @Override
    public void init() {
        // TODO prendere i generi e caricarli
    }
}
