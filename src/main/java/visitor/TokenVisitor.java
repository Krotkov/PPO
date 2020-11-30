package visitor;

import tokenizer.tokens.Token;

import java.util.List;

public interface TokenVisitor {
    void visit(Token token);

    void visit(List<Token> token);
}
