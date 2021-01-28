package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
	
	private static final String LAST_NAME = "Smith";

	@Mock
	OwnerRepository ownerRepository;
	
	@Mock
	PetRepository petRepository;
	
	@Mock
	PetTypeRepository petTypeRepository;
	
	@InjectMocks
	OwnerSDJpaService service;
	
	Owner returnOwner;

	@BeforeEach
	void setUp() throws Exception {
		returnOwner = Owner.builder().id(1l).lastName(LAST_NAME).build();
	}

	@Test
	void findAll() {
		Set<Owner> returnOwnersSet = new HashSet<>();
		returnOwnersSet.add(Owner.builder().id(1l).build());
		returnOwnersSet.add(Owner.builder().id(2l).build());
		
		when(ownerRepository.findAll()).thenReturn(returnOwnersSet);
		
		Set<Owner> owners = service.findAll();
		
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void findById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
		
		Owner owner = service.findById(1l);
		
		assertNotNull(owner);
	}
	
	@Test
	void findByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Owner owner = service.findById(1l);
		
		assertNull(owner);
	}

	@Test
	void save() {
		Owner ownerToSaveOwner = Owner.builder().id(1L).build();
		
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		
		Owner savedOwner = service.save(ownerToSaveOwner);
		
		assertNotNull(savedOwner);
		
		verify(ownerRepository).save(any());
	}

	@Test
	void delete() {
		service.delete(returnOwner);
		
		verify(ownerRepository).delete(any());
	}

	@Test
	void deleteById() {
		service.deleteById(returnOwner.getId());
		// verify if it's called 1 time
		verify(ownerRepository).deleteById(any());
	}

	@Test
	void findByLastName() {
		when(service.findByLastName(any())).thenReturn(returnOwner);
		
		Owner smith = service.findByLastName(LAST_NAME);
		
		assertEquals(LAST_NAME, smith.getLastName());
		
		verify(ownerRepository).findByLastName(any());
	}

}
