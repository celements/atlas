package org.opencelements.atlas;

import java.util.ArrayList;
import java.util.List;

import org.opencelements.atlas.domain.DataObject;
import org.opencelements.atlas.domain.Document;
import org.opencelements.atlas.driven.mongo.DataObjectRepository;
import org.opencelements.atlas.driven.mongo.DocumentRepository;
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
      testInsertion(context.getBeanFactory());
    } catch (Exception exc) {
      if (context != null) {
        context.close();
      }
      throw exc;
    }
  }

  private static void testInsertion(BeanFactory bf) {
    var docRepo = bf.getBean(DocumentRepository.class);
    var objRepo = bf.getBean(DataObjectRepository.class);
    if (docRepo.count() == 0) {
      var dataObj = objRepo.save(DataObject.builder()
        .data(new org.bson.Document("hello", "world"))
        .build());
      docRepo.save(Document.builder()
          .objects(List.of(dataObj))
          .build());   
    }
    LOG.info("Docs in DB: {}", docRepo.findAll());
  }

}
