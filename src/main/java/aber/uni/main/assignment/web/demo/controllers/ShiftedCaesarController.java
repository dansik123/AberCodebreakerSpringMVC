package aber.uni.main.assignment.web.demo.controllers;

import aber.uni.main.assignment.web.demo.cyphers.KeyedCaesarCypher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ShiftedCaesarController handle all request from server
 * for Caesar Shifted Cypher
 * @author Daniel Jozef Sikora
 * @version 1.0 (25th April 2020)
 */
@Controller
public class ShiftedCaesarController {

    /**
     * Controller initialize alphabet object
     */
    public ShiftedCaesarController() {
    }

    /**
     * Method display web page for caesarCypher.html template
     * for GET request
     * @param model Model
     * @return Sting
     */
    @RequestMapping(value = "/shifted", method = RequestMethod.GET)
    public String getCaesarShiftedPage(Model model)
    {
        KeyedCaesarCypher cypher = new KeyedCaesarCypher("",0,"",true);
        model.addAttribute("shiftedCypher",cypher);
        return "shiftedCaesar";
    }

    /**
     * Method handle POST request from server
     * Gets values key, text, textResult, toDecode as params
     * and returns page template with result in textResult variable
     * @param cypher CaesarCypher
     * @param model Model
     * @return String
     */
    @RequestMapping(value = "/shifted", method = RequestMethod.POST, params={"key","toDecode","plainText","resultText"})
    public String textAndKeyToText(@ModelAttribute KeyedCaesarCypher cypher,
                                   Model model)
    {
        cypher.createAlphabet();
        cypher.coded();
        model.addAttribute("shiftedCypher", cypher);
        return "shiftedCaesar";
    }


    /**
     * Method handle POST request from server
     * Gets values key, text toDecode as params
     * and returns byte array
     * which on client side is text file as result
     * @param cypher CaesarModel
     * @return byte[]
     */
    @RequestMapping(value = "/shiftedCypherFile.txt", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
            params={"key","plainText","toDecode"})
    public @ResponseBody
    byte[] textAndKeyToFile(@ModelAttribute KeyedCaesarCypher cypher){
        cypher.createAlphabet();
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
    @RequestMapping(value = "/shiftedCypherFile.txt", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] fileAndKeyToFile(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("key") String key,
                                                 @RequestParam("shift") int shift,
                                                 @RequestParam("toDecode") boolean toDecode) throws IOException {
        byte[] bytes = file.getBytes();
        KeyedCaesarCypher keyedCaesarCypher = new KeyedCaesarCypher(new String(bytes),shift,key,toDecode);
        keyedCaesarCypher.createAlphabet();
        keyedCaesarCypher.coded();
        return keyedCaesarCypher.getResultText().getBytes();
    }

    /**
     * Method handle POST request from server
     * Gets values key, file, toDecode as params
     * and returns byte array
     * which on client side is text file as result
     * @param model Model
     * @param file MultipartFile
     * @param key int
     * @param toDecode boolean
     * and returns page template with result in textResult variable
     * @throws IOException access to file exception
     */
    @RequestMapping(value = "/shifted", method = RequestMethod.POST)
    public String fileAndKeyToText(@RequestParam("file") MultipartFile file,
                                   @RequestParam("key") String key,
                                   @RequestParam("shift") int shift,
                                   @RequestParam("toDecode") boolean toDecode,
                                   Model model) throws IOException {
        byte[] bytes = file.getBytes();
        KeyedCaesarCypher cypher = new KeyedCaesarCypher(new String(bytes), shift, key, toDecode);
        cypher.createAlphabet();
        cypher.coded();
        model.addAttribute("shiftedCypher", cypher);
        return "shiftedCaesar";
    }
}
