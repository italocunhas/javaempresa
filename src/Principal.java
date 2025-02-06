import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 15), new BigDecimal("2500.00"), "Desenvolvedor"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1985, 8, 20), new BigDecimal("3000.00"), "Analista"));
        funcionarios.add(new Funcionario("Carlos", LocalDate.of(1979, 2, 10), new BigDecimal("3500.00"), "Gerente"));

        funcionarios.removeIf(func -> func.getNome().equals("João"));

        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);

        System.out.println("\nFuncionários com aumento de 10%:");
        List<Funcionario> funcionariosComAumento = funcionarios.stream()
                .map(func -> func.aumentarSalario(new BigDecimal("0.10")))
                .toList();

        funcionariosComAumento.forEach(System.out::println);

        Map<String, List<Funcionario>> agrupadosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\nFuncionários agrupados por função:");
        agrupadosPorFuncao.forEach((_, lista) -> lista.forEach(System.out::println));

        System.out.println("\nFuncionários com aniversário em outubro ou dezembro:");
        funcionarios.stream()
                .filter(func -> func.getDataNascimento().getMonthValue() == 10 || func.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        System.out.println("\nFuncionário mais velho:");
        funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .ifPresent(maisVelho -> System.out.println(maisVelho.getNome() + " - " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()) + " anos"));

        System.out.println("\nFuncionários por ordem alfabética:");
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        funcionarios.forEach(System.out::println);

        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTotal dos salários: " + totalSalarios.setScale(2, RoundingMode.HALF_EVEN).toString().replace('.', ','));

        System.out.println("\nQuantidade de salários mínimos:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(func -> {
            BigDecimal qtdSalariosMinimos = func.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_EVEN);
            System.out.println(func.getNome() + ": " + qtdSalariosMinimos);
        });
    }
}