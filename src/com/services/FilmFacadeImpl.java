package com.services;

import com.entities.Film;

public class FilmFacadeImpl extends GenericAbstractFacade<Film> {

    public FilmFacadeImpl(Class<Film> clazz) {
        super(clazz);
    }

    // TODO find by genre
}
