package hr.fer.tzadro.nenr.lab7;

import java.util.stream.IntStream;

public class Example {
    public double inputs[];
    public int outputs[];

    public Example(String line, int numInputs, int numOutputs) {
        inputs = new double[numInputs];
        outputs = new int[numOutputs];

        String elements[] = line.split("\\t");
        IntStream.range(0, numInputs)
                 .forEach(i -> inputs[i] = Double.parseDouble(elements[i]));
        IntStream.range(numInputs, numInputs + numOutputs)
                 .forEach(i -> outputs[i - numInputs] = Integer.parseInt(elements[i]));
    }
}
