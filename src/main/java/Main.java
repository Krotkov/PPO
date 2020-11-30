import tokenizer.Tokenizer;
import tokenizer.tokens.Token;
import visitor.EvalTokenVisitor;
import visitor.PrintTokenVisitor;
import visitor.RPNTokenVisitor;
import visitor.TokenVisitor;

import java.io.PrintWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String test = "(2 + 3 * 4) / 10 - 5";
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize(test);
        List<Token> tokens = tokenizer.getTokens();
        TokenVisitor visitor = new PrintTokenVisitor(new PrintWriter(System.out));
        visitor.visit(tokens);

        RPNTokenVisitor rpnTokenVisitor = new RPNTokenVisitor();
        rpnTokenVisitor.visit(tokens);
        tokens = rpnTokenVisitor.getTokens();
        visitor.visit(tokens);

        EvalTokenVisitor evalTokenVisitor = new EvalTokenVisitor();
        evalTokenVisitor.visit(tokens);
        System.out.println(evalTokenVisitor.getAnswer());
    }
}
