package tokenizer.tokens.operations;

public class MinusToken implements OperationToken {
    @Override
    public String toString() {
        return "MINUS";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int work(int a, int b) {
        return a - b;
    }
}
