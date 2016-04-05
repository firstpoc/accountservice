/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.msf4jpoc.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.msf4jpoc.bean.UserAccount;
import org.msf4jpoc.dao.UserAccountCreationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * This is the Microservice resource class.
 * See <a href="https://github.com/wso2/msf4j#getting-started">https://github.com/wso2/msf4j#getting-started</a>
 * for the usage of annotations.
 *
 * @since 1.0.0
 */
@Path("/accountservice")
public class AccountCreationService {

	private static final Logger log = LoggerFactory.getLogger(AccountCreationService.class);
   

    @POST
    @Consumes("application/json")
    //@Consumes({MediaType.APPLICATION_JSON})
    public Response post(UserAccount userAccount)  {
    	log.info("values.."+userAccount);
    	
    	UserAccountCreationDao userAccountCreationDao = new UserAccountCreationDao();
    	String result = userAccountCreationDao.insertUserAccount(userAccount);
    	return (result == null) ?
                Response.status(Response.Status.NOT_FOUND).build() :
                Response.status(Response.Status.OK).entity(result).build();
    }

    

    
}
