package com.example.document;

import com.example.document.dto.DocumentChangeRequestDto;
import com.example.document.dto.DocumentCreateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DocumentApplicationTests {
	@Autowired
	private MockMvc mvc;

	@Test
	public void createDocumentAPI() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
				.post("/document")
				.content(asJsonString(new DocumentCreateRequestDto("Test_document_create", 3L, 2L, "")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nameDocument").exists());
	}

	@Test
	public void updateEmployeeAPI() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
				.put("/document/{id}", 1)
				.content(asJsonString(new DocumentChangeRequestDto("Test_document_put", "")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nameDocument").value("Test_document_put"));
	}

	@Test
	public void limitDocumentCompanyToCompany() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
				.post("/document")
				.content(asJsonString(new DocumentCreateRequestDto("Test_document_create", 2L, 1L, "")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Превышен лимит документооборота между двумя компаниями"));
	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
