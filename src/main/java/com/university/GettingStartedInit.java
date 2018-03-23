package com.university;


import com.university.models.messanger.GetStarted;
import com.university.models.messanger.MessengerProfileApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


import static com.university.enums.payloads.PostBackPayloads.GET_STARTED_PAYLOAD;

@Component
public class GettingStartedInit {
    @Value("${page.access.token}")
    private String PAGE_ACCESS_TOKEN;

    @Value("${profile.api.uri}")
    private String FACEBOOK_PROFILE_URI;




    private static final Logger logger = Logger.getLogger(GettingStartedInit.class);

    @PostConstruct
    public void getStarted() throws Exception {
        MessengerProfileApi messengerProfileApi = new MessengerProfileApi();
        messengerProfileApi.setGetStarted(new GetStarted(GET_STARTED_PAYLOAD.name()));

        try {
            ResponseEntity<?> responseEntity = new RestTemplate()
                    .postForEntity(FACEBOOK_PROFILE_URI + PAGE_ACCESS_TOKEN, messengerProfileApi, MessengerProfileApi.class);
            logger.debug(responseEntity);


        } catch (Exception ex) {
            logger.warn(ex);
        }
    }


}


