package com.autentia.tnt.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class ActivityImageTag extends TagSupport {

    private Date insertionDate;

    private String fileName;

    private boolean rendered;

    @Override
    public void release() {
        super.release();
        insertionDate = null;
        fileName = null;
        rendered = false;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        JspWriter out = pageContext.getOut();

        try {
            if (fileName != null && !fileName.equals("")) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(insertionDate);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;

                if (rendered) {
                    out.print("<img src=\"" + request.getContextPath() + "/doc/activity/images/" + year + "/" + month + "/" + fileName + ".jpg" + "\">");
                }
            }
        } catch (IOException e) {
            throw new JspException("Error rendering imgTag");
        }

        return EVAL_PAGE;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
}
