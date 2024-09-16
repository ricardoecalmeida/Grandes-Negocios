package Controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioControllerTest {

    FuncionarioController funcionarioController;

    /**
     * Bloco de código que corre antes de cada teste unitário
     *
     * @throws FileNotFoundException Lança uma excepção para ficheiro não encontrado
     */
    @BeforeEach
    void setUp() throws FileNotFoundException {
        funcionarioController = new FuncionarioController("src/test/resources/minimercadoTest.csv"); // Instanciar um FuncionarioController
    }

    /**
     * Teste unitário para o método que adiciona uma venda
     * @throws IOException
     */
    @Test
    void addVendaTest() throws IOException {
        String filePath = "src/test/resources/minimercadoTest.csv";
        funcionarioController.addVenda("Higiene","Cotonetes",5,3, filePath);
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String tipoProduto="";
        String nomeProduto="";
        int quantidadeProduto = 0;
        double precoProduto=0;
        String linha = scanner.nextLine();
        while (scanner.hasNextLine()){
            linha = scanner.nextLine();
            String [] linhaDividida = linha.split(",");
            tipoProduto= linhaDividida[0];
            nomeProduto=linhaDividida[1];
            quantidadeProduto= Integer.parseInt(linhaDividida[2]);
            precoProduto= Double.parseDouble(linhaDividida[3]);
        }

        assertEquals("Higiene",tipoProduto);
        assertEquals("Cotonetes",nomeProduto);
        assertEquals(5,quantidadeProduto);
        assertEquals(3,precoProduto);

        assertNotEquals("Alimentacao",tipoProduto);
        assertNotEquals("Tremoços",nomeProduto);
        assertNotEquals(100,quantidadeProduto);
        assertNotEquals(0.99,precoProduto);
    }

    /**
     * Teste para o método de consultar o stock de um produto
     */
    @Test
    void consultarStockProdutoTest() {
        assertEquals(1175, funcionarioController.consultarStockProduto("Pasta de Banho")); // Verifica se o valor de stock de pasta de banho é igual a 1200 (stock inicial de todos os produtos) menos 25 (unidades vendidas)
        assertEquals(1191, funcionarioController.consultarStockProduto("Cabrito")); // Verifica se o valor de stock de cabrito é igual a 1200 (stock inicial de todos os produtos) menos 9 (unidades vendidas)
        assertNotEquals(1200, funcionarioController.consultarStockProduto("Cabrito")); // Verifica se o valor de stock de cabrito não é igual a 1200 (stock inicial de todos os produtos)
        assertNotEquals(9, funcionarioController.consultarStockProduto("Cabrito")); // Verifica se o valor de stock de cabrito é igual a 9 (unidades vendidas)
    }

    /**
     * Bloco de código que vai ser executado após os testes unitários e que serve para repor o ficheiro original
     *
     * @throws IOException Lança uma excepção que indica um problema ao executar operações de entrada/saída
     */
    @AfterEach
    void tearDown() throws IOException {
        File ficheiroTeste = new File("src/test/resources/minimercadoTest.csv");
        File ficheiroOriginal = new File("src/test/resources/minimercadoTest_original.csv");
        FileWriter escreveTeste = new FileWriter(ficheiroTeste);
        Scanner scannerFicheiro = new Scanner(ficheiroOriginal);
        while (scannerFicheiro.hasNextLine()) {
            String linha = scannerFicheiro.nextLine();
            escreveTeste.write(linha);
            if (scannerFicheiro.hasNextLine()) {
                escreveTeste.write("\n");
            }
        }
        escreveTeste.close();
    }
}