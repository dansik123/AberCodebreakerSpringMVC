package aber.uni.main.assignment.web.demo.controllers;

import aber.uni.main.assignment.web.demo.cyphers.CaesarCypher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

/**
 * IndexController Handle all request from server
 * for Caesar Cypher
 * @author Daniel Jozef Sikora
 * @version 1.0 (25th April 2020)
 */
@Controller
public class CaesarCypherController {

    /**
     * Controller initialize alphabet object
     */
    public CaesarCypherController() {

    }

    /**
     * Method display web page for caesarCypher.html template
     * for GET request
     * @param model Model
     * @return Sting
     */
    @RequestMapping(value = "/caesar", method = RequestMethod.GET)
    public String getCaesarCypherPage(Model model)
    {
        CaesarCypher caesarCypher = new CaesarCypher("",0,true);
        model.addAttribute("cypher",caesarCypher);
        return "caesarCypher";
    }

    /**
     * Method handle POST request from server
     * Gets values key, text, textResult, toDecode as params
     * and returns page template with result in textResult variable
     * @param cypher CaesarCypher
     * @param model Model
     * @return String
     */
    @RequestMapping(value = "/caesar", method = RequestMethod.POST,
                    params={"key","toDecode","plainText","resultText"})
    public String textAndKeyToText(@ModelAttribute CaesarCypher cypher, Model model)
    {
        cypher.coded();
        model.addAttribute("cypher", cypher);
        return "caesarCypher";
    }


    /**
     * Method handle POST request from server
     * Gets values key, text toDecode as params
     * and returns byte array
     * which on client side is text file as result
     * @param cypher CaesarModel
     * @return byte[]
     */
    @RequestMapping(value = "/cypherFile.txt", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
            params={"key","plainText","toDecode"})
    public @ResponseBody byte[] textAndKeyToFile(@ModelAttribute CaesarCypher cypher){
        cypher.coded();
        return cypher.getResultText().getBytes();
    }

    /**
     * Method handle POST request from server
     * Gets values key, file toDecode as params
     * and returns byte array
     * which on client side is text file as result
     * @param file MultipartFile
     * @param key int
     * @param toDecode boolean
     * @return byte[]
     * @throws IOException access to file exception
     */
    @RequestMapping(value = "/cypherFile.txt", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] fileAndKeyToFile(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("key") int key,
                                                 @RequestParam("toDecode") boolean toDecode) throws IOException {
        byte[] bytes = file.getBytes();
        CaesarCypher cypher = new CaesarCypher(new String(bytes),key,toDecode);
        cypher.coded();
        return cypher.getResultText().getBytes();
    }

    /**
     * Method handle POST request from server
     * Gets values key, file toDecode as params
     * and returns byte array
     * which on client side is text file as result
     * @param model Model
     * @param file MultipartFile
     * @param key int
     * @param toDecode boolean
     * and returns page template with result in textResult variable
     * @throws IOException access to file exception
     */
    @RequestMapping(value = "/caesar", method = RequestMethod.POST)
    public String fileAndKeyToText(@RequestParam("file") MultipartFile file,
                                   @RequestParam("key") int key,
                                   @RequestParam("toDecode") boolean toDecode,
                                   Model model) throws IOException {
        byte[] bytes = file.getBytes();
        CaesarCypher cypher = new CaesarCypher(new String(bytes),key,toDecode);
        cypher.coded();
        model.addAttribute("cypher", cypher);
        return "caesarCypher";
    }
}

