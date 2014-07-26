package org.papiez.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ParserPermute {

    public static void main(final String[] args) throws IOException {
        int i = 0;
        double[][] finger1joint1 = generateValues(2, 1, 1, -0.8, 0.1, -0.08, 0.03, -0.09, -0.09);
        double[][] finger1joint2 = generateValues(2, 1, 1, -0.8, 0.44, 0.0, 0.0, 0.0, 0.0);
        double[][] finger1joint3 = generateValues(2, 1, 1, -0.95, 0.1, 0.0, 0.0, 0.0, 0.0);
        double[][] finger2joint1 = generateValues(2, 1, 1, -1.0, 0.25, 0.0, 0.07, 0.13, 0.13);
        double[][] finger2joint2 = generateValues(2, 1, 1, -1.4, 0.0, 0.0, 0.0, 0.0, 0.0);
        double[][] finger2joint3 = generateValues(2, 1, 1, -0.85, 0.3, 0.19, 0.19, 0.0, 0.0);
        double[][] finger3joint1 = generateValues(2, 1, 1, -1.4, 0.25, 0.08, 0.15, 0.0, 0.0);
        double[][] finger3joint2 = generateValues(2, 1, 1, -0.85, 0.55, -0.2, -0.2, 0.0, 0.0);
        double[][] finger3joint3 = generateValues(2, 1, 1, -0.9, 0.25, 0.0, 0.0, 0.0, 0.0);
        double[][] finger4joint1 = generateValues(2, 1, 1, -1.55, 0.15, 0.05, 0.2, 0.0, 0.0);
        double[][] finger4joint2 = generateValues(2, 1, 1, -0.6, 0.65, 0.0, 0.0, 0.0, 0.0);
        double[][] finger4joint3 = generateValues(2, 1, 1, -1.35, 0.0, 0.0, 0.0, 0.0, 0.0);

        for (double[] f1j1 : finger1joint1) {
            for (double[] f1j2 : finger1joint2) {
                for (double[] f1j3 : finger1joint3) {
                    for (double[] f2j1 : finger2joint1) {
                        for (double[] f2j2 : finger2joint2) {
                            for (double[] f2j3 : finger2joint3) {
                                for (double[] f3j1 : finger3joint1) {
                                    for (double[] f3j2 : finger3joint2) {
                                        for (double[] f3j3 : finger3joint3) {
                                            for (double[] f4j1 : finger4joint1) {
                                                for (double[] f4j2 : finger4joint2) {
                                                    for (double[] f4j3 : finger4joint3) {
                                                        Yaml yaml = new Yaml();
                                                        HashMap<String, Object> map = new LinkedHashMap<>();
                                                        map.put("rotation", new double[]{9.8628562688827515e-01, -8.6223828077436337e-08,
                                                                -1.6504754126071930e-01, -6.5812639892101288e-02,
                                                                -9.1706007719039917e-01, -3.9328047633171082e-01,
                                                                -1.5135848522186279e-01, 3.9874908328056335e-01,
                                                                -9.0448319911956787e-01});

                                                        HashMap<String, double[]> jointsMap = new LinkedHashMap<>();
                                                        jointsMap.put("finger1joint1", f1j1);
                                                        jointsMap.put("finger1joint2", f1j2);
                                                        jointsMap.put("finger1joint3", f1j3);
                                                        jointsMap.put("finger2joint1", f2j1);
                                                        jointsMap.put("finger2joint2", f2j2);
                                                        jointsMap.put("finger2joint3", f2j3);
                                                        jointsMap.put("finger3joint1", f3j1);
                                                        jointsMap.put("finger3joint2", f3j2);
                                                        jointsMap.put("finger3joint3", f3j3);
                                                        jointsMap.put("finger4joint1", f4j1);
                                                        jointsMap.put("finger4joint2", f4j2);
                                                        jointsMap.put("finger4joint3", f4j3);
                                                        jointsMap.put("finger5joint1", new double[]{6.8940508365631104e-01, 2.6179939508438110e-01, 0.});
                                                        jointsMap.put("finger5joint2", new double[]{3.9269909262657166e-01, -3.4906584769487381e-02, 1.7453292384743690e-02});
                                                        jointsMap.put("finger5joint3", new double[]{3.0543261766433716e-01, 0., 0.});
                                                        jointsMap.put("metacarpals", new double[]{0., 0., 0.});
                                                        jointsMap.put("carpals", new double[]{0., 0., 0.});
                                                        jointsMap.put("root", new double[]{0., 0., 0.});

                                                        map.put("hand_joints", jointsMap);
                                                        String output = yaml.dump(map);

                                                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(i + ".yml")))) {
                                                            writer.write("%YAML:1.0\n");
                                                            writer.write(output);
                                                        };
                                                        i++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static double[][] generateValues(final int quantityx, final int quantityy, final int quantityz,
                                             final double xmin, final double xmax,
                                             final double ymin, final double ymax,
                                             final double zmin, final double zmax) {

        double[] xvalues = new double[quantityx];
        if (quantityx == 1) {
            xvalues[0] = (xmin + xmax) / 2.0;
        } else {
            double xstep = (xmax - xmin) / (quantityx - 1);
            for (int i = 0; i < xvalues.length; i++) {
                xvalues[i] = xmin + i * xstep;
            }
        }

        double[] yvalues = new double[quantityy];
        if (quantityy == 1) {
            yvalues[0] = (ymin + ymax) / 2.0;
        } else {
            double ystep = (ymax - ymin) / (quantityx - 1);
            for (int i = 0; i < yvalues.length; i++) {
                yvalues[i] = ymin + i * ystep;
            }
        }

        double[] zvalues = new double[quantityz];
        if (quantityz == 1) {
            zvalues[0] = (zmin + zmax) / 2.0;
        } else {
            double zstep = (zmax - zmin) / (quantityx - 1);
            for (int i = 0; i < zvalues.length; i++) {
                zvalues[i] = zmin + i * zstep;
            }
        }

        double[][] matrix = new double[quantityx * quantityy * quantityz][3];
        int i = 0;
        for (double x : xvalues) {
            for (double y : yvalues) {
                for (double z : zvalues) {
                    double[] arr = new double[]{x, y, z};
                    matrix[i++] = arr;
                }
            }
        }

        return matrix;
    }
}
