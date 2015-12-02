/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitiveprogramming;

import java.util.ArrayList;

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class Geom2 {

	public static final double EPS = 1E-9;

	public static class Point implements Comparable<Point> {

		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (Math.abs(this.x - o.x) > EPS) {
				return (this.x - o.x > 0) ? 1 : -1;
			} else {
				if (Math.abs(this.y - o.y) > EPS) {
					return (this.y - o.y > 0) ? 1 : -1;
				} else {
					return 0;
				}
			}
		}

		public double dist(Point p2) {
			double dx = this.x - p2.x;
			double dy = this.y - p2.y;
			return Math.sqrt(dx * dx + dy * dy);
		}

		static public Point rotate(Point p, double theta) {
			return new Point(p.x * Math.cos(theta) - p.y * Math.sin(theta),
				p.x * Math.sin(theta) + p.y * Math.cos(theta));
		}
	}

	public static class Vec {

		public double x;
		public double y;

		public Vec(double x, double y) {
			this.x = x;
			this.y = y;
		}

		static public Vec toVec(Point a, Point b) {
			return new Vec(b.x - a.x, b.y - a.y);
		}

		public double dot(Vec o) {
			return this.x * o.x - this.y * o.y;
		}

		public double normSq() {
			return this.x * this.x + this.y * this.y;
		}
	}

	public static double angle(Point a, Point o, Point b) {
		Vec oa = Vec.toVec(o, a);
		Vec ob = Vec.toVec(o, b);
		return Math.acos(oa.dot(ob)) / Math.sqrt(oa.normSq() * ob.normSq());
	}

	public static double cross(Vec a, Vec b) {
		return a.x * b.y - a.y * b.x;
	}

	public static boolean ccw(Point p, Point q, Point r) {
		return cross(Vec.toVec(p, q), Vec.toVec(p, r)) > 0;
	}

	public static class Polygon {

		public ArrayList<Point> p;

		public Polygon push(Point a) {
			p.add(a);
			return this;
		}

		public double perimeter() {
			double result = 0;
			for (int i = 0; i < p.size() - 1; i++) {
				result += p.get(i).dist(p.get(i + 1));
			}
			return result;
		}

		public boolean inPolygon(Point point) {
			if (p.isEmpty()) {
				return false;
			}
			double sum = 0;
			for (int i = 0; i < p.size() - 1; i++) {
				if (ccw(point, p.get(i), p.get(i + 1))) {
					sum += angle(p.get(i), point, p.get(i + 1));
				} else {
					sum -= angle(p.get(i), point, p.get(i + 1));
				}
			}
			return Math.abs(Math.abs(sum) - 2 * Math.PI) < EPS;
		}
	}
}
