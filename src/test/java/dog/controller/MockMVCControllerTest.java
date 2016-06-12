package dog.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dog.dao.DogDao;
import dog.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:test-context.xml")
public class MockMVCControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    DogDao dogDao;

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void mustReturnCollectionOfDogs() throws Exception {

        List<Dog> dogs = dogDao.createStaticDogs();

        String result = this.mockMvc.perform(get("/dog"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<Dog> fetchedDogs = mapper.readValue(result, new TypeReference<List<Dog>>(){});

        assertReflectionEquals(dogs,fetchedDogs);

    }
}
