package WTproject.boekenWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

// import WTproject.boekenWT.logic.BookCRUD.CreateBook;

@SpringBootApplication
public class BoekenWtApplication {
	@Autowired
	private Environment environment;
	public static void main(String[] args) {

		SpringApplication.run(BoekenWtApplication.class, args);
	}

}

	



