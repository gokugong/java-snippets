/*
 * Copyright &#169; 2019 Manhattan Associates, Inc.  All Rights Reserved.
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
 */
package rabbitmq;

import com.rabbitmq.http.client.Client;
import com.rabbitmq.http.client.domain.QueueInfo;
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Service
@EnableScheduling
public class ConsumerAutoRecoveryService
{
    private Logger logger = LoggerFactory.getLogger(ConsumerAutoRecoveryService.class);

    @Value("${application.instance.id:}")
    String componentInstanceId;
    
    @Value("${rabbitmq.consumerrecovery.enabled:true}")
    Boolean isConsumerRecoveryEnabled;

    MessagingProperties messagingProperties;

    @Autowired
    CamelContext camelContext;

    String broadcastReceiverQueue;
    
    String broadcastReceiverUri;

    private final String BROADCAST_RECEIVER_ROUTE_ID = "broadcastReceiverRoute";

    @Scheduled(cron = "${rabbitmq.consumerrecovery.cron:0 0/2 * 1/1 * ?}")
    public void recoverBroadcastReceiver() throws MalformedURLException, URISyntaxException
    {

        logger.trace(">> recoverBroadcastReceiver()");

        logger.info(
                "-- recoverBroadcastReceiver() : Starting broadcast receiver recovery. isConsumerRecoveryEnabled={}, Queue={}",
                isConsumerRecoveryEnabled, broadcastReceiverQueue);

        if (isConsumerRecoveryEnabled)
        {
            if (!StringUtils.isEmpty(broadcastReceiverQueue))
            {
                QueueInfo queue = getQueue();

                if (queue == null || (queue != null && queue.getConsumerCount() == 0))
                {
                    logger.info(
                            "-- recoverBroadcastReceiver()  renewing route : Queue={}, Instance id={}, Number of consumers={}",
                            broadcastReceiverQueue, componentInstanceId,
                            queue != null ? queue.getConsumerCount() : "queue null");

                    renewBroadcastRoute(broadcastReceiverUri, BROADCAST_RECEIVER_ROUTE_ID);

                    logger.info("-- recoverBroadcastReceiver()  renewed route : Queue={}, Instance id={}",
                            broadcastReceiverQueue, componentInstanceId);

                }
                else
                {

                    logger.info(
                            "Broadcast receiver queue is healthy. Queuename={}, component instance={}, number of consumers={}",
                            broadcastReceiverQueue, componentInstanceId, queue.getConsumerCount());
                }

            }
            else
            {
                logger.warn("-- recoverBroadcastReceiver() : broadcast receiver queue name is null for instance={}",
                        componentInstanceId);
            }
        }
        
        logger.info(
                "-- recoverBroadcastReceiver() : Completed broadcast receiver recovery. isConsumerRecoveryEnabled={}, Queue={}",
                isConsumerRecoveryEnabled, broadcastReceiverQueue);
        
        logger.trace("<< recoverBroadcastReceiver()");
    }
    
    protected QueueInfo getQueue() throws MalformedURLException, URISyntaxException
    {
        String rabbitMqHostName = messagingProperties.getHost();
        String rabbitMqAdminHttpPort = messagingProperties.getAdminHttpPort();
        String rabbitMqURL = "http://" + rabbitMqHostName + ":" + rabbitMqAdminHttpPort + "/api/";
        String userName = messagingProperties.getUserName();
        String password = messagingProperties.getPassword();
        Client client = new Client(rabbitMqURL, userName, password);
        String vhost = "/";

        QueueInfo queue = client.getQueue(vhost, broadcastReceiverQueue);
        return queue;
    }

    private void renewBroadcastRoute(String broadcastUri, String routeId)
    {
        try
        {

            cleanupBroadcastReceiverRoute(routeId);

            camelContext.addRoutes(new RouteBuilder()
            {
                @Override
                public void configure() throws Exception
                {

                    from(broadcastUri).routeId(routeId).bean("broadcastMessageReceiver", "onBroadcast");
                }
            });
            camelContext.start();

        }
        catch (Exception e)
        {
            logger.error("Could not re-initialize camelContext due to " + e.getMessage(), e);
        }
    }

    private void cleanupBroadcastReceiverRoute(String routeId)
    {

        Route broadcastReceiverRoute = camelContext.getRoute(routeId);
        if (broadcastReceiverRoute != null)
        {
            try
            {
                camelContext.stopRoute(routeId);
                camelContext.removeRoute(routeId);
            }
            catch (Exception e)
            {
                logger.error("Exception in cleaning up broadcast route in instance =" + componentInstanceId, e);
            }
        }
    }

    public void setBroadcastReceiverQueue(String broadcastReceiverQueue)
    {
        this.broadcastReceiverQueue = broadcastReceiverQueue;
    }

    public void setBroadcastReceiverUri(String broadcastReceiverUri)
    {
        this.broadcastReceiverUri = broadcastReceiverUri;
    }
    
    

}
