def send(URL_WEBHOOK) {
   sh """#!/bin/bash
      
      curl --location --request POST '$URL_WEBHOOK' \
                        --header 'Content-Type: application/json'   \
                        --data-raw '{
                            "text" : "hello"
                        }'                    
      """
}
