/*
 * Copyright (c) 2016 Manhattan Associates, Inc.  All Rights Reserved.
 *
 * Confidential, Proprietary and Trade Secrets Notice
 *
 * Use of this software is governed by a license agreement. This software
 * contains confidential, proprietary and trade secret information of
 * Manhattan Associates, Inc. and is protected under United States and
 * international copyright and other intellectual property laws. Use, disclosure,
 * reproduction, modification, distribution, or storage in a retrieval system in
 * any form or by any means is prohibited without the prior express written
 * permission of Manhattan Associates, Inc.
 *
 * Manhattan Associates, Inc.
 * 2300 Windy Ridge Parkway, 10th Floor
 * Atlanta, GA 30339 USA
 *
 */

package encode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
/*
import com.manh.cp.fw.entity.client.dto.DomainDTO;
*/

public class JacksonDTOPropertyFilter extends SimpleBeanPropertyFilter
{
    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider,
                                 PropertyWriter writer) throws Exception
    {
/*
        if (pojo instanceof DomainDTO)
        {
            DomainDTO domainDTO = (DomainDTO) pojo;
            if (domainDTO.getInitializedProperties().contains(writer.getName()) ||
                    "Actions".equals(writer.getName()))
            {
                writer.serializeAsField(pojo, jgen, provider);
            }
        }
        else
        {
*/
            writer.serializeAsField(pojo, jgen, provider);
/*
        }
*/
    }

    @Override
    protected boolean include(PropertyWriter writer)
    {
        return true;
    }

    @Override
    protected boolean include(BeanPropertyWriter writer)
    {
        return true;
    }
}
