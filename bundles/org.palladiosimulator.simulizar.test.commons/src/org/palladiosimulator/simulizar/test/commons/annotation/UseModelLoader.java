package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.palladiosimulator.simulizar.test.commons.util.ModelLoader;

@Retention(RUNTIME)
@Target({ TYPE, METHOD, ANNOTATION_TYPE })
@Repeatable(UseModelLoader.UseModelLoaders.class)
public @interface UseModelLoader {
    @Retention(RUNTIME)
    @Target({ TYPE, METHOD, ANNOTATION_TYPE })
    public @interface UseModelLoaders {
        UseModelLoader[] value();
    }

    Class<? extends ModelLoader> value();
}
