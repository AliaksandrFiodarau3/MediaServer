package com.epam.mediaserver.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Customer tag for set format date
 */

public class TodayDateTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    private String format;

    public void setFormat(String format) {
        this.format = format;
    }


    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            Date today = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
            out.print(dateFormatter.format(today));

        } catch (IOException ioe) {
            throw new JspException("Error: " + ioe.getMessage());
        }
        return SKIP_BODY;
    }


    public int doEndTag() throws JspException {
        return SKIP_PAGE;
    }

}
