package com.bep.roomidparser.services;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.factories.RoomFactory;
import com.bep.roomidparser.exceptions.RoomIdParserException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>The implementation of the parser-service</p>
 *
 * @author sido
 */
@Service
public class RoomIdParserServiceImpl implements RoomIdParserService {

  private static final Log LOG = LogFactory.getLog(RoomIdParserServiceImpl.class);

  @Override
  public List<Room> determineValidRoomIds(MultipartFile file) throws RoomIdParserException {
    List<Room> validRoomIds = new ArrayList<Room>();

    if (file == null) {
      LOG.trace("No input data was received");
      throw new RoomIdParserException("No input data was received");
    }

    InputStream inputStream;

    try {
      inputStream = file.getInputStream();
    } catch (IOException err) {
      LOG.trace("Something went wrong while uploading file", err);
      throw new RoomIdParserException(err.getMessage());
    }

    try {
      validRoomIds = RoomFactory.determineRooms(inputStream);
    } catch (IOException err) {
      LOG.trace("Something went wrong while uploading file", err);
      throw new RoomIdParserException(err.getMessage());
    }

    return validRoomIds;
  }

  @Override
  public int calculateNumberOfValidRooms(List<Room> validRoomIds) throws RoomIdParserException {
    int value = 0;
    for (Room room : validRoomIds) {
      value += room.getNumber();
    }
    return value;
  }


}
