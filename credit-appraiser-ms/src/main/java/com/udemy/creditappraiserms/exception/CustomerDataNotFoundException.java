package com.udemy.creditappraiserms.exception;

public class CustomerDataNotFoundException extends Exception{
    public CustomerDataNotFoundException(String cpf){
        super("Dados do cliente não encontrados para o cpf "+cpf);
    }
}