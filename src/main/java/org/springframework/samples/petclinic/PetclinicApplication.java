package org.springframework.samples.petclinic;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication()
@ImportResource("/integration/integration.xml")
public class PetclinicApplication {

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(PetclinicApplication.class, args);
		ConfigurableApplicationContext ctx = new SpringApplication(PetclinicApplication.class).run(args);
	    System.out.println("Hit Enter to terminate");
	    System.in.read();
	    ctx.close();
	}

}
