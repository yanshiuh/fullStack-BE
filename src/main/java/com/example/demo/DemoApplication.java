package com.example.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		ClassLoader classLoader = DemoApplication.class.getClassLoader();

		String path = "src/main/resources/serviceAccountKey.json";
		File file = new File(path);
//		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
		FileInputStream serviceAccount =
				new FileInputStream(file.getAbsolutePath());

		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		FirebaseApp firebaseApp = null;
		List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
		if(firebaseApps!=null && !firebaseApps.isEmpty()){
			for(FirebaseApp app : firebaseApps){
				if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
					firebaseApp = app;
			}
		}
		else
			firebaseApp = FirebaseApp.initializeApp(options);

		SpringApplication.run(DemoApplication.class, args);
	}

}
