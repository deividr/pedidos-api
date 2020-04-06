package br.com.labuonapasta.pedidosapi.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.labuonapasta.pedidosapi.model.UnidadeType;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
    ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UnidadeTypeSubsetValidator.class)
public @interface UnidadeTypeSubset {
  UnidadeType[] anyOf();

  String message() default "deve ser um valor entre {anyOf}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}