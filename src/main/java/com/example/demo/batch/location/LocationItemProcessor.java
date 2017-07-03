package com.example.demo.batch.location;

import com.example.demo.model.Location;
import lombok.extern.java.Log;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created on 03 July 2017 @ 9:11 PM
 * Component for project "batch-demo"
 *
 * @author Thomas Bezuidenhout
 */
@Component
@Log
public class LocationItemProcessor implements ItemProcessor<Location, Location> {
    @Override
    public Location process(Location item) throws Exception {
        log.info("Processing item...");

        return item;
    }
}
