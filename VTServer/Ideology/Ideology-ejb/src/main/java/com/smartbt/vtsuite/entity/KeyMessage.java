
package com.smartbt.vtsuite.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */

@XmlRootElement(name = "results")
public class KeyMessage {
    private String key;
    private String message;

    @Override
    public String toString() {
        return "{ key: " + key + ", message: " + message + "}";
    }

    
    
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    @XmlElement(name = "key")
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
     @XmlElement(name = "message")
    public void setMessage(String message) {
        this.message = message;
    }
}
