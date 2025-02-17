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
 
    
}