package tokenizer.states;

import tokenizer.Tokenizer;

public class EndState extends State{
    public EndState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void apply(char c) {
        throw new RuntimeException("Incorrect state");
    }

    @Override
    public void end() {}
}
