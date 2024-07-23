package UNite_TEST.UNIT_TEST;

import UNite_TEST.UNIT_TEST.Repo.UserRepo;
import UNite_TEST.UNIT_TEST.Service.UserService;
import UNite_TEST.UNIT_TEST.entities.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Rollback
public class UserServiceTest {

	@Autowired
	private UserRepo userRepository;

	private UserService userService;

	@BeforeEach
	public void setUp() {
		userService = new UserService();
		userService.userRepo = userRepository;
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setName("Daniele");
		user.setEmail("deo9674@gmail.com");

		User savedUser = userService.createUser(user);

		assertNotNull(savedUser);
		assertNotNull(savedUser.getId());
		assertEquals("Daniele", savedUser.getName());
		assertEquals("deo9674@gmail.com", savedUser.getEmail());
	}

	@Test
	public void testGetAllUsers() {
		User user1 = new User();
		user1.setName("Daniele");
		user1.setEmail("deo9674@gmail.com");

		User user2 = new User();
		user2.setName("Daniela");
		user2.setEmail("dea9674@gmail.com");

		userService.createUser(user1);
		userService.createUser(user2);

		List<User> users = userService.getAllUsers();
		assertEquals(2, users.size());
	}

	@Test
	public void testGetUserById() {
		User user = new User();
		user.setName("Daniele");
		user.setEmail("deo9674@gmail.com");

		User savedUser = userService.createUser(user);

		Optional<User> foundUser = userService.getUserById(savedUser.getId());

		assertTrue(foundUser.isPresent());
		assertEquals(savedUser.getId(), foundUser.get().getId());
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setName("Daniele");
		user.setEmail("deo9674@gmail.com");

		User savedUser = userService.createUser(user);

		User userDetails = new User();
		userDetails.setName("Updated Name");
		userDetails.setEmail("updated@example.com");

		User updatedUser = userService.aggiornaUser(savedUser.getId(), userDetails);

		assertNotNull(updatedUser);
		assertEquals("Updated Name", updatedUser.getName());
		assertEquals("updated@example.com", updatedUser.getEmail());
	}

	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setName("Daniele");
		user.setEmail("deo9674@gmail.com");

		User savedUser = userService.createUser(user);

		userService.deleteUser(savedUser.getId());

		Optional<User> deletedUser = userService.getUserById(savedUser.getId());

		assertFalse(deletedUser.isPresent());
	}
}
