package util.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 测试排序类
 * 
 * @author liuyazhuang
 *
 */
public class SortTest {
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<Employee>() {
			{
				add(new Employee(1, 9, 10000, 10));
				add(new Employee(2, 9, 12000, 7));
				add(new Employee(3, 5, 10000, 12));
				add(new Employee(4, 5, 10000, 6));
				add(new Employee(5, 3, 5000, 3));
				add(new Employee(6, 1, 2500, 1));
				add(new Employee(7, 5, 8000, 10));
				add(new Employee(8, 3, 8000, 2));
				add(new Employee(9, 1, 3000, 5));
				add(new Employee(10, 1, 2500, 4));
				add(new Employee(11, 2, 2000, 4));
			}
		};
		Collections.sort(employeeList, new EmpComparator());
		System.out.println("ID\tLevel\tSalary\tYears");
		System.out.println("=============================");
		for (Employee employee : employeeList) {
			System.out.printf("%d\t%d\t%d\t%d\n", employee.getId(), employee.getLevel(), employee.getSalary(),
					employee.getYear());
		}
		System.out.println("=============================");
	}

}
