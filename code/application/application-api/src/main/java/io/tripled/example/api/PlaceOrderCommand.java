package io.tripled.example.api;

import io.tripled.example.vocabulary.Amount;
import io.tripled.example.vocabulary.Name;
import io.tripled.example.vocabulary.ShoeSize;

public class PlaceOrderCommand {
    public final Name name;
    public final ShoeSize shoeSize;
    public final Amount amount;

    private PlaceOrderCommand(Builder builder) {
        name = builder.name;
        shoeSize = builder.shoeSize;
        amount = builder.amount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Name name;
        private ShoeSize shoeSize;
        private Amount amount;

        private Builder() {
        }

        public Builder withName(Name val) {
            name = val;
            return this;
        }

        public Builder withShoeSize(ShoeSize val) {
            shoeSize = val;
            return this;
        }

        public Builder withAmount(Amount val) {
            amount = val;
            return this;
        }

        public PlaceOrderCommand build() {
            validate();
            return new PlaceOrderCommand(this);
        }

        private void validate() {
            if (name == null)
                throw new RuntimeException("A name is mandatory");
            if (shoeSize == null)
                throw new RuntimeException("The shoeSize is mandatory");
            if (amount == null)
                throw new RuntimeException("The amount is mandatory");
        }
    }
}
