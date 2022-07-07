package com.taxi.DispatchSimulator.geo;

import java.util.ArrayList;
import java.util.Collection;

public class GeoHashUtils {
    private static final char[] BASE_32 = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static final int PRECISION = 12;
    private static final int[] BITS = {16, 8, 4, 2, 1};

    private GeoHashUtils() {
    }

    public static String encode(double latitude, double longitude) {
        return encode(latitude, longitude, PRECISION);
    }

    /**
     * Encodes the given latitude and longitude into a geohash
     *
     * @param latitude  Latitude to encode
     * @param longitude Longitude to encode
     * @return Geohash encoding of the longitude and latitude
     */
    public static String encode(double latitude, double longitude, int precision) {
        double latInterval0 = -90.0;
        double latInterval1 = 90.0;
        double lngInterval0 = -180.0;
        double lngInterval1 = 180.0;

        final StringBuilder geohash = new StringBuilder();
        boolean isEven = true;

        int bit = 0;
        int ch = 0;

        while (geohash.length() < precision) {
            double mid = 0.0;
            if (isEven) {
                mid = (lngInterval0 + lngInterval1) / 2D;
                if (longitude > mid) {
                    ch |= BITS[bit];
                    lngInterval0 = mid;
                } else {
                    lngInterval1 = mid;
                }
            } else {
                mid = (latInterval0 + latInterval1) / 2D;
                if (latitude > mid) {
                    ch |= BITS[bit];
                    latInterval0 = mid;
                } else {
                    latInterval1 = mid;
                }
            }

            isEven = !isEven;

            if (bit < 4) {
                bit++;
            } else {
                geohash.append(BASE_32[ch]);
                bit = 0;
                ch = 0;
            }
        }

        return geohash.toString();
    }

    private static final char encode(int x, int y) {
        return BASE_32[((x & 1) + ((y & 1) * 2) + ((x & 2) * 2) + ((y & 2) * 4) + ((x & 4) * 4)) % 32];
    }

    /**
     * Calculate all neighbors of a given geohash cell.
     *
     * @param geohash Geohash of the defined cell
     * @return geohashes of all neighbor cells
     */
    public static Collection<? extends CharSequence> neighbors(String geohash) {
        return addNeighbors(geohash, geohash.length(), new ArrayList<CharSequence>(8));
    }

