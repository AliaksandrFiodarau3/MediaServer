/*
package com.epam.mediaserver.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.mediaserver.entity.OrderSong;
import com.epam.mediaserver.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
class GoodServiceTest {

    @Mock
    private GoodTableService goodTableService;

    @Test
    public void getByOrder() throws ServiceException {
        when(goodTableService.getByOrder(15)).thenReturn( Arrays.asList(new OrderSong(), new OrderSong()));
    }

    @Test
    public void delete() throws ServiceException {
        verify(goodTableService, times(1)).delete(15);

    }

}*/
