package com.proxsoftware.webapp.other;

/**
 * Created by Proxima on 13.07.2016.
 */
public class Account {
    private int value;
    private String name;

    private Account() {
    }

    public static Builder newBuilder() {
        return new Account().new Builder();
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public class Builder {
        private String name;
        private int value;

        private Builder() {

        }

        public Builder setName(String name) {
            Account.this.name = name;
            return this;
        }

        public Builder setValue(int value) {
            Account.this.value = value;
            return this;
        }

        public Account build() {
            return Account.this;
        }
    }

    public static void main(String[] args) {
        Account vova = Account.newBuilder().setName("Vova").setValue(24).build();
        System.out.println(vova.getName());
        System.out.println(vova.getValue());
    }
}
