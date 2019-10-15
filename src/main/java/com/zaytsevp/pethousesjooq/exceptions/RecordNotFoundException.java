package com.zaytsevp.pethousesjooq.exceptions;

/**
 * Created by Pavel Zaytsev
 */
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }
}
