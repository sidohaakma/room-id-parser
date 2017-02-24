package com.bep.roomidparser.services;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.exceptions.RoomIdParserException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * <p>Test service calls for room-id-parser.</p>
 *
 * @author sido
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class RoomIdParserServiceTest {

  private static final Log LOG = LogFactory.getLog(RoomIdParserServiceTest.class);

  @Autowired
  private RoomIdParserService service;

  @Test
  public void testDetermineValidRoomIds() {
    InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("roomids.txt");

    MultipartFile roomIds = null;

    try {
      roomIds = new MockMultipartFile("file", stream);
    } catch (IOException err) {
      LOG.error(err);
    }

    List<Room> validRoomIds = null;

    try {
      validRoomIds = service.determineValidRoomIds(roomIds);
    } catch (RoomIdParserException err) {
      LOG.error(err);
    }

    assertEquals(337, validRoomIds.size());

  }

  @Test
  public void testCalculateNumberOfValidRooms() {
    InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("roomids.txt");

    MultipartFile roomIds = null;

    try {
      roomIds = new MockMultipartFile("file", stream);
    } catch (IOException err) {
      LOG.error(err);
    }

    List<Room> validRoomIds = null;

    try {
      validRoomIds = service.determineValidRoomIds(roomIds);
    } catch (RoomIdParserException err) {
      LOG.error(err);
    }

    int value = 0;

    try {
      value = service.calculateNumberOfValidRooms(validRoomIds);
    } catch (RoomIdParserException err) {
      LOG.error(err);
    }

    assertEquals(185371, value);

  }

  @Test
  public void testDecryptRoomNamesOfValidRooms() {
    InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("roomids.txt");

    MultipartFile roomIds = null;

    try {
      roomIds = new MockMultipartFile("file", stream);
    } catch (IOException err) {
      LOG.error(err);
    }

    List<Room> validRoomIds = null;

    try {
      validRoomIds = service.determineValidRoomIds(roomIds);
    } catch (RoomIdParserException err) {
      LOG.error(err);
    }

    List<Room> decyptedRooms = new ArrayList<Room>();

    try {
      decyptedRooms = service.decryptRoomNamesOfValidRooms(validRoomIds);
    } catch (RoomIdParserException err) {
      LOG.error(err);
    }

    for(Room room : decyptedRooms) {
      LOG.info("Decrypted room | name : [ " + room.getDecryptedName() + " ] sectorId [" + room.getSectorId() + "]");
    }


  }

  @Configuration
  static class ContextConfiguration {

    // this bean will be injected into the OrderServiceTest class
    @Bean
    public RoomIdParserService orderService() {
      RoomIdParserService roomIdParserService = new RoomIdParserServiceImpl();
      return roomIdParserService;
    }
  }


}
