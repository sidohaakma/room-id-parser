package com.bep.roomidparser.controllers;

import com.bep.roomidparser.exceptions.UserException;
import com.bep.roomidparser.services.RoomIdParserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 *
 *
 *
 * @author sido
 */
@Controller
@RequestMapping("/parser")
public class RoomIdParserController {


    private static final Log log = LogFactory.getLog(RoomIdParserController.class);

    @Autowired
    private RoomIdParserService service;


    @RequestMapping("/calculate")
    @ResponseBody
    public int calculateNumberOfValidRooms() {

        try {
            service.calculateNumberOfValidRooms(null);
        } catch (UserException err) {
            log.error(err);
        }

        return 1000;
    }

}
