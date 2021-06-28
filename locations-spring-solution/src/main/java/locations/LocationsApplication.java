package locations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class LocationsApplication {

	public static void main(String[] args) {

		ApplicationContext atxt = SpringApplication.run(LocationsApplication.class, args);

		//LocationsController locationsController = atxt.getBean(LocationsController.class);

		//System.out.println(locationsController.getLocations(Optional.empty()));
	}



	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper().findAndRegisterModules();
	}


}
