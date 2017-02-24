package com.bep.roomidparser.controllers;

import com.bep.roomidparser.domain.Room;
import com.bep.roomidparser.exceptions.UserException;
import com.bep.roomidparser.services.RoomIdParserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 *
 *
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

    @PostMapping("/rooms")
    @ResponseBody
    public RedirectView parseRoomIds(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_MESSAGE, "Please select a file to upload");
                return new RedirectView("/parser/result", true);
            }

            int countNumberOfValidRooms;


            try {
                List<Room> roomIds = service.determineValidRoomIds(file.getInputStream());
                countNumberOfValidRooms = service.calculateNumberOfValidRooms(roomIds);
                redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_MESSAGE, "All rooms have been examined (file used is: [ " + file.getOriginalFilename() + " ])");
                redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_VALID_ROOMS, "The total count of the valid rooms is : [ " + roomIds.size() + " ]");
                redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_COUNT_ROOMS, "The total count of the room-numbers is: [ " + countNumberOfValidRooms + " ]");
            } catch (IOException|UserException e) {
                redirectAttributes.addFlashAttribute(REDIRECT_KEY_ATTRIBUTE_MESSAGE, "Something went wrong when the rooms were examined");
                LOG.error(e);
            }

            return new RedirectView("/parser/result", true);
        }

    @RequestMapping("/result")
    public String status() {
    return "result";
  }

}
