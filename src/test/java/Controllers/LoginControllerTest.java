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

class LoginControllerTest {

    LoginController loginController;

    /**
     * Bloco de código que é executado antes de cada teste unitário
     *
     * @throws IOException Lança uma excepção que indica um problema ao executar operações de entrada/saída
     */
    @BeforeEach
    void setUp() throws IOException {
        this.loginController = new LoginController();
        File ficheiroTeste = new File("src/test/resources/login_grandesNegociosTest.csv");
        File ficheiroOriginal = new File("src/test/resources/login_grandesNegociosTest_original.csv");
        FileWriter escreverFicheiro = new FileWriter(ficheiroTeste);
        Scanner scannerFicheiro = new Scanner(ficheiroOriginal);
        while (scannerFicheiro.hasNextLine()) {
            String linha = scannerFicheiro.nextLine();
            escreverFicheiro.write(linha);
            if (scannerFicheiro.hasNextLine()) {
                escreverFicheiro.write("\n");
            }
        }
        escreverFicheiro.close();
    }

    /**
     * Teste para o método que valida as credenciais de acesso (login)
     */
    @Test
    void validarLoginTest() throws IOException {
        assertTrue(loginController.validarLogin("ADMIN", "patrao", "grandesnegocios")); // Verifica se credenciais válidas retornam true
        assertTrue(loginController.validarLogin("FUNC", "ivone", "costa")); // Verifica se credenciais válidas retornam true
        assertFalse(loginController.validarLogin("ADMIN", "ivone", "grandesnegocios")); // Testa se acede com password errada
        assertFalse(loginController.validarLogin("FUNC", "ivone", "")); // Testa se acede sem password
    }

}