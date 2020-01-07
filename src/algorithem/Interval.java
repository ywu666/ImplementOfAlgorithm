package algorithem;

import java.util.*;

class Interval {
	int start;
	int end;

	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public static ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervalsList) {
		if (intervalsList == null || intervalsList.size()< 2) {
			return intervalsList;
		} else {
			ArrayList<Interval> out = new ArrayList<>();
			Collections.sort(intervalsList, new Comparator<Interval>() {
				@Override
				public int compare(Interval o1, Interval o2) {
					return Integer.compare(o1.start, o2.start);
				}
			});

			Interval prev = intervalsList.get(0);
			for (int i = 1; i < intervalsList.size(); i++) {
				Interval cur = intervalsList.get(i);
				if (cur.start <= prev.end) {  //merge if not overlapping
					prev = new Interval(prev.start, Math.max(prev.end, cur.end));
				} else { // otherwise add and update curInterval
					out.add(prev);
					prev = cur;
				}
			}
			out.add(prev);
			return out;
		} 
	}
	
	public static ArrayList<Interval> insertRange(ArrayList<Interval> intervalsList, Interval insert) {
	    ArrayList<Interval> out = new ArrayList<>();
	    for (Interval i: intervalsList) {
	        if (i.end < insert.start) {
	            out.add(i);
	        }else if (i.start > insert.end) {
	            out.add(insert);
	            insert = i;
	        }else if (i.start <= insert.end || i.end > insert.start) {
	            int newStart = Math.min(i.start, insert.start);
	            int newEnd = Math.max(i.end,insert.end);
	            insert = new Interval(newStart, newEnd);
	        }
	    }    
	        out.add(insert);
	        return out;
	}
}
