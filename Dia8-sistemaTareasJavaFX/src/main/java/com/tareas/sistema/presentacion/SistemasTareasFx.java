package com.tareas.sistema.presentacion;

import com.tareas.sistema.TareasApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class SistemasTareasFx extends Application {

	private ConfigurableApplicationContext applicationContext;

	//public static void main(String[] args) {
	//	launch(args);
	//}


	@Override
	public void init(){
		this.applicationContext =
			new SpringApplicationBuilder(TareasApplication.class).run();
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/index.fxml"));
		loader.setControllerFactory(applicationContext::getBean);
		Scene escena = new Scene(loader.load());
		stage.setScene(escena);
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		this.applicationContext.close();
		Platform.exit();
	}

}
