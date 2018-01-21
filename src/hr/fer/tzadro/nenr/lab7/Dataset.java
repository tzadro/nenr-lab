package hr.fer.tzadro.nenr.lab7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dataset {
    public List<Example> data;
    public int size;

    public Dataset(String path, int numInputs, int numOutputs) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            data = stream.map(line -> new Example(line, numInputs, numOutputs))
                         .collect(Collectors.toList());
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not load file.");
        }

        size = data.size();
    }
}
