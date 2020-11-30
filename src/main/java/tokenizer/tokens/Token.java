package tokenizer.tokens;

import visitor.TokenVisitor;

public interface Token {
    default void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
