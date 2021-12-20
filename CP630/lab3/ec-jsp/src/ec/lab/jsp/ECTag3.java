package ec.lab.jsp;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ECTag3 extends SimpleTagSupport {
	private String message;

	public void setMessage(String msg) {
		this.message = msg;
	}

	StringWriter sw = new StringWriter();
	public void doTag() throws JspException, IOException {


		if (message != null) {
			/* Use message from attribute */
			JspWriter out = getJspContext().getOut();
			out.println("Processed tag attribute: " + message);
		} else {
			/* use message from the body */
			getJspBody().invoke(sw);
			getJspContext().getOut().println("Processed tag attribute: " + sw.toString().toUpperCase());
		}
	}
}