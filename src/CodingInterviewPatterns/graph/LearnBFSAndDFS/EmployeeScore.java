package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.*;

public class EmployeeScore {
    //https://interviewing.io/questions/employee-hierarchy
    //https://www.youtube.com/watch?v=jvN-Lmjl25M&t=294s&ab_channel=interviewing.io
    /*
The employee score for an employee equals "The total number of directs and indirect employees report to that employee,
then plus 1. The "plus one" here means adding the employee itself as self-reporting.

An employee without any other employees reporting to them. Will have employee score of 1.

Each employee has a unique eid, (employee id). Given a direct report map, where key is an eid, value is an array of
eids who direct report to key. This map should contain all employees.

Here is an example of direct report map.
 {123: [234, 345],
    234: [456, 789],
    345:[],
    456:[],
    789:[]}

no loops -> Manager can't report to their reports or employees can't report to themselves.
Employees can't have more than one manager
Company have only one CEO

Write a function, Input is given eid, calculate employee score for the eid, Direct report map is a global variable
that you can access anywhere in your code. Assume map is valid.

*/


    //V1 -> Write a function, Input is given eid,

    private int getScore1(int eid, Map<Integer, List<Integer>> directReports) {
        if (!directReports.containsKey(eid)) return 0;

        int score = 1;
        for (int neiId : directReports.get(eid)) {
            score += getScore1(neiId, directReports);
        }
        return score;
    }

    //v2 follow up 2, Assume getScore will call multiple times, improve the efficiency
    private static int getScore(int eid, Map<Integer, List<Integer>> directReports, Map<Integer, Integer> cache) {
        if (!directReports.containsKey(eid)) return 0;
        if (cache.containsKey(eid)) return cache.get(eid);

        int score = 1;
        for (int neiId : directReports.get(eid)) {
            score += getScore(neiId, directReports, cache);
        }
        cache.put(eid, score);
        return score;
    }


//v4 -> follow up 4, Validate the direct report.

    /**
     * no loops -> Manager can't report to their reports or employees can't report to themselves.
     * Employees can't have more than one manager
     * Company have only one CEO
     */

    public static boolean validateDirectReports(Map<Integer, List<Integer>> directReports) {
        // Employees can't have more than one manager
        Set<Integer> allEmployees = new HashSet<>(directReports.keySet());
        for (List<Integer> reports : directReports.values()) {
            allEmployees.addAll(reports);
        }
        if (allEmployees.size() != directReports.size()) {
            return false;
        }

        //There is only one CEO
        for (int eid : directReports.keySet()) {
            List<Integer> reports = directReports.get(eid);
            if (reports.contains(eid)) {
                return false;
            }
        }

        //There are no loops
        Set<Integer> visited = new HashSet<>();
        for (int eid : directReports.keySet()) {
            if (!visited.contains(eid)) {
                if (hasCycle(eid, directReports, visited, new HashSet<>())) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasCycle(int eid, Map<Integer, List<Integer>> directReports, Set<Integer> visited, Set<Integer> stack) {
        visited.add(eid);
        stack.add(eid);

        for (int report : directReports.getOrDefault(eid, Collections.emptyList())) {
            if (!visited.contains(report)) {
                if (hasCycle(report, directReports, visited, stack)) {
                    return true;
                }
            } else if (stack.contains(report)) {
                return true;
            }
        }

        stack.remove(eid);
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> directReports = new HashMap<>();
        directReports.put(123, Arrays.asList(234, 345));
        directReports.put(234, Arrays.asList(456, 789));
        directReports.put(345, new ArrayList<>());
        directReports.put(456, new ArrayList<>());
        directReports.put(789, new ArrayList<>());

        if (validateDirectReports(directReports)) {
            Map<Integer, Integer> memo = new HashMap<>();
            //v3 -> follow up 3, should return on O(1) time.
            // This is O(1) because there are scenarios where the score will be already be in cache
            for (int eid : directReports.keySet()) {
                int score = getScore(eid, directReports, memo);
                System.out.println("Employee " + eid + ": Score = " + score);
            }
        } else {
            System.out.println("Invalid direct reports map");
        }
        /*
          Time/Space Complexity Analysis
Time Complexity:
First time: O(n)

The time complexity of the first-time run is dependent on the depth of the reporting hierarchy for the given employee ID. In the worst case, if there are n employees reporting to the given employee ID, the time complexity can be considered O(n), as each direct report needs to be traversed. Additionally, if there are cycles in the reporting hierarchy, the time complexity can be higher.

Second time: O(1)

The subsequent runs for the same employee ID will have a time complexity of O(1) since the employee score has been memoized in the memo map. The score can be directly retrieved from the memoized values.

Space Complexity:
First time: O(n)

The space complexity of the first-time run is determined by the depth of the recursion stack. In the worst case, if the reporting hierarchy is a straight line without cycles, the space complexity can be considered O(n), as the recursion stack will have n frames.

Second time: O(n)

The space complexity remains the same as the first-time run since the memo map is used to store the calculated scores for all employee IDs. The space complexity is still O(n) in the worst case.

It's important to README.md that the space complexity mentioned here assume that the direct reports map is valid, without any circular reporting hierarchy or missing employee IDs.
         */
    }

}
