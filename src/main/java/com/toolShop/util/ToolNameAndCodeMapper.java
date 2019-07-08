package com.toolShop.util;

import java.util.HashMap;
import java.util.Map;

import com.toolShop.ToolCode;

/**
 * A singleton class that will have the tool name and code mapped
 * @author tadtab
 *
 */
public class ToolNameAndCodeMapper {
    
    public static ToolNameAndCodeMapper mapperInstance = null;
    
    /**
     * This property holds the relationship between tool code and the name.
     * 
     */
    public Map<ToolCode, String> toolCodeNameMap = new HashMap<ToolCode, String>();
    
    /**
     * private constructor to protect creation of more than one instance.
     */
    private ToolNameAndCodeMapper() {
        
        this.toolCodeNameMap.put(ToolCode.LADW, "Ladder");
        this.toolCodeNameMap.put(ToolCode.CHNS, "Chainsaw");
        this.toolCodeNameMap.put(ToolCode.JAKR, "Jackhammer");
        this.toolCodeNameMap.put(ToolCode.JAKD, "Jackhammer");
        
    }
    
    /**
     * Public accessor for the single instance of this class
     * @return
     */
    public static ToolNameAndCodeMapper getMapper() {
        if(mapperInstance == null) {
            mapperInstance = new ToolNameAndCodeMapper();
        }
        
        return mapperInstance;
    }

}
