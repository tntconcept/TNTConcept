package com.autentia.tnt.dao.hibernate;

import com.autentia.tnt.businessobject.Contact;
import com.autentia.tnt.businessobject.Department;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.search.DepartmentSearch;
import com.autentia.tnt.test.utils.IntegrationTest;
import com.autentia.tnt.util.SpringUtils;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DepartmentDAO_IT extends IntegrationTest {
    final DepartmentDAO departmentDAO;

    public DepartmentDAO_IT() { departmentDAO = (DepartmentDAO) SpringUtils.getSpringBean("daoDepartment");}

    @Test
    public void loadByIdShouldLoadDepartment() {
        final int departmentId = 4;

        final Department department = departmentDAO.loadById(departmentId);

        assertEquals("Indefinido", department.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadByIdShouldGetNullDepartment() {
        final int departmentId = Integer.MAX_VALUE;

        final Department department = departmentDAO.loadById(departmentId);

        assertNull(department);
    }

    @Test
    public void searchShouldReturnMoreThanTheDefaultDepartment() {
        Department department = createDepartment();
        departmentDAO.insert(department);

        List<Department> departments = departmentDAO.search(new SortCriteria());

        assert(departments.size() > 1);
    }

    @Test
    public void searchByCriteriaShouldReturnExpectedDepartment() {
        Department department = createDepartment();
        departmentDAO.insert(department);

        DepartmentSearch departmentSearch = new DepartmentSearch();
        departmentSearch.setName(department.getName());
        List<Department> departments = departmentDAO.search(departmentSearch, new SortCriteria());

        assert(departments.size() == 1);
    }

    @Test
    public void updateShouldChangeDepartmentName() {
        Department departmentToUpdate = departmentDAO.getById(1);
        departmentToUpdate.setName("Update");

        departmentDAO.update(departmentToUpdate);

        Department updatedDepartment = departmentDAO.getById(1);
        assertEquals("Update", updatedDepartment.getName());
    }

    @Test
    public void deleteShouldRemoveDepartment() {
        Department departmentToDelete = departmentDAO.getById(1);

        departmentDAO.delete(departmentToDelete);

        assertNull(departmentDAO.getById(1));
    }

    @Test
    public void getContactsFromDepartmentShouldReturnZeroContacts() {

        Department department = departmentDAO.getById(1);

        List<Contact> contacts = departmentDAO.getContactsForDepartment(department);
        System.out.println(contacts.size());
        assert(contacts.size() == 0);
    }

    @Test
    public void getChildrenDepartmentsShouldReturnMoreThanOneDepartment() {
        Department department = createDepartment();
        departmentDAO.insert(department);
        Department parentDepartment = departmentDAO.getById(1);

        List<Department> childrenDepartments = departmentDAO.getChildrenDepartments(parentDepartment);

        assert(childrenDepartments.size() > 1);
    }

    private Department createDepartment() {
        Department parentDepartment = departmentDAO.getById(1);
        Department department = new Department();
        department.setName("Department");
        department.setDescription("Tests");
        department.setParent(parentDepartment);

        return department;
    }

}