package ec.lab.jsp;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ECTag2 extends SimpleTagSupport {
  StringWriter sw = new StringWriter();
  public void doTag() throws JspException, IOException {
    getJspBody().invoke(sw);
    getJspContext().getOut().println("Process tag body: " + sw.toString().toUpperCase());
  }
}