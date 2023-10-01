package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.*;

public class EmployeeImportanceDFS {

    //https://leetcode.com/problems/employee-importance/description/
    //https://www.youtube.com/watch?v=8uMRWVfsfmQ&ab_channel=WorkWithGoogler
    //https://leetcode.com/problems/employee-importance/editorial/
    //https://www.youtube.com/watch?v=3FZRnVJ98X0&ab_channel=NideeshTerapalli
    //https://walkccc.me/LeetCode/problems/0690/
    //https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/690-employee-importance.html
    //https://www.youtube.com/watch?v=uKMQCFb1Vc8&ab_channel=Sasacodes
    //Best https://www.youtube.com/watch?v=FhH-VYYNeDA&ab_channel=ParthMathuria

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

    static class Solution2 {
        public int getImportance(List<Employee> employees, int id) {
            Map<Integer, Employee> idToEmployee = new HashMap<>();

            for (Employee employee : employees)
                idToEmployee.put(employee.id, employee);

            return dfs(id, idToEmployee);
        }

        private int dfs(int id, Map<Integer, Employee> idToEmployee) {
            int values = 0;

            for (final int subId : idToEmployee.get(id).subordinates)
                values += dfs(subId, idToEmployee);

            return idToEmployee.get(id).importance + values;
        }
    }


    static class Solution {
        public int getImportance(List<Employee> employees, int id) {
            Map<Integer, Employee> graph = new HashMap<>();
            for (Employee emp : employees) {
                graph.put(emp.id, emp);
            }
            if (!graph.containsKey(id)) {
                return 0;
            }

            return dfs(id, graph);
        }

        private int dfs(int id, Map<Integer, Employee> graph) {
            Employee emp = graph.get(id);
            int imp = emp.importance;
            for (int neiId : emp.subordinates) {
                imp += dfs(neiId, graph);
            }
            return imp;
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

}


