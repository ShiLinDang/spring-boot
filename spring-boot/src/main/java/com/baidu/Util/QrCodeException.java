package com.baidu.Util;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;

public class QrCodeException extends RuntimeException {
    private static final long serialVersionUID = 469027218631691847L;

    public QrCodeException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public QrCodeException(String message) {
        super(message);
    }

    public QrCodeException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public QrCodeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public QrCodeException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
