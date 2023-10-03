package br.com.pedro.learningapring.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CreateTransactionDto {
    @NotNull(message = "Payer id cannot be null")
    private long payer_id;
    @NotNull(message = "Payee id cannot be null")
    private long payee_id;
    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "0.01", message = "Value must be higher than 0.01")
    private long value;


    public void setPayer_id(long payer_id) {
        this.payer_id = payer_id;
    }

    public void setPayee_id(long payee_id) {
        this.payee_id = payee_id;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getPayer_id() {
        return payer_id;
    }

    public long getPayee_id() {
        return payee_id;
    }

    public long getValue() {
        return value;
    }
}
