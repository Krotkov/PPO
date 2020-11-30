package tokenizer.tokens.operations;

public class PlusToken implements OperationToken {
    @Override
    public String toString() {
        return "PLUS";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int work(int a, int b) {
        return a + b;
    }
}
