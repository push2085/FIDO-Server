/**
 * Copyright StrongAuth, Inc. All Rights Reserved.
 *
 * Use of this source code is governed by the Gnu Lesser General Public License 2.3.
 * The license can be found at https://github.com/StrongKey/FIDO-Server/LICENSE
 */

package com.strongkey.apiws.rest;

//import com.strongauth.apiws.fido2.rest.FidoAdminServlet;
import com.strongauth.apiws.filters.CrossOriginResourceSharingFilter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<>(Arrays.asList(APIServlet.class, CrossOriginResourceSharingFilter.class));
//        return new HashSet<>(Arrays.asList(APIServlet.class, FidoAdminServlet.class));
    }
}
