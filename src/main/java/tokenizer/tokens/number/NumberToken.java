package tokenizer.tokens.number;

import tokenizer.tokens.Token;

public class NumberToken implements Token {
    private int value;

    public NumberToken(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NUMBER(" + String.valueOf(value) + ")";
    }

    public int getValue() {
        return value;
    }
}
