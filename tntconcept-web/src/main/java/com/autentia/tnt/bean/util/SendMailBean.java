/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.bean.util;
import java.util.ArrayList;
import java.util.List;

import com.autentia.tnt.bean.BaseBean;
import com.autentia.tnt.util.FacesUtils;

/**
 * UI bean for send mail screen
 * @author stajanov code generator
 */
public class SendMailBean extends BaseBean
{
  /** Serial version field */
  private static final long serialVersionUID = -1L;
  
  private static final String OUTCOME_RESULT = "result";
  
  private List<SendMailBeanListener> listeners = new ArrayList<SendMailBeanListener>();

  private String from;
  private List<String> to;
  private List<String> cc;
  private List<String> bcc;
  private String subject;
  private String message;
  
  public String send()
  {
    // TODO: send email

    // Notify listeners
    SendMailBeanEvent event = new SendMailBeanEvent();
    for( SendMailBeanListener listener : listeners )
    {
      listener.mailSent( event );
    }
      
    // Show message to user
    FacesUtils.addInfoMessage(null,"sendMailResult.sendOk");
    return OUTCOME_RESULT;
  }
  
  public String discard()
  {
    // Notify listeners
    SendMailBeanEvent event = new SendMailBeanEvent();
    for( SendMailBeanListener listener : listeners )
    {
      listener.mailDiscarded( event );
    }
      
    // Show message to user
    FacesUtils.addWarningMessage(null,"sendMailResult.sendDiscard");
    return OUTCOME_RESULT;
  }
  
  public String getFrom()
  {
    return from;
  }
  
  public void setFrom(String from)
  {
    this.from = from;
  }
  
  public List<String> getTo()
  {
    return to;
  }
  
  public void setTo(List<String> to)
  {
    this.to = to;
  }
  
  public List<String> getCc()
  {
    return cc;
  }
  
  public void setCc(List<String> cc)
  {
    this.cc = cc;
  }
  
  public List<String> getBcc()
  {
    return bcc;
  }
  
  public void setBcc(List<String> bcc)
  {
    this.bcc = bcc;
  }
  
  public String getSubject()
  {
    return subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public void addSendMailBeanListener( SendMailBeanListener listener )
  {
    listeners.add( listener );
  }
  
  public void removeSendMailBeanListener( SendMailBeanListener listener )
  {
    listeners.remove( listener );
  }
}
