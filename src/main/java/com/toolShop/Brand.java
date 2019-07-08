package com.toolShop;

/**
 * Enum for tool brands. Adding more brands is just inserting the values into
 * the enum.
 * 
 * @author tadtab
 *
 */
public enum Brand {

    /*
     * Werner brand
     */
    WERNER("Werner"),

    /*
     * Stihl brand
     */
    STIHL("Stihl"),

    /*
     * Ridgid brand
     */
    RIDGID("Ridgid"),

    /*
     * DeWalt brand
     */
    DEWALT("DeWalt")
   ;
    
    String value;
    
    Brand(String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }

}
