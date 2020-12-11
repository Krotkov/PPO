package tokenizer.states;

import tokenizer.Tokenizer;
import tokenizer.tokens.Token;
import tokenizer.tokens.brackets.LeftBracketToken;
import tokenizer.tokens.brackets.RightBracketToken;
import tokenizer.tokens.operations.DivToken;
import tokenizer.tokens.operations.MinusToken;
import tokenizer.tokens.operations.MulToken;
import tokenizer.tokens.operations.PlusToken;

public class MainState extends State {
    public MainState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void apply(char c) {
        if (Character.isWhitespace(c)) {
            return;
        }
        Token token = parseOperation(c);
        if (token != null) {
            tokenizer.addToken(token);
            return;
        }
        if (Character.isDigit(c)) {
            tokenizer.setState(new NumberState(tokenizer));
            tokenizer.getState().apply(c);
        }
    }

    @Override
    public void end() {
        tokenizer.setState(new EndState(tokenizer));
    }

    private Token parseOperation(char c) {
        switch (c) {
            case '+':
                return new PlusToken();
            case '-':
                return new MinusToken();
            case '*':
                return new MulToken();
            case '/':
                return new DivToken();
            case '(':
                return new LeftBracketToken();
            case ')':
                return new RightBracketToken();
            default:
                return null;
        }
    }
}
