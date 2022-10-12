package io.tripled.example.api;

import io.tripled.example.vocabulary.*;

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

    public static FactoryResultBuilder newFactoryResultBuilder() {
        return new FactoryResultBuilder();
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

    public static final class FactoryResultBuilder {

        private FactoryResult<Name> name = FactoryResult.failure("No name set");
        private FactoryResult<ShoeSize> shoeSize = FactoryResult.failure("No ShoeSize set");
        private FactoryResult<Amount> amount = FactoryResult.failure("No Amount set");

        private FactoryResultBuilder() {
        }

       public  FactoryResult<PlaceOrderCommand> build() {
            final ValidationResult validationResult = name.validationResult().merge(shoeSize.validationResult()).merge(amount.validationResult());
            return FactoryResult.createIfValid(validationResult, () -> buildCommand(name, shoeSize, amount));
        }


        public FactoryResultBuilder withName(FactoryResult<Name> val) {
            name = val;
            return this;
        }

        public FactoryResultBuilder withShoeSize(FactoryResult<ShoeSize> val) {
            shoeSize = val;
            return this;
        }

        public FactoryResultBuilder withAmount(FactoryResult<Amount> val) {
            amount = val;
            return this;
        }


        private static PlaceOrderCommand buildCommand(FactoryResult<Name> name,
                                                      FactoryResult<ShoeSize> shoeSize,
                                                      FactoryResult<Amount> amount) {
            return PlaceOrderCommand.newBuilder()
                    .withAmount(amount.mandatoryValidInstance())
                    .withName(name.mandatoryValidInstance())
                    .withShoeSize(shoeSize.mandatoryValidInstance())
                    .build();
        }
    }
}
