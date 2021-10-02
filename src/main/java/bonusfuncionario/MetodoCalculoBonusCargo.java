package bonusfuncionario;

import model.Bonus;
import model.Funcionario;

public class MetodoCalculoBonusCargo implements IMetodoCalculaBonus {

    //Constante
    private static String CARGO = "Cargo: ";
    
    @Override
    public void calcular(Funcionario funcionario) {
        String cargoFuncionario = funcionario.getCargo();

        if (cargoFuncionario.toUpperCase().contains("GERENTE")) {
            funcionario.addBonus(new Bonus(CARGO + cargoFuncionario, 100));
        } else if (cargoFuncionario.toUpperCase().contains("SUPERVISOR")) {
            funcionario.addBonus(new Bonus(CARGO + cargoFuncionario, 80));
        } else if (cargoFuncionario.toUpperCase().contains("PROGRAMADOR")) {
            funcionario.addBonus(new Bonus(CARGO + cargoFuncionario, 50));
        }
    }
}
