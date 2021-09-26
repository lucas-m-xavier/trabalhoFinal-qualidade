import bonusFuncionario.ProcessadoraBonus;
import model.Funcionario;

public class Main {
    public static void main(String args[]) throws Exception {
        Funcionario funcionario = new Funcionario("Astolfo Luiz", "Gerente de vendas");

        ProcessadoraBonus processadoraBonus = new ProcessadoraBonus();
        processadoraBonus.processar(funcionario);

        System.out.println(funcionario.toString());
    }
}
