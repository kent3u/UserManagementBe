package com.example.demo.appdomin.validation;

import com.example.demo.appdomin.ValidationErrorCode;
import com.example.demo.appdomin.country.IsCountryIsoExists;
import com.example.demo.appdomin.user.IsClientModifyingAllowedForUser;
import com.example.demo.appdomin.user.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidateClientCommon {

    private final IsClientModifyingAllowedForUser isClientModifyingAllowedForUser;
    private final IsCountryIsoExists countryIsoExists;

    public Set<ValidationErrorCode> execute(Request request) {
        Set<ValidationErrorCode> errorCodes = new HashSet<>();

        if (request.getUserId() == null) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_USER_ID_MISSING);
        } else if (!isClientModifyingAllowedForUser.execute(IsClientModifyingAllowedForUser.Request.of(request.getUserId()))) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_MODIFYING_NOT_ALLOWED_FOR_USER_ID);
        }

        if (request.getFirstName() == null || request.getFirstName().isBlank()) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_FIRST_NAME_MISSING);
        } else if (request.getFirstName().length() > 64) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_FIRST_NAME_EXCEEDS_64_CHARS);
        }

        if (request.getLastName() == null || request.getLastName().isBlank()) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_LAST_NAME_MISSING);
        } else if (request.getLastName().length() > 64) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_LAST_NAME_EXCEEDS_64_CHARS);
        }

        if (request.getUsername() == null || request.getUsername().isBlank()) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_USERNAME_MISSING);
        } else if (request.getUsername().length() > 64) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_USERNAME_EXCEEDS_64_CHARS);
        }

        if (request.getEmail() != null && !isEmailValid(request.getEmail())) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_EMAIL_INVALID_FORMAT);
        }

        if (request.getAddress() == null || request.getAddress().isBlank()) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_ADDRESS_MISSING);
        } else if (request.getAddress().length() > 256) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_ADDRESS_EXCEEDS_255_CHARS);
        }

        if (request.getCountryIso() == null || !countryIsoExists.execute(IsCountryIsoExists.Request.of(request.getCountryIso()))) {
            errorCodes.add(ClientCommonValidationErrorCode.CLIENT_COUNTRY_ISO_UNKNOWN);
        }

        return errorCodes;
    }

    private boolean isEmailValid(String email) {
        return email.length() < 128 && email.matches("^(.+)@(\\S+)$");
    }

    @Value
    @Builder
    public static class Request {
        User.Id userId;
        String firstName;
        String lastName;
        String username;
        String email;
        String address;
        String countryIso;
    }

    public enum ClientCommonValidationErrorCode implements ValidationErrorCode {
        CLIENT_USER_ID_MISSING,
        CLIENT_MODIFYING_NOT_ALLOWED_FOR_USER_ID,
        CLIENT_FIRST_NAME_MISSING,
        CLIENT_FIRST_NAME_EXCEEDS_64_CHARS,
        CLIENT_LAST_NAME_MISSING,
        CLIENT_LAST_NAME_EXCEEDS_64_CHARS,
        CLIENT_USERNAME_MISSING,
        CLIENT_USERNAME_EXCEEDS_64_CHARS,
        CLIENT_EMAIL_INVALID_FORMAT,
        CLIENT_ADDRESS_MISSING,
        CLIENT_ADDRESS_EXCEEDS_255_CHARS,
        CLIENT_COUNTRY_ISO_UNKNOWN,
    }
}
