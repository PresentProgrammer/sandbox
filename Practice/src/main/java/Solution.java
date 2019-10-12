import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static java.util.Comparator.comparingInt;

public class Solution {

    List<List<Integer>> optimalUtilization(int maxTravelDist,
            List<List<Integer>> forwardRouteList,
            List<List<Integer>> returnRouteList) {
        if (forwardRouteList.isEmpty() || returnRouteList.isEmpty()) {
            return new ArrayList<>();
        }

        sort(forwardRouteList, comparingInt(pair -> pair.get(1)));
        sort(returnRouteList, comparingInt((List<Integer> pair) -> pair.get(1)).reversed());
        int optimalDist = -1;
        List<List<Integer>> result = new ArrayList<>();
        int forwardInd = 0;
        int returnInd = 0;
        while (forwardInd < forwardRouteList.size()
                && returnInd < returnRouteList.size()) {
            final int currDist = forwardRouteList.get(forwardInd).get(1)
                    + returnRouteList.get(returnInd).get(1);
            if (currDist > maxTravelDist) {
                returnInd++;
            } else if (currDist < optimalDist) {
                forwardInd++;
            } else {
                if (currDist > optimalDist) {
                    result = new ArrayList<>();
                }
                result.add(asList(
                        forwardRouteList.get(forwardInd).get(0),
                        returnRouteList.get(returnInd).get(0)));
            }
        }
        return result;
    }
}