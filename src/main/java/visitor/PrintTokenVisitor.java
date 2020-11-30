package visitor;

import tokenizer.tokens.Token;

import java.io.PrintWriter;
import java.util.List;

public class PrintTokenVisitor implements TokenVisitor {
    private final PrintWriter printWriter;

    public PrintTokenVisitor(PrintWriter writer) {
        this.printWriter = writer;
    }

    @Override
    public void visit(Token token) {
        printWriter.print(token.toString());
    }

    @Override
    public void visit(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
            printWriter.print(" ");
        }
        printWriter.println();
        printWriter.flush();
    }
}
