package model;

import java.util.ArrayList;

public class Funcionario {

    private String nome;
    private double salarioBase;
    private double salarioTotal;
    private int distanciaMoradia;
    private int faltas;
    private String cargo;
    private final ArrayList<Bonus> bonus;

    public Funcionario(String nome, double salarioBase, String cargo) throws Exception {
        String exceptions = "";

        if (nome == null) {
            exceptions = exceptions.concat("\n#1 Informe um nome válido");
        }

        if (cargo == null) {
            exceptions = exceptions.concat("\n#2 Informe um cargo válido");
        }
        if (salarioBase != 998) {
            exceptions = exceptions.concat("\n#3 O salário base deve ser R$ 990.00");
        }
        if(!nome.matches("[a-zA-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]([a-zA-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ])*")){
            exceptions = exceptions.concat("\n#4 O nome possui caracteres inválidos");
        }

        if (exceptions.length() > 0) {
            throw new Exception(exceptions);
        }

        this.nome = nome;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.bonus = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        String exceptions = "";
        if (nome == null) {
            exceptions = exceptions.concat("\n#1 Informe um nome válido");
        }

        if(!nome.matches("[a-zA-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]([a-zA-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ])*")){
            exceptions = exceptions.concat("\n#4 O nome possui caracteres inválidos");
        }

        if (exceptions.length() > 0) {
            throw new Exception(exceptions);
        }

        this.nome = nome;
    }

    public double getSalarioBase() {
        return this.salarioBase;
    }

    public void setSalarioBase(double salarioBase) throws Exception{
        if(salarioBase != 998.00) {
            throw new Exception("\n#3 O salário base deve ser R$ 990.00");
        }
        this.salarioBase = salarioBase;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) throws Exception{
        if(faltas < 0){
            throw new Exception("\n#6 O total de faltas é inválido");
        }
        this.faltas = faltas;
    }

    public int getDistanciaMoradia() {
        return distanciaMoradia;
    }

    public void setDistanciaMoradia(int distanciaMoradia) throws Exception{
        if(distanciaMoradia < 0){
            throw new Exception("\n#5 A distância é inválida");
        }
        this.distanciaMoradia = distanciaMoradia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) throws Exception {
        if (cargo.contains("PROGRAMADOR") || cargo.contains("GERENTE") ||cargo.contains("SUPERVISOR")) {
            this.cargo = cargo;
        }else {
            throw new Exception("\n#2 Informe um cargo válido");
        }
    }

    public double getSalario() {
        return this.calculaSalario();
    }

    private double calculaSalario() {
        this.salarioTotal = this.salarioBase + this.calculaTotalBonus();
        return this.salarioTotal;
    }

    public void addBonus(Bonus bonus) {
        this.bonus.add(bonus);
    }

    public double calculaTotalBonus() {
        double totalBonus = 0;
        for (Bonus bonus : bonus) {
            totalBonus += bonus.getValor();
        }

        return totalBonus;
    }

    @Override
    public String toString() {
        String strBonusRecebidos = "";
        for (Bonus bonusRecebido : bonus) {
            strBonusRecebidos += "\n\t" + bonusRecebido;
        }
        String strFuncionario = "Funcionario {"
                + "nome: " + this.nome + ", "
                + "salarioBase: " + this.salarioBase + ", "
                + "totalBonus: " + this.calculaTotalBonus() + ", "
                + "salarioTotal: " + this.getSalario()
                + '}';

        if (bonus.size() > 0) {
            strFuncionario += "\nBonus recebidos: " + strBonusRecebidos;
        }

        return strFuncionario;
    }
}