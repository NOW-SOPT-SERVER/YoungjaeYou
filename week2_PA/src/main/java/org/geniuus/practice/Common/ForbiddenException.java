package org.geniuus.practice.Common;

import org.geniuus.practice.Common.dto.ErrorMessage;

public class ForbiddenException extends BusinessException{
    public ForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
