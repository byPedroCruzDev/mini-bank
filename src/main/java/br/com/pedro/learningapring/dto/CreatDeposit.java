package br.com.pedro.learningapring.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
public class CreatDeposit {
    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "0.01", message =  "Value must be higher than 0.1")
    private float value;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
