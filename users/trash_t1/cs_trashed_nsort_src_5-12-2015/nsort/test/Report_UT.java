package nsort.test;

import static org.junit.Assert.*;

import nsort.controller.ReportController;
import nsort.model.Report;
import nsort.view.ReportView;

import org.junit.Test;
/**
 * @author (Chris.Stultz, John.Loranger, Ryan.Reams, Josh.Eads) 
 * @version (4/14/2015)
 */
public class Report_UT 
{

	@Test
	/**
	 * Test theView Model creation
	 */
	public void testReportInstanciated() 
	{
		Report theModel = new Report();
		assertNotNull("Report instantiation failed.", theModel);
	}
	@Test
	/**
	 *Test theView View creation
	 */
	public void testReportViewInstanciated()
	{
		Report theModel = new Report();
		ReportView theView = new ReportView(theModel.getUsers());
		assertNotNull("ReportView instantiation failed.", theView);
	}
	@Test
	/**
	 * Test reportController ReportController creation
	 */
	public void testControllerCreated()
	{
		Report theModel = new Report();
		ReportView theView = new ReportView(theModel.getUsers());
		ReportController reportController = new ReportController(theView, theModel);
		assertNotNull("Report instantiation failed.", reportController);
	}
	@Test
	/**
	 * Test theModel.GetUsers returns values
	 */
	public void testGetUserList()
	{
		Report theModel = new Report();
		java.awt.List x = theModel.getUsers();
		assertNotNull("theModel.getUsers method failed", x);
		
	}
	@Test
	/**
	 * Test theModel.getUsersTestID to return values
	 */
	public void testGetUserTest()
	{
		Report theModel = new Report();
		java.awt.List x = theModel.getUsersTestID("Chris Stultz");
		assertNotNull("theModel.getUsersTestID method failed.", x);
	}
	@Test
	/**
	 * Test theController.queryColumnData returns values
	 */
	public void testGetUserTestData()
	{
		Report theModel = new Report();
		Object[][] x = theModel.queryColumnData("Chris Stultz", 27);
		assertNotNull("theModel.queryColumnData method failed", x);
	}
}
