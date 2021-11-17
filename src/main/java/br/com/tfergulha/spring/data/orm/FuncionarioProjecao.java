package br.com.tfergulha.spring.data.orm;

import java.math.BigDecimal;

public interface FuncionarioProjecao {

    Integer getId();

    String getNome();

    BigDecimal getSalario();
}
