package br.com.tfergulha.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;

import br.com.tfergulha.spring.data.orm.Funcionario;
import br.com.tfergulha.spring.data.orm.FuncionarioProjecao;
import br.com.tfergulha.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

    private boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de relatório deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionário por nome");
            System.out.println("2 - Busca funcionário por nome, data contratação e salário maior");
            System.out.println("3 - Busca funcionário data contratação maior");
            System.out.println("4 - Busca funcionário salário");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacaoMaior(scanner);
                    break;
                case 4:
                    buscaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();

        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(l -> System.out.println(l));
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar?");
        String nome = scanner.next();

        System.out.println("Qual data contratação deseja pesquisar?");
        String dataContratacao = scanner.next();
        LocalDate localDate = LocalDate.parse(dataContratacao, formatter);

        System.out.println("Qual salário deseja pesquisar?");
        BigDecimal salario = scanner.nextBigDecimal();

        List<Funcionario> list = funcionarioRepository
            .findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacaoMaior(Scanner scanner) {
        System.out.println("Qual data contratação deseja pesquisar?");
        String dataContratacao = scanner.next();
        LocalDate localDate = LocalDate.parse(dataContratacao, formatter);

        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioSalario() {
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(funcionario -> {
            System.out.println("Id: " + funcionario.getId() + " | " +
                "Nome: " + funcionario.getNome() + " | " +
                "Salário: " + funcionario.getSalario());
        });
    }
}
