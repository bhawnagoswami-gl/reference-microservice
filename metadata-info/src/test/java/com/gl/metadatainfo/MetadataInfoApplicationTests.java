package com.gl.metadatainfo;

import com.ac.metadatainfo.MetadataInfoApplication;
import com.ac.metadatainfo.model.MetadataInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MetadataInfoApplication.class)

@AutoConfigureMockMvc
class MetadataInfoApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getAllMetaInfo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/metainfo/all").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}
/*
	@Test
	public void addMetaInfo() throws Exception {
		MetadataInfo metadata = new MetadataInfo();
		metadata.setDocId("4");
		metadata.setDocSize("200kb");
		metadata.setDocType("pdf");
		mockMvc.perform(MockMvcRequestBuilders.post("/metainfo").content(asJsonString(metadata))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
*/
	@Test
	public void getMetaInfoById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/metainfo/4").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("docId").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("docType").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("docSize").exists());
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