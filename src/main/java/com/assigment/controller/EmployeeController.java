package com.assigment.controller;

import com.assigment.Util.CoreUtil;
import com.assigment.entity.Employee;
import com.assigment.enums.ResponseEnum;
import com.assigment.response.GenericAPIResponse;
import com.assigment.service.EmployeeService;
import com.assigment.wrapper.EmployeeWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import java.util.ResourceBundle;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @ApiOperation(httpMethod = "GET", value = "All Employees",
            notes = "This method will return all Employees",
            produces = "application/json", nickname = "All Employees",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Employees fetched successfully.", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEmployees() {
        logger.error("getAllBranches API initiated");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("employee.fetch.error"));
        response.setResponseCode(ResponseEnum.EMPLOYEE_FETCHED_FAILED.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);
        try {
            logger.error("getAllBranches API - branches fetching from DB");
            List<EmployeeWrapper> employeeWrapperList = employeeService.getAllEmployees();
            ;
            if (CoreUtil.isListEmpty(employeeWrapperList)) {
                response.setResponseMessage(messageBundle.getString("employee.not-found"));
                response.setResponseCode(ResponseEnum.ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("getAllBranches API - Branches not found");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            response.setResponseMessage(messageBundle.getString("employee.fetch.success"));
            response.setResponseCode(ResponseEnum.SUCCESS.getValue());
            response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
            response.setResponseData(employeeWrapperList);

            logger.error("getAllBranches API - Employees successfully fetched.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("getAllBranches API -  exception..", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return employeeService.getAllEmployees();
    }


    @ApiOperation(httpMethod = "POST", value = "Create Employee",
            notes = "This method will Create Employee",
            produces = "application/json", nickname = "Create Employee",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Employee successfully created", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(HttpServletRequest request,
                                            @RequestBody EmployeeWrapper employeeRequestWrapper) {
        logger.info("Create Employee API called...");

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("employee.add.error"));
        response.setResponseCode(ResponseEnum.ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {


            Employee savedBranch = employeeService.saveBranch(employeeRequestWrapper);

            if (CoreUtil.isValidObject(savedBranch)) {
                response.setResponseData(savedBranch);
                response.setResponseMessage(messageBundle.getString("employee.add.success"));
                response.setResponseCode(ResponseEnum.SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                logger.info("Employee created successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }


        } catch (Exception ex) {
            logger.error("Employee Creation Failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ApiOperation(httpMethod = "DELETE", value = "Delete Employee",
            notes = "This method will Delete Employee on base of id",
            produces = "application/json", nickname = "Delete Employee ",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Employee Deleted successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBranch(HttpServletRequest request,
                                          @PathVariable("id") long id) {

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("employee.delete.error"));
        response.setResponseCode(ResponseEnum.ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            Employee employeeWrapper = this.employeeService.findById(id);

            if (CoreUtil.isValidObject(employeeWrapper)) {
                employeeService.deleteEmployee(employeeWrapper);

                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("employee.delete.success"));
                response.setResponseCode(ResponseEnum.SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                logger.info("Employee Deleted successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("employee.not.found"));
                response.setResponseCode(ResponseEnum.ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());

                logger.info("Unable to find Employee...");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Unable to delete Employee.", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(httpMethod = "GET", value = "Fetch Employee",
            notes = "This method will return Employee on base of id",
            produces = "application/json", nickname = "Get Single Employee",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Employee found successfully", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBranchById(HttpServletRequest request,
                                           @PathVariable("id") long id) {

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("employee.not.found"));
        response.setResponseCode(ResponseEnum.ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            EmployeeWrapper employeeWrapper = this.employeeService.findDetailsById(id);

            if (CoreUtil.isValidObject(employeeWrapper)) {
                response.setResponseData(employeeWrapper);
                response.setResponseCode(ResponseEnum.SUCCESS.getValue());
                response.setResponseMessage(messageBundle.getString("employee.found"));
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                logger.info("Employee Found successfully...");

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setResponseData(null);
                response.setResponseMessage(messageBundle.getString("employee.not.found"));
                response.setResponseCode(ResponseEnum.ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                logger.info("Employee Not Found ...");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Employee Not Found", ex.fillInStackTrace());
            response.setResponseData("");
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "PUT", value = "Update Employee ",
            notes = "This method will Update Employee",
            produces = "application/json", nickname = "Update Employee",
            response = GenericAPIResponse.class, protocols = "https")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Employee successfully updated", response = GenericAPIResponse.class),
            @ApiResponse(code = 401, message = "Oops, your fault. You are not authorized to access.", response = GenericAPIResponse.class),
            @ApiResponse(code = 403, message = "Oops, your fault. You are forbidden.", response = GenericAPIResponse.class),
            @ApiResponse(code = 404, message = "Oops, my fault System did not find your desire resource.", response = GenericAPIResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.", response = GenericAPIResponse.class)})
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBranch(HttpServletRequest request,
                                          @PathVariable("id") int id,
                                          @RequestBody EmployeeWrapper employeeWrapper) {

        logger.info("update Employee API called..." + employeeWrapper.getFirstName() + "-" + employeeWrapper.getLastName());

        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("employee.update.error"));
        response.setResponseCode(ResponseEnum.ERROR.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {

            Employee alreadyExistEmployee = employeeService.findById(id);
            if (CoreUtil.isValidObject(alreadyExistEmployee)) {
                logger.info("Employee founded...");
                Employee employeeUpdated = employeeService.updateEmployee(employeeWrapper, alreadyExistEmployee);
                if (CoreUtil.isValidObject(employeeUpdated)) {
                    logger.info("Employee Updated...");
                    response.setResponseData(employeeUpdated);
                    response.setResponseMessage(messageBundle.getString("employee.update.success"));
                    response.setResponseCode(ResponseEnum.SUCCESS.getValue());
                    response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                    logger.info("Employee updated successfully...");

                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                logger.info("Employee not found...");
                response.setResponseMessage(messageBundle.getString("employee.not.found"));
                response.setResponseCode(ResponseEnum.ERROR.getValue());
                response.setResponseStatus(ResponseEnum.ERROR.getValue());
                response.setResponseData(null);
                logger.error("Employee not updated...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception ex) {
            logger.error("Update Employee Failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

