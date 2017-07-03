package com.example.demo.batch.location;

import com.example.demo.model.Location;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Created on 03 July 2017 @ 9:12 PM
 * Component for project "batch-demo"
 *
 * @author Thomas Bezuidenhout
 */
@Component
public class LocationItemWriter {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public LocationItemWriter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoItemWriter<Location> writer() {
        MongoItemWriter<Location> writer = new MongoItemWriter<>();

        writer.setCollection("locations");
        writer.setTemplate(mongoTemplate);

        return writer;
    }
}
