package dev.idinaldo.brabank.account.domain.valueObjects;

import dev.idinaldo.brabank.account.infrastructure.exceptions.InvalidCpfException;

public record CPF(String cpf) {

    public CPF(String cpf) {
        if (this.normalize(cpf).matches("\\d{11}")) {
            this.cpf = cpf;
        }
        throw new InvalidCpfException();
    }

    private String normalize(String cpf) {
        return cpf.replaceAll("[.-]", "");
    }
}
