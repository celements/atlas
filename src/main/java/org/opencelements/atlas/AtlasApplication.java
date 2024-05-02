package org.opencelements.atlas;

import java.util.ArrayList;
import java.util.List;

import org.opencelements.atlas.application.model.AtlasDocument;
import org.opencelements.atlas.application.model.AtlasObject;
import org.opencelements.atlas.application.model.exceptions.DocumentCreationException;
import org.opencelements.atlas.application.ports.driven.DocumentStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.mongodb.client.MongoClient;

@SpringBootApplication
public class AtlasApplication {

  private static final Logger LOG = LoggerFactory.getLogger(AtlasApplication.class);

  public static void main(String[] args) throws Exception {
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
      testInsertion(context.getBeanFactory());
    } catch (Exception exc) {
      if (context != null) {
        context.close();
      }
      throw exc;
    }
  }

  private static void testInsertion(BeanFactory bf) throws DocumentCreationException {
    var store = bf.getBean(DocumentStore.class);
    if (store.count() == 0) {
      var dataObj = AtlasObject.builder()
        .data(new org.bson.Document("hello", "world"))
        .build();
      store.create(AtlasDocument.builder()
        .objects(List.of(dataObj))
        .build());
    }
    LOG.info("Docs in DB: {}", store.findAll());
  }

}
