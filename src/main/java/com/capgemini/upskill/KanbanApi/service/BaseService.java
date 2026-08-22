package com.capgemini.upskill.KanbanApi.service;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Supplier;

public class BaseService {

    public <T> Supplier<? extends RuntimeException> createException(Class<T> clazz, Object obj) {
        return () -> {
            String message = MessageFormat.format("{0} with ID {1} does not exist", clazz.getSimpleName() , obj.toString());
            return new NoSuchElementException(message);
        };
    }

}
