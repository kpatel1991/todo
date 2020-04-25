package com.kunjan.todo.todo.exception;

import com.google.common.base.CaseFormat;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCode {
    public static String toErrorCode(Throwable t) {
        String sanitizedName =
                t.getClass()
                        .getSimpleName()
                        .replace("Exception", "");
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, sanitizedName);
    }
}
