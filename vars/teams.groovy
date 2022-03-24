def send(URL_WEBHOOK) {
   
   echo "${URL_WEBHOOK}"
   
   def resp = """#!/bin/bash
      
      curl --location --request POST '$URL_WEBHOOK' \
                        --header 'Content-Type: application/json'   \
                        --data-raw '{
                            "text" : "hello"
                        }'                    
      """.execute()
}
