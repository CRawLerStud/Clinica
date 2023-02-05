package com.example.clinica.validation;

import com.example.clinica.models.Consultatie;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsultatieValidator {

    public void validate(Consultatie consultatie) throws ValidationException{
        validateCNP(consultatie.getCNPPacient());
        validateNumePacient(consultatie.getNumePacient());
    }

    private void validateNumePacient(String numePacient) throws ValidationException{

        if(numePacient.trim().length() == 0)
            throw new ValidationException("Numele pacientului este prea scurt!");

        Pattern pattern = Pattern.compile("^[A-Z][A-za-z ]*");
        Matcher matcher = pattern.matcher(numePacient);

        if(!matcher.matches())
            throw new ValidationException("Numele pacientului este invalid!");

    }

    private void validateCNP(Long cnpPacient) throws ValidationException{


        Pattern pattern = Pattern.compile("[0-9]{13}");
        Matcher matcher = pattern.matcher(cnpPacient.toString());

        if(!matcher.matches())
            throw new ValidationException("CNP-ul pacientului este invalid!");
        
    }
}
