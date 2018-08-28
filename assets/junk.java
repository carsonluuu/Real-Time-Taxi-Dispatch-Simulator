public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}

public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}







