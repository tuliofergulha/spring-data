package br.com.tfergulha.spring.data.specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

import br.com.tfergulha.spring.data.orm.Funcionario;

public class SpecificationFuncionario {

    public static Specification<Funcionario> nome(String nome) {
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("nome"), "%" + nome + "%")
        );
    }

    public static Specification<Funcionario> cpf(String cpf) {
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("cpf"), cpf)
        );
    }

    public static Specification<Funcionario> salario(BigDecimal salario) {
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThan(root.get("salario"), salario)
        );
    }

    public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao)
        );
    }
}
