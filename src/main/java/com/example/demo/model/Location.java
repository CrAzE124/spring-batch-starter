package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created on 03 July 2017 @ 9:06 PM
 * Component for project "batch-demo"
 *
 * @author Thomas Bezuidenhout
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Document
public class Location {
    private String countryCode;
    private String locationCode;
    private String location;
    private String locationType;
}
