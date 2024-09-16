package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilizadorTest {

    // Instanciar utilizadores
    Utilizador cristina = new Utilizador("FUNC", "cris55", "abc123");
    Utilizador teofilo = new Utilizador("ADMIN", "teo77", "123abc");


    /**
     * Teste unitário para o método que retorna o tipo de conta
     */
    @Test
    void getTipoContaTest() {
        assertEquals("FUNC", cristina.getTipoConta());
        assertEquals("ADMIN", teofilo.getTipoConta());
        assertNotEquals("FUNC", teofilo.getTipoConta());
        assertNotEquals("ADMIN", cristina.getTipoConta());
        assertNotEquals("cris55", cristina.getTipoConta());
    }

    /**
     * Teste unitário para o método que retorna o username
     */
    @Test
    void getUsernameTest() {
        assertEquals("cris55", cristina.getUsername());
        assertEquals("teo77", teofilo.getUsername());
        assertNotEquals("teo77", cristina.getUsername());
        assertNotEquals("cris55", teofilo.getUsername());
        assertNotEquals("FUNC", cristina.getUsername());
    }

    /**
     * Teste unitário para o método que retorna a password
     */
    @Test
    void getPasswordTest() {
        assertEquals("abc123", cristina.getPassword());
        assertEquals("123abc", teofilo.getPassword());
        assertNotEquals("abc123", teofilo.getPassword());
        assertNotEquals("123abc", cristina.getPassword());
        assertNotEquals("abc123 ", cristina.getPassword());
        assertNotEquals("teo77", teofilo.getPassword());
    }
}