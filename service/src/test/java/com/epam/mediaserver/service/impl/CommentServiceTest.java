package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.dao.impl.SqlCommentDao;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.CommentService;
import com.epam.mediaserver.service.ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    private static final Long SONG_ID = 5L;
    private static final String USER_NAME = "User";
    private static final String MESSAGE = "Message Text";
    @Mock
    private CommentService commentService;

    @Test
    public void testAdd() throws ServiceException {
        commentService.add(SONG_ID, USER_NAME, MESSAGE);
        verify(commentService, times(1)).add(SONG_ID, USER_NAME, MESSAGE);
    }

    @Test
    public void getBySong() throws ServiceException {
        when(commentService.getBySong(String.valueOf(SONG_ID))).thenReturn(Arrays.asList(new Comment(), new Comment()));
        assertThat(commentService.getBySong(String.valueOf(SONG_ID)).size(), equalTo(2));
    }

    @Test
    public void testGetAll() throws ServiceException {
        when(commentService.getAll()).thenReturn(Arrays.asList(new Comment(), new Comment()));
        assertThat(commentService.getAll().size(), equalTo(2));
    }

}
