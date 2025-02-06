import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private final BigDecimal salario;
    private final String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public Funcionario aumentarSalario(BigDecimal percentual) {
        BigDecimal novoSalario = salario.add(salario.multiply(percentual));
        return new Funcionario(getNome(), getDataNascimento(), novoSalario, funcao);
    }

    @Override
    public String toString() {
        return super.toString() + " - " + funcao + " - " + salario.setScale(2, RoundingMode.HALF_EVEN).toString().replace('.', ',');
    }
}