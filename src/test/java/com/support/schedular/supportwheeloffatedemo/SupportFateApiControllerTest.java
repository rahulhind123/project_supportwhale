package com.support.schedular.supportwheeloffatedemo;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.support.schedular.controller.SupportFateApiController;
import com.support.schedular.service.ScheduleService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=SupportFateApiController.class , secure = false)
public class SupportFateApiControllerTest {
	
	@Autowired
    private MockMvc mockMVC;
    
    @MockBean
    private ScheduleService scheduleService;
    
    
    @Ignore
    public void generateScheduleTest() throws Exception {
        
        String jsonMock = "{\"startDate\":\"2018-03-10\", \"endDate\":\"2018-03-25\"}";
        
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/generate_schedule")
				.accept(MediaType.APPLICATION_FORM_URLENCODED).param(jsonMock).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMVC.perform(requestBuilder).andReturn();
		MockHttpServletResponse  response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


}
