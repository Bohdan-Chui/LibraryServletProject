package command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class LogOutCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    LogOutCommand logoutCommand = new LogOutCommand();

    @Before
    public void initMocks() throws IOException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getContextPath()).thenReturn("/");
    }

    @Test
    public void shouldPrintAlertAndReturnNullWhenParamsAreIncorrect() throws ServletException, IOException {
        String expectedPrint = "redirect:" + request.getContextPath() + Actions.INDEX_JSP;
        String processResult = logoutCommand.process(request, response);
        Assert.assertEquals(expectedPrint, processResult);
    }

}