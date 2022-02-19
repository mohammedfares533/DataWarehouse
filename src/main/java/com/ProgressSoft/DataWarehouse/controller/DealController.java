package com.ProgressSoft.DataWarehouse.controller;

import com.ProgressSoft.DataWarehouse.controller.base.Controllable;
import com.ProgressSoft.DataWarehouse.dto.common.BaseResponse;
import com.ProgressSoft.DataWarehouse.dto.request.DealRequestDTO;
import com.ProgressSoft.DataWarehouse.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/deal")
public class DealController {


    private final DealService dealService;

    public DealController(@Autowired DealService dealService) {
        this.dealService = dealService;
    }

    /**
     * This Endpoint For Validate User Deal and Save it in DB
     *
     * @param dealRequestDTO : Contains user Deal Information
     */
    @PostMapping("/v1/api/addDeal")
    @ResponseBody
    public ResponseEntity<BaseResponse> addCustomerDealRequest(@RequestBody @Valid DealRequestDTO dealRequestDTO) {

        dealService.addCustomerDealRequest(dealRequestDTO);

        return new ResponseEntity(Controllable.preparedResponse(null, "RESPONSE.OK"), HttpStatus.CREATED);
    }


}
