package com.ProgressSoft.DataWarehouse.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * User Deal API Request
 */
public record DealRequestDTO(@NotNull(message = "{DEAL_ID_NOT_NULL_OR_EMPTY}")
                             Long dealId,
                             @NotNull(message = "{DEAL_CURRENCY_NOT_NULL_OR_EMPTY}")
                             @NotEmpty(message = "{DEAL_CURRENCY_NOT_NULL_OR_EMPTY}")
                             String orderCurrencyCode,
                             @NotNull(message = "{DEAL_CURRENCY_NOT_NULL_OR_EMPTY}")
                             @NotEmpty(message = "{DEAL_CURRENCY_NOT_NULL_OR_EMPTY}")
                             String toCurrencyCode,
                             @NotNull(message = "{DEAL_AMOUNT_NOT_NULL_OR_EMPTY}")
                             @DecimalMin(value = "0.0", inclusive = false)
                             @Digits(integer = 8, fraction = 2)
                             BigDecimal dealAmount) {
    ;

}
