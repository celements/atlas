package org.opencelements.atlas;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.mongodb.client.MongoClient;

@SpringBootApplication
public class AtlasApplication {

  private static final Logger LOG = LoggerFactory.getLogger(AtlasApplication.class);

  public static void main(String[] args) {
    ConfigurableApplicationContext context = null;
    try {
      context = SpringApplication.run(AtlasApplication.class, args);
      Environment env = context.getBean(Environment.class);
      var mongoClient = context.getBean(MongoClient.class);
      var dbs = new ArrayList<String>();
      mongoClient.listDatabaseNames().forEach(dbs::add);
      LOG.info("Startup successfull\n" +
          "-----------------------------------------------------------\n" +
          "  MongoDB:\t{}\n" +
          "-----------------------------------------------------------",
          dbs);
    } catch (Exception exc) {
      if (context != null) {
        context.close();
      }
      throw exc;
    }
  }

}
