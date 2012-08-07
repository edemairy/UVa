
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author edemairy
 */
public class Main {

    static public class IntervalNode {

        private SortedMap<Interval, List<Interval>> intervals;
        private long center;
        private IntervalNode leftNode;
        private IntervalNode rightNode;

        public IntervalNode() {
            intervals = new TreeMap<Interval, List<Interval>>();
            center = 0;
            leftNode = null;
            rightNode = null;
        }

        public IntervalNode(List<Interval> intervalList) {

            intervals = new TreeMap<Interval, List<Interval>>();

            SortedSet<Integer> endpoints = new TreeSet<Integer>();

            for (Interval interval : intervalList) {
                endpoints.add(interval.x);
                endpoints.add(interval.y);
            }

            long median = getMedian(endpoints);
            center = median;

            List<Interval> left = new ArrayList<Interval>();
            List<Interval> right = new ArrayList<Interval>();

            for (Interval interval : intervalList) {
                if (interval.y < median) {
                    left.add(interval);
                } else if (interval.x > median) {
                    right.add(interval);
                } else {
                    List<Interval> posting = intervals.get(interval);
                    if (posting == null) {
                        posting = new ArrayList<Interval>();
                        intervals.put(interval, posting);
                    }
                    posting.add(interval);
                }
            }

            if (left.size() > 0) {
                leftNode = new IntervalNode(left);
            }
            if (right.size() > 0) {
                rightNode = new IntervalNode(right);
            }
        }

        /**
         * Perform a stabbing query on the node
         *
         * @param time the time to query at
         * @return	all intervals containing time
         */
        public List<Interval> stab(int time) {
            List<Interval> result = new ArrayList<Interval>();

            for (Entry<Interval, List<Interval>> entry : intervals.entrySet()) {
                if (entry.getKey().contains(time)) {
                    for (Interval interval : entry.getValue()) {
                        result.add(interval);
                    }
                } else if (entry.getKey().x > time) {
                    break;
                }
            }

            if (time < center && leftNode != null) {
                result.addAll(leftNode.stab(time));
            } else if (time > center && rightNode != null) {
                result.addAll(rightNode.stab(time));
            }
            return result;
        }

        /**
         * Perform an interval intersection query on the node
         *
         * @param target the interval to intersect
         * @return	all intervals containing time
         */
        public List<Interval> query(Interval target) {
            List<Interval> result = new ArrayList<Interval>();

            for (Entry<Interval, List<Interval>> entry : intervals.entrySet()) {
                if (entry.getKey().intersects(target)) {
                    for (Interval interval : entry.getValue()) {
                        result.add(interval);
                    }
                } else if (entry.getKey().x > target.y) {
                    break;
                }
            }

            if (target.x < center && leftNode != null) {
                result.addAll(leftNode.query(target));
            }
            if (target.y > center && rightNode != null) {
                result.addAll(rightNode.query(target));
            }
            return result;
        }

        public long getCenter() {
            return center;
        }

        public void setCenter(long center) {
            this.center = center;
        }

        public IntervalNode getLeft() {
            return leftNode;
        }

        public void setLeft(IntervalNode left) {
            this.leftNode = left;
        }

        public IntervalNode getRight() {
            return rightNode;
        }

        public void setRight(IntervalNode right) {
            this.rightNode = right;
        }

        /**
         * @param set the set to look on
         * @return	the median of the set, not interpolated
         */
        private Integer getMedian(SortedSet<Integer> set) {
            int i = 0;
            int middle = set.size() / 2;
            for (Integer point : set) {
                if (i == middle) {
                    return point;
                }
                i++;
            }
            return null;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(center + ": ");
            for (Entry<Interval, List<Interval>> entry : intervals.entrySet()) {
                sb.append("[" + entry.getKey().x + "," + entry.getKey().y + "]:{");
                for (Interval interval : entry.getValue()) {
                    sb.append("(" + interval.x + "," + interval.y + "," + interval.z + ")");
                }
                sb.append("} ");
            }
            return sb.toString();
        }
    }

    /**
     * An Interval Tree is essentially a map from intervals to objects, which
     * can be queried for all data associated with a particular interval of time
     *
     * @author Kevin Dolan
     *
     * @param <Type> the type of objects to associate
     */
    static public class IntervalTree<Type> {

        private IntervalNode head;
        private List<Interval> intervalList;
        private boolean inSync;
        private int size;

        /**
         * Instantiate a new interval tree with no intervals
         */
        public IntervalTree() {
            this.head = new IntervalNode();
            this.intervalList = new ArrayList<Interval>();
            this.inSync = true;
            this.size = 0;
        }

