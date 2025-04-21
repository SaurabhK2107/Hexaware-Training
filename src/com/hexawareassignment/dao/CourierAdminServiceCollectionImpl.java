//Task 8
package com.hexawareassignment.dao;

import com.hexawareassignment.entity.CourierCompanyCollection;
import com.hexawareassignment.entity.Employee;

public class CourierAdminServiceCollectionImpl extends CourierUserServiceCollectionImpl implements ICourierAdminService {

    public CourierAdminServiceCollectionImpl(CourierCompanyCollection companyObj) {
        super(companyObj);
    }

    public int addCourierStaff(Employee employee) {
        companyObj.getEmployeeDetails().add(employee);
        return employee.getEmployeeId();
    }
}

