package visitor;

import tokenizer.tokens.Token;
import tokenizer.tokens.brackets.BracketToken;
import tokenizer.tokens.number.NumberToken;
import tokenizer.tokens.operations.OperationToken;

import java.util.List;

public abstract class AbstractTokenVisitor implements TokenVisitor {

    protected abstract void doVisit(BracketToken token);

    protected abstract void doVisit(OperationToken token);

    protected abstract void doVisit(NumberToken token);

    @Override
    public void visit(Token token) {
        if (token instanceof BracketToken) {
            doVisit((BracketToken) token);
        } else if (token instanceof OperationToken) {
            doVisit((OperationToken) token);
        } else if (token instanceof NumberToken) {
            doVisit((NumberToken) token);
        }
    }

    @Override
    public void visit(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
    }
}
