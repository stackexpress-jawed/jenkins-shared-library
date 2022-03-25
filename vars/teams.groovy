def send(URL_WEBHOOK) {
   
   echo "${URL_WEBHOOK}"
   
   def resp = """/usr/bin/curl --location --request POST '$URL_WEBHOOK' --header 'Content-Type: application/json' --data-raw '{ "text" : "hello" }'""".execute().text
   
   echo ">> ${resp}"
}
