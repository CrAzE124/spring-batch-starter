package com.example.demo.batch.location;

import com.example.demo.model.Location;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * Created on 03 July 2017 @ 9:06 PM
 * Component for project "batch-demo"
 *
 * @author Thomas Bezuidenhout
 */
@Component
public class FileReader {
    public FlatFileItemReader<Location> locationFlatFileItemReader() {
        FlatFileItemReader<Location> reader = new FlatFileItemReader<>();

        reader.setResource(new ClassPathResource("locations.csv"));
        reader.setLineMapper(new DefaultLineMapper<Location>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] {"countryCode", "locationCode", "location", "locationType"});
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<Location>() {{
                setTargetType(Location.class);
            }});
        }});

        return reader;
    }
}
