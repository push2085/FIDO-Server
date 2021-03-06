/**
 * Copyright StrongAuth, Inc. All Rights Reserved.
 *
 * Use of this source code is governed by the Gnu Lesser General Public License 2.3.
 * The license can be found at https://github.com/StrongKey/FIDO-Server/LICENSE
 */

package com.strongkey.skfs.pojos;

import com.strongkey.skfs.utilities.skfsConstants;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * POJO to bind the fido web services' response being sent back to the calling
 * application.
 */
public class FIDOReturnObject {
      
    /**
     * Local variables
     */
    private String response = "";
       
    /**
     * Constructor of this class.
     * 
     * @param response
     */
    public FIDOReturnObject(String response) {
        
        if (response != null) {
            this.response = response;
        }
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
            
    /**
     * Over-ridden toString method to print the object content in a readable 
     * manner
     * @return  String with object content laid in a readable manner. 
     */
    @Override
    public String toString() {
        return "\n\tresponse    = " + this.response;
    }
    
    /**
     * Constructs this class object as a Json to be passed back to the client.
     * 
     * @return  - String object of the Json representation of this object
     */
    public String toJsonString() {
        
        if ( response == null ) {
            response = "";
        }
        
        // Build the output json object
        JsonObject responseJSON = null;
        
        responseJSON = Json.createObjectBuilder()
        .add(skfsConstants.JSON_KEY_SERVLET_RETURN_RESPONSE, response)
        .build();   
        
        if ( responseJSON != null )
            return responseJSON.toString();
        else
            return null;
    }
}
