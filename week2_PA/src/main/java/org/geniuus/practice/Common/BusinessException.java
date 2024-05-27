package org.geniuus.practice.Common;

import org.geniuus.practice.Common.dto.ErrorMessage;

public class BusinessException extends RuntimeException {
    public ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}