package com.bikebuka.bikebuka.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DateDiff {
    public static Map<TimeUnit, Long> computeDiff(Date date1, Date date2) {

        long diffInMillies = date2.getTime() - date1.getTime();

        //create the list
        List<TimeUnit> units = new ArrayList<>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);

        //create the result map of TimeUnit and difference
        Map<TimeUnit, Long> result = new LinkedHashMap<>();
        long milliesRest = diffInMillies;

        for (TimeUnit unit : units) {

            //calculate difference in millisecond
            long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            milliesRest = milliesRest - diffInMilliesForUnit;

            //put the result in the map
            result.put(unit, diff);
        }

        return result;
    }
}
