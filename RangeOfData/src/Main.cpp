
#include <cstdio>
#include <set>
/**
 *
 * @author edemairy
 */
class Main {

    static class Interval implements Comparable<Interval> {

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

        @Override
        public int compareTo(Interval t) {
            int r = -((this.y - this.x) - (t.y - t.x));
            if (r == 0) {
                return this.x - t.x;
            } else {
                return r;
            }
        }
//        @Override
//        public String toString() {
//            return x+" "+y+" "+z;
//        }
    }
    private static long nbTC;
    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = readInt(reader);
//        scanner.nextLine();
        for (long tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(reader));
            result.append('\n');
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

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        output.setLength(0);
//        for (long i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
        N = readInt(reader);
        int M = readInt(reader);

        s.clear();
        val[1] = 0;
        s.add(1);
        long itn = 0;
        TreeSet<Interval> inters = new TreeSet<Interval>();
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
            inters.add(new Interval(x, y, z));
        }
        for (Interval inter : inters) {
            int x = inter.x;
            int y = inter.y;
            int z = inter.z;
            while (x <= y) {
                int cur = s.floor(x);
                x = computeUnion(cur, x, y, z);
            }

        }
        int min = 1;
        int max = N;
        for (int cur : s) {
            Integer end = s.ceiling(cur + 1);
            if (end == null) {
                end = N;
            } else {
                end--;
            }
            min = Math.min(min, cur + val[cur]);
            min = Math.min(min, end + val[cur]);
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
