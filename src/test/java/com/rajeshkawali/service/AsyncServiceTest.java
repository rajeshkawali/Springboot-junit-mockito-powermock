package com.rajeshkawali.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.rajeshkawali.model.ModelName;
import com.rajeshkawali.repository.AsyncRepository;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
public class AsyncServiceTest {

	@InjectMocks
	private AsyncService asyncService;

	@Mock
	private AsyncRepository asyncRepository;

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = Mockito.mock(RestTemplate.class);
		ReflectionTestUtils.setField(asyncService, "asyncRepository", asyncRepository);
		ReflectionTestUtils.invokeMethod(asyncService, "asyncRepository" ,"test");
	}

	@Test
	public void testFetchUserById() {
		ModelName modelName = new ModelName();
		Mockito.when(asyncRepository.fetchUserById(Mockito.anyInt())).thenReturn(new ModelName(1, "Rajesh", "Kawali"));
		modelName = asyncService.fetchUserById(1);
		assertEquals(1, modelName.getId());
	}

	@Test
	public void testFetchUserByIdException() {
		ModelName modelName = new ModelName();
		Mockito.doThrow(new RuntimeException()).when(asyncRepository).fetchUserById(Mockito.anyInt());
		modelName = asyncService.fetchUserById(1);
		assertNull(modelName);
	}
	
	@Test(expected = Exception.class)
	public void testFetchUserException() {
		ModelName modelName = new ModelName();
		Mockito.doThrow(new Exception()).when(asyncRepository).fetchUserById(Mockito.anyInt());
		modelName = asyncService.fetchUserById(1);
		assertEquals(Exception.class, modelName);
	}

	@Test // example RestTemplate
	public void testRestTemplateExample() {
		ModelName modelName = new ModelName();
		Mockito.doNothing().when(asyncRepository).fetchUserById(Mockito.anyInt());
		Mockito.when(restTemplate.getForObject(Mockito.startsWith("exampleURL"), Mockito.eq(ModelName.class))).thenReturn(modelName);
		Mockito.when(restTemplate.postForObject(Mockito.startsWith("exampleURL"), Mockito.any(), Mockito.eq(String.class))).thenReturn("xyz");
		// modelName = asyncService.fetchUserById(1);
		assertNotNull(modelName);
	}
}
