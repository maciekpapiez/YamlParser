package org.papiez.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.*;

import static java.lang.Math.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class Parser {
    private static final int QUANTITY = 50;

    public static void main(final String[] args) throws IOException {

        for (int i = 0; i <= QUANTITY; i++) {
            double[] finger1joint1 = generateValue(3.7524580955505371e-01, 5.2359879016876221e-02, 0.);
            double[] finger1joint2 = generateValue(3.6651915311813354e-01, -9.5993109047412872e-02, 0.);
            double[] finger1joint3 = generateValue(2.0943951606750488e-01, -1.2217304855585098e-01, 0.);
            double[] finger2joint1 = generateValue(3.2288593053817749e-01, 8.7266467511653900e-02, 0.);
            double[] finger2joint2 = generateValue(4.7996556758880615e-01, -1.3089969754219055e-01, 0.);
            double[] finger2joint3 = generateValue(3.2288593053817749e-01, -2.1816615760326385e-01, 0.);
            double[] finger3joint1 = generateValue(2.9670599102973938e-01, -2.6179939508438110e-02, 2.0943951606750488e-01);
            double[] finger3joint2 = generateValue(5.0614547729492188e-01, -2.0943951606750488e-01, 0.);
            double[] finger3joint3 = generateValue(1.0471975803375244e-01, 0., 0.);
            double[] finger4joint1 = generateValue(2.7052605152130127e-01, 7.8539818525314331e-02, 0.);
            double[] finger4joint2 = generateValue(1.5707963705062866e-01, 0., 0.);
            double[] finger4joint3 = generateValue(3.0543261766433716e-01, 0., 0.);
            double[] finger5joint1 = generateValue(0., 3.4906587004661560e-01, 0.);
            double[] finger5joint2 = generateValue(0., 0., 0.);
            double[] finger5joint3 = generateValue(0., 0., 0.);

            double p = generateValue(-PI*70.0/180, PI*10.0/180);        // pitch -> theta
            double y = generateValue(PI*130.0/180, PI*190.0/180);       // yaw   -> phi
            double r = generateValue(PI*75.0/180, PI*110.0/180);        // roll  -> tilt

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
            jointsMap.put("finger5joint1", finger5joint1);
            jointsMap.put("finger5joint2", finger5joint2);
            jointsMap.put("finger5joint3", finger5joint3);
            jointsMap.put("metacarpals", new double[]{0., 0., 0.});
            jointsMap.put("carpals", new double[]{0., 0., 0.});
            jointsMap.put("root", new double[]{0., 0., 0.});

            map.put("hand_joints", jointsMap);
            String output = yaml.dump(map);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(i + ".yml")))) {
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

    private static double[] generateValue(final double x, final double y, final double z) {
        return generateValue(0.8 * x, 1.2 * x, 0.95 * y, 1.05 * y, z, z);
    }

    private static double generateValue(final double x) {
        return generateValue(0.9 * x, 1.1 * x);
    }
}
