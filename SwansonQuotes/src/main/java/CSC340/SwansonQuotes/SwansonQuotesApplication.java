/*
    Andrew Nice
    CSC 340-01
    Individual Assignment #2 API Prototype
    2/3/2024
*/
package CSC340.SwansonQuotes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SwansonQuotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwansonQuotesApplication.class, args);
        SwansonQuote();
        System.exit(0);

    }

    public static void SwansonQuote() {                                                     //Displays a Quote from Ron Swanson, a character from Parks and Rec
        try {

            String url = "https://ron-swanson-quotes.herokuapp.com/v2/quotes";              //Setting up to pull JSON node from API
            RestTemplate restTemplate = new RestTemplate();                                 
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);      //Pulls the node from API in JSON String form and Defines it as a JSON node for manipulation
            JsonNode root = mapper.readTree(jSonQuote);
            
            String quote = root.toString();                                                 //Turns Node to String since the node only contains the quote

            System.out.print("Ron Swanson once said, ");
            System.out.println(quote.substring(1, quote.length()-1));            //Displays quote with brackets removed
            
        }catch(JsonProcessingException ex){                             
            System.out.println("Sorry, Ron Swanson is not available for Quotes, Try again Later");  //Error Message for failed conversion of JSON data
        }
    }

}