    /**
     * Calculate the geohash of a neighbor of a geohash
     *
     * @param geohash the geohash of a cell
     * @param level   level of the geohash
     * @param dx      delta of the first grid coordinate (must be -1, 0 or +1)
     * @param dy      delta of the second grid coordinate (must be -1, 0 or +1)
     * @return geohash of the defined cell
     */
    private final static String neighbor(String geohash, int level, int dx, int dy) {
        int cell = decode(geohash.charAt(level - 1));

        // Decoding the Geohash bit pattern to determine grid coordinates
        int x0 = cell & 1;  // first bit of x
        int y0 = cell & 2;  // first bit of y
        int x1 = cell & 4;  // second bit of x
        int y1 = cell & 8;  // second bit of y
        int x2 = cell & 16; // third bit of x

        // combine the bitpattern to grid coordinates.
        // note that the semantics of x and y are swapping
        // on each level
        int x = x0 + (x1 / 2) + (x2 / 4);
        int y = (y0 / 2) + (y1 / 4);

        if (level == 1) {
            // Root cells at north (namely "bcfguvyz") or at
            // south (namely "0145hjnp") do not have neighbors
            // in north/south direction
            if ((dy < 0 && y == 0) || (dy > 0 && y == 3)) {
                return null;
            } else {
                return Character.toString(encode(x + dx, y + dy));
            }
        } else {
            // define grid coordinates for next level
            final int nx = ((level % 2) == 1) ? (x + dx) : (x + dy);
            final int ny = ((level % 2) == 1) ? (y + dy) : (y + dx);

            // if the defined neighbor has the same parent a the current cell
            // encode the cell directly. Otherwise find the cell next to this
            // cell recursively. Since encoding wraps around within a cell
            // it can be encoded here.
            // xLimit and YLimit must always be respectively 7 and 3
            // since x and y semantics are swapping on each level.
            if (nx >= 0 && nx <= 7 && ny >= 0 && ny <= 3) {
                return geohash.substring(0, level - 1) + encode(nx, ny);
            } else {
                String neighbor = neighbor(geohash, level - 1, dx, dy);
                if (neighbor != null) {
                    return neighbor + encode(nx, ny);
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * Add all geohashes of the cells next to a given geohash to a list.
     *
     * @param geohash   Geohash of a specified cell
     * @param neighbors list to add the neighbors to
     * @return the given list
     */
    public static final <E extends Collection<? super String>> E addNeighbors(String geohash, E neighbors) {
        return addNeighbors(geohash, geohash.length(), neighbors);
    }

    /**
     * Add all geohashes of the cells next to a given geohash to a list.
     *
     * @param geohash   Geohash of a specified cell
     * @param length    level of the given geohash
     * @param neighbors list to add the neighbors to
     * @return the given list
     */
    public static final <E extends Collection<? super String>> E addNeighbors(String geohash, int length, E neighbors) {
        String south = neighbor(geohash, length, 0, -1);
        String north = neighbor(geohash, length, 0, +1);
        if (north != null) {
            neighbors.add(neighbor(north, length, -1, 0));
            neighbors.add(north);
            neighbors.add(neighbor(north, length, +1, 0));
        }

        neighbors.add(neighbor(geohash, length, -1, 0));
        neighbors.add(neighbor(geohash, length, +1, 0));

        if (south != null) {
            neighbors.add(neighbor(south, length, -1, 0));
            neighbors.add(south);
            neighbors.add(neighbor(south, length, +1, 0));
        }

        return neighbors;
    }

    private static final int decode(char geo) {
        switch (geo) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'b':
                return 10;
            case 'c':
                return 11;
            case 'd':
                return 12;
            case 'e':
                return 13;
            case 'f':
                return 14;
            case 'g':
                return 15;
            case 'h':
                return 16;
            case 'j':
                return 17;
            case 'k':
                return 18;
            case 'm':
                return 19;
            case 'n':
                return 20;
            case 'p':
                return 21;
            case 'q':
                return 22;
            case 'r':
                return 23;
            case 's':
                return 24;
            case 't':
                return 25;
            case 'u':
                return 26;
            case 'v':
                return 27;
            case 'w':
                return 28;
            case 'x':
                return 29;
            case 'y':
                return 30;
            case 'z':
                return 31;
            default:
                throw new IllegalArgumentException("the character '" + geo + "' is not a valid geohash character");
        }
    }

    /**
     * Decodes the given geohash
     *
     * @param geohash Geohash to decocde
     * @return {@link GeoPoint} at the center of cell, given by the geohash
     */
    public static GeoPoint decode(String geohash) {
        return decode(geohash, new GeoPoint());
    }

    /**
     * Decodes the given geohash into a latitude and longitude
     *
     * @param geohash Geohash to decocde
     * @return the given {@link GeoPoint} reseted to the center of
     *         cell, given by the geohash
     */
    public static GeoPoint decode(String geohash, GeoPoint ret) {
        double[] interval = decodeCell(geohash);
        return ret.reset((interval[0] + interval[1]) / 2D, (interval[2] + interval[3]) / 2D);
    }

    private static double[] decodeCell(String geohash) {
        double[] interval = {-90.0, 90.0, -180.0, 180.0};
        boolean isEven = true;

        for (int i = 0; i < geohash.length(); i++) {
            final int cd = decode(geohash.charAt(i));

            for (int mask : BITS) {
                if (isEven) {
                    if ((cd & mask) != 0) {
                        interval[2] = (interval[2] + interval[3]) / 2D;
                    } else {
                        interval[3] = (interval[2] + interval[3]) / 2D;
                    }
                } else {
                    if ((cd & mask) != 0) {
                        interval[0] = (interval[0] + interval[1]) / 2D;
                    } else {
                        interval[1] = (interval[0] + interval[1]) / 2D;
                    }
                }
                isEven = !isEven;
            }
        }
        return interval;
    }

}
