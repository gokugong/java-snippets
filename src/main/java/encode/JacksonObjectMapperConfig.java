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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
/*
import com.manh.cp.fw.entity.JacksonDTOPropertyFilter;
*/
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Utility class that holds the Jackson object mapper configuration, so that i
 * can be reused.
 * <p>
 * Created by prajan on 3/8/16.
 */
public class JacksonObjectMapperConfig
{
    public static Jackson2ObjectMapperBuilder objectMapperBuilder()
    {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        customize(builder);
        return builder;
    }

    public static void customize(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder)
    {
        jackson2ObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        //Zone ID Print can be enabled if needed
        //builder.featuresToEnable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
        jackson2ObjectMapperBuilder.featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JacksonDTOPropertyFilter jacksonDTOPropertyFilter = new JacksonDTOPropertyFilter();
        FilterProvider filters = new SimpleFilterProvider().addFilter("InitializationFilter", jacksonDTOPropertyFilter);
        jackson2ObjectMapperBuilder.filters(filters);
    }
}
