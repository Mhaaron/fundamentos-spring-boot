package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyChallenge;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	//Libreria de apache common para logs
	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	//Inyección de la dependencia
	private UserPojo userPojo;
	private MyBeanWithProperties myBeanWithProperties;
	private MyChallenge myChallenge;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBean mybean;
	private ComponentDependency componentDependency;
	private UserRepository userRepository;
	private UserService userService;
	//Constructor donde se finaliza la inyección de la dependencia
	//@Autowride se agregaba en versiones anteriores, en esta versión 11 no es necesario
	//Con @Qualifier podemos decidir que clase queremos inyectar, si tenemos más de un component definido
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean mybean, MyBeanWithDependency myBeanWithDependency, MyChallenge myChallenge, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.mybean = mybean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myChallenge = myChallenge;
		this.myBeanWithProperties =myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}
	//Método de CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional() {
		User test1 = new User("Test1Transactional", "test1@domain.com", LocalDate.now());
		User test2 = new User("Test2Transactional", "test2@domain.com", LocalDate.now());
		User test3 = new User("Test1Transactional", "test1@domain.com", LocalDate.now());
		User test4 = new User("Test4Transactional", "test4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try {
			userService.saveTransactional(users);
		} catch (Exception e) {
			LOGGER.error("Esta es una exception dentro del transactional " + e);
		}

		userService.getAllUsers()
				.stream()
				.forEach(user -> LOGGER.info("Usuario dentro de transactional: " + user));
	}

	private void getInformationJpqlFromUser() {
		/*LOGGER.info("Usuario con el método findByUserEmail " +
				userRepository.findByUserEmail("jonh@domain.com")
					.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending())
			.stream()
			.forEach(user -> LOGGER.info("Usuario con metodo sort " + user));

		userRepository.findByName("John")
			.stream()
			.forEach(user -> LOGGER.info("Usuario con query method " + user));

		LOGGER.info("Usuario con query method name and email" + userRepository.findByNameAndEmail("John", "jonh@domain.com")
			.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%u%")
			.stream()
			.forEach(user -> LOGGER.info("Usuario con like " + user));

		userRepository.findByNameOrEmail(null, "jonh@domain.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con OR " + user));

		userRepository.findByBirthDateBetween(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 7, 5))
			.stream()
			.forEach(user -> LOGGER.info("Usuario con intervalo de fechas " + user));

		userRepository.findByNameContainingOrderByIdDesc("user")
			.stream()
			.forEach(user -> LOGGER.info("User con contain y ordenado " + user));

		userRepository.findByNameOrEmailContainingOrderByNameAsc("user", "@domain.com")
			.stream()
			.forEach(user -> LOGGER.info("Usuarios con nombre o correo ordenados por nombre " + user));*/

		LOGGER.info("Usuario a partir del named parameter es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 05, 03), "daniela@domain.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario a partir del named parameter")));
	}

	//Método para persistir nuestra información
	private void saveUsersInDataBase() {
		User user1 = new User("John", "jonh@domain.com", LocalDate.of(2021, 03, 01));
		User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021, 04, 02));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 05, 03));
		User user4 = new User("user4", "user4@domain.com", LocalDate.of(2021, 06, 04));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021, 07, 05));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021, 8, 06));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021, 9, 07));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021, 10, 8));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021, 11, 9));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021, 12, 10));
		//Se usa una lista para poder hacer el registro más rapidamente
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores() {
		componentDependency.saludar();
		mybean.print();
		myBeanWithDependency.printWithDependency();
		myChallenge.print();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
		try {
			//ERROR
			int value = 10/0;
			LOGGER.debug("Mi valor: " + value);
		} catch (Exception e) {
			LOGGER.error("Esto es un error al dividir por cero " + e.getMessage());
		}
	}
}
