package bonusfuncionario;

import model.Bonus;
import model.Funcionario;

class MetodoCalculaBonusFalta implements IMetodoCalculaBonus {

    //Constante
    private static String falta = "Falta: ";
    
    @Override
    public void calcular(Funcionario funcionario) {
        int faltasFuncionario = funcionario.getFaltas();
        double salarioFuncionario = funcionario.getSalarioBase();

        if (faltasFuncionario == 0) {
            funcionario.addBonus(new Bonus(falta, salarioFuncionario * 0.05));
        } else if (faltasFuncionario <= 5) {
            funcionario.addBonus(new Bonus(falta, salarioFuncionario * 0.02));
        } else if (faltasFuncionario <= 10) {
            funcionario.addBonus(new Bonus(falta, salarioFuncionario * 0.01));
        }
    }
}
