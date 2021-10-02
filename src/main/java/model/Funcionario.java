package model;

import exceptions.ModelException;
import java.util.ArrayList;

public class Funcionario {

    private String nome;
    private double salarioBase;
    private double salarioTotal;
    private int distanciaMoradia;
    private int faltas;
    private String cargo;
    private final ArrayList<Bonus> bonus;
    private static String nomeMatch = "[a-zA-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]([a-zA-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ])*";
    
    public Funcionario(String nome, String cargo) throws Exception {
        StringBuilder exceptions = new StringBuilder();

        this.setNome(nome);

        if (cargo == null || cargo.isEmpty() ) {
            exceptions.append("\n#2 Informe um cargo válido");
        }

        if (exceptions.length() > 0) {
            throw new ModelException(exceptions.toString());
        }

        this.nome = nome;
        this.cargo = cargo;
        this.salarioBase = 998.00;
        this.bonus = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        StringBuilder exceptions = new StringBuilder();
        
        if ( nome == null || nome.isEmpty() ) {
            exceptions.append("\n#1 Informe um nome válido");
        
        } else if(!nome.matches(nomeMatch)){
            exceptions.append("\n#4 O nome possui caracteres inválidos");
        }

        if (exceptions.length() > 0) {
            throw new ModelException(exceptions.toString());
        }

        this.nome = nome;
    }

    public double getSalarioBase() {
        return this.salarioBase;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) throws Exception{
        if(faltas < 0){
            throw new ModelException("\n#6 O total de faltas é inválido");
        }
        this.faltas = faltas;
    }

    public int getDistanciaMoradia() {
        return distanciaMoradia;
    }

    public void setDistanciaMoradia(int distanciaMoradia) throws Exception{
        if(distanciaMoradia < 0){
            throw new ModelException("\n#5 A distância é inválida");
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
            throw new ModelException("\n#2 Informe um cargo válido");
        }
    }

    public double getSalarioTotal() {
        this.calculaSalario();
        return this.salarioTotal;
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
        for (Bonus bonusFuncionario : bonus) {
            totalBonus += bonusFuncionario.getValor();
        }

        return totalBonus;
    }
    
}