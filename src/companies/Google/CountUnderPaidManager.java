package companies.Google;

import java.util.List;

/**
 * Question:
 * 3 You work in Human Resources of a large company, and during a recent review, 4 you come to realize that some managers might seem underpaid.
 * 5
 * 6 To ensure fairness, you come up with an impartial rule:
 * 7
 * 8 A manager is underpaid if and only if their salary is less than the average
 * 9 of the salaries of all their direct and indirect reports.
 * 10 For example, consider the following:
 * 11
 * 12 A($100)
 * 13
 * 14
 * 15
 * +- B($100)
 * + - C($200)
 * 16
 * 17
 * + - D ($60)
 * 18
 * 19 Here there are two managers, A and C.
 * 20 A is underpaid since the average salary of their reports is
 * 21 ($100+$200+$60) / 3 = $120 which is more than $100.
 * 22
 * 23 C is not underpaid because their salary is $200 which is more than the average salary
 * 24 of their reports, that is $60.
 * 25
 * 26
 * Question:
 * 27
 * Given an company organization tree. Count the number of underpaid managers.
 */

public class CountUnderPaidManager {
    class Employee{
        long salary;
        List<Employee> employeeList;
    }

    class ReportSummary{
        int totalEmployes;

    }

}
