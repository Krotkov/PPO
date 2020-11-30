package tokenizer.tokens.operations;

public class DivToken implements OperationToken {
    @Override
    public String toString() {
        return "DIV";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public int work(int a, int b) {
        return a / b;
    }
}
