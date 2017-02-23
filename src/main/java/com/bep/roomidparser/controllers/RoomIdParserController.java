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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

/**
 *
 *
 *
 * @author sido
 */
@Controller
public class RoomIdParserController {

    private static final Log LOG = LogFactory.getLog(RoomIdParserController.class);

    private int countNumberOfValidRooms;

    @Autowired
    private RoomIdParserService service;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/rooms")
    @ResponseBody
    public String parseRoomIds(@RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {

            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
                return "redirect:/status";
            }

            try {
                List<Room> roomIds = service.determineValidRoomIds(file.getInputStream());
                countNumberOfValidRooms = service.calculateNumberOfValidRooms(roomIds);

                redirectAttributes.addFlashAttribute("message", "All rooms have been examined [ " + file.getOriginalFilename() + " ]");
            } catch (IOException|UserException e) {
                redirectAttributes.addFlashAttribute("message", "Something went wrong when the rooms were examined");
                LOG.error(e);
            }
            return "redirect:/status";
        }


    @RequestMapping("/count")
    @ResponseBody
    public String countNumberOfValidRooms() {
        return "The sum of all room numbers is [ " + countNumberOfValidRooms + " ]";
    }

    @RequestMapping("/status")
    @ResponseBody
    public String parserStatus() {
        return "status";
    }

}
