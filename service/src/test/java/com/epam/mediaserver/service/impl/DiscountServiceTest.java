package com.epam.mediaserver.service.impl;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

    @Mock
    private DiscountService discountService;

    @Test
    public void getPrice(){

        when(discountService.getPrice(50)).thenReturn(42.5);
    }





}
