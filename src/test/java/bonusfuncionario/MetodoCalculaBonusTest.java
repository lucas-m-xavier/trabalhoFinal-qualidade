/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonusfuncionario;

import exceptions.ModelException;
import jdk.jfr.Description;
import model.Bonus;
import model.Funcionario;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author gabriel
 */
public class MetodoCalculaBonusTest {
    
    public MetodoCalculaBonusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    //Constantes
    private static double SALARIO_MINIMO = 998;
    private static double BONUS_PROGRAMADOR = 50;
    private static double BONUS_SUPERVISOR = 80;
    private static double BONUS_GERENTE = 100;

    @Test
    @Description("Teste básico: Novo Funcionário")
    public void CT001() throws Exception {
        
        double salarioEsperado = 998.00;
        String nomeEsperado = "Fulano";
        String cargoEsperado = "Gerente";
        
        Funcionario funcionario = new Funcionario(nomeEsperado, cargoEsperado);

        assertEquals(salarioEsperado, funcionario.getSalarioBase(), 0.001);
        assertEquals(nomeEsperado, funcionario.getNome());
        assertEquals(cargoEsperado, funcionario.getCargo());
    }

    @Test
    @Description("Teste da aplicação de bônus: Gerente")
    public void CT002() throws Exception {
        Funcionario funcionario = new Funcionario("Fulano", "Gerente");

        funcionario.setFaltas(2);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = SALARIO_MINIMO * 1.02 + BONUS_GERENTE;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }

    @Test
    @Description("Teste da aplicação de bônus: Gerente - com faltas")
    public void CT003() throws Exception {
        Funcionario funcionario = new Funcionario("Fulano", "Gerente");

        funcionario.setFaltas(2);
        funcionario.setDistanciaMoradia(101);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = SALARIO_MINIMO * 1.02 + BONUS_GERENTE + 300;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }
    
    
    @Test
    @Description("Teste de novo funcionário: Nome inválido")
    public void CT004() throws Exception {
        
        thrown.expect(ModelException.class);
        thrown.expectMessage(is("\n#1 Informe um nome válido"));
        
        Funcionario funcionario = new Funcionario(null, "Supervisor");
        funcionario.setDistanciaMoradia(53);
        funcionario.setFaltas(7);
    }
    
    
    @Test
    @Description("Teste de novo funcionário: Cargo válido")
    public void CT005() throws Exception {
        
        Funcionario funcionario = new Funcionario("Cléber Machado", "Programador");
        funcionario.setDistanciaMoradia(113);
        funcionario.setFaltas(1);
        
        String novoCargo = "Supervisor Geral";
        funcionario.setCargo(novoCargo);
        
        assertEquals(novoCargo, funcionario.getCargo());
    }
    
    @Test
    @Description("Teste de novo funcionário: Cargo inválido - Nulo")
    public void CT006() throws Exception {
        
        thrown.expect(ModelException.class);
        thrown.expectMessage(is("\n#2 Informe um cargo válido"));
        
        Funcionario funcionario = new Funcionario("Andre Heinning", null);
        funcionario.setDistanciaMoradia(12);
        funcionario.setFaltas(37);
    }
    
    
    @Test
    @Description("Teste de bônus: Variação de cenários")
    public void CT007() throws Exception {
        Funcionario funcionario = new Funcionario("Alexandre", "Programador");
        funcionario.setDistanciaMoradia(239);
        funcionario.setFaltas(0);
        
        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = SALARIO_MINIMO * 1.05 + BONUS_PROGRAMADOR + 500;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }
    
    @Test
    @Description("Teste de bônus: Distância inválida")
    public void CT008() throws Exception {
        thrown.expect(ModelException.class);
        thrown.expectMessage(is("\n#5 A distância é inválida"));

        Funcionario funcionario = new Funcionario("Clovis", "PROGRAMADOR");
        funcionario.setDistanciaMoradia(-5);
    }

    @Test
    @Description("Teste de bônus: Faltas inválida")
    public void CT009() throws Exception {
        thrown.expect(ModelException.class);
        thrown.expectMessage(is("\n#6 O total de faltas é inválido"));

        Funcionario funcionario = new Funcionario("Alfredo", "PROGRAMADOR");

        funcionario.setFaltas(-7);
    }

    @Test
    @Description("Teste de bônus: Nome inválido")
    public void CT010() throws Exception {
        thrown.expect(ModelException.class);
        thrown.expectMessage(is("\n#4 O nome possui caracteres inválidos"));

        Funcionario funcionario = new Funcionario("Alexandre77", "Gerente");

    }

    @Test
    @Description("Teste de bônus: Variação de cenários")
    public void CT011() throws Exception {

        Funcionario funcionario = new Funcionario("Cláudio Adão", "Supervisor");

        funcionario.setFaltas(3);
        funcionario.setDistanciaMoradia(23);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = SALARIO_MINIMO * 1.02 + BONUS_SUPERVISOR;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }
    
    @Test
    @Description("Teste de bônus: Variação de cenários")
    public void CT013() throws Exception {

        Funcionario funcionario = new Funcionario("Cláudio Adão", "Supervisor");

        funcionario.setFaltas(3);
        funcionario.setDistanciaMoradia(58);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = SALARIO_MINIMO * 1.02 + BONUS_SUPERVISOR + 150;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }
    
    @Test
    @Description("Teste básico: Bônus")
    public void CT014() throws Exception {
        
        double valorEsperado = 50.00;
        String tipoEsperado = "Falta: ";
        
        Bonus bonus = new Bonus(tipoEsperado, valorEsperado);

        assertEquals(tipoEsperado, bonus.getTipo());
        assertEquals(valorEsperado, bonus.getValor(), 0.001);
    }

}
