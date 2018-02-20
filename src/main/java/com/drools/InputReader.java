package com.drools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    public static List<Data> readSourceData(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .skip(1)
                .map(line -> new Data.Builder(line).build())
                .collect(Collectors.toList());
    }

}
