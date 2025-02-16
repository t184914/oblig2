package oppgave1;

import java.util.Stack;

public class ParentesSjekker {
    public boolean sjekkParenteser(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (erStartParentes(c)) {
                stack.push(c);
            } else if (erSluttParentes(c)) {
                if (stack.isEmpty() || !erParentesPar(stack.pop(), c)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty(); // Hvis stabelen er tom, er alle parenteser matchet riktig
    }

    private boolean erStartParentes(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private boolean erSluttParentes(char c) {
        return c == ')' || c == '}' || c == ']';
    }

    private boolean erParentesPar(char start, char slutt) {
        return (start == '(' && slutt == ')') ||
               (start == '{' && slutt == '}') ||
               (start == '[' && slutt == ']');
    }
    
    public static void main(String[] args) {
        ParentesSjekker sjekker = new ParentesSjekker();
        String test1 = "{ [ ( ) ] }";
        String test2 = "{ [ ( ) }";
        String test3 = "[ ( ) ] }";
        String test4 = "{ [ ( ] ) }";
        String javaprogram = """
            class HelloWorld {
                public static void main(String[] args) {
                    System.out.println(\"Hello World!\");
                }
            }
            """;
         
        System.out.println(sjekker.sjekkParenteser(test1)); // true
        System.out.println(sjekker.sjekkParenteser(test2)); // false
        System.out.println(sjekker.sjekkParenteser(test3)); // false
        System.out.println(sjekker.sjekkParenteser(test4)); // false
        System.out.println(sjekker.sjekkParenteser(javaprogram)); // true
    }
}