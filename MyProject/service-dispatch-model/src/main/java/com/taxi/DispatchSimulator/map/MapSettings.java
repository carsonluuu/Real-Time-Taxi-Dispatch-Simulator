package com.taxi.DispatchSimulator.map;

public class MapSettings {
    /**
     * @Discription Map related setting
     * @Parameters min/max of lon/lat
     * @Creator Jiahui
     * @Other
     */
    private static double LONGITUDE_MIN;
    private static double LONGITUDE_MAX;
    private static double LATITUDE_MIN;
    private static double LATITUDE_MAX;
    private static double PRECISION;

//    public MapSettings() {
//        LONGITUDE_MIN = 0;
//        LONGITUDE_MAX = 0;
//        LATITUDE_MIN = 0;
//        LATITUDE_MAX = 0;
//        PRECISION = 0;
//    }

    public void setup(double LONGITUDE_MIN, double LONGITUDE_MAX,
                        double LATITUDE_MIN, double LATITUDE_MAX,
                        double PRECISION) {
        MapSettings.LONGITUDE_MIN = LONGITUDE_MIN;
        MapSettings.LONGITUDE_MAX = LONGITUDE_MAX;
        MapSettings.LATITUDE_MIN = LATITUDE_MIN;
        MapSettings.LATITUDE_MAX = LATITUDE_MAX;
        MapSettings.PRECISION = PRECISION;
    }

    /** Return an array of the size with length of four
     * A example:
     * lonScope: (104.11 - 104.00) / 0.001 = 110
     * latScope: (31.0 - 30.1) / 0.001 = 900
     * Map area: 100.11 * 10.5 ~= 10000 km^2
     * Grid size: 10000/99000 ~= 0.1 km^2
     * **/
    public double[] getSize() {
        return new double[]{LONGITUDE_MIN, LONGITUDE_MAX,
                            LATITUDE_MIN, LATITUDE_MAX};
    }

    /** Return an array of the size with length of four**/
    public double getPrecision() { return PRECISION; }
}
