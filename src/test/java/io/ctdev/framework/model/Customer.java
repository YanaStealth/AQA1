package io.ctdev.framework.model;

public class Customer {

    private String email;
    private String password;
    private String securityAnswer;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String SecurityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Customer() {
    }

    private Customer(final Builder builder) {
        email = builder.email;
        password = builder.password;
        securityAnswer =builder.securityAnswer;
    }

    public static final class Builder {
        public String securityAnswer;
        private String email;
        private String password;

        private Builder() {
        }

        public Builder withName(final String val) {
            email = val;
            return this;
        }

        public Builder withPassword(final String val) {
            password = val;
            return this;
        }
        public Builder withSecurityAnswer(final String val) {
            password = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

}
