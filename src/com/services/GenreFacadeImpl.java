package com.services;

import com.entities.Genre;

public class GenreFacadeImpl extends GenericAbstractFacade<Genre> {

    public GenreFacadeImpl(Class<Genre> clazz) {
        super(clazz);
    }
}
