package pizzeria;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Order;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileReader;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Controllers Testing.
 * Created by lucasluduena on 07/05/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objmapper = new ObjectMapper();

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

        Order order = objmapper.readValue(String.valueOf(getJsonOrderFile("order.json")),
                Order.class);

        this.mockMvc.perform(post("/order")
                .content(order.toString()).contentType(contentType)).andExpect(status().isOk());
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage1() throws Exception {

        Order order = objmapper.readValue(String.valueOf(getJsonOrderFile("order.json")),
                Order.class);

        this.mockMvc.perform(post("/routing")
                .content(order.toString()).contentType(contentType)).andExpect(status().isOk());
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        Order order = objmapper.readValue(String.valueOf(getJsonOrderFile("order.json")),
                Order.class);

        this.mockMvc.perform(post("/preparation/status/DONE")
                .content(order.toString()).contentType(contentType)).andExpect(status().isOk());
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage3() throws Exception {

        Order order = objmapper.readValue(String.valueOf(getJsonOrderFile("order.json")),
                Order.class);

        this.mockMvc.perform(post("/notification/sms")
                .content(order.toString()).contentType(contentType)).andExpect(status().isOk());
        this.mockMvc.perform(post("/notification/email")
                .content(order.toString()).contentType(contentType)).andExpect(status().isOk());
        this.mockMvc.perform(post("/notification/smsandemail")
                .content(order.toString()).contentType(contentType)).andExpect(status().isOk());

    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage4() throws Exception {

        this.mockMvc.perform(post("/location")
                .contentType(contentType)).andExpect(status().isOk());
    }


    public JSONObject getJsonOrderFile(String filename) {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader
                    (String.valueOf(ClassLoader
                            .getSystemResource(filename).getFile())));

            return (JSONObject) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
