package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    // Instanciar vendas
    Venda venda1 = new Venda("Alimentacao", "Postas de pescada", 2, 9.99);
    Venda venda2 = new Venda("Higiene", "Palitos", 10, 0.99);
    Venda venda3 = new Venda("Farmacia", "Comprimido para o Ricardo acalmar a franga", 999, 1);

    /**
     * Teste unitário para o método que retorna o tipo de produto
     */
    @Test
    void getTipoProdutoTest() {
        assertEquals("Alimentacao", venda1.getTipoProduto());
        assertEquals("Higiene", venda2.getTipoProduto());
        assertEquals("Farmacia", venda3.getTipoProduto());
        assertNotEquals("Alimentacao", venda3.getTipoProduto());
        assertNotEquals("Palitos", venda2.getTipoProduto());
    }

    /**
     * Teste unitário para o método que retorna o nome do produto
     */
    @Test
    void getProdutoTest() {
        assertEquals("Postas de pescada", venda1.getProduto());
        assertEquals("Palitos", venda2.getProduto());
        assertEquals("Comprimido para o Ricardo acalmar a franga", venda3.getProduto());
        assertNotEquals("Comprimido para o Ricardo acalmar a franga", venda1.getProduto());
        assertNotEquals("Palitos", venda1.getProduto());
    }

    /**
     * Teste unitário para o método que retorna a quantidade vendida
     */
    @Test
    void getQuantidadeVendidaTest() {
        assertEquals(2, venda1.getQuantidadeVendida());
        assertEquals(10, venda2.getQuantidadeVendida());
        assertEquals(999, venda3.getQuantidadeVendida());
        assertNotEquals(999, venda2.getQuantidadeVendida());
        assertNotEquals(1, venda3.getQuantidadeVendida());
    }

    /**
     * Teste unitário para o método que retorna o preço unitário
     */
    @Test
    void getPrecoUnitarioTest() {
        assertEquals(9.99, venda1.getPrecoUnitario());
        assertEquals(0.99, venda2.getPrecoUnitario());
        assertEquals(1, venda3.getPrecoUnitario());
        assertNotEquals(10, venda1.getPrecoUnitario());
        assertNotEquals(99, venda2.getPrecoUnitario());
        assertNotEquals(0.99, venda3.getPrecoUnitario());
    }

    // NOTA: Método "exibirDetalhesProduto" tem retorno void. Logo, não pode ser testado.

}