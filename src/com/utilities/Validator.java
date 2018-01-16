package com.utilities;

import java.util.List;

public interface Validator<T> {
    List<?> validate(T t);
}
