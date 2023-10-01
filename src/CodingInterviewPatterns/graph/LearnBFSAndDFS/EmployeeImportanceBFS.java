package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.*;

public class EmployeeImportanceBFS {
    //https://leetcode.com/problems/employee-importance/description/

    //https://leetcode.com/problems/employee-importance/description/
    //https://www.youtube.com/watch?v=8uMRWVfsfmQ&ab_channel=WorkWithGoogler
    //https://leetcode.com/problems/employee-importance/editorial/
    //https://www.youtube.com/watch?v=3FZRnVJ98X0&ab_channel=NideeshTerapalli
    //https://walkccc.me/LeetCode/problems/0690/
    //https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/690-employee-importance.html
    //https://www.youtube.com/watch?v=c_KBNpmkn6w&ab_channel=JavaCodingInsightInterviewPreparation

    /**
     * Time complexity O(n)
     */

    /**
     * You have a data structure of employee information, including the employee's unique ID, importance value, and direct subordinates' IDs.
     * <p>
     * You are given an array of employees where:
     * <p>
     * employees[i].id is the ID of the ith employee.
     * employees[i].importance is the importance value of the ith employee.
     * employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.
     * Given an integer id that represents an employee's ID, return the total importance value of this employee and all their direct and indirect subordinates.
     */

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        // Example employees
        Employee emp1 = new Employee(1, 5, Arrays.asList(2, 3));
        Employee emp2 = new Employee(2, 3, Collections.emptyList());
        Employee emp3 = new Employee(3, 3, Collections.emptyList());

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);

        Solution solution = new Solution();
        int result = solution.getImportance(employees, 1);
        System.out.println("Total importance: " + result);
    }


    static class Solution {
        public int getImportance(List<Employee> employees, int id) {
            HashMap<Integer, Employee> map = new HashMap<>();
            for (Employee emp : employees) {
                map.put(emp.id, emp);
            }
            if (!map.containsKey(id)) {
                return 0;
            }
            Queue<Employee> q = new LinkedList<>();
            q.add(map.get(id));
            int res = 0;
            while (!q.isEmpty()) {
                Employee emp = q.poll();
                res += emp.importance;
                for (Integer empId : emp.subordinates) {
                    q.add(map.get(empId));
                }
            }
            return res;
        }
    }


    static class Solution2 {
        public int getImportance(List<Employee> employees, int id) {
            HashMap<Integer, Employee> map = new HashMap<>();
            for (Employee emp : employees) {
                map.put(emp.id, emp);
            }
            if (!map.containsKey(id)) {
                return 0;
            }
            Queue<Employee> q = new LinkedList<>();
            q.add(map.get(id));
            int res = 0;
            while (!q.isEmpty()) {
                Employee emp = q.poll();
                res += emp.importance;
                for (int empId : emp.subordinates) {
                    q.add(map.get(empId));
                }
            }
            return res;
        }
    }


    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }

    class Solution3 {
        public int getImportance(List<Employee> employees, int id) {
            // Form adjacency list:
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Map<Integer, Integer> imp = new HashMap<>();

            for (Employee emp : employees) {
                graph.putIfAbsent(emp.id, new ArrayList<>());
                graph.get(emp.id).addAll(emp.subordinates);
                imp.put(emp.id, emp.importance);
            }

            if (!imp.containsKey(id)) return 0;

            Queue<Integer> q = new LinkedList<>();
            q.add(id);

            int res = 0;
            while (!q.isEmpty()) {
                int empId = q.poll();
                res += imp.get(empId);

                List<Integer> nei = graph.get(empId);
                if (nei != null) {
                    /*for (int neiID : nei) {
                        q.add(neiID);
                    }*/
                    q.addAll(nei);
                }
            }

            return res;
        }
    }
}
