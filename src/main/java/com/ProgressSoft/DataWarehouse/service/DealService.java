package com.ProgressSoft.DataWarehouse.service;

import com.ProgressSoft.DataWarehouse.dto.request.DealRequestDTO;
import com.ProgressSoft.DataWarehouse.exception.ApiException;
import com.ProgressSoft.DataWarehouse.model.Deal;
import com.ProgressSoft.DataWarehouse.repository.DealRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Currency;

@Log4j2
@Service
public class DealService {

    private final DealRepository dealRepository;

    public DealService(@Autowired DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }


    /**
     * This Method For Validate Customer Deal And Save It In DB
     *
     * @param dealRequestDTO : Contains All Customer Deal Info
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addCustomerDealRequest(DealRequestDTO dealRequestDTO) {

        // Check If System Have previous Deal With Same RequestId
        var existDeal = dealRepository.findByDealId(dealRequestDTO.dealId());

        if (existDeal.isPresent()) {
            log.error("Deal Id {} Found For Another Deal", dealRequestDTO.dealId());
            throw new ApiException(HttpStatus.CONFLICT, "DEAL_EXIST");
        }

        // Fill Deal Object To Save It
        var customerDeal = new Deal();
        BeanUtils.copyProperties(dealRequestDTO, customerDeal);
        try {
            var orderCurrency = Currency.getInstance(dealRequestDTO.toCurrencyCode());
            customerDeal.setOrderCurrency(orderCurrency.getDisplayName());
            var toCurrency = Currency.getInstance(dealRequestDTO.orderCurrencyCode());
            customerDeal.setToCurrency(toCurrency.getDisplayName());
        } catch (IllegalArgumentException argumentException) {
            log.error("Deal Currency Code Not Valid with error ");
            argumentException.printStackTrace();
            throw new ApiException(HttpStatus.BAD_REQUEST, "DEAL_CURRENCY_NOT_VALID");
        } catch (Exception exception) {
            log.error("SomeThing went wrong when convert To Currency Object with error");
            exception.printStackTrace();
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR.MESSAGE");
        }

        customerDeal.setCreateDate(LocalDateTime.now());

        // Save Deal In DataBase
        this.saveDeal(customerDeal);

    }

    /**
     * Save Deal In DB
     *
     * @param customerDeal
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void saveDeal(Deal customerDeal) {
        dealRepository.save(customerDeal);
    }
}

