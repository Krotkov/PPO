package tokenizer.states;

import tokenizer.Tokenizer;
import tokenizer.tokens.number.NumberToken;

public class NumberState extends State {
    private int number;

    public NumberState(Tokenizer tokenizer) {
        super(tokenizer);
        this.number = 0;
    }

    @Override
    public void apply(char c) {
        if (Character.isDigit(c)) {
            number = number * 10 + Character.digit(c, 10);
        } else {
            tokenizer.addToken(new NumberToken(number));
            tokenizer.setState(new MainState(tokenizer));
            tokenizer.getState().apply(c);
        }
    }

    @Override
    public void end() {
        tokenizer.addToken(new NumberToken(number));
        tokenizer.setState(new MainState(tokenizer));
    }
}
