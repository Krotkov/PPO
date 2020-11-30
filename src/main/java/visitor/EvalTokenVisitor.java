package visitor;

import tokenizer.tokens.brackets.BracketToken;
import tokenizer.tokens.number.NumberToken;
import tokenizer.tokens.operations.OperationToken;

import java.util.Stack;

public class EvalTokenVisitor extends AbstractTokenVisitor {
    private final Stack<NumberToken> stack = new Stack<>();

    @Override
    protected void doVisit(BracketToken token) {
        throw new RuntimeException("brackets in RPN");
    }

    @Override
    protected void doVisit(OperationToken token) {
        int b = stack.pop().getValue();
        int a = stack.pop().getValue();
        stack.push(new NumberToken(token.work(a, b)));
    }

    @Override
    protected void doVisit(NumberToken token) {
        stack.push(token);
    }

    public int getAnswer() {
        return stack.pop().getValue();
    }

}
