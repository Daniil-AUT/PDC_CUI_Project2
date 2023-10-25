package pdc_cui_project2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniil
 */
public class WindowManagerTest {
    // Create glbal Window manager instance (singleton)
    private final WindowManager manager;
    
    public WindowManagerTest() {
        // Get the WindowManager instance
        manager = WindowManager.getManager();
    }

    /*
    It's important that getManager() always returns the same instance of the 
    WindowManager class no matter how many times it was called. Make sure
    that the two variable classes point to the same object.
     */
    @Test
    public void testGetManagerReturnsSameInstance() {
        // Instantiate two variables by getting reference
        WindowManager windowManager1 = WindowManager.getManager();
        WindowManager windowManager2 = WindowManager.getManager();

        // Assert whether the two classes have same reference address point
        assertSame(windowManager1, windowManager2);
    }
    
    
    /**
     This test method verifies that view methods in WindowManager class correctly
     set the visibility for each page based on which is set to visible/invisible
     by calling the setHomeVisible() along with setFAQVisible while setting home
     to being invisible and FAQ to visible thus showing that the page has had
     a successful transition.
     */
    @Test
    public void testViewSelection() {
        
        // Will act as a button event when the user wants to visit faq page
        manager.setFAQVisible(true);
        manager.setHomeVisible(false);
        
        // Assert that FAQView is visible and all other views are invisible
        assertTrue(manager.faqView.isVisible());
        assertFalse(manager.homeView.isVisible());
        assertFalse(manager.loginView.isVisible());
        assertFalse(manager.signupView.isVisible());
        assertFalse(manager.userAccountView.isVisible());
        assertFalse(manager.assistantView.isVisible());
        assertFalse(manager.ticketView.isVisible());
    }
}
