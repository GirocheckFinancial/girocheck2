package com.smartbt.vtsuite.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rrodriguez
 */
@XmlRootElement(name = "velocity-results")
public class VelocityResultList { 
    private List<VelocityResult> velocityResults = new ArrayList<>();
 

    /**
     * @return the velocityResults
     */
    public List<VelocityResult> getVelocityResults() {
        return velocityResults;
    }

     @XmlElement(name = "velocity-result")
    public void setVelocityResults(List<VelocityResult> velocityResults) {
        this.velocityResults = velocityResults;
    }
}
