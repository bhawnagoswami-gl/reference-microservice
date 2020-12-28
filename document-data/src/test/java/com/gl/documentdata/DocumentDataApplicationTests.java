package com.gl.documentdata;

import com.ac.documentdata.DocumentDataApplication;
import com.ac.documentdata.controller.DocumentDataController;
import com.ac.documentdata.dao.DocumentDataDAO;
import com.ac.documentdata.model.DocumentData;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DocumentDataApplication    .class)
@SpringBootTest
@AutoConfigureMockMvc
class DocumentDataApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	@Mock
	DocumentDataDAO documentDataDAO;
	@InjectMocks
	DocumentDataController documentDataController;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getAllDocs() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/docinfo/all").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}

	/*@Test
	public void addDoc() throws Exception{
		DocumentData docData = new DocumentData();
		docData.setDocId("4");
		docData.setDocLocation("D drive");
		docData.setDocName("Library Card");
		mockMvc.perform(MockMvcRequestBuilders.post("/docinfo")
				.content(asJsonString(docData))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
*/
	@Test
	public void getDocById() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/docinfo/4").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("docId").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("docName").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("docLocation").exists());
	}


	public String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

