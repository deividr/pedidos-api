package br.com.labuonapasta.pedidosapi.util;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.labuonapasta.pedidosapi.model.UnidadeType;

public class UnidadeTypeSubsetValidator implements ConstraintValidator<UnidadeTypeSubset, UnidadeType> {
  private UnidadeType[] subset;

  @Override
  public void initialize(UnidadeTypeSubset constraintAnnotation) {
    this.subset = constraintAnnotation.anyOf();
  }

  @Override
  public boolean isValid(UnidadeType value, ConstraintValidatorContext context) {
    return value == null || Arrays.asList(subset).contains(value);
  }

}