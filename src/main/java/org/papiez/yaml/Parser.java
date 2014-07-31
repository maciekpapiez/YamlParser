package org.papiez.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.*;

import static java.lang.Math.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class Parser {
    private static final int QUANTITY = 1;

    public static void main(final String[] args) throws IOException {

        double[] finger1joint1 = generateValue(-0.8115, 3.7524e-01, 5.2359e-02, 5.2359e-02, 0.0, 0.0);
        double[] finger1joint2 = generateValue(-1.1780, 3.6651e-01, -9.5993e-02, -9.5993e-02, 0.0, 0.0);
        double[] finger1joint3 = generateValue(-1.4660, 2.0943e-01, -1.1344e-01, -1.2217e-01, 0.0, 0.0);
        double[] finger2joint1 = generateValue(-1.0003, 3.2288e-01, 9.5993e-02, 8.7266e-02, 0.0, 0.0);
        double[] finger2joint2 = generateValue(-0.9250, 4.7996e-01, -1.3089e-01, -1.3089e-01, 0.0, 0.0);
        double[] finger2joint3 = generateValue(-1.8064, 3.2288e-01, 2.6179e-02, -2.1816e-01, 0.0, 0.0);
        double[] finger3joint1 = generateValue(-1.4660, 2.9670e-01, 1.5707e-01, -2.6179e-02, 2.0943e-01, 2.0943e-01);
        double[] finger3joint2 = generateValue(-1.0733, 5.0614e-01, -2.0943e-01, -2.0943e-01, 0.0, 0.0);
        double[] finger3joint3 = generateValue(-1.5358, 1.0471e-01, 0.0, 0.0, 0.0, 0.0);
        double[] finger4joint1 = generateValue(-1.1606, 2.7052e-01, 1.1344e-01, 7.8539e-02, 0.0, 0.0);
        double[] finger4joint2 = generateValue(-1.1344, 1.5707e-01, 0.0, 0.0, 0.0, 0.0);
        double[] finger4joint3 = generateValue(-1.2828, 3.0543e-01, 0.0, 0.0, 0.0, 0.0);

        double y = generateValue(PI*125.0/180, PI*225.0/180);       // yaw   -> phi
        double p = generateValue(PI*(-35.0)/180, PI*65.0/180);      // pitch -> theta
        double r = generateValue(PI*30.0/180, PI*140.0/180);        // roll  -> tilt

        for (int i = 0; i <= QUANTITY; i++) {
            Yaml yaml = new Yaml();
            HashMap<String, Object> map = new LinkedHashMap<>();
            map.put("rotation", new double[]{cos(p) * cos(r), -cos(p) * sin(r), sin(p),
                    cos(y) * sin(r) + cos(r) * sin(y) * sin(p), cos(y) * cos(r) - sin(y) * sin(p) * sin(r), -cos(p) * sin(y),
                    sin(y) * sin(r) - cos(y) * cos(r) * sin(p), cos(r) * sin(y) + cos(y) * sin(p) * sin(r), cos(y) * cos(p)});

            HashMap<String, double[]> jointsMap = new LinkedHashMap<>();

            jointsMap.put("finger1joint1", finger1joint1);
            jointsMap.put("finger1joint2", finger1joint2);
            jointsMap.put("finger1joint3", finger1joint3);
            jointsMap.put("finger2joint1", finger2joint1);
            jointsMap.put("finger2joint2", finger2joint2);
            jointsMap.put("finger2joint3", finger2joint3);
            jointsMap.put("finger3joint1", finger3joint1);
            jointsMap.put("finger3joint2", finger3joint2);
            jointsMap.put("finger3joint3", finger3joint3);
            jointsMap.put("finger4joint1", finger4joint1);
            jointsMap.put("finger4joint2", finger4joint2);
            jointsMap.put("finger4joint3", finger4joint3);
            jointsMap.put("finger5joint1", new double[]{6.8940508365631104e-01, 2.6179939508438110e-01, 0.});
            jointsMap.put("finger5joint2", new double[]{3.9269909262657166e-01, -3.4906584769487381e-02, 1.7453292384743690e-02});
            jointsMap.put("finger5joint3", new double[]{3.0543261766433716e-01, 0., 0.});
            jointsMap.put("metacarpals", new double[]{0., 0., 0.});
            jointsMap.put("carpals", new double[]{0., 0., 0.});
            jointsMap.put("root", new double[]{0., 0., 0.});

            map.put("hand_joints", jointsMap);
            String output = yaml.dump(map);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(++i + ".yml")))) {
                writer.write("%YAML:1.0\n");
                writer.write(output);
            }
        }
    }

    private static double[] generateValue(final double xmin, final double xmax,
                                          final double ymin, final double ymax,
                                          final double zmin, final double zmax) {
        return new double[]{generateValue(xmin, xmax),
                            generateValue(ymin, ymax),
                            generateValue(zmin, zmax)};
    }

    private static double generateValue(final double min, final double max) {
        final Random random = new Random();

        return random.nextDouble() * (max - min) + min;
    }
}
