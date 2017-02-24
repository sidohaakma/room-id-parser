package com.bep.roomidparser.services;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.exceptions.RoomIdParserException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * <p>Service to determine if rooms are valid and calclute the sum of room numbers.</p>
 *
 * @author sido
 */
public interface RoomIdParserService {

  /**
   *
   * <p>Determine if room-id's are valid</p>
   *
   * @param file file wioth room-id's
   * @return List<Room> list with valid room-id's
   * @throws RoomIdParserException
   */
  List<Room> determineValidRoomIds(MultipartFile file) throws RoomIdParserException;

  /**
   *
   * <p>Calculate the sum of room-numbers of the valid room-id's.</p>
   *
   * @param validRoomIds list of valid room-id's
   * @return sum of room numbers
   * @throws RoomIdParserException
   */
  int calculateNumberOfValidRooms(List<Room> validRoomIds) throws RoomIdParserException;
}
