package Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {

    ClientController clientController;

    /**
     * Bloco de código que é excutado antes de cada teste unitário
     *
     * @throws FileNotFoundException Lança uma excepção para ficheiro não encontrado
     */
    @BeforeEach
    void setUp() throws FileNotFoundException {
        clientController = new ClientController(); // Instanciar um ClientController
    }

    /**
     * Teste unitário para o método que verifica qual é o produto mais caro
     */
    @Test
    void produtoMaisCaroTest() {
        assertEquals("LGTV OLED 85 Golden Edition", clientController.produtoMaisCaro().getProduto()); // Verifica se corresponde ao produto correcto
        assertEquals("Produtos para Casa", clientController.produtoMaisCaro().getTipoProduto()); // Verifica se corresponde ao tipo correcto
        assertEquals(40000.99, clientController.produtoMaisCaro().getPrecoUnitario());// Verifica se corresponde ao preço correcto
        assertNotEquals("Pate de Sardinha", clientController.produtoMaisCaro().getProduto()); // Verifica se não está a retornar o produto mais barato
        assertNotEquals("Alimentacao", clientController.produtoMaisCaro().getTipoProduto()); // Verifica se não está a retornar outro tipo de produto (o do produto mais barato)
        assertNotEquals(0.65, clientController.produtoMaisCaro().getPrecoUnitario()); // Verifica se não está a retornar o preço do produto mais barato
    }

    /**
     * Teste unitário para o método que retorna os produtos disponíveis sem duplicados
     */
    @Test
    void produtosDisponiveisSemDuplicadosTest() {
        assertEquals(108, clientController.produtosDisponiveisSemDuplicados().size()); // Verifica se o size é 108 (valor correcto sem produtos duplicados)
        assertNotEquals(133, clientController.produtosDisponiveisSemDuplicados().size()); // Verifica se o size não é 133 (número total dos produtos, mas com duplicados)
    }

    /**
     * Teste unitário para o método que retorna os produtos de cada categoria
     */
    @Test
    void produtosDeCategoriaTest() {
        assertEquals(44, clientController.produtosDeCategoria("Alimentacao").size()); // Verifica se o número de produtos do tipo Alimentacao é 44
        assertEquals(30, clientController.produtosDeCategoria("Higiene").size()); // Verifica se o número de produtos do tipo Higiene é 30
        assertEquals(34, clientController.produtosDeCategoria("Produtos para Casa").size()); // Verifica se o número de produtos do tipo Produtos para Casa é 34
        assertNotEquals(67, clientController.produtosDeCategoria("Alimentacao").size()); // Verifica se o número de produtos do tipo Alimentacao não é 67 (número de produtos com duplicados)
        assertNotEquals(32, clientController.produtosDeCategoria("Higiene").size()); // Verifica se o número de produtos do tipo Higiene não é 32 (número de produtos com duplicados)
    }

    /**
     * Teste unitário para o método que verifica qual é o produto mais barato
     */
    @Test
    void produtoMaisBaratoTest() {
        assertEquals("Pate de Sardinha", clientController.produtoMaisBarato().getProduto()); // Verifica se corresponde ao produto mais barato
        assertEquals("Alimentacao", clientController.produtoMaisBarato().getTipoProduto()); // Verifica se corresponde ao tipo de produto (o do produto mais barato)
        assertEquals(0.65, clientController.produtoMaisBarato().getPrecoUnitario()); // Verifica se corresponde ao preço correcto
        assertNotEquals("LGTV OLED 85 Golden Edition", clientController.produtoMaisBarato().getProduto()); // Verifica se não está a retornar o produto mais caro
        assertNotEquals("Produtos para Casa", clientController.produtoMaisBarato().getTipoProduto()); // Verifica se não está a retornar outro tipo de produto (o do produto mais caro)
        assertNotEquals(40000.99, clientController.produtoMaisBarato().getPrecoUnitario()); // Verifica se não está a retornar o preço do produto mais caro
    }
}