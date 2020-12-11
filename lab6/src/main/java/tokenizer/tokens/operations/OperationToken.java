package tokenizer.tokens.operations;

import tokenizer.tokens.Token;

public interface OperationToken extends Token {
    int getPriority();

    int work(int a, int b);
}