        /**
         * Instantiate and build an interval tree with a preset list of
         * intervals
         *
         * @param intervalList the list of intervals to use
         */
        public IntervalTree(List<Interval> intervalList) {
            this.head = new IntervalNode(intervalList);
            this.intervalList = new ArrayList<Interval>();
            this.intervalList.addAll(intervalList);
            this.inSync = true;
            this.size = intervalList.size();
        }

        /**
         * Perform a stabbing query, returning the associated data Will rebuild
         * the tree if out of sync
         *
         * @param time the time to stab
         * @return	the data associated with all intervals that contain time
         */
        public List<Integer> get(int time) {
            List<Interval> intervals = getIntervals(time);
            List<Integer> result = new ArrayList<Integer>();
            for (Interval interval : intervals) {
                result.add(interval.z);
            }
            return result;
        }

        /**
         * Perform a stabbing query, returning the interval objects Will rebuild
         * the tree if out of sync
         *
         * @param time the time to stab
         * @return	all intervals that contain time
         */
        public List<Interval> getIntervals(int time) {
            build();
            return head.stab(time);
        }

        /**
         * Perform an interval query, returning the associated data Will rebuild
         * the tree if out of sync
         *
         * @param start the start of the interval to check
         * @param end	the end of the interval to check
         * @return	the data associated with all intervals that intersect target
         */
        public List<Integer> get(int start, int end) {
            List<Interval> intervals = getIntervals(start, end);
            List<Integer> result = new ArrayList<Integer>();
            for (Interval interval : intervals) {
                result.add(interval.z);
            }
            return result;
        }

        /**
         * Perform an interval query, returning the interval objects Will
         * rebuild the tree if out of sync
         *
         * @param start the start of the interval to check
         * @param end	the end of the interval to check
         * @return	all intervals that intersect target
         */
        public List<Interval> getIntervals(int start, int end) {
            build();
            return head.query(new Interval(start, end, 0));
        }

        /**
         * Add an interval object to the interval tree's list Will not rebuild
         * the tree until the next query or call to build
         *
         * @param interval the interval object to add
         */
        public void addInterval(Interval interval) {
            intervalList.add(interval);
            inSync = false;
        }

        /**
         * Add an interval object to the interval tree's list Will not rebuild
         * the tree until the next query or call to build
         *
         * @param begin the beginning of the interval
         * @param end	the end of the interval
         * @param data	the data to associate
         */
        public void addInterval(int begin, int end, int data) {
            intervalList.add(new Interval(begin, end, data));
            inSync = false;
        }

        /**
         * Determine whether this interval tree is currently a reflection of all
         * intervals in the interval list
         *
         * @return true if no changes have been made since the last build
         */
        public boolean inSync() {
            return inSync;
        }

        /**
         * Build the interval tree to reflect the list of intervals, Will not
         * run if this is currently in sync
         */
        public void build() {
            if (!inSync) {
                head = new IntervalNode(intervalList);
                inSync = true;
                size = intervalList.size();
            }
        }

        /**
         * @return the number of entries in the currently built interval tree
         */
        public int currentSize() {
            return size;
        }

        /**
         * @return the number of entries in the interval list, equal to .size()
         * if inSync()
         */
        public int listSize() {
            return intervalList.size();
        }

        @Override
        public String toString() {
            return nodeString(head, 0);
        }

