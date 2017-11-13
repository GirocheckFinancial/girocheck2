package com.smartbt.vtsuite.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "qualifiers")
public class QualifierList { 
    private List<Qualifier> qualifiers = new ArrayList<>();

    /**
     * @return the qualifiers
     */
    public List<Qualifier> getQualifiers() {
        return qualifiers;
    }

    @XmlElement(name = "qualifier")
    public void setQualifiers(List<Qualifier> qualifiers) {
        this.qualifiers = qualifiers;
    }
}
