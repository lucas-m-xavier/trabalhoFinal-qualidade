/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonusFuncionario;

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
    private static double salarioMinimo = 998;
    private static double bonusProgramador = 50;
    private static double bonusSupervisor = 80;
    private static double bonusGerente = 100;

    @Test
    public void CT001() throws Exception {
        Funcionario funcionario = new Funcionario("Fulano", "Gerente");

        double salarioEsperado = 998.00;

        assertEquals(salarioEsperado, funcionario.getSalarioBase(), 0.001);
    }

    @Test
    public void CT002() throws Exception {
        Funcionario funcionario = new Funcionario("Fulano", "Gerente");

        funcionario.setFaltas(2);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = salarioMinimo * 1.02 + bonusGerente;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }

    @Test
    public void CT003() throws Exception {
        Funcionario funcionario = new Funcionario("Fulano", "Gerente");

        funcionario.setFaltas(2);
        funcionario.setDistanciaMoradia(101);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = salarioMinimo * 1.02 + bonusGerente + 300;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }

    @Test
    public void CT004() throws Exception {
        Funcionario funcionario = new Funcionario("Fulano", "Gerente");

        funcionario.setFaltas(2);
        funcionario.setDistanciaMoradia(151);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = salarioMinimo * 1.02 + bonusGerente + 500;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }
    
     @Test
    public void CT005() throws Exception {
        Funcionario funcionario = new Funcionario("Fulano", "Gerente");

        funcionario.setFaltas(2);
        funcionario.setDistanciaMoradia(51);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = salarioMinimo * 1.02 + bonusGerente + 150;
        
        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }
    
    
    //Novos testes, segundo a planilha
    @Test
    public void CT006() throws Exception {
        
        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#1 Informe um nome válido"));
        
        Funcionario funcionario = new Funcionario(null, "Supervisor");
        funcionario.setDistanciaMoradia(53);
        funcionario.setFaltas(7);
    }
    
    
    @Test
    public void CT007() throws Exception {
        
        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#3 O salário base deve ser >= R$ 998,00"));
        
        Funcionario funcionario = new Funcionario("Cléber Machado", "Programador");
        funcionario.setDistanciaMoradia(113);
        funcionario.setFaltas(1);
    }
    
    @Test
    public void CT008() throws Exception {
        
        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#2 Informe um cargo válido"));
        
        Funcionario funcionario = new Funcionario("Andre Heinning", null);
        funcionario.setDistanciaMoradia(12);
        funcionario.setFaltas(37);
    }
    
    @Test
    public void CT009() throws Exception {
        Funcionario funcionario = new Funcionario("Alexandre", "Programador");
        funcionario.setDistanciaMoradia(239);
        funcionario.setFaltas(0);
        
        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = salarioMinimo * 1.05 + bonusProgramador + 500;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }
    
    @Test
    public void CT010() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#5 A distância é inválida"));

        Funcionario funcionario = new Funcionario("Clovis", "PROGRAMADOR");
        funcionario.setDistanciaMoradia(-5);
    }

    @Test
    public void CT011() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#6 O total de faltas é inválido"));

        Funcionario funcionario = new Funcionario("Alfredo", "PROGRAMADOR");

        funcionario.setFaltas(-7);
    }

    @Test
    public void CT012() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(is("\n#4 O nome possui caracteres inválidos"));

        Funcionario funcionario = new Funcionario("Alexandre77", "Gerente");

    }

    @Test
    public void CT013() throws Exception {

        Funcionario funcionario = new Funcionario("Cláudio Adão", "Supervisor");

        funcionario.setFaltas(3);
        funcionario.setDistanciaMoradia(23);

        ProcessadoraBonus pb = new ProcessadoraBonus();
        pb.processar(funcionario);

        double salarioEsperado = salarioMinimo * 1.02 + bonusSupervisor;

        assertEquals(salarioEsperado, funcionario.getSalarioTotal(), 0.001);
    }
}
