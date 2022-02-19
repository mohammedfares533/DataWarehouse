package com.ProgressSoft.DataWarehouse.controller;

import com.ProgressSoft.DataWarehouse.DataBeanProvider;
import com.ProgressSoft.DataWarehouse.DataWarehouseApplicationTests;
import com.ProgressSoft.DataWarehouse.Endpoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DealControllerTest extends DataWarehouseApplicationTests {


    @Autowired
    private MockMvc mockMvc;


    @Test
    void addCustomerDealRequest() throws Exception {

        // Valid Test Case For Save Customer Deal
        mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.CREATE_DEAL_ENDPOINT.getUri())
                        .content(asJsonString(DataBeanProvider.ValidAddDealRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();



        // InValid Test Case For Save Customer Deal with duplicate dealId
        mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.CREATE_DEAL_ENDPOINT.getUri())
                        .content(asJsonString(DataBeanProvider.ValidAddDealRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andReturn();



        // InValid Test Case For Save Customer Deal with invalid Deal Amount
        mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.CREATE_DEAL_ENDPOINT.getUri())
                        .content(asJsonString(DataBeanProvider.InValidTestCaseInvalidDealAmount()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


        // InValid Test Case For Save Customer Deal with invalid Currency Code
        mockMvc.perform(MockMvcRequestBuilders.post(Endpoint.CREATE_DEAL_ENDPOINT.getUri())
                        .content(asJsonString(DataBeanProvider.InValidTestCaseInvalidDealCurrencyCode()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


}