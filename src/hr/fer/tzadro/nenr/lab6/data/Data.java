package hr.fer.tzadro.nenr.lab6.data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Data {
    private static int domainMin = -4;
    private static int domainMax = 4;

    public static List<Example> getTrainingData() {
        return IntStream.range(domainMin, domainMax + 1)
                        .mapToObj(i -> IntStream.range(domainMin, domainMax + 1)
                                                .mapToObj(j -> new Example(i, j, CharacteristicFunction.valueAt(i, j))))
                        .flatMap(e -> e)
                        .collect(Collectors.toList());
    }
}
