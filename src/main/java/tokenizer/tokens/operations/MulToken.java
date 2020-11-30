package tokenizer.tokens.operations;

public class MulToken implements OperationToken {
    @Override
    public String toString() {
        return "MUL";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public int work(int a, int b) {
        return a * b;
    }
}
