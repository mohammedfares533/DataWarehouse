package com.ProgressSoft.DataWarehouse;

import com.ProgressSoft.DataWarehouse.dto.request.DealRequestDTO;

import java.math.BigDecimal;

public class DataBeanProvider {


    // Valid Add Deal Request
    public static DealRequestDTO ValidAddDealRequest() {
        var dealRequestDTO = new DealRequestDTO(1000l,
                "AUD",
                "JPY",
               new BigDecimal(7000.5));
        return dealRequestDTO;

    }
    // InValid Test Case For Save Customer Deal with invalid Deal Amount
    public static DealRequestDTO InValidTestCaseInvalidDealAmount () {
        var dealRequestDTO = new DealRequestDTO(1000l,
                "AUD",
                "JPY",
               new BigDecimal(0.0));
        return dealRequestDTO;

    }

    // InValid Test Case For Save Customer Deal with invalid Currency Code
    public static DealRequestDTO InValidTestCaseInvalidDealCurrencyCode () {
        var dealRequestDTO = new DealRequestDTO(1001l,
                "JO",
                "JPY",
                new BigDecimal(70.0));
        return dealRequestDTO;

    }

}
