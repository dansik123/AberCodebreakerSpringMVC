package aber.uni.main.assignment.web.demo.controllers;

import aber.uni.assignment.cyphers.VigenereCypher;
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
public class VigenereCypherController {

    /**
     * Controller initialize alphabet object
     */
    public VigenereCypherController() {

    }

    /**
     * Method display web page for caesarCypher.html template
     * for GET request
     * @param model Model
     * @return Sting
     */
    @RequestMapping(value = "/vigenere", method = RequestMethod.GET)
    public String getVigenereCypherPage(Model model)
    {
        VigenereCypher cypher = new VigenereCypher("", "",true);
        model.addAttribute("vigenereCypher",cypher);
        return "vigenereCypher";
    }

    /**
     * Method handle POST request from server
     * Gets values key, text, textResult, toDecode as params
     * and returns page template with result in textResult variable
     * @param cypher CaesarCypher
     * @param model Model
     * @return String
     */
    @RequestMapping(value = "/vigenere",
            method = RequestMethod.POST,
            params={"key","toDecode","plainText","resultText"})
    public String textAndKeyToText(@ModelAttribute VigenereCypher cypher,
                                   Model model)
    {
        cypher.initializeMatrix();
        cypher.coded();
        model.addAttribute("vigenereCypher", cypher);
        return "vigenereCypher";
    }


    /**
     * Method handle POST request from server
     * Gets values key, text toDecode as params
     * and returns byte array
     * which on client side is text file as result
     * @param cypher CaesarModel
     * @return byte[]
     */
    @RequestMapping(value = "/vigenereCypherFile.txt",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
            params={"key","plainText","toDecode"})
    public @ResponseBody
    byte[] textAndKeyToFile(@ModelAttribute VigenereCypher cypher){
        cypher.initializeMatrix();
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
    @RequestMapping(value = "/vigenereCypherFile.txt",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] fileAndKeyToFile(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("key") String key,
                                                 @RequestParam("toDecode") boolean toDecode) throws IOException {
        byte[] bytes = file.getBytes();
        VigenereCypher vigenereCypher = new VigenereCypher(new String(bytes),key,toDecode);
        vigenereCypher.coded();
        return vigenereCypher.getResultText().getBytes();
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
    @RequestMapping(value = "/vigenere", method = RequestMethod.POST)
    public String fileAndKeyToText(@RequestParam("file") MultipartFile file,
                                   @RequestParam("key") String key,
                                   @RequestParam("toDecode") boolean toDecode,
                                   Model model) throws IOException {
        byte[] bytes = file.getBytes();
        VigenereCypher cypher = new VigenereCypher(new String(bytes),key,toDecode);
        cypher.coded();
        model.addAttribute("vigenereCypher", cypher);
        return "vigenereCypher";
    }
}

