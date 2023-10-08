package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.*;

/*
//Question:

You work in Human Resources of a large company and during a recent review, you realize that some managers might be underpaid.
To ensure fairness, you've come up with an impartial rule:
A manager is considered underpaid if and only if their salary is less than the average of the salaries of all their direct and indirect reports.
For example, consider the following organization tree:

A (Salary: $100)
|
+-- B (Salary: $100)
|
+-- C (Salary: $200)
    |
    +-- D (Salary: $60)
In this example, there are two managers, A and C.

A is considered underpaid since the average salary of their reports is ($100 + $200 + $60) / 3 = $120, which is more than $100.
C is not considered underpaid because their salary is $200, which is more than the average salary of their reports, which is $60.

Question:
Given a company organization tree, your task is to count the number of underpaid managers.Please determine the number of underpaid managers in this organization tree.

Here's another example organization tree:
B (Manager, Salary: $90)
|
+-- A (Manager, Salary: $300)
|   |
|   +-- C (Manager, Salary: $80)
|   |   |
|   |   +-- E (Employee, Salary: $60)
|   |
|   +-- D (Employee, Salary: $70)

*/


public class CountUnderPaidManager {
    static class Employee {
        long salary;
        List<Character> employeeList;
        char name;

        Employee(long salary, List<Character> employeeList, char name) {
            this.salary = salary;
            this.employeeList = employeeList;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = createEmployeeList();

        char managerName = 'B'; // Change the manager name for this example
        Solution sln = new Solution();
        if (sln.isUnderPaid(managerName, employees)) {
            System.out.println("Manager " + managerName + " is underpaid");
        } else {
            System.out.println("Manager " + managerName + " is not underpaid");
        }
        Solution2 sln2 = new Solution2();
        if (sln2.isUnderPaid(managerName, employees)) {
            System.out.println("Manager " + managerName + " is underpaid");
        } else {
            System.out.println("Manager " + managerName + " is not underpaid");
        }

        Solution3 sln3 = new Solution3();
        if (sln3.isUnderPaid(managerName, employees)) {
            System.out.println("Manager " + managerName + " is underpaid");
        } else {
            System.out.println("Manager " + managerName + " is not underpaid");
        }
    }

    private static List<Employee> createEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        Employee employeeA = new Employee(300, List.of('C', 'D'), 'A');
        Employee employeeB = new Employee(90, List.of('A'), 'B');
        Employee employeeC = new Employee(80, List.of('E'), 'C');
        Employee employeeD = new Employee(70, Collections.emptyList(), 'D');
        Employee employeeE = new Employee(60, Collections.emptyList(), 'E');
        employees.add(employeeA);
        employees.add(employeeB);
        employees.add(employeeC);
        employees.add(employeeD);
        employees.add(employeeE);
        return employees;
    }

    static class Solution {

        private boolean isUnderPaid(char name, List<Employee> employeeList) {
            Map<Character, Employee> graph = new HashMap<>();
            Map<Character, Long> salaryMap = new HashMap<>();
            for (Employee employee : employeeList) {
                graph.put(employee.name, employee);
                salaryMap.put(employee.name, employee.salary);
            }

            long totalSalary = getTotalSalary(graph, name);
            long salaryOfSubordinates = totalSalary - salaryMap.get(name);
            long totalSubordinates = totalCount - 1;
            double avgSalary = (totalSubordinates > 0) ? (double) salaryOfSubordinates / totalSubordinates : 0;

            System.out.println("The average salary of " + name + " is " + avgSalary);

            return avgSalary > salaryMap.get(name);
        }

        private static long totalCount = 0;

        private long getTotalSalary(Map<Character, Employee> graph, char name) {
            totalCount++;
            long salary = graph.get(name).salary;

            for (char emp : graph.get(name).employeeList) {
                salary += getTotalSalary(graph, emp);
            }
            return salary;
        }
    }

    static class Solution2 {
        class ReportSummary {
            int totalEmployees;
            long totalSalary;

            public ReportSummary(int totalCount, long salary) {
                this.totalEmployees = totalCount;
                this.totalSalary = salary;
            }
        }


        private boolean isUnderPaid(char name, List<Employee> employeeList) {
            Map<Character, Employee> graph = new HashMap<>();
            Map<Character, Long> salaryMap = new HashMap<>();
            for (Employee employee : employeeList) {
                graph.put(employee.name, employee);
                salaryMap.put(employee.name, employee.salary);
            }

            ReportSummary summary = getTotalSalary2(graph, name);
            long salaryOfSubordinates = summary.totalSalary - salaryMap.get(name);
            long totalSubordinates = summary.totalEmployees - 1;
            double avgSalary = (totalSubordinates > 0) ? (double) salaryOfSubordinates / totalSubordinates : 0;

            System.out.println("The average salary of " + name + " is " + avgSalary);

            return avgSalary > salaryMap.get(name);
        }


        private ReportSummary getTotalSalary2(Map<Character, Employee> graph, char name) {
            int totalCount = 1;
            long salary = graph.get(name).salary;

            for (char emp : graph.get(name).employeeList) {
                ReportSummary subResult = getTotalSalary2(graph, emp);
                salary += subResult.totalSalary;
                totalCount += subResult.totalEmployees;

            }
            return new ReportSummary(totalCount, salary);
        }
    }

    static class Solution3 {

        private boolean isUnderPaid(char managerName, List<Employee> employees) {
            Map<Character, Employee> employeeMap = new HashMap<>();
            for (Employee employee : employees) {
                employeeMap.put(employee.name, employee);
            }
            return isUnderPaidDFS(managerName, employeeMap);
        }

        private boolean isUnderPaidDFS(char managerName, Map<Character, Employee> employeeMap) {
            Employee manager = employeeMap.get(managerName);
            if (manager == null) {
                // Manager not found in the organization tree, they are not underpaid.
                return false;
            }

            long totalSalary = manager.salary;
            int countEmployees = 1;

            for (char employeeName : manager.employeeList) {
                if (employeeMap.containsKey(employeeName)) {
                    Employee employee = employeeMap.get(employeeName);
                    totalSalary += employee.salary;
                    countEmployees++;
                    if (isUnderPaidDFS(employeeName, employeeMap)) {
                        return true;
                    }
                }
            }

            long averageSalary = totalSalary / countEmployees;
            return manager.salary < averageSalary;
        }
    }
}


