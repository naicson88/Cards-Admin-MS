package com.naicson.yugioh.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cardscommons.dto.AssociationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.naicson.yugioh.mocks.AssociationDTOMock;
import com.naicson.yugioh.resttemplates.SetCollectionRestTemplate;
import com.naicson.yugioh.service.setCollection.SetCollectionServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)	
public class SetCollectionTest {
	
	@Spy
	@InjectMocks
	SetCollectionServiceImpl setService;
	
	@Mock
	SetCollectionRestTemplate setTemplate;
	
	private ResponseEntity<String> respEntity = new ResponseEntity<String>("Registered!", HttpStatus.OK);
	
	@BeforeEach
	private  void setup(){
	    MockitoAnnotations.openMocks(this); 
	}	
	
	@Test
	public void createNewAssociation() {
		AssociationDTO dto = AssociationDTOMock.returnAssociationDTO();
		String token = "Token Test";
		
		Mockito.when(setTemplate.sendNewAssociation(dto, token)).thenReturn(respEntity);
		
		AssociationDTO registered = setService.newAssociation(dto, token);
		
		assertNotNull(registered);
		assertEquals(dto.getSourceId(), registered.getSourceId());
		assertEquals(2, registered.getArrayToAssociate().size());
		
	}
	
	@Test
	public void validTokenNewAssociation() {
		AssociationDTO dto = AssociationDTOMock.returnAssociationDTO();
		String token = null;
		
		IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			 setService.newAssociation(dto, token);  
		});
				
		String expected = "Invalid Token informed! " + token;
		String actual = exception.getMessage();
		
		assertTrue(actual.contains(expected));		
	}
	
	@Test
	public void verifyResponseEntityError() {
		AssociationDTO dto = AssociationDTOMock.returnAssociationDTO();
		String token = "Token Test";
		
		respEntity = new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		
		Mockito.when(setTemplate.sendNewAssociation(dto, token)).thenReturn(respEntity);
		
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
			 setService.newAssociation(dto, token);  
		});
				
		String expected = "It was not possible register a new Association " + respEntity.getBody();
		String actual = exception.getMessage();
		
		assertTrue(actual.contains(expected));	
	}
	
}
