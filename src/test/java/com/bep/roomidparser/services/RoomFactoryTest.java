package com.bep.roomidparser.services;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.factories.RoomFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * <p>Test service calls for room-id-parser.</p>
 *
 * @author sido
 */
@RunWith(JUnit4.class)
public class RoomFactoryTest {

  private static final Log LOG = LogFactory.getLog(RoomFactoryTest.class);


  @Test
  public void testDetermineCharacter() {

    String character = "z";
    int sectorId = 343;
    String validCharacter = null;

    // is a private method
    // is only tested when nessecary
    // validCharacter = RoomFactory.determineCharacter(character, sectorId);

    assertEquals("e", validCharacter);

  }

  @Test
  public void testDetermineActualName() {

    String evaluationToken = "qzmt-zixmtjozy-ivhz";
    int sectorId = 343;
    String decryptedName = RoomFactory.decryptRoomName(evaluationToken, sectorId);

    assertEquals("very encrypted name", decryptedName);

  }


  @Test
  public void testDetermineRooms() {
    InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("roomids.txt");

    MultipartFile roomIds = null;

    try {
      roomIds = new MockMultipartFile("file", stream);
    } catch (IOException err) {
      LOG.error(err);
    }

    List<Room> validRoomIds = null;

    try {
      validRoomIds = RoomFactory.determineRooms(roomIds.getInputStream());
    } catch (IOException err) {
      LOG.error(err);
    }

    assertEquals(337, validRoomIds.size());

  }





}
