package com.support.schedular;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
        
    @Test
    public void generateScheduleTest() throws Exception {
        
        String inputMock = "{\"startDate\":\"2018-03-10\", \"endDate\":\"2018-03-25\"}";
        
        Mockito.when(scheduleService.generateSchedules(Mockito.anyString(), Mockito.anyString())).thenReturn(Mockito.any());
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/generate_schedule")
				.accept(MediaType.APPLICATION_JSON).content(inputMock).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMVC.perform(requestBuilder).andReturn();
		MockHttpServletResponse  response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


}
