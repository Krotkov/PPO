package tokenizer;

import tokenizer.states.MainState;
import tokenizer.states.State;
import tokenizer.tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private State state = new MainState(this);
    private final List<Token> tokens = new ArrayList<>();

    public void addToken(Token token) {
        tokens.add(token);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void tokenize(String str) {
        for (int i = 0; i < str.length(); i++) {
            state.apply(str.charAt(i));
        }
        state.end();
    }
}
