package org.geniuus.practice.Common;

import org.geniuus.practice.Common.dto.ErrorMessage;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
