package com.test.mail.mailet;

import org.apache.mailet.GenericRecipientMatcher;   
import org.apache.mailet.MailAddress;   
  
/**  
 * Mailetƥ����  
 *  
 */  
public class BizMatcher extends GenericRecipientMatcher {   
    public boolean matchRecipient(MailAddress recipient) {   
        return true;   
    }   
}
