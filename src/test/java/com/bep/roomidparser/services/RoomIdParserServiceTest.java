package com.bep.roomidparser.services;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.exceptions.UserException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sido on 14-2-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class RoomIdParserServiceTest {

    private static final Log log = LogFactory.getLog(RoomIdParserServiceTest.class);

    @Configuration
    static class ContextConfiguration {

        // this bean will be injected into the OrderServiceTest class
        @Bean
        public RoomIdParserService orderService() {
            RoomIdParserService roomIdParserService = new RoomIdParserServiceImpl();
            return roomIdParserService;
        }
    }

    @Autowired
    private RoomIdParserService service;

    @Test
    public void testDetermineValidRoomIds() {
        String fileName = "roomids.txt";

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<Room> validRoomIds = null;

        try {
            validRoomIds = service.determineValidRoomIds(inputStream);
        } catch (UserException|IOException err) {
            log.error(err);
        }

        assertEquals(337, validRoomIds.size());

    }

    @Test
    public void testCalculateNumberOfValidRooms() {
        String fileName = "roomids.txt";

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<Room> validRoomIds = null;

        try {
            validRoomIds = service.determineValidRoomIds(inputStream);
        } catch (UserException|IOException err) {
            log.error(err);
        }

        int value = 0;

        try {
            value = service.calculateNumberOfValidRooms(validRoomIds);
        } catch (UserException err) {
            log.error(err);
        }

        assertEquals(185371, value);

    }




}
