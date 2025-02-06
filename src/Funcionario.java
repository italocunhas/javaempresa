import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
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

    public void aumentarSalario(BigDecimal percentual) {
        this.salario = salario.add(salario.multiply(percentual));
    }

    @Override
    public String toString() {
        return super.toString() + " - " + funcao + " - " + salario.setScale(2, RoundingMode.HALF_EVEN).toString().replace('.', ',');
    }
}