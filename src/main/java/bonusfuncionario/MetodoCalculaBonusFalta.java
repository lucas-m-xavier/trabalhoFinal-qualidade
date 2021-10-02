package bonusfuncionario;

import model.Bonus;
import model.Funcionario;

class MetodoCalculaBonusFalta implements IMetodoCalculaBonus {

    //Constante
    private static String falta = "Falta: ";
    
    @Override
    public void calcular(Funcionario funcionario) {
        int faltas = funcionario.getFaltas();
        double salario = funcionario.getSalarioBase();

        if (faltas == 0) {
            funcionario.addBonus(new Bonus(falta, salario * 0.05));
        } else if (faltas < 5) {
            funcionario.addBonus(new Bonus(falta, salario * 0.02));
        } else if (faltas < 10) {
            funcionario.addBonus(new Bonus(falta, salario * 0.01));
        }
    }
}
