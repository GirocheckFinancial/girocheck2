package com.smartbt.vtsuite.connection;

import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CardRestoreResponse complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="CardRestoreResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://SistemasGalileo.com/Services/}MainResponseContainer">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardRestoreResponse")
public class CardRestoreResponse
        extends MainResponseContainer
        implements IMap {

    private static final String EXPECTED_RESULT_CODE = "000000";

    @Override
    public Map toMap() {
        return super.getMap(EXPECTED_RESULT_CODE);
    }

}
