package visitor;

import tokenizer.tokens.Token;
import tokenizer.tokens.brackets.BracketToken;
import tokenizer.tokens.brackets.LeftBracketToken;
import tokenizer.tokens.number.NumberToken;
import tokenizer.tokens.operations.OperationToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RPNTokenVisitor extends AbstractTokenVisitor {
    private final List<Token> tokens = new ArrayList<>();
    private final Stack<Token> stack = new Stack<>();


    @Override
    protected void doVisit(BracketToken token) {
        if (token instanceof LeftBracketToken) {
            stack.push(token);
        } else {
            Token last = stack.pop();
            while (!(last instanceof LeftBracketToken)) {
                tokens.add(last);
                last = stack.pop();
            }
        }
    }

    @Override
    protected void doVisit(OperationToken token) {
        while (!stack.empty() && !(stack.peek() instanceof BracketToken)) {
            OperationToken last = (OperationToken) stack.peek();
            if (last.getPriority() > token.getPriority()) {
                tokens.add(last);
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(token);
    }

    @Override
    protected void doVisit(NumberToken token) {
        tokens.add(token);
    }

    @Override
    public void visit(List<Token> tokens) {
        super.visit(tokens);
        while (!stack.isEmpty()) {
            this.tokens.add(stack.pop());
        }
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
