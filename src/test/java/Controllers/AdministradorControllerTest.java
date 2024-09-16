package Controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorControllerTest {

    AdministradorController administradorController;

    /**
     * Bloco de código que é excutado antes de cada teste unitário
     *
     * @throws FileNotFoundException Lança uma excepção para ficheiro não encontrado
     */
    @BeforeEach
    void setUp() throws FileNotFoundException {
        administradorController = new AdministradorController(); // Instanciar um AdministradorController
    }

    /**
     * Teste unitário para o método que retorna o produto mais vendido
     */
    @Test
    void produtoMaisVendidoTest() {
        assertEquals("Alimentacao", administradorController.produtoMaisVendido().getTipoProduto()); // Nome do tipo de produto do produto mais vendido
        assertEquals("Cerveja Super Bock", administradorController.produtoMaisVendido().getProduto()); // Nome do produto mais vendido
        assertEquals(1111, administradorController.produtoMaisVendido().getQuantidadeVendida()); // Total de Super Bock vendidas
        assertNotEquals("Cerveja Sagres", administradorController.produtoMaisVendido().getProduto()); // Outra cerveja que não a mais vendida
        assertNotEquals("Higiene", administradorController.produtoMaisVendido().getTipoProduto()); // Outro tipo de produtos que não o do produto mais vendido
        assertNotEquals(445, administradorController.produtoMaisVendido().getQuantidadeVendida()); // Quantidade de Super Bock da venda com mais quantidade, mas não o total de Super Bock vendidas em todas as vendas
    }

    /**
     * Teste unitário para o método que retorna o produto que mais vendeu
     */
    @Test
    void produtoQueMaisVendeuTest() {
        assertEquals("Alimentacao", administradorController.produtoQueMaisVendeu().getTipoProduto());
        assertEquals("Cerveja Super Bock", administradorController.produtoQueMaisVendeu().getProduto());
        assertEquals(445, administradorController.produtoQueMaisVendeu().getQuantidadeVendida());
        assertNotEquals("Produtos para Casa", administradorController.produtoQueMaisVendeu().getTipoProduto());
        assertNotEquals("Sacos do Lixo", administradorController.produtoQueMaisVendeu().getProduto());
        assertNotEquals(1111, administradorController.produtoQueMaisVendeu().getQuantidadeVendida()); // Valor não deve ser o total de todas as Super Bock de todas as vendas
    }

    /**
     * Teste unitário para o método que retorna a venda com maior valor
     */
    @Test
    void vendaMaisValorTest() {
        assertEquals("Produtos para Casa", administradorController.vendaMaisValor().getTipoProduto());
        assertEquals("LGTV OLED 85 Golden Edition", administradorController.vendaMaisValor().getProduto());
        assertEquals(40000.99, administradorController.vendaMaisValor().getPrecoUnitario());
        assertNotEquals("Alimentacao", administradorController.vendaMaisValor().getTipoProduto());
        assertNotEquals("Aspirador de Po", administradorController.vendaMaisValor().getProduto()); // Segunda venda com mais valor
        assertNotEquals(99.99, administradorController.vendaMaisValor().getPrecoUnitario()); // Segunda venda com mais valor
    }

    /**
     * Teste unitário para o método de adicionar um utilizador
     *
     * @throws IOException Lança uma excepção que indica um problema ao executar operações de entrada/saída
     */
    @Test
    void adicionarUtilizadorTest() throws IOException {
        String filePath = "src/test/resources/login_grandesNegociosTest.csv";
        administradorController.adicionarUtilizador("ADMIN", "marco", "12345", filePath);
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String tipoUtilizador = "";
        String username = "";
        String password = "";
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] linhaSeparada = linha.split(";");
            tipoUtilizador = linhaSeparada[0];
            username = linhaSeparada[1];
            password = linhaSeparada[2];
        }
        assertEquals("ADMIN", tipoUtilizador);
        assertEquals("marco", username);
        assertEquals("12345", password);
        assertNotEquals("FUNC", tipoUtilizador);
        assertNotEquals("tobias", username);
        assertNotEquals("123456", password);
    }

    /**
     * Teste unitário para o método que calcula o valor total de vendas
     */
    @Test
    void valorVendasTest() {
        assertEquals(56095.609999999964, administradorController.valorVendas()); // Testa se o resultado é a soma das quantidades a multiplicar pelo valor unitário
        assertNotEquals(40938.91, administradorController.valorVendas()); // Testa se o resultado não são as vendas todas sem estarem multiplicadas pela quantidade
    }

    /**
     * Teste unitário para o método que calcula a média do valor das vendas
     */
    @Test
    void mediaVendasTest() {
        assertEquals(421.77150375939823, administradorController.mediaVendas()); // Testa se o resultado é a soma das vendas (quantidade x valor unitário) a dividir pela quantidade de vendas
        assertNotEquals(307.811353, administradorController.mediaVendas()); // Testa se o resultado não é a média dos valores unitários (sem estarem multiplicados pela quantidade)
    }

    /**
     * Bloco de código que vai ser executado após os testes unitários e que serve para repor o ficheiro original
     *
     * @throws IOException Lança uma excepção que indica um problema ao executar operações de entrada/saída
     */
    @AfterEach
    void tearDown() throws IOException {
        File ficheiroTeste = new File("src/test/resources/login_grandesNegociosTest.csv");
        File ficheiroOriginal = new File("src/test/resources/login_grandesNegociosTest_original.csv");
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