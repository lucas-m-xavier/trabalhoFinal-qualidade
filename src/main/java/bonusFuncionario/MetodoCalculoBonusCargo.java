package bonusFuncionario;

import model.Bonus;
import model.Funcionario;

public class MetodoCalculoBonusCargo implements IMetodoCalculaBonus {

    @Override
    public void calcular(Funcionario funcionario) {
        String cargo = funcionario.getCargo();

        if (cargo.toUpperCase().contains("GERENTE")) {
            funcionario.addBonus(new Bonus("Cargo:" + cargo, 100));
        } else if (cargo.toUpperCase().contains("SUPERVISOR")) {
            funcionario.addBonus(new Bonus("Cargo:" + cargo, 80));
        } else if (cargo.toUpperCase().contains("PROGRAMADOR")) {
            funcionario.addBonus(new Bonus("Cargo:" + cargo, 50));
        }
    }
}
