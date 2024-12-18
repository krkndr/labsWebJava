package kpi.labswebjava.dto.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class CosmicWordCheckValidator implements ConstraintValidator<CosmicWordCheck, String> {

    private final List<String> cosmicTerms = Arrays.asList("star", "galaxy", "comet", "nebula", "asteroid", "quasar",
            "supernova", "black hole", "satellite", "meteor", "cosmos", "astrophysics", "dark matter", "gravity",
            "exoplanet", "spacecraft", "orbit", "astrology");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return cosmicTerms.stream().anyMatch(value.toLowerCase()::contains);
    }
}