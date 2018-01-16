package com.controller;

import com.entities.Film;
import com.entities.Genre;
import com.services.FilmFacadeImpl;
import com.services.GenreFacadeImpl;
import com.utilities.FilmValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "FilmOperationServlet", urlPatterns = "/FilmOperationServlet")
@MultipartConfig
public class FilmOperationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        System.out.println("doPost() method print: " + method);

        if (method == null) {
            request.getRequestDispatcher("ShowFilmServlet").forward(request, response);
        } else if (method.equals("add")) {
            addFilm(request, response);
        } else {
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Genre> genres = new GenreFacadeImpl(Genre.class).findAll();
        System.out.println(genres);
        request.setAttribute("genreList", genres);

        System.out.println("doGet() method print: " + request.getParameter("method"));

        if (request.getParameter("method") != null) {
            performCRUDOperation(request, response);
        } else {
            switch (request.getParameter("method")) {
                case "add":
                    request.getRequestDispatcher("FilmOperationPage.jsp").forward(request, response);
            }
        }
        request.getRequestDispatcher("ShowFilmServlet").forward(request, response);
    }

    private void performCRUDOperation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method) {
            case "cancellation":
                removeFilm(request.getParameter("id"));
                break;
            case "edit":
                editFilm();
                break;
            case "add":
                request.getRequestDispatcher("FilmOperationPage.jsp").forward(request, response);
                break;
            default:
                System.out.println("Wrong method passed in URL");
        }
    }

    private void addFilm(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String title = request.getParameter("title");
        String idGenre = request.getParameter("genre");
        String year = request.getParameter("year");
        String director = request.getParameter("director");
        String cast = request.getParameter("cast");
        String duration = request.getParameter("duration");
        String description = request.getParameter("description");


        Part filePart = request.getPart("cover"); // Retrieves <input type="file" name="file">
        System.out.println(filePart);

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        System.out.println(fileName);

//        final String path = "http://localhost:8080/images/";

        // add element
        if (title != null && idGenre != null && year != null && director != null && cast != null && duration != null) {
            Film film = new Film();
            film.setTitle(title);

            Genre genre = new Genre();
            genre.setId(Integer.parseInt(idGenre));

            film.setFilmGenre(genre);
            film.setYear(Integer.parseInt(year));
            film.setDirector(director);
            film.setCast(cast);
            film.setDuration(Integer.parseInt(duration));
            film.setDirector(description);

            //Immagine
            final String IMAGES_PATH = "C:\\Users\\Ciccio\\Desktop\\apache-tomcat-8.5.23\\webapps\\images\\";

            if (filePart.getSize() > 0) { // Ha caricato l'immagine
                film.setFileCoverName(fileName); // Insert into Film object
                String finalPath =  IMAGES_PATH + fileName;

                try (OutputStream out = new FileOutputStream(new File(finalPath));
                     InputStream filecontent = filePart.getInputStream()) {
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = filecontent.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                } catch (FileNotFoundException fne) {
                    fne.printStackTrace();
                }
            }

            List<String> errors = new FilmValidator().validate(film); // check if errors present
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/FilmOperationPage.jsp").forward(request, response);
            } else {
                new FilmFacadeImpl(Film.class).create(film);
                request.getRequestDispatcher("/ShowFilmServlet").forward(request, response);
            }
        }
    }

    private void removeFilm(String id) {
        Film film = new Film();
        film.setId(Integer.parseInt(id));
        new FilmFacadeImpl(Film.class).remove(film);
    }

    private void editFilm() {
        // TODO Edit Film method
    }


    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content
                        .substring(content.indexOf('=') + 1)
                        .trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
}
