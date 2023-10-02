package br.com.pedro.learningapring.dto;

public class CreateTransactionDto {

    private long payer_id;
    private long payee_id;
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
