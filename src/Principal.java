import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

public class Principal {

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 15), new BigDecimal("2500.00"), "Desenvolvedor"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1985, 8, 20), new BigDecimal("3000.00"), "Analista"));
        funcionarios.add(new Funcionario("Carlos", LocalDate.of(1979, 2, 10), new BigDecimal("3500.00"), "Gerente"));
        funcionarios.removeIf(func -> func.getNome().equals("João"));

        System.out.println("Funcionários:");
        for (Funcionario func : funcionarios) {
            System.out.println(func);
        }
        List<Funcionario> funcionariosComAumento = new ArrayList<>();
        for (Funcionario func : funcionarios) {
            funcionariosComAumento.add(func.aumentarSalario(new BigDecimal("0.10")));
        }

        System.out.println("\nFuncionários com aumento de 10%:");
        for (Funcionario func : funcionariosComAumento) {
            System.out.println(func);
        }
        Map<String, List<Funcionario>> agrupadosPorFuncao = new HashMap<>();
        for (Funcionario func : funcionarios) {
            agrupadosPorFuncao.computeIfAbsent(func.getFuncao(), key -> new ArrayList<>()).add(func);
        }
        System.out.println("\nFuncionários agrupados por função:");
        for (List<Funcionario> lista : agrupadosPorFuncao.values()) {
            for (Funcionario func : lista) {
                System.out.println(func);
            }
        }
        System.out.println("\nFuncionários com aniversário outubro ou dezembro:");
        for (Funcionario func : funcionarios) {
            if (func.getDataNascimento().getMonthValue() == 10 || func.getDataNascimento().getMonthValue() == 12) {
                System.out.println(func);
            }
        }
        System.out.println("\nFuncionário mais velho:");
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparingInt(func -> func.getDataNascimento().getYear()));
        System.out.println(maisVelho.getNome() + " - " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()) + " anos");
        System.out.println("\nFuncionários por ordem alfabética:");
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        for (Funcionario func : funcionarios) {
            System.out.println(func);
        }
        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + totalSalarios.setScale(2, RoundingMode.HALF_EVEN).toString().replace('.', ','));
        System.out.println("\nQuantidade de salários mínimos:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario func : funcionarios) {
            System.out.println(func.getNome() + ": " + func.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_EVEN));
        }
    }
}
