package com.github.di;

import com.github.di.exceptions.NewInstanceCreationException;
import com.github.di.exceptions.NoAccessException;
import com.github.di.exceptions.NoSuchFileException;
import com.github.di.exceptions.NoSuchImplementation;

public class ApplicationInit {

    public static Context init(Class<?> applicationClass) throws NoAccessException, NewInstanceCreationException, NoSuchFileException, NoSuchImplementation {
        return new Context(applicationClass);
    }
}
