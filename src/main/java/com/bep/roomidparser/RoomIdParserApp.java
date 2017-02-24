package com.bep.roomidparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 *
 * <p>Starting point of room-id-parser</p>
 *
 * @author sido
 *
 */
@ComponentScan
@EnableAutoConfiguration
public class RoomIdParserApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RoomIdParserApp.class, args);
    }


}
