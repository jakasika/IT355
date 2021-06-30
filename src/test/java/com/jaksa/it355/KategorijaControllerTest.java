package com.jaksa.it355;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaksa.it355.entity.KategorijeEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

//import org.junit.jupiter.api.Test;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureMockMvc
public class KategorijaControllerTest   {


    @Autowired
    private MockMvc mockMvc;

        protected  <T> T mapFromJson(String json, Class<T> clss) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clss);
    }
//    @Test
//    public void givenUrlEncodedFormData_whenAddEmployeeEndpointCalled_thenModelContainsMsgAttribute() throws Exception {
//        Collection<NameValuePair> formData = Arrays.asList(new BasicNameValuePair("name", "employeeName"), new BasicNameValuePair("id", "99"), new BasicNameValuePair("contactNumber", "123456789"));
//        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));
//
//        mockMvc.perform(post("/addEmployee").contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .content(urlEncodedFormData))
//                .andExpect(status().isOk())
//                .andExpect(view().name("employeeView"))
//                .andExpect(model().attribute("msg", "Welcome to the Netherlands!"));
//    }


    @Test
    public void lista() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/admin/kategorije/").accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(status, HttpStatus.OK.value());
        String content = mvcResult.getResponse().getContentAsString();
        assertNotEquals(content.length(), 0);
        KategorijeEntity[] kategorije = mapFromJson(content, KategorijeEntity[].class);
        assertTrue(kategorije.length > 0);
    }
}
