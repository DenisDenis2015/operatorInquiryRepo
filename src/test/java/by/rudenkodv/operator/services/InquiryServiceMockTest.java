package by.rudenkodv.operator.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import by.rudenkodv.operator.dao.InquiryDao;
import by.rudenkodv.operator.model.Inquiry;
import by.rudenkodv.operator.model.Topic;
import by.rudenkodv.operator.services.impl.InquiryServiceImpl;
 
@RunWith(MockitoJUnitRunner.class)
public class InquiryServiceMockTest{

    public static final Inquiry FIRST_INQUIRY = new Inquiry(1l, new Topic(1l,"TopicNew"), "description", new Timestamp(32235), "CustomerName");
    public static final Inquiry NONEXISTENT_INQUIRY = new Inquiry(5l, new Topic(2l,"TopicNew"), "description", new Timestamp(3232235), "NameName");
    public static final Inquiry NEW_INQUIRY = new Inquiry(2l, new Topic(2l,"TopicNew"), "description", new Timestamp(242343), "CustomerNewName");

    @Mock
    private InquiryDao inquiryDao;

    private InquiryServiceImpl inquiryService;

    @Before
    public void setUp() throws IOException {
    	inquiryService = new InquiryServiceImpl();
    	inquiryService.setInquiryDao(inquiryDao);
    }
    
    @Test
    public void testGetById(){
    	Mockito.doReturn(FIRST_INQUIRY).when(inquiryDao).getById(FIRST_INQUIRY.getId());
    	Inquiry actual = inquiryService.get(FIRST_INQUIRY.getId());
    	Assert.assertEquals(FIRST_INQUIRY, actual);
    }
    
    @Test
    public void testGetByIdNotExistedInquiry() {
        Mockito.doReturn(null).when(inquiryDao).getById(FIRST_INQUIRY.getId());
        Inquiry actual = inquiryService.get(NONEXISTENT_INQUIRY.getId());
        Assert.assertNull(actual);
    }
    
    @Test
    public void testGetAll(){
    	ArrayList<Inquiry> testList = new ArrayList<Inquiry>();
    	testList.add(new Inquiry());
    	testList.add(new Inquiry());
    	Mockito.doReturn(testList).when(inquiryDao).getAll();
    	List<Inquiry> inquiryList = inquiryService.getAllInquiry();
    	Assert.assertFalse(inquiryList.isEmpty());
    }
    
    @Test
    public void testGetAllEmpty(){
    	Mockito.doReturn(new ArrayList<Inquiry>()).when(inquiryDao).getAll();
    	List<Inquiry> inquiryList = inquiryService.getAllInquiry();
    	Assert.assertTrue(inquiryList.isEmpty());
    }
    
    @Test
    @Ignore
    public void testSave() {
    	
    }
    
    @Test
    public void testUpdate(){
    	Inquiry newInquiry = new Inquiry(NEW_INQUIRY.getId(), NEW_INQUIRY.getTopic(), NEW_INQUIRY.getDescription(), NEW_INQUIRY.getCreateDate(), NEW_INQUIRY.getCustomerName());
    	Mockito.doReturn(NEW_INQUIRY).when(inquiryDao).update(newInquiry);
    	inquiryService.saveOrUpdate(newInquiry);
    	Assert.assertEquals(newInquiry, NEW_INQUIRY);
    }
    
    @Test
    public void testDelete() {
        Inquiry originIquiry = new Inquiry(NEW_INQUIRY.getId(), NEW_INQUIRY.getTopic(), NEW_INQUIRY.getDescription(), NEW_INQUIRY.getCreateDate(), NEW_INQUIRY.getCustomerName());
        Mockito.doNothing().when(inquiryDao).delete(originIquiry.getId());
        inquiryService.delete(originIquiry);
        Assert.assertEquals(NEW_INQUIRY, originIquiry);
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteNotExistedUser() {
        Mockito.doThrow(new RuntimeException()).when(inquiryDao).delete(NONEXISTENT_INQUIRY.getId());
        inquiryService.delete(NONEXISTENT_INQUIRY);
    }
    
    
}
