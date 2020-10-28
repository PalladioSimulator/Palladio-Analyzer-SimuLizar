package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.simulizar.test.commons.extension.LoadModelFromSupplierExtension;
import org.palladiosimulator.simulizar.test.commons.extension.ProvideEDP2RepositoryExtension;
import org.palladiosimulator.simulizar.test.commons.extension.ProvideSimuLizarConfigurationExtension;
import org.palladiosimulator.simulizar.test.commons.extension.ProvideSimuLizarJobExtension;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestParameterProvider;

@Retention(RUNTIME)
@Target({ TYPE, METHOD, ANNOTATION_TYPE })
@ExtendWith({ LoadModelFromSupplierExtension.class, ProvideEDP2RepositoryExtension.class, ProvideSimuLizarConfigurationExtension.class,
    ProvideSimuLizarJobExtension.class, SimuLizarTestParameterProvider.class })
public @interface SimuLizarTest {

}
