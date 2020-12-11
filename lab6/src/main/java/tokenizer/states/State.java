package tokenizer.states;

import tokenizer.Tokenizer;

public abstract class State {
    protected final Tokenizer tokenizer;

    public State(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public abstract void apply(char c);

    public abstract void end();
}
