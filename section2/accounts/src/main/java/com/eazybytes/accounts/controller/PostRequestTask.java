package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class PostRequestTask implements Runnable {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final CountDownLatch latch;

    public PostRequestTask(RestTemplate restTemplate, String apiUrl, CountDownLatch latch) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.latch = latch;
    }

    @Override
    public void run() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail("xyx@email.com");
        customerDto.setName("dubdwu jdieji");
        String cardurl = "http://192.168.100.253:9000/api/create";
        String loanurl = "http://192.168.100.253:8090/api/create";
        try {
            for (int i = 0; i <= 10000000; i++) {
                customerDto.setMobileNumber(createRandomMobileNumber());
                ResponseEntity<CustomerDto> response = restTemplate.postForEntity(apiUrl, customerDto, CustomerDto.class);
                ResponseEntity<ResponseDto> responseCard = restTemplate.postForEntity(cardurl + "?mobileNumber=" + customerDto.getMobileNumber(), null, ResponseDto.class);
                ResponseEntity<ResponseDto> responseLoan = restTemplate.postForEntity(loanurl + "?mobileNumber=" + customerDto.getMobileNumber(), null, ResponseDto.class);
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            latch.countDown();
        }
    }

    private String createRandomMobileNumber() {
        Random random = new Random();
        StringBuilder mobileNumber = new StringBuilder("9"); // Start with 9 for mobile numbers in many countries

        for (int i = 0; i < 9; i++) {
            int digit = random.nextInt(10); //
            mobileNumber.append(digit);
        }

        return mobileNumber.toString();
    }
}
