import java.util.*;

class Employee {
    String employeeName;
    int employeeId;
    Integer employeeManagerId;
    List<Employee> hierarchyData;

    public Employee(String employeeName, int employeeId, Integer employeeManagerId) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.employeeManagerId = employeeManagerId;
        this.hierarchyData = new ArrayList<>();
    }
}

public class OrganizationHierarchy {

    public static Employee buildHierarchy(Map<Integer, Employee> employeeMap) {
        Employee companyCEO = null;
        for (Employee employee : employeeMap.values()) {
            /*IValidating if employee doesnt have managerID then it can be a ceo*/
            if (employee.employeeManagerId == null) {
                companyCEO = employee;
            } else {
                Employee employeeManager = employeeMap.get(employee.employeeManagerId);
                if (employeeManager != null) {
                    employeeManager.hierarchyData.add(employee);
                }
            }
        }
        return companyCEO;
    }

    public static void displayHierarchy(Employee root, int level) {
        if (root != null) {
            for (int i = 0; i < level; i++) {
                System.out.print("      |");
            }
            System.out.println(root.employeeName +" |");
            for (Employee data : root.hierarchyData) {
                displayHierarchy(data, level + 1);
            }
        }
    }

    public static void main(String[] args) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(100, new Employee("Alan", 100, 150));
        employeeMap.put(220, new Employee("Martin", 220, 100));
        employeeMap.put(150, new Employee("Jamie", 150, null));
        employeeMap.put(275, new Employee("Alex", 275, 100));
        employeeMap.put(400, new Employee("Steve", 400, 150));
        employeeMap.put(190, new Employee("David", 190, 400));

        Employee ceo = buildHierarchy(employeeMap);
        displayHierarchy(ceo, 0);
    }
}