        private String nodeString(IntervalNode node, int level) {
            if (node == null) {
                return "";
            }

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < level; i++) {
                sb.append("\t");
            }
            sb.append(node + "\n");
            sb.append(nodeString(node.getLeft(), level + 1));
            sb.append(nodeString(node.getRight(), level + 1));
            return sb.toString();
        }
    }

    public static class Interval implements Comparable<Interval> {

        public int x;
        public int y;
        public int z;

        public Interval(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static boolean isValid(int x, int y) {
            return (y >= x);
        }

        public boolean isValid() {
            return Interval.isValid(this.x, this.y);
        }

        public boolean intersect(Interval other) {
            if ((this.y < other.x) || (this.x > other.y)) {
                return false;
            } else {
                return true;
            }
        }

        public Set<Interval> computeUnion(Interval other) {
            HashSet<Interval> result = new HashSet<Interval>();
            if ((this.y < other.x) || (this.x > other.y)) {
                result.add(this);
                result.add(other);
            } else if (this.x <= other.x) {
                if (this.y <= other.y) {
                    addIfValid(result, this.x, other.x - 1, this.z);
                    addIfValid(result, other.x, this.y, this.z + other.z);
                    addIfValid(result, this.y + 1, other.y, other.z);
                    this.x = this.y + 1;
                } else {
                    addIfValid(result, this.x, other.x - 1, this.z);
                    addIfValid(result, other.x, other.y, this.z + other.z);
                    this.x = other.y + 1;
                }
            } else {
                if (this.y <= other.y) {
                    addIfValid(result, other.x, this.x - 1, other.z);
                    addIfValid(result, this.x, this.y, this.z + other.z);
                    addIfValid(result, this.y + 1, other.y, other.z);
                    this.x = this.y + 1;
                } else {
                    addIfValid(result, other.x, this.x - 1, other.z);
                    addIfValid(result, this.x, other.y, this.z + other.z);
                    this.x = other.y + 1;
                }
            }
            return result;
        }

        private void addIfValid(HashSet<Interval> result, int x, int y, int z) {
            if (isValid(x, y)) {
                result.add(new Interval(x, y, z));
            }
        }

        public int min() {
            return x + z;
        }

        public int max() {
            return y + z;
        }

        public int compareTo(Interval other) {
            if (x < other.x) {
                return -1;
            } else if (x > other.x) {
                return 1;
            } else if (y < other.y) {
                return -1;
            } else if (y > other.y) {
                return 1;
            } else {
                return 0;
            }
        }
//   

        private boolean intersects(Interval target) {
            return (this.x <= target.y && this.y >= target.x);
        }

        private boolean contains(int time) {
            return (time >= x && time <= y);
        }
    }
    private static long nbTC;
    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 10000);
        nbTC = readInt(reader);
//        scanner.nextLine();
        for (long tc = 1; tc <= nbTC; ++tc) {
//            result.append(oneTestCase(reader));
//            result.append('\n');
            System.out.println(oneTestCase(reader));
        }
        System.out.print(result);
    }
    public static int[] val = new int[1000001];
    public static TreeSet<Integer> s = new TreeSet<Integer>();
    public static StringBuilder output = new StringBuilder();

    private static void addIfValid(int x, int y, int thisz) {
//        if (y >= x) {
        s.add(x);
        val[x] = thisz;
//        }
    }

    private static int computeUnion(int cur, int thisx, int thisy, int thisz) {
        int otherx = cur;
        Integer othery = s.ceiling(cur + 1);
        if (othery == null) {
            othery = N;
        }
        int otherz = val[cur];
        if (thisx <= otherx) {
            addIfValid(thisx, otherx - 1, thisz);
        } else {
            addIfValid(otherx, thisx - 1, otherz);
        }
        if (thisy <= othery) {
            addIfValid(thisx, thisy, thisz + otherz);
            addIfValid(thisy + 1, othery, otherz);
            thisx = thisy + 1;
        } else {
            addIfValid(thisx, othery, thisz + otherz);
            thisx = othery + 1;
        }
        return thisx;
    }
    private static int N;
    private static IntervalTree<Interval> inters;
    private static Set<Integer> mins = new TreeSet<Integer>();
    private static Set<Integer> maxs = new TreeSet<Integer>();

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        output.setLength(0);
        inters = new IntervalTree<Interval>();
        mins.clear();
        maxs.clear();
//        for (long i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
        N = readInt(reader);
        int M = readInt(reader);

        s.clear();
        val[1] = 0;
        s.add(1);
        long itn = 0;


        for (long i = 0; i < M; ++i) {
//            String line = reader.readLine();
//            String[] parts = line.split(" ");
            int w = readInt(reader);
            int x = readInt(reader);
            int y = readInt(reader);
            int z = readInt(reader);
            if (w == 2) {
                z = -z;
            }
            inters.addInterval(new Interval(x, y, z));
            mins.add(x);
            maxs.add(y);
        }
        inters.build();
        int min = 1;

        for (int i : mins) {
            List<Integer> vals = inters.get(i);
            int s = 0;
            for (int v : vals) {
                s += v;
            }
            min = Math.min(min, s + i);
        }
        int max = N;
        for (int i : maxs) {
            List<Integer> vals = inters.get(i);
            int s = 0;
            for (int v : vals) {
                s += v;
            }
            max = Math.max(max, s + i);
        }
        output.append(max - min);
        return output;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        int result = 0;
        boolean positive = true;
        char currentChar = (char) reader.read();

        while ((currentChar == ' ') || (currentChar == '\n')) {
            currentChar = (char) reader.read();
        }
        if (currentChar == (char) -1) {
            throw new IOException("end of stream");
        }
        if (currentChar == '-') {
            positive = false;
            currentChar = (char) reader.read();
        }
        while ((currentChar >= '0') && (currentChar <= '9')) {
            result = result * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        if (positive) {
            return result;
        } else {
            return -result;
        }
    }

    private static char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }
}
