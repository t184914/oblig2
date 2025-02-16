package test;

import org.junit.jupiter.api.Test;

import oppgave1.ParentesSjekker;

import static org.junit.jupiter.api.Assertions.*;

class ParentesSjekkerTest {

    private final ParentesSjekker sjekker = new ParentesSjekker();

    @Test
    void testKorrektParenteser() {
        assertTrue(sjekker.sjekkParenteser("{ [ ( ) ] }"));
    }

    @Test
    void testManglerSluttParentes() {
        assertFalse(sjekker.sjekkParenteser("{ [ ( ) }"));
    }

    @Test
    void testManglerStartParentes() {
        assertFalse(sjekker.sjekkParenteser("[ ( ) ] }"));
    }

    @Test
    void testFeilRekkefølge() {
        assertFalse(sjekker.sjekkParenteser("{ [ ( ] ) }"));
    }

    @Test
    void testJavaProgram() {
        String javaprogram = """
            class HelloWorld {
                public static void main(String[] args) {
                    System.out.println(\"Hello World!\");
                }
            }
            """;
        assertTrue(sjekker.sjekkParenteser(javaprogram));
    }
}
