package com.bep.roomidparser.controllers;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.services.RoomIdParserService;
import exceptions.RoomIdParserException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>Room-id-parser controller.</p>
 *
 * @author sido
 */
@Controller
@RequestMapping("/parser")
public class RoomIdParserController {

  private static final Log LOG = LogFactory.getLog(RoomIdParserController.class);

  private static final String REDIRECT_KEY_ATTRIBUTE_MESSAGE = "message";
  private static final String REDIRECT_KEY_ATTRIBUTE_VALID_ROOMS = "validRooms";
  private static final String REDIRECT_KEY_ATTRIBUTE_COUNT_ROOMS = "countNumberRooms";

  @Autowired
  private RoomIdParserService service;

  /**
   * <p>Parses the room-ids. This results in the determination of valid rooms and a count of the room-numbers.</p>
   *
   * @param file               the posted file that contains the room-id's
   * @param redirectAttributes you can add messages to the view with these attributes
   * @return RedirectView to load frontend redirect urls
   */
  @PostMapping("/rooms")
  @ResponseBody
  public RedirectView parseRoomIds(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_MESSAGE, "Please select a file to upload");
      return new RedirectView("/parser/result", true);
    }

    int countNumberOfValidRooms;


    try {
      List<Room> roomIds = service.determineValidRoomIds(file);
      countNumberOfValidRooms = service.calculateNumberOfValidRooms(roomIds);
      redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_MESSAGE, "All rooms have been examined (file used is: [ " + file.getOriginalFilename() + " ])");
      redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_VALID_ROOMS, "The total count of the valid rooms is : [ " + roomIds.size() + " ]");
      redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_COUNT_ROOMS, "The total count of the room-numbers is: [ " + countNumberOfValidRooms + " ]");
    } catch (RoomIdParserException e) {
      LOG.error(e);
      redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_MESSAGE, "Something went wrong when the rooms were examined");
    }

    return new RedirectView("/parser/result", true);
  }

  /**
   * <p>When the rooms are parsed this is the route that deliover the page with the results.</p>
   *
   * @return delivers results.html
   */
  @RequestMapping("/result")
  public String status() {
    return "result";
  }

}
