package com.ProgressSoft.DataWarehouse.controller.base;

import com.ProgressSoft.DataWarehouse.configurations.ApplicationResourceBundle;
import com.ProgressSoft.DataWarehouse.dto.common.BaseResponse;
import com.ProgressSoft.DataWarehouse.dto.common.ErrorModel;

import java.util.Locale;

/**
 * Controllable helper class used to prepared response and error response
 */
public abstract class Controllable {


    public static BaseResponse preparedResponse(Object body,
                                                String message) {

        return preparedResponse(body, message, 0);
    }

    public static BaseResponse preparedResponse(Object body,
                                                String message, int flowCode) {

        return new BaseResponse(flowCode, ApplicationResourceBundle.getString(message), body);
    }

    public static ErrorModel preparedErrorResponse(String message) {

        return new ErrorModel(null, ApplicationResourceBundle.getString(message));
    }
}
